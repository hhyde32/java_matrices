public class Project3 {
  public static double matVariance(Matrix matrix, int nSamp) {
    double sum1 = 0.0;
    double sum2 = 0.0;
    for (int i = 0; i < nSamp; i++) {
      matrix.random();
      double d = matrix.determinant();
      sum1 += d * d;
      sum2 += d;
    }
    double mean = (sum2 / nSamp);
    double matVar = (sum1 / nSamp) - mean * mean;
    return matVar;
  }

  public static void main(String[] args) {
    for (int n = 2; n <= 30; n++) {
      GeneralMatrix genMat = new GeneralMatrix(n, n);
      TriMatrix triMat = new TriMatrix(n);

      double varGen = matVariance(genMat, 20000);
      double varTri = matVariance(triMat, 200000);
      System.out.printf("%d %.15e %.15e%n", n, varGen, varTri);
    }
  }
}
