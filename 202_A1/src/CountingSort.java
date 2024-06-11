public class CountingSort {

    public static double[] sort(double[] array, int k) {
        double count[] = new double[k + 1];
        double output[] = new double[array.length];
        int size = array.length;

        for (int i = 0; i < size; i++) {
            int j = (int) array[i];
            count[j]++;
        }

        for (int i = 1; i < k + 1; i++) {
            count[i] += count[i - 1];
        }
        
        for (int i = size - 1; i >= 0; i--) {
            int j = (int) array[i];

            count[j] = count[j] - 1;
            output[(int) count[j]] = array[i];
        }

        return output;
        
    }

}
