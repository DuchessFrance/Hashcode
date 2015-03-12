package fr.duchess.hashcode;

import fr.duchess.hashcode.bean.DataCenter;
import fr.duchess.hashcode.bean.Servor;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataCenter dataCenter = new DataCenter();
        
        Parser parser = Parser.getInstance();

        List<Servor> servers = parser.parse(dataCenter, "/home/cecilia/IdeaProjects/Hashcode/src/fr/duchess/hashcode/resources", "dc.in");



    }
}
