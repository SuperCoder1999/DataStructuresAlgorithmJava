package DataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
    选择排序

    1. 一般来说选择比冒泡效率高，因为只要交换一次，
    但是冒泡也可以只记录坐标然后做一次性变换，只是牺牲空间复杂度。
    2. 但是冒泡有个很大的优点就是它可以检测整个数组是否已经有序，
    当某次遍历没有发生任何交换的时候你就可以提前终止了
 */

public class SelectSort {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //创建一个80000个元素的数组
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] = (int)(Math.random() * 80000);//[0, 80000)
        }

        Date date1 = new Date();
        System.out.println("选择排序开始时间：" + simpleDateFormat.format(date1));
        selectSort(nums);
        //System.out.println(Arrays.toString(nums));
        Date date2 = new Date();
        System.out.println("选择排序结束时间:" + simpleDateFormat.format(date2));
    }

    //选择排序。
    public static void selectSort(int[] nums) {
        /*1. index是在 i 后面的最小数的下标。找到这个 i 后面的最小数后需要将 i 和这个最小值交换
          切记 要将这两个数字交换。
          2. 如果没有标记index而在if()判断后就交换，将会浪费许多功夫
         */
        int index = -1;
        int temp = 0;//辅助变量，用于交换元素
        for (int i = 0; i < nums.length - 1; i++) {
            /*
            1.外层是nums.length-1轮，因为找到前 nums.length-1个最小数，最后一个一定是最大值
            2.每一轮，都将i到nums.length-1中最小值 调换到i位置。
             */
            index = i;//假设最小值的下标是当前元素下标
            for (int j = i + 1; j < nums.length; j++) {
                /*
                 将假设最小值和 i之后的所有元素比较。如果找到更小的就记录其小标。直到后面的所有元素
                 都找了，然后将这个最小下标位置的元素和假设最小元素交换，实现将最小值 调换到最前面。
                 */
                if (nums[index] > nums[j])
                    index = j;
            }
            //将最小值对应下标的 元素 和 nums[i]交换
            //优化：如果index和i不一样才交换
            if (index != i) {
                temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
