package org.condit.kimp;

public class Dimension {
    private int rows;
    private int columns;

    public Dimension(int r, int c){
        rows = r; columns = c;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
