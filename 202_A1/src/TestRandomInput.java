import java.util.Arrays;
import java.util.Random;

public class TestRandomInput {

    public static void testSorting(double[][] values, int[] inputAxis, double[][] countSorted, double[][] mergeSorted,
            double[][] times) {

        for (int i = 0; i < inputAxis.length; i++) {

            long time1 = System.currentTimeMillis();
            countSorted[i] = CountingSort.sort(values[i], (int) Arrays.stream(values[i]).max().orElse(0.0));
            long time2 = System.currentTimeMillis();
            times[0][i] += (time2 - time1);

            long time3 = System.currentTimeMillis();
            mergeSorted[i] = MergeSort.sort(values[i]);
            long time4 = System.currentTimeMillis();
            times[1][i] += (time4 - time3);

            long time5 = System.currentTimeMillis();
            InsertionSort.sort(values[i]);
            long time6 = System.currentTimeMillis();
            times[2][i] += (time6 - time5);

        }
    }
        
    public static void testSearching(double[][] valuesOrdered, double[][] valuesRandom, int[] inputAxis, double[][] times) {

        for (int i = 0; i < inputAxis.length; i++) {

            Random random = new Random();
            int randomIndex = random.nextInt(valuesRandom[i].length);
            double randomNumber = valuesRandom[i][randomIndex];

            long time1 = System.nanoTime();
            LinearSearch.search(valuesRandom[i], randomNumber);
            long time2 = System.nanoTime();
            times[0][i] += (time2 - time1);

            long time3 = System.nanoTime();
            LinearSearch.search(valuesOrdered[i], randomNumber);
            long time4 = System.nanoTime();
            times[1][i] += (time4 - time3);



            long time6 = System.nanoTime();
            BinarySearch.binarySearch(valuesOrdered[i], randomNumber);
            long time5 = System.nanoTime();
            times[2][i] += (time5 - time6);





        }
    }
    
    public static void calculate(double[][] times, double count) {
        

        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < times[i].length; j++) {
                times[i][j] /= count;
            }
        }
        
        
    }
    }
