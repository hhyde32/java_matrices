// This class is a template for the TriMatrix and GeneralMatrix classes

public abstract class Matrix {
    protected int iDim;
    protected int jDim;

    // Initialises the dimensions of the matrix to be stored
    protected Matrix(int firstDim, int secondDim) {
        this.iDim = firstDim;
        this.jDim = secondDim;
    }

    // Method to return the matrix in string format
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.iDim; i++) {
            for (int j = 0; j < this.jDim; j++) {
                s.append(String.format("%.2f ", getIJ(i, j)));
            }
            s.append(System.lineSeparator());
        }

        return s.toString();
    }

    // Returns the dimensions of the matrix
    public abstract double getIJ(int i, int j);

    // Method to set the value at position i,j
    public abstract void setIJ(int i, int j, double val);

    // Calculates the determinant of the matrix
    public abstract double determinant();

    // Returns the result of adding the matrix to another matrix
    public abstract Matrix add(Matrix second);

    // Returns the result of multiplying the matrix by another matrix
    public abstract Matrix multiply(Matrix A);

    // Returns the result of multiplying the matrix by a scalar constant
    public abstract Matrix multiply(double scalar);

    // Method to fill the matrix with random numbers
    public abstract void random();
}
