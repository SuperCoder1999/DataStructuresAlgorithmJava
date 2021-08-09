package DataStructures.sort;

/*
    shellSort2()80000数据时间 1s 左右
    shellSort 80000数据时间 13s 左右

    希尔排序 也是插入排序。使插入排序的更高效的版本，也称为缩小增量排序。

    疑问为什么希尔排序 的分组长度，每次是变成两倍，而不是三倍
 */

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        /*int[] array2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(array2);
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
        System.out.println("冒泡排序开始时时间：" + date1Str);

        shellSort2(array2);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    //移位式希尔排序
    public static void shellSort2(int[] nums) {
        int temp = 0;
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < nums.length; i++) {
                temp = nums[i];//辅助变量
                int j = 0;//循环变量
                /*
                1.这里，j > -1+gap;也是用的让 j只循环到倒数第二次 的条件。
                2.这里如果将j赋值i-gap，即j从每个数组的需要插入的元素的前一个元素开始。
                其实从后移的步骤可以看出，用nums[j + 1] = nums[j];时，j=0时，下标为0的元素被后移，出循环时j-1；变为-1
                而 用nums[j] = nums[j - gap];时，j=1时，下标为0的元素被后移，出循环时j-1，变成了0；
                所以最终插入位置时，下标的写法不一样的。其主要原因在于使用的是 j+1还是 j-1
                则代码如下：
                        for (j = i - gap; j >= 0 && nums[j] > temp; j -= gap) {
                            nums[j + gap] = nums[j];
                        }
                        nums[j + gap] = temp;//找到的
                 */
                for (j = i; j > (-1 + gap) && temp < nums[j - gap]; j -= gap) {
                    nums[j] = nums[j - gap];
                }
                nums[j] = temp;
                /*for (j = i - gap; j >= 0 && nums[j] > temp; j -= gap) {
                    nums[j + gap] = nums[j];
                }
                nums[j + gap] = temp;*/
            }
        }
    }

    //交换式希尔排序
    public static void shellSort(int[] nums) {
        //第一层循环，是确定每次分组 的数组长度
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            /*1.说明，第二层循环：每一次分组后 从第一组的下一个元素往后遍历 每一个数字都是一个组中的数字，
            并且是第二个及以后的数字。将这个数字和所在组的前一个数字比较(j-=gap就是找到前一个数字)
            顺序错误就交换。在往后遍历的过程中每一个数字都是一个组中的数字，遍历完成，所有组的顺序就
            正确了。
            2. 这层循环对应插入排序的第一层for (int i = 1; i < len; i++)。
            i=1对于一个数组来说是从数组的第二个数字开始。对于每层数组来说，也是这样。
             */
            for (int i = gap; i < nums.length; i++) {
                /*1.说明：第三层相当于是插入排序的内层。这里和 传统插入排序的操作不一样，传统的
                是后移。
                2.这里和插入排序(移位法)有些不同，即这里j的起始值是从每个数组的需要插入的元素的前一个元素开始的。
                所以有j+gap。-------- 推荐这样的方法，较为渐变。
                如果是从第二个元素开始，则循环应该是：
                            for(int j=i;j>gap-1;j-=gap){
                             if (nums[j - gap] > nums[j])
                                swap(nums, j, j - gap);
                             }
                //判断语句：j不等于gap因为交换的两个数是j和j-gap，所以当j的顺序确定，j-gap的顺序也被确定了。
                所以循环时只要循环到j的倒数第二次情况，即j不会越界的上一次情况(不会越界的情况是:j>-1,上一次就是j>-1+gap)。
                当gap=1就变成了插入排序，满足条件。
                 */
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (nums[j + gap] < nums[j])
                        swap(nums, j, j + gap);
                }
                /*尝试另一种插入排序的循环判断
                for (int j = i; j > gap - 1; j -= gap) {
                    if (nums[j - gap] > nums[j])
                        swap(nums, j, j - gap);
                }*/
            }
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
