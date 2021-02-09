package com.starriddle.starter.java.algorithm.other;

import java.util.Arrays;
import java.util.Random;

/**
 * 十大经典排序算法
 *
 * 冒泡排序（Bubble Sort）
 * 选择排序（Selection Sort）
 * 插入排序（Insertion Sort）
 * 希尔排序（Shell Sort）
 * 归并排序（Merge Sort）
 * 快速排序（Quick Sort）
 * 堆排序（Heap Sort）
 *
 * 计数排序（Counting Sort）
 * 桶排序（Bucket Sort）
 * 基数排序（Radix Sort）
 *
 * @author CYL
 * @date 2019-07-31
 */
public class Sort {

    /**
     * 重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
     * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。<p/>
     *
     * - 比较相邻的元素。如果第一个比第二个大，就交换它们两个；<br>
     * - 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；<br>
     * - 针对所有的元素重复以上的步骤，除了最后一个；<br>
     * - 重复步骤1~3，直到排序完成。<p/>
     *
     * - 最佳情况：T(n) = O(n) <br>
     * - 最差情况：T(n) = O(n²) <br>
     * - 平均情况：T(n) = O(n²)
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。<p/>
     *
     * - 初始状态：无序区为R[1..n]，有序区为空；<br>
     * - 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
     *   该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
     *   使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；<br>
     * - n-1趟结束，数组有序化了。<p/>
     *
     * - 最佳情况：T(n) = O(n²) <br>
     * - 最差情况：T(n) = O(n²) <br>
     * - 平均情况：T(n) = O(n²)
     *
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int index;
        int temp;
        for (int i = 0; i < array.length; i++) {
            index = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
        return array;
    }

    /**
     * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。<p/>
     *
     * - 从第一个元素开始，该元素可以认为已经被排序；<br>
     * - 取出下一个元素，在已经排序的元素序列中从后向前扫描；<br>
     * - 如果该元素（已排序）大于新元素，将该元素移到下一位置；<br>
     * - 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；<br>
     * - 将新元素插入到该位置后；<br>
     * - 重复步骤2~5。<p/>
     *
     * - 最佳情况：T(n) = O(n) <br>
     * - 最坏情况：T(n) = O(n²) <br>
     * - 平均情况：T(n) = O(n²)
     *
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int current;
        int index;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i+1];
            for (index = i; index >= 0; index--) {
                if (current >= array[index]) {
                    break;
                }
                array[index+1] = array[index];
            }
            array[index+1] = current;
        }
        return array;
    }

    public static int[] shellSort(int[] array) {
        int current;
        int index;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                current = array[i];
                for (index = i - gap; index >= 0; index -= gap ) {
                    if (current >= array[index]) {
                        break;
                    }
                    array[index + gap] = array[index];
                }
                array[index + gap] = current;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[50];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        long start = System.currentTimeMillis();
        // 冒泡排序
//        array = bubbleSort(array);
        // 选择排序
//        array = selectionSort(array);
        // 插入排序
//        array = insertionSort(array);
        // 希尔排序
        array = shellSort(array);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        System.out.println("Duration Time: " + (end - start));
    }

}
