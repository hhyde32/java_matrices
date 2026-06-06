import java.util.Random;

public class TriMatrix extends Matrix {
  private double[] diagonal;
  private double[] upperDiagonal;
  private double[] lowerDiagonal;

  public TriMatrix(int dimension) {
    super(dimension, dimension);
    this.diagonal = new double[dimension];
    this.upperDiagonal = new double[dimension - 1];
    this.lowerDiagonal = new double[dimension - 1];
  }

  public double getIJ(int i, int j) {
    if (i < 0 || i >= iDim || j < 0 || j >= jDim) {
      throw new MatrixException("Index out of bounds");
    }
    if (i == j) {
      return diagonal[i];
    } else if (i + 1 == j) {
      return upperDiagonal[i];
    } else if (i - 1 == j) {
      return lowerDiagonal[i - 1];
    } else {
      return 0;
    }
  }

  public void setIJ(int i, int j, double value) {
    if (i < 0 || i >= iDim || j < 0 || j >= jDim) {
      throw new MatrixException("Index out of bounds");
    }
    if (i == j) {
      diagonal[i] = value;
    } else if (i + 1 == j) {
      upperDiagonal[i] = value;
    } else if (i - 1 == j) {
      lowerDiagonal[i - 1] = value;
    }
  }

  public double determinant() {
    TriMatrix decomp = LUdecomp();
    double det = 1.0;

    for (int i = 0; i < iDim; i++) {
      det *= decomp.getIJ(i, i);
    }

    return det;
  }

  public TriMatrix LUdecomp() {
    TriMatrix result = new TriMatrix(iDim);

    result.setIJ(0, 0, getIJ(0, 0));
    if (iDim > 1) {
      result.setIJ(0, 1, getIJ(0, 1));
    }

    for (int i = 1; i < iDim; i++) {
      double pivot = result.getIJ(i - 1, i - 1);

      if (Math.abs(pivot) < 1e-12) {
        throw new MatrixException("Pivot too close to zero");
      }

      double l = this.getIJ(i, i - 1) / pivot;
      double d = this.getIJ(i, i) - l * result.getIJ(i - 1, i);

      result.setIJ(i, i - 1, l);
      result.setIJ(i, i, d);

      if (i < iDim - 1) {
        result.setIJ(i, i + 1, this.getIJ(i, i + 1));
      }
    }

    return result;
  }

  public Matrix add(Matrix second) {
    if (iDim != second.iDim || jDim != second.jDim) {
      throw new MatrixException("Matrices can only be added to matrices of the same dimension");
    }
    Matrix result = null;

    if (second instanceof TriMatrix) {
      TriMatrix triResult = new TriMatrix(iDim);

      for (int i = 0; i < iDim; i++) {
        triResult.setIJ(i, i, this.getIJ(i, i) + second.getIJ(i, i));
        if (i > 0) {
          triResult.setIJ(i, i - 1, this.getIJ(i, i - 1) + second.getIJ(i, i - 1));
        }
        if (i < iDim - 1) {
          triResult.setIJ(i, i + 1, this.getIJ(i, i + 1) + second.getIJ(i, i + 1));
        }
      }
      result = triResult;
    } else if (second instanceof GeneralMatrix) {
      GeneralMatrix genResult = new GeneralMatrix(iDim, iDim);
      for (int i = 0; i < iDim; i++) {
        for (int j = 0; j < iDim; j++) {
          genResult.setIJ(i, j, this.getIJ(i, j) + second.getIJ(i, j));
        }
      }
      result = genResult;
    }
    return result;
  }

  public Matrix multiply(Matrix A) {
    if (jDim != A.iDim) {
      throw new MatrixException("Inner dimensions must match for multiplication.");
    }
    GeneralMatrix result = new GeneralMatrix(iDim, A.jDim);

    for (int i = 0; i < iDim; i++) {
      for (int j = 0; j < A.jDim; j++) {
        double sum = getIJ(i, i) * A.getIJ(i, j);

        if (i > 0) {
          sum += getIJ(i, i - 1) * A.getIJ(i - 1, j);
        }

        if (i < iDim - 1) {
          sum += getIJ(i, i + 1) * A.getIJ(i + 1, j);
        }

        result.setIJ(i, j, sum);
      }
    }

    return result;
  }

  public Matrix multiply(double scalar) {
    TriMatrix result = new TriMatrix(iDim);

    result.setIJ(0, 0, this.getIJ(0, 0) * scalar);
    for (int i = 1; i < iDim; i++) {
      result.setIJ(i, i, this.getIJ(i, i) * scalar);
      result.setIJ(i - 1, i, this.getIJ(i - 1, i) * scalar);
      result.setIJ(i, i - 1, this.getIJ(i, i - 1) * scalar);
    }
    return result;
  }

  public void random() {

    Random r = new Random();

    for (int i = 0; i < iDim; i++) {
      this.setIJ(i, i, r.nextDouble());
      if (i > 0) {
        this.setIJ(i, i - 1, r.nextDouble());
      }
      if (i < iDim - 1) {
        this.setIJ(i, i + 1, r.nextDouble());
      }
    }
  }

  public static void main(String[] args) {
    TriMatrix A = new TriMatrix(4);
    GeneralMatrix B = new GeneralMatrix(4, 4);

    A.random();
    B.random();

    System.out.println("A: \n" + A.toString());
    System.out.println("B: \n" + B.toString());
    System.out.println("A + B = \n" + A.add(B).toString());
    System.out.println("AB: \n" + A.multiply(B).toString());
    System.out.println("5*A: \n" + A.multiply(5).toString());
    System.out.println("A[1,1] = " + A.getIJ(1, 1));
    A.setIJ(0, 0, 1);
    System.out.println("A[0,0] = 1: \n" + A.toString());
    System.out.println("det(A) = " + A.determinant());
  }
}
