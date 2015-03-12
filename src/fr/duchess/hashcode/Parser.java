package fr.duchess.hashcode;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.DataCenterRow;
import fr.duchess.hashcode.bean.Servor;
import fr.duchess.hashcode.bean.Slot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cecilia on 12/03/15.
 */
public class Parser {

    final static Parser parser = new Parser();

    private Parser() {
    }


    public static Parser getInstance() {
        return parser;
    }

    public List<Servor> parse(DataCenter dataCenter, String filePath) {

        List<Servor> servors = new ArrayList<>();

        try {
            Path path = Paths.get(filePath);
            Stream<String> lines = null;
            lines = Files.lines(path);

            Object[] linesArray = lines.toArray();

            String firstLine = (String) linesArray[0];
            String[] firstLineValues = firstLine.split(" ");
            int nbRows = Integer.valueOf(firstLineValues[0]);
            int nbSlots = Integer.valueOf(firstLineValues[1]);
            int nbSlotsUnavailable = Integer.valueOf(firstLineValues[2]);
            int nbPools = Integer.valueOf(firstLineValues[3]);
            int nbServors = Integer.valueOf(firstLineValues[4]);

            System.out.println("Nb rows " + nbRows + " nbSlots " + nbSlots + " nbSlotsUnavailable " + nbSlotsUnavailable + " nbPools " + nbPools + " nbServors " + nbServors);

            // Rows creation
            DataCenterRow[] rows = createRows(nbRows, nbSlots);

            makeSlotsUnavailable(linesArray, nbSlotsUnavailable, rows);

            dataCenter.setRows(Arrays.asList(rows));

            servors = getServers(linesArray, nbSlotsUnavailable, nbServors);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return servors;

    }

    private List<Servor> getServers(Object[] linesArray, int nbSlotsUnavailable, int nbServors) {
        Object[] servorsToBeAllocatedLines = Arrays.copyOfRange(linesArray, nbSlotsUnavailable + 1, nbSlotsUnavailable + nbServors + 1);
        List<Servor> servorsToBeAllocated = new ArrayList<>();
        for (int i = 0; i < servorsToBeAllocatedLines.length; i++) {

            String stringservorsToBeAllocatedLine = (String) servorsToBeAllocatedLines[i];

            String[] lineValues = stringservorsToBeAllocatedLine.split(" ");
            Integer nbServorSlots = Integer.valueOf(lineValues[0]);
            Integer capacity = Integer.valueOf(lineValues[1]);

            Servor servor = new Servor();
            servor.setCapacity(capacity.intValue());
            servor.setSize(nbServorSlots.intValue());
            servor.setServorId(i);


            servorsToBeAllocated.add(servor);

            System.out.println("Server : id : " + i + " " + capacity + " " + nbServorSlots);
        }
        return servorsToBeAllocated;
    }

    private void makeSlotsUnavailable(Object[] linesArray, int nbSlotsUnavailable, DataCenterRow[] rows) {
        Object[] unavailableSlotsLines = Arrays.copyOfRange(linesArray, 1, nbSlotsUnavailable);

        for (Object unavailableSlotLine : unavailableSlotsLines) {
            String stringUnavailableSlotLine = (String) unavailableSlotLine;
            String[] lineValues = stringUnavailableSlotLine.split(" ");

            Integer rowNumber = Integer.valueOf(lineValues[0]);
            Integer slotNumber = Integer.valueOf(lineValues[1]);

            DataCenterRow row = rows[rowNumber];
            Slot slot = row.getSlots().get(slotNumber);

            slot.setAvailable(false);

            System.out.println("Slot unavailable : " + rowNumber + " " + slotNumber);
        }
    }

    private DataCenterRow[] createRows(int nbRows, int nbSlots) {
        DataCenterRow[] rows = new DataCenterRow[nbRows];
        for (int i = 0; i < nbRows; i++) {
            DataCenterRow row = new DataCenterRow();
            row.setRowIndex(i);

            // slots creation
            Slot[] slots = new Slot[nbSlots];

            for (int j = 0; j < nbSlots; j++) {
                Slot slot = new Slot();
                slot.setSlotNumber(j);
                slot.setAvailable(true);
                slots[j] = slot;
            }

            row.setSlots(Arrays.asList(slots));

            rows[i] = row;
        }
        return rows;
    }

}
