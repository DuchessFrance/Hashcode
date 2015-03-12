package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Created by Axelle on 12-03-15.
 */
public class Group {
    public List<Servor> servors = new ArrayList<>();

    int groupNumber;

    public Group(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getCapacity(){
        return servors.stream().mapToInt(new ToIntFunction<Servor>() {
            @Override
            public int applyAsInt(Servor value) {
                return value.getCapacity();
            }
        }).sum();
    }
}
