package fr.duchess.hashcode.bean;


import java.util.List;

public class DataCenter {
    private List<DataCenterRow> rows;

    public int getRowCount() {
        return getRows().size();
    }


    public List<DataCenterRow> getRows() {
        return rows;
    }

    public void setRows(List<DataCenterRow> rows) {
        this.rows = rows;
    }
}