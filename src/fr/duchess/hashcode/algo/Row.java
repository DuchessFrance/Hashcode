package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * Created by Axelle on 12-03-15.
 */
public class Row {

    private List<Segment> slots = new ArrayList<>();
    private int rowNumber;

    public Row(int i) {
        rowNumber = i;
    }

    public int getValueByGroup(final int group) {
        return slots.stream().filter(new Predicate<Segment>() {
            @Override
            public boolean test(Segment segment) {
                return segment.status == Status.SERVER && segment.servor.isPresent() && segment.servor.get().getGroup() == group;
            }
        }).mapToInt(new ToIntFunction<Segment>() {
            @Override
            public int applyAsInt(Segment value) {
                return value.servor.get().getCapacity();
            }
        }).sum();
    }

    public List<Segment> getSlots() {
        return slots;
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
