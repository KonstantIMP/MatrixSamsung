package org.condit.kimp;

public class Matrix {
    private int [][] p;

    public Matrix() {
        p = new int[0][0];
    }

    public Matrix(int [] m, int n) {
        p = new int[m.length / n][n];
        for(int i = 0; i < m.length; i++){
            p[i / n][i % n] = m[i];
        }
    }

    public Matrix(int [][] m){
        if(m.length == 0) {
            p = new int[0][0];
            return;
        }
        p = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++){
                p[i][j] = m[i][j];
            }
        }
    }

    public Matrix(Matrix c){
        this(c.p);
    }
    
}
