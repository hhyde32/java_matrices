/*
 * NAME: Hedley Hyde
 * UNIVERSITY ID: 5700617
 * DEPARTMENT: Mathematics
 */

import java.util.Random;

public class GeneralMatrix extends Matrix {
  private double[][] values;

  public GeneralMatrix(int firstDim, int secondDim) {
    super(firstDim, secondDim);
    this.values = new double[firstDim][secondDim];
  }

  public GeneralMatrix(GeneralMatrix second) {
    this(second.iDim, second.jDim);

    for (int i = 0; i < iDim; i++) {
      for (int j = 0; j < jDim; j++) {
        this.values[i][j] = second.values[i][j];
      }
    }
  }

  public double getIJ(int i, int j) {
    if (i < 0 || i >= iDim || j < 0 || j >= jDim) {
      throw new MatrixException("Index out of bounds");
    }
    return values[i][j];
  }

  public void setIJ(int i, int j, double value) {
    if (i < 0 || i >= iDim || j < 0 || j >= jDim) {
      throw new MatrixException("Index out of bounds");
    }
    this.values[i][j] = value;
  }

  public double determinant() {
    double[] sign = new double[1];
    GeneralMatrix decomp = LUdecomp(sign);

    double det = sign[0];

    for (int i = 0; i < iDim; i++) {
      det *= decomp.getIJ(i, i);
    }

    return det;
  }

  public Matrix add(Matrix second) {
    if (iDim != second.iDim || jDim != second.jDim) {
      throw new MatrixException("Matrices can only be added to matrices of the same dimension");
    }
    GeneralMatrix result = new GeneralMatrix(iDim, jDim);
    double val;

    for (int i = 0; i < iDim; i++) {
      for (int j = 0; j < jDim; j++) {
        val = getIJ(i, j) + second.getIJ(i, j);
        result.setIJ(i, j, val);
      }
    }
    return result;
  }

  public Matrix multiply(double scalar) {
    GeneralMatrix result = new GeneralMatrix(iDim, jDim);
    double val;

    for (int i = 0; i < iDim; i++) {
      for (int j = 0; j < jDim; j++) {
        val = getIJ(i, j) * scalar;
        result.setIJ(i, j, val);
      }
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
        double sum = 0;
        for (int k = 0; k < jDim; k++) {
          sum += this.getIJ(i, k) * A.getIJ(k, j);
        }
        result.setIJ(i, j, sum);
      }
    }
    return result;
  }

  public void random() {
    Random r = new Random();

    double val;
    for (int i = 0; i < iDim; i++) {
      for (int j = 0; j < jDim; j++) {
        val = r.nextDouble();
        this.setIJ(i, j, val);
      }
    }
  }

  public GeneralMatrix LUdecomp(double[] sign) {
    if (jDim != iDim) throw new MatrixException("Matrix is not square");
    if (sign.length != 1) throw new MatrixException("d should be of length 1");

    int i, imax = -10, j, k;
    double big, dum, sum, temp;
    double[] vv = new double[jDim];
    GeneralMatrix a = new GeneralMatrix(this);

    sign[0] = 1.0;

    for (i = 1; i <= jDim; i++) {
      big = 0.0;
      for (j = 1; j <= jDim; j++) if ((temp = Math.abs(a.values[i - 1][j - 1])) > big) big = temp;
      if (big == 0.0) throw new MatrixException("Matrix is singular");
      vv[i - 1] = 1.0 / big;
    }

    for (j = 1; j <= jDim; j++) {
      for (i = 1; i < j; i++) {
        sum = a.values[i - 1][j - 1];
        for (k = 1; k < i; k++) sum -= a.values[i - 1][k - 1] * a.values[k - 1][j - 1];
        a.values[i - 1][j - 1] = sum;
      }
      big = 0.0;
      for (i = j; i <= jDim; i++) {
        sum = a.values[i - 1][j - 1];
        for (k = 1; k < j; k++) sum -= a.values[i - 1][k - 1] * a.values[k - 1][j - 1];
        a.values[i - 1][j - 1] = sum;
        if ((dum = vv[i - 1] * Math.abs(sum)) >= big) {
          big = dum;
          imax = i;
        }
      }
      if (j != imax) {
        for (k = 1; k <= jDim; k++) {
          dum = a.values[imax - 1][k - 1];
          a.values[imax - 1][k - 1] = a.values[j - 1][k - 1];
          a.values[j - 1][k - 1] = dum;
        }
        sign[0] = -sign[0];
        vv[imax - 1] = vv[j - 1];
      }
      if (a.values[j - 1][j - 1] == 0.0) a.values[j - 1][j - 1] = 1.0e-20;
      if (j != jDim) {
        dum = 1.0 / a.values[j - 1][j - 1];
        for (i = j + 1; i <= jDim; i++) a.values[i - 1][j - 1] *= dum;
      }
    }

    return a;
  }

  public static void main(String[] args) {
    GeneralMatrix A = new GeneralMatrix(5, 5);
    GeneralMatrix B = new GeneralMatrix(5, 5);

    A.random();
    B.random();

    System.out.println("A: \n" + A.toString());
    System.out.println("B: \n" + B.toString());
    System.out.println("A + B = \n" + A.add(B).toString());
    System.out.println("AB: \n" + A.multiply(B).toString());
    System.out.println("3*A: \n" + A.multiply(3).toString());
    System.out.println("A[1,1] = " + A.getIJ(1, 1));
    System.out.println("det(A) = " + A.determinant());
    A.setIJ(1, 1, 3);
    System.out.println("A[1,1] = 3: \n" + A.toString());
  }
}
