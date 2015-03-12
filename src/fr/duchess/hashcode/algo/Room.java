package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.DataCenterRow;
import fr.duchess.hashcode.bean.Slot;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Axelle on 12-03-15.
 */
public class Room {
    private List<Row> rows;
    public static final Comparator<Group> ORDER_GROUP_BY_CAPACITY = new Comparator<Group>() {
        @Override
        public int compare(Group o1, Group o2) {
            return Integer.compare(o1.getCapacity(), o2.getCapacity());
        }
    };

    public Stream<Row> orderRowforGroup(final int group){
        return rows.stream().sorted(new Comparator<Row>() {
            @Override
            public int compare(Row o1, Row o2) {
                return Integer.compare(o1.getValueByGroup(group),o2.getValueByGroup(group));
            }
        });
    }



    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public Room (int groupCount, DataCenter datacenter){
        groups = new ArrayList<>();
        rows = new ArrayList<>();
        for (int i = 1; i <= groupCount; i++) {
            groups.add(new Group(i));
        }

         for (DataCenterRow row : datacenter.getRows()) {
            Row newRow = new Row(row.getRowIndex());

            rows.add(newRow);
            Optional<Segment> currentSegment = Optional.empty() ;
            for (Slot slot : row.getSlots()) {
                if(currentSegment.isPresent()){
                    if(currentSegment.get().status == Status.FREE && slot.isAvailable()){
                        currentSegment.get().size ++;
                        continue;
                    }
                    if(currentSegment.get().status == Status.UNAV && !slot.isAvailable()){
                        currentSegment.get().size ++;
                        continue;
                    }

                }
                Segment segment = new Segment();
                currentSegment = Optional.of(segment);
                if(slot.isAvailable())
                    segment.status = Status.FREE;
                else
                    segment.status = Status.UNAV;
            }
        }
    }

}
