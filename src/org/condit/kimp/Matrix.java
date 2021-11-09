package org.condit.kimp;

public class Matrix {
    private int [][] p;
    private Dimension dimension;

    public Matrix() {
        p = new int[0][0];
        dimension = new Dimension(0, 0);
    }

    public Matrix(int [] m, int n) {
        p = new int[m.length / n][n];
        dimension = new Dimension(m.length / n, n);

        for(int i = 0; i < m.length; i++){
            p[i / n][i % n] = m[i];
        }
    }

    public Matrix(int [][] m){
        if(m.length == 0) {
            p = new int[0][0];
            dimension = new Dimension(0, 0);
            return;
        }

        p = new int[m.length][m.length > 0 ? m[0].length : 0];
        dimension = new Dimension(m.length, m.length > 0 ? m[0].length : 0);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++){
                p[i][j] = m[i][j];
            }
        }
    }

    public Matrix(Matrix c){
        this(c.p);
    }

    public Matrix sum(Matrix m) throws Exception {
        if (dimension.getColumns() != m.dimension.getColumns() || dimension.getRows() != m.dimension.getRows()) throw new Exception("Incorrect matrix size");
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] + m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    public Matrix sub(Matrix m) throws Exception {
        if (dimension.getColumns() != m.dimension.getColumns() || dimension.getRows() != m.dimension.getRows()) throw new Exception("Incorrect matrix size");
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] - m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    public Matrix mul(int m){
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] * m;
            }
        }

        return new Matrix(res);
    }
}
