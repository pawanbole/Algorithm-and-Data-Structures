import java.io.*;
import java.rmi.server.ExportException;

public class ArrayHelper {

    public static void sortArray(int[] arr, int left, int right) {

        // I have used dual pivot quick sort as it is more efficient than single pivot quick sort.
        // We can use insertion sort in case the number of elements are less than 48.
        // This approach is similar to the implementaion of Array sort function for primitives in Java

        if (right > left) {
            // Choose outermost elements as pivots
            if (arr[left] > arr[right]) swap(arr, left, right);
            int p = arr[left], q = arr[right];

            // Partition A according to invariant below
            int l = left + 1, g = right - 1, k = l;
            while (k <= g) {
                if (arr[k] < p) {
                    swap(arr, k, l);
                    ++l;
                } else if (arr[k] >= q) {
                    while (arr[g] > q && k < g) --g;
                    swap(arr, k, g);
                    --g;
                    if (arr[k] < p) {
                        swap(arr, k, l);
                        ++l;
                    }
                }
                ++k;
            }
            --l;
            ++g;

            // Swap pivots to final place
            swap(arr, left, l);
            swap(arr, right, g);

            // Recursively sort partitions
            sortArray(arr, left, l - 1);
            sortArray(arr, l + 1, g - 1);
            sortArray(arr, g + 1, right);
        }
    }

    static void swap(int[] A, int i, int j) {
        final int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }


    public static void minDifference(int[] arr) {

        try {
            if (arr.length > 2) {
                int fMin = Integer.MAX_VALUE, sMin = Integer.MAX_VALUE;


                for (int i = 0; i < arr.length - 1; i++) {
                    if ((arr[i + 1] - arr[i]) < fMin) {
                        fMin = (arr[i + 1] - arr[i]);
                    } else if ((arr[i + 1] - arr[i]) < sMin && (arr[i + 1] - arr[i]) != fMin) {
                        sMin = (arr[i + 1] - arr[i]);
                    }
                }
                writeToFile(arr, fMin, sMin);
            } else {
                writeToFile(arr, 0, 0);
            }
        } catch (IOException e) {
            System.out.println("IO Exception occured");
        }

    }

    private static void writeToFile(int[] arr, int fMin, int sMin) throws IOException {
        File outputFile = new File("Output1.txt");
        FileWriter fw = null;
        String output = printOutput(arr, fMin, sMin);
        System.out.println("Output : Sorted array is - " + output);
        try {
            fw = new FileWriter(outputFile);
            fw.write(output);

        } catch (Exception e) {
            System.out.println("Exception occurred while creating the file");
        } finally {
            fw.close();
        }

    }

    public static String printOutput(int arr[], int fMin, int sMin) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) {
                sb.append(arr[i] + ",");
            } else {
                sb.append(arr[i] + "] ");
            }
        }
        sb.append("  First minimum is " + fMin);
        sb.append("  Second minimum is " + sMin);
        return sb.toString();
    }
}
