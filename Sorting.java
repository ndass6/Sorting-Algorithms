import java.util.Comparator;
import java.util.Random;
import java.util.ArrayDeque;

/**
 * Various sorting methods
 *
 * @author Nathan Dass
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubblesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array or use null as the comparator.");
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array or use null as the comparator.");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j], arr[j - 1]) < 0) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    /**
     * Implement shell sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log(n))
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void shellsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array or use null as the comparator.");
        }
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                T temp = arr[i];
                int j = i;
                while (j >= gap && comparator.compare(arr[j - gap], temp) > 0) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap = gap / 2;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quicksort(T[] arr, Comparator<T> comparator,
            Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array, use null as the comparator, or use "
                + "null to select the pivots.");
        }
        quicksortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * Helper method for quick sort
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param lower the lower bound of the current area of interest in arr
     * @param upper the upper bound of the current area of interest in arr
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    private static <T> void quicksortHelper(T[] arr, int lower,
            int upper, Comparator<T> comparator, Random rand) {
        if (lower < upper) {
            int pos = partition(arr, lower, upper, comparator, rand);
            if (lower < pos - 1) {
                quicksortHelper(arr, lower, pos - 1, comparator, rand);
            }
            if (pos < upper) {
                quicksortHelper(arr, pos, upper, comparator, rand);
            }
        }
    }

    /**
     * Helper method for partitioning the array for quick sort
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param lower the lower bound of the current area of interest in arr
     * @param upper the upper bound of the current area of interest in arr
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the position of the partition in the array
     */
    private static <T> int partition(T[] arr, int lower,
            int upper, Comparator<T> comparator, Random rand) {
        int pos = rand.nextInt(upper - lower) + lower;
        T val = arr[pos];
        T temp = val;
        int i = lower;
        int j = upper;
        while (i <= j) {
            while (comparator.compare(arr[i], val) < 0) {
                i++;
            }
            while (comparator.compare(arr[j], val) > 0) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array or use null as the comparator.");
        }
        T[] temp = (T[]) new Object[arr.length];
        mergesortHelper(arr, temp, 0, arr.length - 1, comparator);
    }

    /**
     * Helper method for merge sort
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param temp the temporary array used to store the partially sorted array
     * @param lower the lower bound of the current area of interest in arr
     * @param upper the upper bound of the current area of interest in arr
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void mergesortHelper(T[] arr,
            T[] temp, int lower, int upper, Comparator<T> comparator) {
        if (lower < upper) {
            int mid = (lower + upper) / 2;
            mergesortHelper(arr, temp, lower, mid, comparator);
            mergesortHelper(arr, temp, mid + 1, upper, comparator);
            merge(arr, temp, lower, upper, comparator);
        }
    }

    /**
     * Helper method for quick sort
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param temp the temporary array used to store the partially sorted array
     * @param lowerStart the lower bound of the first part of the array that is
     *        being merged
     * @param upperEnd the upper bound of the second part of the array that is
     *        being merged
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void merge(T[] arr, T[] temp,
            int lowerStart, int upperEnd, Comparator<T> comparator) {
        int upperStart = (lowerStart + upperEnd) / 2 + 1;
        int lowerEnd = upperStart - 1;
        int pos = lowerStart;
        int total = upperEnd - lowerStart + 1;
        while (lowerStart <= lowerEnd && upperStart <= upperEnd) {
            if (comparator.compare(arr[lowerStart], arr[upperStart]) <= 0) {
                temp[pos++] = arr[lowerStart++];
            } else {
                temp[pos++] = arr[upperStart++];
            }
        }
        while (lowerStart <= lowerEnd) {
            temp[pos++] = arr[lowerStart++];
        }
        while (upperStart <= upperEnd) {
            temp[pos++] = arr[upperStart++];
        }
        for (int i = 0; i < total; i++, upperEnd--) {
            arr[upperEnd] = temp[upperEnd];
        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * You may use an ArrayList or LinkedList if you wish,
     * but it may only be used inside radixsort and any radix sort helpers
     * Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixsort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException(
                "Can't sort a null array or use null as the comparator.");
        }
        ArrayDeque<Integer>[] buckets = new ArrayDeque[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayDeque<Integer>(arr.length);
        }
        long place = 1L;
        long mod = 10L;
        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = false;
            for (int i = 0; i < arr.length; i++) {
                int bucket = (int) ((arr[i] % mod) / place) + 9;
                buckets[bucket].addLast(arr[i]);
                if (arr[i] >= mod) {
                    keepGoing = true;
                }
            }
            int j = 0;
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    while (buckets[i].size() > 0) {
                        arr[j++] = buckets[i].removeFirst();
                    }
                }
            }
            mod *= 10;
            place *= 10;
        }
        return arr;
    }
}