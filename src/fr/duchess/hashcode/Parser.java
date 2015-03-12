package fr.duchess.hashcode;

import fr.duchess.hashcode.bean.DataCenter;
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

    public void parse(String filePath, String fileName) {
        Path path = Paths.get(filePath, fileName);
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
            DataCenter dataCenter = new DataCenter();

            Object[] linesArray = lines.toArray();

            String firstLine = (String) linesArray[0];
            String[] firstLineValues = firstLine.split(" ");
            Integer nbRows = Integer.valueOf(firstLineValues[0]);
            Integer nbSlots = Integer.valueOf(firstLineValues[1]);
            Integer nbSlotsUnavailable = Integer.valueOf(firstLineValues[2]);
            Integer nbPools = Integer.valueOf(firstLineValues[3]);
            Integer nbServors = Integer.valueOf(firstLineValues[4]);

            //System.out.println("Nb rows "+nbRows + " nbSlots " + nbSlots + " nbSlotsUnavailable "+ nbSlotsUnavailable + " nbPools "+ nbPools + " nbServors "+ nbServors);

            Object[] unavailableSlotsLines = Arrays.copyOfRange(linesArray, 1, nbSlotsUnavailable.intValue());
            List<Slot> unavailableSlots = new ArrayList<>();
            for (Object unavailableSlotLine : unavailableSlotsLines) {
                String stringUnavailableSlotLine = (String) unavailableSlotLine;
                String[] lineValues = stringUnavailableSlotLine.split(" ");
                Integer rowNumber = Integer.valueOf(lineValues[0]);
                Integer slotNumber = Integer.valueOf(lineValues[1]);

                Slot slot = new Slot();
                slot.setAvailable(false);
                slot.setRowNumber(rowNumber.intValue());
                slot.setSlotNumber(slotNumber.intValue());

                unavailableSlots.add(slot);

                //System.out.println("Slot unavailable : " + rowNumber + " " + slotNumber);
            }


            Object[] servorsToBeAllocatedLines = Arrays.copyOfRange(linesArray, nbSlotsUnavailable.intValue() + 1, nbSlotsUnavailable.intValue() + nbServors.intValue() + 1);
            List<Servor> servorsToBeAllocated = new ArrayList<>();
            for (Object servorsToBeAllocatedLine : servorsToBeAllocatedLines) {

                String stringservorsToBeAllocatedLine = (String) servorsToBeAllocatedLine;

                String[] lineValues = stringservorsToBeAllocatedLine.split(" ");
                Integer nbServorSlots = Integer.valueOf(lineValues[0]);
                Integer capacity = Integer.valueOf(lineValues[1]);

                Servor servor = new Servor();
                servor.setCapacity(capacity.intValue());
                servor.setSize(nbServorSlots.intValue());


                servorsToBeAllocated.add(servor);

                //System.out.println("Server : " + capacity + " " + nbServorSlots);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
