/*
 * NAME: Jonathan Tran
 * PID:  A15967290
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author TODO
 * @since  TODO
 */
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
        int i = 0;
        int j = 0;
        for (i = 1; i <= end; i++) {
            j = i;
            while (j > start && list.get(j).compareTo(list.get(j-1)) < 0) {
                T temp = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, temp);
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
        if (end < start) {
            return;
        }
//        int newStart = partition(list, start, end);
//        QuickSort(list, start, newStart-1);
//        QuickSort(list, newStart + 1, end);
        int index = partition(list, start, end);
        if (start < index - 1) {
            QuickSort(list, start, index - 1);
        }
        if (index < end) {
            QuickSort(list, index, end);
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
        int i = l, j = h;
        T tmp;
        T pivot = arr.get((l + h) / 2);
        while (i <= j) {
            while (arr.get(i).compareTo(pivot) < 0) {
                i++;
            }

            while (arr.get(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                tmp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }
//        // gets the middle point
//        int midpoint = l + (h - l) / MIDDLE_IDX;
//        // gets the pivot
//        T piv = arr.get(midpoint);
//        boolean isDone = false;
//        while (!isDone) {
//            // increases left index while piv is greater than arr.get(l)
//            while (arr.get(l).compareTo(piv) < 0) {
//                l++;
//            }
//            // decreases right index while piv is less than arr.get(h)
//            while (arr.get(h).compareTo(piv) > 0) {
//                h--;
//            }
//            // ends while loop if there are less than 2 elements
//            if (h < l) {
//                isDone = true;
//            }
//            else {
//                // create a temp variable to swap the two elements
//                T temp = arr.get(l);
//                arr.set(l, arr.get(h));
//                arr.set(h, temp);
//                // decrease right index and increase left index
//                h--;
//                l++;
//            }
//        }
//        return midpoint;


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
        if (end < start) {
            return;
        }
        if (list.size() <= cutoff) {
            InsertionSort(list, start, end);
        }
        int newStart = partition(list, start, end);
        QuickSort(list, start, newStart);
        QuickSort(list, newStart + 1, end);
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == true) {
                break;
            }
            swapped = false;

            for (int i = end; i > start; i--) {
                if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i - 1));
                    list.set(i - 1, temp);
                    swapped = true;
                }
            }
            start++;
        }
    }
}
