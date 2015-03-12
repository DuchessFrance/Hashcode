package fr.duchess.hashcode.bean;

public class Servor {
    int size;
    int capacity;
    private int group;
    int row;
    int servorId;

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

    public void setSize(int size) {
        this.size = size;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getServorId() {
        return servorId;
    }

    public void setServorId(int servorId) {
        this.servorId = servorId;
    }

}