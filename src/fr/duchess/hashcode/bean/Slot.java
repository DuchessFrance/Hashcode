package fr.duchess.hashcode.bean;

public class Slot {
    int slotNumber;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSlotNumber (int number){ this.slotNumber = number;}
}