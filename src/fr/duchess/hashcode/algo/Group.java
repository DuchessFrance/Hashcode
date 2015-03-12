package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

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


    public int getGaranteedCapacity(int rowCount) {
        return getCapacity() - maxRowCapacity(rowCount);
    }

    private int maxRowCapacity(int rowCount) {
        int maxCapa = 0;
        for (int i = 0; i < rowCount; i++) {
            int currentCapa = 0;
            for (Servor servor : servors) {
                if(servor.getRow() == i){
                    currentCapa += servor.getCapacity();
                }
            }
            if(currentCapa > maxCapa)
                maxCapa = currentCapa;
        }
        return maxCapa;
    }
}
