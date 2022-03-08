/*
 * NAME: Jonathan Tran
 * PID:  A15967290
 */
import java.util.ArrayList;

public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        // creating pointers
        int i = 0;
        int j;
        // start at the second element since first can not be
        // compared to any elements before
        for (i = 1; i <= end; i++) {
            // j is set to i and will be moved around to
            // compare it to the element at index i
            j = i;
            // keeps iterating as long as the elem at j is not the first elem
            // and the elem to the left of the elem at j is greater than the
            // elem at j
            while (j > start && list.get(j).compareTo(list.get(j-1)) < 0) {
                // swap the values
                T temp = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, temp);
                // decremented to check the values to the left
                j--;
            }
        }
    }

        /**
         * This method performs merge sort on the input arraylist
         *
         * @param list The arraylist we want to sort
         * @param start The inital index on subsection of Arraylist we want to sort
         * @param end The final index of the subsection of Arraylist we want to sort
         */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        // finds the new index after partitioning
        int newIndex = partition(list, start, end);
        if (end < start) {
            return;
        }
        // sort again if the start is less than (newIndex - 1)
        if (start < (newIndex - 1)) {
            QuickSort(list, start, newIndex - 1);
        }
        // sort again if the end is greater than newIndex
        if (newIndex < end) {
            QuickSort(list, newIndex, end);
        }
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        // gets the middle point
        int midpoint = l + (h - l) / MIDDLE_IDX;
        // i is used to indicate the left index
        int i = l;
        // j is used to indicate the right index
        int j = h;
        // creates a temporary variable that will be used to swap elements
        T temp;
        // gets the pivot
        T piv = arr.get(midpoint);
        // while the left index is not past the right index
        while (i <= j) {
            // while the element is less than the pivot, increase left index
            while (arr.get(i).compareTo(piv) < 0) {
                i++;
            }
            //while the element is greater than the pivot, decrease right index
            while (arr.get(j).compareTo(piv) > 0) {
                j--;
            }
            // if the left index has not passed the right index
            if (i <= j) {
                // temp variable used to store the element
                temp = arr.get(i);
                // swap the elements
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                // increase left index and decrease right index
                i++;
                j--;
            }
        }
        return i;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        // finds the new index after partitioning
        int newIndex = partition(list, start, end);
        if (end < start) {
            return;
        }
        // if the size is less than or equal to the cutoff, switch
        // to InsertionSort
        if (list.size() <= cutoff) {
            InsertionSort(list, start, end);
        }
        // sort again if the start is less than (newIndex - 1)
        if (start < (newIndex - 1)) {
            QuickSort(list, start, newIndex - 1);
        }
        // sort again if the end is greater than newIndex
        if (newIndex < end) {
            QuickSort(list, newIndex, end);
        }
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        // used to check when to stop sorting
        boolean swapped = true;
        // stops sorting when there is nothing left to sort -> swapped = false
        while (swapped) {
            // no swaps have happened yet
            swapped = false;
            // goes through elements starting from the beginning
            for (int i = start; i < end; i++) {
                //if the elem at index i is greater than the elem to its right
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    // swap the values
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    // since the values are swapped, swapped will become true
                    swapped = true;
                }
            }
            // finish the program if no more swaps are made
            if (swapped == false) {
                break;
            }
            // switch back to false to check if backward pass has any swaps
            swapped = false;
            // goes through elements starting from the back
            for (int i = end; i > start; i--) {
                //if the elem at index i is less than the elem to its left
                if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                    // swap the values
                    T temp = list.get(i);
                    list.set(i, list.get(i - 1));
                    list.set(i - 1, temp);
                    // since the values are swapped, swapped will become true
                    swapped = true;
                }
            }
            // moves on to next element
            start++;
        }
    }
}
