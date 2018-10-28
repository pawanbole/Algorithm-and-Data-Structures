import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        int[] arr = getInput();
        int n = arr.length;

        ArrayHelper.sortArray(arr, 0, arr.length - 1);
        ArrayHelper.minDifference(arr);
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
}

