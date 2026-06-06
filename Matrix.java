public abstract class Matrix {
    protected int iDim;
    protected int jDim;

    protected Matrix(int firstDim, int secondDim) {
        this.iDim = firstDim;
        this.jDim = secondDim;
    }

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

    public abstract double getIJ(int i, int j);

    public abstract void setIJ(int i, int j, double val);

    public abstract double determinant();

    public abstract Matrix add(Matrix second);

    public abstract Matrix multiply(Matrix A);

    public abstract Matrix multiply(double scalar);

    public abstract void random();
}
