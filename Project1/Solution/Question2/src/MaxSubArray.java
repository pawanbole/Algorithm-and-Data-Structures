import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaxSubArray {
    public static void main(String[] args) throws IOException {
        int[] arr = getInput();
        int x = getParameter();
        int res = maxSubArray(arr, x);
        if(res > -1)
        System.out.println("Max subarray length is : " + res);
        else{
            System.out.println("No max subarray for the given input");
        }
        try {
            writeToFile(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getParameter() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Enter median value");
        return kbd.nextInt();
    }

    private static int maxSubArray(int[] arr, int x) {

        sortArray(arr, 0, arr.length - 1);
        return findSubArrayLength(arr, x);
    }

    private static int findSubArrayLength(int[] arr, int x) {
        int res = 0, left = 0, right = 0;
        int index = findIndex(arr, x); 
        left = index - 0;
        right = (arr.length - 1) - index;

        if(index > 0) {
            if (arr[index] == x) {
                if (left <= right) {
                    return arr.length;
                } else {
                    return ((right * 2) + 1);
                }
            } else {
                if (index > 0) {
                    if (((arr[index - 1] + arr[index]) / 2) < x) {
                        if (left <= right) {
                            return arr.length;
                        } else {
                            return ((right * 2) + 1);
                        }
                    } else {
                        if (left <= right) {
                            return arr.length;
                        } else {
                            return ((right * 2) + 2);
                        }
                    }

                } else {
                    return arr.length;
                }
            }
        }
        return -1;
    }

    public static int findIndex(int arr[], int x) {    //returns the first occurance of the data or the first highest element
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            } else if (arr[i] > x) {
                return i;
            }
        }
        return -1;
    }


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

    public static int[] getInput() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Enter array separated with ',' ");
        String s1 = kbd.next();
        String[] a = s1.split(",");
        int[] arr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }
        return arr;
    }

    private static void writeToFile(int res) throws IOException {
        File outputFile = new File("Output2.txt");
        FileWriter fw = null;
        String output = ("Max subarray length is : " + res);
        try {
            fw = new FileWriter(outputFile);
            fw.write(output);

        } catch (Exception e) {
            System.out.println("Exception occurred");
        } finally {
            fw.close();
        }

    }
}
