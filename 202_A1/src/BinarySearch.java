public class BinarySearch {

    public static int binarySearch(double[] array, double x) {
        int low = 0;
        int high = array.length - 1;

        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (array[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (array[low] == x) {
            return low;
        } else if (array[high] == x) {
            return high;
        }

        return -1;
    }
    
    
}
