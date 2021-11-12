package org.condit.kimp;

import java.util.Random;

public class Matrix {
    private int [][] p;
    private Dimension dimension;

    /**
     * Creates a n*m matrix (all elements are 0)
     */
    public static Matrix createNull(int n, int m) {
        int [][] res = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = 0;
            }
        }

        return new Matrix(res);
    }

    /**
     * Creates a n*n matrix (all elements are 0 except elements on the main diagonal)
     */
    public static Matrix createUnit(int n) {
        int [][] res = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = 0;
                if (i == j) res[i][j] = 1;
            }
        }

        return new Matrix(res);
    }

    /**
     * Creates a n*m matrix (all elements are 0 or 1 - random)
     */
    public static Matrix genRandomLogical(int n, int m) {
        int [][] res = new int[n][m];

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                res[i][j] = rand.nextInt() % 2;
            }
        }

        return new Matrix(res);
    }

    /**
     * Creates a matrix by a Range
     * Contain all elements in [first; last]
     * Has n elements per row
     */
    public static Matrix genRange(int first, int last, int n) throws Exception {
        if ((last - first + 1) % n != 0) throw new Exception("Impossible num of elements per line");

        int [] tmp = new int[last - first + 1];
        for (int i = first; i <= last; i++) tmp[i - first] = i;

        return new Matrix(tmp, n);
    }

    /**
     * Creates a matrix by a Range (but fills it by a snake)
     * Contain all elements in [first; last]
     * Has n elements per row
     */
    public static Matrix genSnake(int first, int last, int n) throws Exception {
        if ((last - first + 1) % n != 0) throw new Exception("Impossible num of elements per line");

        int [][] res = new int[(last - first + 1) / n][n];
        int ct = first;

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0) res[i][j] = ct;
                else res[i][n - j - 1] = ct;
                ct++;
            }
        }

        return new Matrix(res);
    }

    /**
     * Swap the Matrix axes
     * @return Matrix with the swapped axes
     */
    public Matrix transpose() {
        int [][] res = new int[dimension.getColumns()][dimension.getRows()];

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getRows(); j++) {
                res[j][i] = p[i][j];
            }
        }

        return new Matrix(res);
    }

    /**
     * Check if the matrix is diagonal
     */
    public boolean isDiagonal() {
        if (dimension.getColumns() != dimension.getRows()) return false;

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                if(i == j) {
                    if (p[i][j] == 0) return false;
                    continue;
                }
                else if(p[i][j] != 0) return false;
            }
        }

        return true;
    }

    /**
     * Calculates arithmetic mean of the elements
     */
    public double mean() throws Exception {
        if (dimension.getColumns() * dimension.getColumns() == 0)
            throw new Exception("Empty matrix");
        return (double)this.sum() / (double)(dimension.getColumns() * dimension.getColumns());
    }

    /**
     * Creates a new 0*0 Matrix
     */
    public Matrix() {
        p = new int[0][0];
        dimension = new Dimension(0, 0);
    }

    /**
     * Creates a new matrix with all elements from m array
     * Has n elements per row
     */
    public Matrix(int [] m, int n) {
        p = new int[m.length / n][n];
        dimension = new Dimension(m.length / n, n);

        for(int i = 0; i < m.length; i++){
            p[i / n][i % n] = m[i];
        }
    }

    /**
     * Creates a new Matrix from two-dimensional array
     */
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

    /**
     * Copy ctor
     */
    public Matrix(Matrix c){
        this(c.p);
    }

    /**
     * Returns the size of the Matrix
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Calculates a sum of 2 matrices
     */
    public Matrix sum(Matrix m) throws Exception {
        if (dimension.getColumns() != m.dimension.getColumns() || dimension.getRows() != m.dimension.getRows()) throw new Exception("Incorrect matrix size");
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                res[i][j] = p[i][j] + m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    /**
     * Calculates a sub of 2 matrices
     */
    public Matrix sub(Matrix m) throws Exception {
        if (dimension.getColumns() != m.dimension.getColumns() || dimension.getRows() != m.dimension.getRows()) throw new Exception("Incorrect matrix size");
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                res[i][j] = p[i][j] - m.p[i][j];
            }
        }

        return new Matrix(res);
    }

    /**
     * Multiplies the matrix by m
     */
    public Matrix mul(int m){
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                res[i][j] = p[i][j] * m;
            }
        }

        return new Matrix(res);
    }

    /**
     * Divides the matrix by m(by elements)
     */
    public Matrix div(int n) {
        int [][] res = new int[dimension.getRows()][dimension.getColumns()];

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                res[i][j] = p[i][j] / n;
            }
        }

        return new Matrix(res);
    }

    /**
     * Search n in the matrix and return it`s index
     */
    public int search(int n){
        int index = -1;

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                if (p[i][j] == n) index = i * dimension.getColumns() + j;
                if (index != -1) break;
            }
            if (index != -1) break;
        }

        return index;
    }

    /**
     * Returns index of the max element of the Matrix
     */
    public int argmax() {
        return search(max());
    }

    /**
     * Returns index of the min element of the Matrix
     */
    public int argmin() {
        return search(min());
    }

    /**
     * Multiply 2 matrices
     */
    public Matrix mul(Matrix m) throws Exception {
        if (dimension.getColumns() != m.dimension.getRows())
            throw new Exception("Cannot mul matrix with this size");

        int [][] res = new int[Math.max(dimension.getColumns(), m.dimension.getColumns())][Math.max(dimension.getRows(), m.dimension.getRows())];
        Dimension resSize = new Dimension(Math.max(dimension.getColumns(), m.dimension.getColumns()), Math.max(dimension.getRows(), m.dimension.getRows()));

        for (int i = 0; i < resSize.getRows(); i++) {
            for (int j = 0; j < resSize.getColumns(); j++) {
                res[i][j] = 0;
                for (int k = 0; k < dimension.getColumns(); k++) {
                    res[i][j] += p[i][k] * m.p[k][j];
                }
            }
        }

        return new Matrix(res);
    }

    /**
     * Return max element of the Matrix
     */
    public int max() {
        if (dimension.getColumns() == 0 || dimension.getRows() == 0) return 0;
        int m = p[0][0];
        for(int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                m = Math.max(m, p[i][j]);
            }
        }
        return m;
    }

    /**
     * Return min element of the Matrix
     */
    public int min() {
        if (dimension.getColumns() == 0 || dimension.getRows() == 0) return 0;
        int m = p[0][0];
        for(int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                m = Math.min(m, p[i][j]);
            }
        }
        return m;
    }

    /**
     * Calculates sum of the elements
     */
    public int sum() {
        int s = 0;

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                s += p[i][j];
            }
        }

        return s;
    }

    /**
     * Calculates sum of the main diagonal elements
     */
    public int trace() throws Exception {
        if (dimension.getColumns() != dimension.getRows())
            throw new Exception("Cannot trace non square matrix");

        int t = 0;

        for (int i = 0; i < dimension.getRows(); i++) {
            t += p[i][i];
        }

        return t;
    }

    /**
     * Returns element by index
     */
    public int getElement(int index) throws Exception {
        if (index >= dimension.getRows() + dimension.getColumns())
            throw new Exception("Incorrect element index");

        return p[index / dimension.getColumns()][index % dimension.getColumns()];
    }

    /**
     * Returns element by it`s position
     */
    public int getElement(int x, int y) throws Exception {
        if (x >= dimension.getColumns() || y >= dimension.getRows())
            throw new Exception("Incorrect element index");
        return this.getElement(x + y * dimension.getColumns());
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        int num = String.valueOf(this.max()).length();

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                String n = String.valueOf(p[i][j]);
                for (int k = 0; k < num - n.length(); k++) builder.append(" ");
                builder.append(n + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public String toLine() {
        StringBuilder builder = new StringBuilder();

        int counter = dimension.getColumns() * dimension.getRows();

        for (int i = 0; i < dimension.getRows(); i++) {
            for (int j = 0; j < dimension.getColumns(); j++) {
                builder.append(String.valueOf(p[i][j]));
                if (counter != 1) builder.append(", ");
                counter--;
            }
        }

        return builder.toString();
    }
}
