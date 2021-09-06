package DataStructures.sort;

/*
    基数排序：不支持负数(负数处理有待整理)，数据上限低
    耗时：1s(8w数据)

    空间耗费多，在 8000w数据时，消耗3G内存
 */

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        /*int[] array2 = {221, 1, 45, 2, 56, 73, 4, 3};
        radixSort(array2);
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
        System.out.println("基数排序开始时时间：" + date1Str);

        radixSort(array2);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    /*基数排序。
    1. 原理：一个数字中，根据位数的增加，在比较时越要重点考虑。
    因为基数排序(放入桶中并取出后)后，之前处于 什么前后关系，排序之后 仍然不会变。
    所以，作为比较标准中的重要标准，要放在最后进行比较。
    如果将权重小的标准放在后面，就会打乱重要权重的排序结果。
    2. 步骤：找到最大数字，将其他数字前面补零(这个是概念上的补零)。从个位开始比较，将所有数字按照其个位
    放入桶中，遍历完毕，将桶中的数组转移到原数组中。再进行十位的排序。
     */
    public static void radixSort(int[] nums) {
        //求nums中最大数的 位数
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i])
                max = nums[i];
        }
        int maxLength = (max + "").length();

        //定义桶 数组[二维数组元素都是桶]。一维数组是 按照 元素每一位的数字 存储原数组元素的。
        // 其实的二维数组是存放数组中 那一位数字是一维数组的下标的所有情况。
        int[][] bucket = new int[10][nums.length];

        //记录桶数组 中每个桶[中有效数据个数。用于从桶中取出所有数据
        int[] bucketElementCounts = new int[10];


        /*从个位开始比较，将所有数字按照其个位放入桶中，遍历完毕，
        将桶数组中的所有有效元素转移到原数组中。再进行十位的排序。
         */
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < nums.length; j++) {
                int digitOfElement = (nums[j] / (int) Math.pow(10, i)) % 10;//从 个位到 最高位。
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = nums[j];
                bucketElementCounts[digitOfElement]++;//将每个桶中有效元素个数+1.便于后续取出
            }

            //将bucket中的数据 再转移到nums中
            int index = 0;//遍历 原数组nums 的索引变量
            for (int j = 0; j < 10; j++) {
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        nums[index++] = bucket[j][k];
                    }
                }
                bucketElementCounts[j] = 0;//取出当前桶中的所有元素后，置空桶。用于下一次排序循环
            }
            //直观查看每次基数排序后的效果
            // System.out.println(Arrays.toString(nums));
        }
    }
}
