public class MergeSort {

    public static double[] sort(double[] array) {
        int n = array.length;
        if (n <= 1) {
            return array;
        }
        double[] left = new double[n / 2];
        double[] right = new double[n - n / 2];
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }


    private static double[] merge(double[] a, double[] b) {
        double[] c = new double[a.length + b.length];

         int i = 0, j = 0, k = 0;
         while (i < a.length && j < b.length) {
             if (a[i] > b[j]) {
                 c[k++] = b[j++];
             } else {
                 c[k++] = a[i++];
             }
         }
         
         while (i < a.length) {
             c[k++] = a[i++];
         }

         while (j < b.length) {
             c[k++] = b[j++];
         }
        return c;
    }
}
