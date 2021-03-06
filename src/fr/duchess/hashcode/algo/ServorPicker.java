package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Axelle on 12-03-15.
 */
public class ServorPicker {


    private final List<Servor> servors;

    public ServorPicker(Room room, List<Servor> servors) {
        this.room = room;
       this.servors = new ArrayList<>();
        this.remainingServors = new PriorityQueue<Servor>(new Comparator<Servor>() {
            @Override
            public int compare(Servor o1, Servor o2) {
                return Double.compare(o1.getEfficiency(),o2.getEfficiency());
            }
        });
        remainingServors.addAll(servors);
    }

    public void placeNextServor() {
        Servor peek = remainingServors.poll();
        boolean b = attemptPlacingServor(peek);
        peek.setPlaced(b);
        servors.add(peek);

    }

    private boolean attemptPlacingServor(Servor nextServor) {
        List<Group> groups = room.getGroups();
        groups.sort(Room.ORDER_GROUP_BY_CAPACITY);
        for (Group group : groups) {
            Stream<Row> rowStream = room.orderRowforGroup(group.groupNumber);
            List<Row> collect = rowStream.collect(Collectors.toList());
            for (Row row : collect) {
                int fittedSegmentIndex = -1;
                Segment fittedSegment = null;
                for (int i = 0; i < row.getSlots().size(); i++) {
                    Segment segment = row.getSlots().get(i);
                    if(serverFitsInSegment(nextServor, segment)){
                        if(fittedSegment == null){
                            fittedSegment = segment;
                            fittedSegmentIndex = i;
                        }else{
                            if(fittedSegment.size > segment.size){
                                fittedSegment = segment;
                                fittedSegmentIndex = i;
                            }
                        }
                    }
                }
                if(fittedSegment != null){
                    addServorToGroupAndRow(nextServor, group, row, fittedSegmentIndex,fittedSegment);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean serverFitsInSegment(Servor nextServor, Segment segment) {
        return segment.status == Status.FREE && segment.size >= nextServor.getSize();
    }

    private void addServorToGroupAndRow(Servor nextServor, Group group, Row row, int segmentIndex, Segment segment) {
        nextServor.setGroup(group.groupNumber);
        nextServor.setRow(row.getRowNumber());
        if(segment.size > nextServor.getSize()){
            Segment newSegment = new Segment();
            newSegment.size = segment.size - nextServor.getSize();
            row.getSlots().add(segmentIndex + 1,newSegment);
            newSegment.status = Status.FREE;
            newSegment.beginningSlot = segment.beginningSlot + nextServor.getSize();
            segment.size = nextServor.getSize();
        }
        segment.status = Status.SERVER;
        segment.servor = Optional.of(nextServor);
        segment.group = Optional.of(group);
        nextServor.setSlotNumber(segment.getBeginningSlot());
        group.servors.add(nextServor);

    }

    public int evaluate(){
        return room.getGroups().stream().mapToInt(
                new ToIntFunction<Group>() {
                    @Override
                    public int applyAsInt(Group value) {
                        return value.getGaranteedCapacity(room.getRows().size());
                    }
                }
        ).min().getAsInt();
    }

    public String serialize(){
        StringBuilder string = new StringBuilder();
        servors.stream().sorted(new Comparator<Servor>() {
            @Override
            public int compare(Servor o1, Servor o2) {
                return Integer.compare(o1.getServorId(),o2.getServorId());
            }
        }).forEachOrdered(new Consumer<Servor>() {
            @Override
            public void accept(Servor servor) {
                if(servor.isPlaced())
                    string.append(servor.getRow()).append(" ").append(servor.getSlotNumber()).append(" ").append(servor.getGroup()).append("\n");
                else
                    string.append("x").append("\n");
            }
        });
        File file = new File("output");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }

    private Room room;
    private PriorityQueue<Servor> remainingServors;
}
