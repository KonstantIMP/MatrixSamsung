package org.condit.kimp;

import java.io.PrintStream;

public class Main {
    private static PrintStream out = new PrintStream(System.out);

    public static void main(String[] args) throws Exception {
        Matrix a = new Matrix(new int[]{2, 1, 0, 3, 4, 5, 8, 7, 6}, 3);
        Matrix b = new Matrix(new int[]{12,12,13,13,13,14,14,14,15}, 3);

        out.println(Matrix.genSnake(1, 12, 4));
    }
}
