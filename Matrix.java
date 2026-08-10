public abstract class Matrix {
    // Abstract base class for TriMatrix and GeneralMatrix.
    
    // Matrix dimensions.
    protected int iDim;
    protected int jDim;

    protected Matrix(int firstDim, int secondDim) {
        // Initialise the dimensions of the matrix.
        this.iDim = firstDim;
        this.jDim = secondDim;
    }

    public String toString() {
        // Convert the matrix to string format.
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.iDim; i++) {
            for (int j = 0; j < this.jDim; j++) {
                s.append(String.format("%.2f ", getIJ(i, j)));
            }
            s.append(System.lineSeparator());
        }

        return s.toString();
    }

    public abstract double getIJ(int i, int j); // Get the value at position (i, j).

    public abstract void setIJ(int i, int j, double val); // Set the value at position (i, j).
    
    public abstract double determinant(); // Calculate the determinant of the matrix.

    public abstract Matrix add(Matrix second); // Add the matrix to another matrix.

    public abstract Matrix multiply(Matrix A); // Multiply the matrix by another matrix.

    public abstract Matrix multiply(double scalar); // Multiply the matrix by a scalar.

    public abstract void random(); // Populate the matrix with random numbers.
}
