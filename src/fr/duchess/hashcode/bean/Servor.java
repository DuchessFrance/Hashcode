package fr.duchess.hashcode.bean;

public class Servor {
    int servorId;
    int size;
    int capacity;
    private int group;
    int row;

    public Servor(int i) {
        servorId = i;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public double getEfficiency() {
        return ((double) capacity)/size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}