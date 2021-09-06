package DataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    归并排序算法。
    时间复杂度：

    时间：1s（8w数据）
 */

public class MergeSort {
    public static void main(String[] args) {
        /*int[] array2 = {2, 1, 4 ,2, 56, 7, 4, 3};
        mergeSort(array2, 0, array2.length - 1, new int[array2.length]);
        System.out.println(Arrays.toString(array2));*/

        //创建8w随机数组，进行测试运行时间
        //创建数组
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] array2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array2[i] = (int)(Math.random() * 80000 + 1);//[1, 80001)
        }
        //记录开始时间
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("归并排序开始时时间：" + date1Str);

        mergeSort(array2, 0, array2.length - 1, new int[array2.length]);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    public static void mergeSort(int[] nums, int left, int right, int[] temp) {
        /*1.归并排序：将原数组逐步划分，直至划分到子数组有1个元素，将每次划分后的两子数组进行合并。
        由于先进行划分操作的递归，所以直至每个子数组只1两个元素，才会退出递归。
        因为最小子数组为两个元素，而这两个元素还会再分成两个只有一个元素的子数组。只是两个子数组并不会有任何操作而返回。
        最内层的循环结束后，将两个只有一个元素的数组进行合并。形成一个两个元素的有序数组。
        以此类推在逐步返回递归的过程中，每一个子数组中的元素都是有序的。所以对于每个子数组还原为上一层的数组的操作都是
        将两个子数组进行合并。这就是递归的原因。
        2.归并排序的结束标志：按顺序归并的数组元素为两个，即right-left=1。
        此时进一步划分效果是：左边一个右边一个元素。这两边划分出来的子数组并不需要进行合并，当然
        也没必要进行划分，所以可以退出递归。
         */
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);

            /*//直观展现每次合并的是哪些子数组
            System.out.println(Arrays.toString(nums));
            System.out.println(left + " " + right);*/
        }
    }

    /*归并排序中 将两个子数组 合并的操作。
    1. 因为每次由递归返回时子数组都是 有序的状态。所以只需要将返回的两个子数组合并，即可将目前的子数组变成
    有序状态。
     */
    public static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int l = left;//指向当前子数组中左边子数组的第一个元素
        int m = mid + 1;//指向当前子数组中右边子数组的第一个元素。
        int t = left;
        /*将两个数组合并时暂存数据的数组 的第一个下标。
        1.这里的辅助数组，每次存储的是当前子数组合并后的有序数据。
        当当前子数组的两个子数组合并完成，需要将辅助数组中的当前子数组的数据转移到
        当前子数组中。 */

        /*以下代码就是将两个有序子数组合并为一个有序数组的操作*/
        while (l <= mid && m <= right) {
            if (nums[l] < nums[m]) {
                temp[t] = nums[l];
                t++;
                l++;
            } else {
                temp[t] = nums[m];
                t++;
                m++;
            }
        }
        while (l <= mid) {//当l>mid 才是将左边子数组提取完
            temp[t] = nums[l];
            l++;
            t++;
        }
        while (m <= right) {
            temp[t] = nums[m];
            t++;
            m++;
        }

        /*当当前子数组的两个子数组合并完成，需要将辅助数组中的当前子数组的数据转移到
        当前子数组中。 */
        t = left;
        l = left;
        while (l <= right) {
            nums[l] = temp[t];
            t++;
            l++;
        }
    }
}
