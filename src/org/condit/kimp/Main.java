package org.condit.kimp;

import java.io.PrintStream;

public class Main {
    private static PrintStream out = new PrintStream(System.out);

    public static void main(String[] args) {
        Matrix a = new Matrix(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 3);

        out.println(a + a.toLine());
    }
}
