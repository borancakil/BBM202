import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadCsv {


    public static double[][] Reader(int[] inputAxis, String input) {


        String csvFile = input;
        int columnToRead = 6; // Index of the 7th column (0-based indexing)
        String csvDelimiter = ","; // Change this if your CSV uses a different delimiter

        
        double[][] values = new double[inputAxis.length][];


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            for (int i = 0; i < inputAxis.length; i++) {
                int dataSize = inputAxis[i];
                values[i] = readData(csvFile, csvDelimiter, columnToRead, dataSize);
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;


    }
    



        public static double[] readData(String csvFile, String csvDelimiter, int columnToRead, int dataSize) throws IOException {
        double[] values = new double[dataSize];
        String line;
        int currentIndex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip the first line
            while ((line = br.readLine()) != null && currentIndex < dataSize) {
                String[] data = line.split(csvDelimiter);
                if (data.length > columnToRead) {
                        int value = Integer.parseInt(data[columnToRead]);
                        values[currentIndex++] = (double) value;
                }
            }
        }   
        return values;
    }
    
}
