package fr.duchess.hashcode.algo;

/**
 * Created by Axelle on 12-03-15.
 */
public enum Status {
    FREE, UNAV, SERVER;

    public String serialize() {
        switch (this){

            case FREE:
                return "-";
            case UNAV:
                return "x";
            case SERVER:
                return "s";

        }
        return "";
    }
}
