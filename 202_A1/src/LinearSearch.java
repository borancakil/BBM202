public class LinearSearch {
    
    public static double search(double[] array, double x) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            if (array[i] == x) {
                return i;
            }
        }
        return -1;
    }
}
