package fr.duchess.hashcode.algo;

import fr.duchess.hashcode.bean.Servor;

import java.util.Optional;

/**
 * Created by Axelle on 12-03-15.
 */
public class Segment {
    int size;
    Status status;
    public Optional<Servor> servor;
    public Optional<Group> group;

    public Segment() {
        this.size = 1;
    }
}
