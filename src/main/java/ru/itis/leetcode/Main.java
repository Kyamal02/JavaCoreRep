package ru.itis.leetcode;


import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;


public class Main {
    public static void main(String[] args) {


//        List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry", "sdf", "sdfsdfsdf", "Apple");
//
//        // Основной поток запускает этот код
//        list.parallelStream()
//                .forEach(s -> {
//                    // Параллельный поток выполняет этот код
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    String time = LocalTime.now().toString(); // Получаем текущее время
//                    System.out.println(Thread.currentThread().getName() + ": " + s + " at " + time);
//                });
//        String time = LocalTime.now().toString();
//        System.out.println("привет" + Thread.currentThread().getName() + time);

//        List<String> list = new ArrayList<>();
//        list.add("asdas");
//        list.add("asda");
//
//
//        Iterable<String> iterable = list;
//
//        iterable.spliterator().forEachRemaining(System.out::println);
//        Iterator<String> iterator= iterable.iterator();
        new Main().run();


    }


    void run() {

        //System.out.println(maxProfit(new int[]{3, 2, 6, 5, 0, 3}));
        int[] testArray = new int[]{3, 8, 2, 5, 1, 4};
        partOfSortLomuto(testArray, 0, testArray.length - 1);

        for (int i = 0; i < testArray.length; i++) {
            System.out.print(testArray[i] + " ");
        }

    }


    public int mySqrt(int x) {

        return (int) Math.sqrt(x);
    }

    public int maxProfit(int[] prices) {

        return 0;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 | j == i) {
                    row.add(j, 1);
                } else if (i > 1 && j > 0) {
                    int sum = resultList.get(i - 1).get(j - 1) + resultList.get(i - 1).get(j);
                    row.add(j, sum);
                }
            }
            resultList.add(row);
        }
        return resultList;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];


        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }


    // Сортировка пузырьком (O(n^2))
    private int[] bubbleSort(int[] nums) {
        int length = nums.length;
        while (length != 0) {
            for (int i = 1; i < length; i++) {
                if (nums[i] < nums[i - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                }
            }
            length -= 1;
        }
        return nums;
    }

    // Сортировка выбором (O(n^2)) присваиваний в неотсортированном массиве будет меньше
    private int[] selectionSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[j - 1]) {
                    minIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }

    //Сортировка вставками O(n^2) сравнений в неотсортированном массиве будет меньше
    private int[] insertionSort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int sortedIndex = i - 1;
            while (sortedIndex != -1 && nums[sortedIndex + 1] < nums[sortedIndex]) {
                int temp = nums[sortedIndex];
                nums[sortedIndex] = nums[sortedIndex + 1];
                nums[sortedIndex + 1] = temp;
                sortedIndex--;
            }
        }
        return nums;
    }

    ///////////////////////
// nlog(n) в среднем случае и O(N^2) в худшем
    private void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int pivot = nums[(left + right) / 2];
        int l = left;
        int r = right;
        while (l <= r) {
            while (nums[l] < pivot) {
                l++;
            }
            while (nums[r] > pivot) {
                r--;
            }
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        quickSort(nums, left, r);
        quickSort(nums, l, right);
    }


    private void partOfSortLomuto(int[] nums, int start, int end) {
        if (start >= end) return;
        int left = start;
        int pivot = nums[end];

        for (int current = start; current < end; current++) {
            if (nums[current] <= pivot) {
                int temp = nums[current];
                nums[current] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
        int temp = nums[left];
        nums[left] = nums[end];
        nums[end] = temp;


        partOfSortLomuto(nums, start, left - 1);
        partOfSortLomuto(nums, left + 1, end);
    }
}


