package fr.duchess.hashcode.bean;

import java.util.List;

/**
 * Created by Axelle on 12-03-15.
 */
public class DataCenterRow {
    int rowIndex;
    private List<Slot> slots;

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
