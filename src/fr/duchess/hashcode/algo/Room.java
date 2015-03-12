package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.DataCenterRow;
import fr.duchess.hashcode.bean.Servor;
import fr.duchess.hashcode.bean.Slot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Created by Axelle on 12-03-15.
 */
public class Room {
    private List<Row> rows;

    public Stream<Row> orderRowforGroup(final int group){
        return rows.stream().sorted(new Comparator<Row>() {
            @Override
            public int compare(Row o1, Row o2) {
                return Integer.compare(o1.getValueByGroup(group),o2.getValueByGroup(group));
            }
        });
    }



    private PriorityQueue<Group> groups;

    public PriorityQueue<Group> getGroups() {
        return groups;
    }

    public Room (int groupCount, DataCenter datacenter){
        groups = new PriorityQueue<>();
        for (int i = 1; i <= groupCount; i++) {
            groups.add(new Group(i));
        }

        int i = 1;
        for (DataCenterRow row : datacenter.getRows()) {
            Row newRow = new Row(i);
            i++;
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
