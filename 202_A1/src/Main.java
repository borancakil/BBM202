import java.io.IOException;
import java.util.Arrays;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;


class Main {
    public static void main(String args[]) throws IOException {



        // X axis data
        int[] inputAxis = { 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000 };
        
        double[][] values = ReadCsv.Reader(inputAxis, args[0]);


        
        double[][] timesUnordered = new double[3][inputAxis.length];
        double[][] timesReverseOrdered = new double[3][inputAxis.length];
        double[][] timesOrdered = new double[3][inputAxis.length];
        double[][] timesSearch = new double[3][inputAxis.length];
        double[][] countSorted = new double[values.length][];
        double[][] mergeSorted = new double[values.length][];
        double[][] valuesCopy = new double[values.length][];


        


            
        /* ****************************************************************
        
        *******************SORTING THE RANDOM VALUES *******************
        
        **************************************************************** */
      
         for (int i = 0; i < 10; i++) {


             // Create deep copy of the original data
             for (int j = 0; j < values.length; j++) {
                 valuesCopy[j] = Arrays.copyOf(values[j], values[j].length);
             }

             TestRandomInput.testSorting(valuesCopy, inputAxis, countSorted, mergeSorted, timesUnordered);

         }
        
        
        TestRandomInput.calculate(timesUnordered, 10);
    
   
        showAndSaveChart("Random Data", inputAxis, timesUnordered, true);





        /* ****************************************************************
        
        *******************SORTING THE SORTED VALUES **********************
        
        **************************************************************** */

         for (int i = 0; i < 10; i++) {

            TestRandomInput.testSorting(valuesCopy, inputAxis, countSorted, mergeSorted, timesOrdered);

        }
            

        TestRandomInput.calculate(timesOrdered, 10);
                 
        showAndSaveChart("Sorted Data", inputAxis, timesOrdered, true);  
            


        /* ****************************************************************
        
        *******************SORTING THE REVERSE SORTED VALUES **************
        
        **************************************************************** */



            
          

        // TESTING THE REVERSE ORDERED VALUES 
        for (int i = 0; i < 10; i++) {



            for (double[] row : valuesCopy) {
                reverseArray(row);
            }

            TestRandomInput.testSorting(valuesCopy, inputAxis, countSorted, mergeSorted, timesReverseOrdered);

        }


        TestRandomInput.calculate(timesReverseOrdered, 10);
      
        showAndSaveChart("Reversely Sorted Data", inputAxis, timesReverseOrdered, true); 
   
        


        /* ****************************************************************
    
        *******************SEARCHING THE REVERSE SORTED VALUES **************
        
        **************************************************************** */


         for (int i = 0; i < 1000; i++) {

            // TESTING
            TestRandomInput.testSearching(valuesCopy, values, inputAxis, timesSearch);

        }

        TestRandomInput.calculate(timesOrdered, 1000);
        
        showAndSaveChart("Searching", inputAxis, timesSearch, false);
 
   
    } 
        
        

    
    

        public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, boolean isSorting) throws IOException {
            // Create Chart
            String yAxisTitle = "";
            if (isSorting) {
                yAxisTitle = "Time in Milliseconds";
            } else {
                yAxisTitle = "Time in Nanoseconds";
            }
            XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                    .yAxisTitle(yAxisTitle).xAxisTitle("Input Size").build();

            // Convert x axis to double[]
            double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

            // Add a plot for a sorting algorithm

            if (isSorting) {
                chart.addSeries("Counting Sort", doubleX, yAxis[0]);
                chart.addSeries("Merge Sort", doubleX, yAxis[1]);
                chart.addSeries("Instertion Sort", doubleX, yAxis[2]);
            } else {
                chart.addSeries("Linear Search (Random Data)", doubleX, yAxis[0]);
                chart.addSeries("Linear Search (Sorted Data)", doubleX, yAxis[1]);
                chart.addSeries("Binary Search (Sorted Data)", doubleX, yAxis[2]);
            }

            // Save the chart as PNG
            BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

            // Show the chart
            new SwingWrapper<>(chart).displayChart();
        }
    

        public static void reverseArray(double[] arr) {
            int left = 0;
            int right = arr.length - 1;

            while (left < right) {
                // Swap elements at left and right indices
                double temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                // Move the pointers inward
                left++;
                right--;
            }
        }
    
   

}