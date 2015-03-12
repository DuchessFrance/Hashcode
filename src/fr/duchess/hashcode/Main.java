package fr.duchess.hashcode;

import fr.duchess.hashcode.algo.Room;
import fr.duchess.hashcode.algo.ServorPicker;
import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.DataCenterRow;
import fr.duchess.hashcode.bean.Servor;
import fr.duchess.hashcode.bean.Slot;

import java.util.ArrayList;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.Servor;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataCenter dataCenter = new DataCenter();
        
        Parser parser = Parser.getInstance();

        List<Servor> servers = parser.parse(dataCenter, "src/fr/duchess/hashcode/resources/dc.in");

        Room room = new Room(10,dataCenter);
        ServorPicker servorPicker = new ServorPicker(room,servers);
        for (int i = 0; i < servers.size(); i++) {
            servorPicker.placeNextServor();
        }

        System.out.print(servorPicker.serialize());

        System.out.println("**********************");
        System.out.println(servorPicker.evaluate());
    }

 public static void testAlgo(String[] args) {
        // write your code here







        DataCenter dataCenter = fakeDataCenter();
        Room room = new Room(2, dataCenter);
        ArrayList servors = new ArrayList<Servor>();
        servors.add(fakeServor(0));
        servors.add(fakeServor(1));
        ServorPicker servorPicker = new ServorPicker(room, servors);
        servorPicker.placeNextServor();
        servorPicker.placeNextServor();

    }

    public static Servor fakeServor(int i){
        Servor servor = new Servor();
        servor.setServorId(i);
        servor.setSize(2);
        servor.setCapacity(2);
        return servor;
    }

    public static DataCenter fakeDataCenter(){
        DataCenter dataCenter = new DataCenter();
        dataCenter.setRows(new ArrayList<>());
        dataCenter.getRows().add(fakeRow(0));
        dataCenter.getRows().add(fakeRow(1));
        return dataCenter;
    }

    public static DataCenterRow fakeRow(int rowNumber){
        DataCenterRow dataCenterRow = new DataCenterRow();
        dataCenterRow.setRowIndex(rowNumber);
        dataCenterRow.setSlots(new ArrayList<>());
        for (int i = 1; i < 6; i++) {
            dataCenterRow.getSlots().add(fakeSlot(i));
        }
        return dataCenterRow;
    }

    public static Slot fakeSlot(int count){
        Slot slot = new Slot();
        slot.setAvailable(true);
        slot.setSlotNumber(count);
        return slot;
    }
}
