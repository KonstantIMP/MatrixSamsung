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
        if ((p.length != m.p.length) || (p.length != 0 && p[0].length != m.p[0].length)) throw new Exception("Incorrect matrix size");
        int [][] res = new int[p.length][p[0].length];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] + m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    public Matrix sub(Matrix m) throws Exception {
        if ((p.length != m.p.length) || (p.length != 0 && p[0].length != m.p[0].length)) throw new Exception("Incorrect matrix size");
        int [][] res = new int[p.length][p[0].length];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] - m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    public Matrix mul(int m){
        int [][] res = new int[p.length][p.length != 0 ? p[0].length : 0];

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                res[i][j] = p[i][j] * m;
            }
        }

        return new Matrix(res);
    }


    private static class Dimension {
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
}
