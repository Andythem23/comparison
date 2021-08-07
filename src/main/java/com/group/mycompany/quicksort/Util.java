package com.group.mycompany.quicksort;

public class Util {

    final static int CUTOFF = 27;

    public static <T extends Comparable<? super T>> void quicksort(T[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void quicksort(T[] a, int low, int high) {
        if (low + CUTOFF > high) {
            insertionSort(a, low, high);
        } else {
            int middle = (low + high) / 2;
            if (a[middle].compareTo(a[low]) < 0) {
                swapReferences(a, low, middle);
            }
            if (a[high].compareTo(a[low]) < 0) {
                swapReferences(a, low, high);
            }
            if (a[high].compareTo(a[middle]) < 0) {
                swapReferences(a, middle, high);
            }
            swapReferences(a, middle, high - 1);
            T pivote = a[high - 1];
            int i, j;
            for (i = low, j = high - 1;;) {
                while (a[++i].compareTo(pivote) < 0);
                while (pivote.compareTo(a[--j]) < 0);
                if (i >= j) {
                    break;
                }
                swapReferences(a, i, j);
            }
            swapReferences(a, i, high - 1);
            quicksort(a, low, i - 1);
            quicksort(a, i + 1, high);
        }
    }

    private static <T extends Comparable<? super T>> void swapReferences(T a[], int x, int y) {
        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private static <T extends Comparable<? super T>> void insertionSort(T a[], int low, int high) {
        for (int p = low + 1; p <= high; p++) {
            T tmp = a[p];
            int j = p;
            for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

}
