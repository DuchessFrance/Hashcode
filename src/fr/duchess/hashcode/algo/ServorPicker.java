package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Axelle on 12-03-15.
 */
public class ServorPicker {

    private Room room;
    private PriorityQueue<Servor> remainingServors;

    public ServorPicker(Room room, List<Servor> servors) {
        this.room = room;
        this.remainingServors = new PriorityQueue<>();
        remainingServors.addAll(servors);
    }

    public void placeNextServor() {
        Servor nextServor = remainingServors.peek();
        PriorityQueue<Group> groups = room.getGroups();
        for (Group group : groups) {
            Stream<Row> rowStream = room.orderRowforGroup(group.groupNumber);
            List<Row> collect = rowStream.collect(Collectors.toList());
            for (Row row : collect) {
                for (int i = 0; i < row.getSlots().size(); i++) {

                }
                for (Segment segment : row.getSlots()) {

                }
            }
        }

    }
}
