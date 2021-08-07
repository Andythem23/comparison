package com.group.mycompany.quicksort;

import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PruebaTiempo {

    private static <T extends Comparable<? super T>> int binarySearch(T a[], T x) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            int cmp = a[mid].compareTo(x);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static <T extends Comparable<? super T>> int linearSearch(T a[], T x) {
        int left;
        int length = a.length;
        int right = length - 1;
        int position = -1;

        // run loop from 0 to right
        for (left = 0; left <= right;) {

            // if search_element is found with left variable
            if (a[left].equals(x)) {
                position = left;
                break;
            }

            // if search_element is found with right variable
            if (a[right].equals(x)) {
                position = right;
                break;
            }
            left++;
            right--;
        }
        return position;
    }

    private static Double log(double num, int base) {
        return (Math.log10(num) / Math.log10(base));
    }

    public static void main(String[] args) {
        Random rd = new Random();
        System.out.format("%32s %30s %45s %n", "N", "Búsqueda binaria", "Búsqueda lineal");
        System.out.format("%42s %7s %17s %10s %10s %10s %15s %8s%n", "T(n)", "T/N", "T/(N log(N))", "T/N^2", "T(n)", "T/N", "T/(N log(N))", "T/N^2");
        long t1, t2;
        double fn, fnl, fn2;
        for (int k = 50000; k <= 1000000; k += 50000) {
            int arr[] = rd.ints(k, 10000, 1000001).toArray();
            Integer array[] = Arrays.stream(arr).boxed().toArray(Integer[]::new);
            Util.quicksort(array);
            t1 = System.nanoTime();
            for (int i = 0; i < k; i++) {
                binarySearch(array, array[rd.nextInt(array.length)]);
            }
            t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1);
            fn = (double) t2 / k;
            fnl = (double) t2 / (k * log(k, 2));
            fn2 = (double) t2 / (Math.pow(k, 2));
            System.out.format("%32d %7d %12.3e %13.3e %14.3e", k, t2, fn, fnl, fn2);
            t1 = System.nanoTime();
            for (int i = 0; i < k; i++) {
                linearSearch(array, array[rd.nextInt(array.length)]);
            }
            t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1);
            fn = (double) t2 / k;
            fnl = (double) t2 / (k * log(k, 2));
            fn2 = (double) t2 / (Math.pow(k, 2));
            System.out.format("%8d %12.3e %13.3e %14.3e %n", t2, fn, fnl, fn2);
        }

    }
}
/*
CPU:AMD Ryzen 3400g
RAM:16 GB
Sistema Operativo: Windows 11
Versión java: java version "16.0.2" 2021-07-20
 */
