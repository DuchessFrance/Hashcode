package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.util.Optional;

/**
 * Created by Axelle on 12-03-15.
 */
public class Segment {
    public Optional<Servor> servor;
    public Optional<Group> group;
    int size;
    Status status;

    public int getBeginningSlot() {
        return beginningSlot;
    }

    public void setBeginningSlot(int beginningSlot) {
        this.beginningSlot = beginningSlot;
    }

    int beginningSlot;

    public Segment() {
        this.size = 1;
    }
}
