    public class InsertionSort {


        public static void sort(double[] values) {
            for (int j = 1; j < values.length; j++) {
                double key = values[j];
                int i = j - 1;
                while (i >= 0 && values[i] > key) {
                    values[i + 1] = values[i];
                    i--;
                }
                values[i + 1] = key;
            } 
        }
    }
