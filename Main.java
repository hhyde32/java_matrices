public class Main {
    // Class to generate statistics on random matrices.

    public static double matVariance(Matrix matrix, int nSamp) {
        // Estimate the variance of the determinants from a sample of random matrices.
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
        // Generate the variance for TriMatrix and GeneralMatrix for increasing dimensions.
        for (int n = 2; n <= 50; n++) {
            GeneralMatrix genMat = new GeneralMatrix(n, n);
            TriMatrix triMat = new TriMatrix(n);

            double varGen = matVariance(genMat, 20000);
            double varTri = matVariance(triMat, 200000);
            System.out.printf("%d %.15e %.15e%n", n, varGen, varTri);
        }
    }
}
