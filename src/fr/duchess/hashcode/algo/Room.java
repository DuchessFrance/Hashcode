package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.DataCenterRow;
import fr.duchess.hashcode.bean.Servor;
import fr.duchess.hashcode.bean.Slot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
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

    public Room(int groupCount, DataCenter datacenter) {
        groups = new ArrayList<>();
        rows = new ArrayList<>();
        for (int i = 1; i <= groupCount; i++) {
            groups.add(new Group(i));
        }

         for (DataCenterRow row : datacenter.getRows()) {
            Row newRow = new Row(row.getRowIndex());

            rows.add(newRow);
             newRow.setRowNumber(row.getRowIndex());
            Optional<Segment> currentSegment = Optional.empty();
             int slotNumber = 0;
            for (Slot slot : row.getSlots()) {
                slotNumber++;
                if (currentSegment.isPresent()) {
                    if (currentSegment.get().status == Status.FREE && slot.isAvailable()) {
                        currentSegment.get().size++;
                        continue;
                    }
                    if (currentSegment.get().status == Status.UNAV && !slot.isAvailable()) {
                        currentSegment.get().size++;
                        continue;
                    }

                }
                Segment segment = new Segment();
                segment.setBeginningSlot(slotNumber);
                currentSegment = Optional.of(segment);
                newRow.getSlots().add(segment);
                if (slot.isAvailable())
                    segment.status = Status.FREE;
                else
                    segment.status = Status.UNAV;
            }
        }
    }

    public String serialize(){
        StringBuilder string = new StringBuilder();
        getRows().stream().forEachOrdered(row -> {
            row.getSlots().stream().forEachOrdered(segment ->{
                string.append(segment.serialize());
            });
            string.append("\n");
        });
        File file = new File("room");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }


    public List<Row> getRows() {
        return rows;
    }
}
