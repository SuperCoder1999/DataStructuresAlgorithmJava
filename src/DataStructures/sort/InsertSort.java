package DataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
    插入排序：

    难点： for (j = i; j > 0 && min < nums[j - 1]; j--) 内层循环的 判断语句

    排序的一个技巧：判断大小时，最好按下标写判断语句，即 nums[j] < nums[j+1]
 */

public class InsertSort {
    public static void main(String[] args) {
        int[] array2 = {2, 1, 4 ,2, 56, 7, 4, 3};
        insertSort(array2);
        System.out.println(Arrays.toString(array2));

        //创建8w随机数组，进行测试运行时间
        //创建数组
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        array2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array2[i] = (int)(Math.random() * 80000 + 1);//[1, 80001)
        }
        //记录开始时间
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("冒泡排序开始时时间：" + date1Str);

        insertSort(array2);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    /*
        方法一：从尾到头一个一个交换
        方法二：老师的，一个一个往后调。也是数据结构与算法书本上的方法。
        我想的：先找位置，再往后调（暂时没有实现）
     */
    public static void insertSort(int[] nums) {
        int j = 0;
        /*外层循环，就是将 1至nums.length-1的数据全部作为需要插入的数字。
         */
        for (int i = 1; i < nums.length; i++) {
            int min = nums[i];//保存要插入的数字
            /*
            1.内层循环：从第 i个元素开始往前遍历，因为凡是在前面的数据都是正确顺序的。
            所以，如果突然找到一个顺序符合的位置，说明已经找到目标位置(目标位置在该位置前面)，
            就可以退出循环了。
            否则，就要将前面的数据后移，为插入的数据腾地方。如果将下标为0的数据也后移了，说明
            目标位置就是0。
            2.这里会因为j=i或j=i-1;而产生不同的判断条件。尽量每次保持一致。
            3.这里就j>0是让j只循环到倒数第二次
             */
            for (j = i; j > 0 && nums[j - 1] > min; j--) {
                    nums[j] = nums[j - 1];
            }
            //这种优化本质上并没有提升效率
            if (j != i)
                nums[j] = min;
        }
    }
}




    /*public static void InsertSort(int[] arr)//------和课本相同
    {
        int i, j;
        int n = arr.Length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = 1; i < n; i++)
        {
            j = i;
            target = arr[i];

            while (j > 0 && target < arr[j - 1])
            {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = target;
        }
    }*/


    /*//插入排序  --- 老师的，比较时，从每个数组的需要插入的元素的前一个元素开始
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        //使用for循环来把代码简化
        for(int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1; // 即arr[1]的前面这个数的下标

            // 给insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到, insertIndex + 1
            // 举例：理解不了，我们一会 debug
            //这里我们判断是否需要赋值
            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            //System.out.println("第"+i+"轮插入");
            //System.out.println(Arrays.toString(arr));
        }*/



//下面三种使一样的。
/**
 *
 * Description: 插入排序Java代码版
 *
 * @Title: insertSort
 * @param a 待排序数组。
 *            void
 *//*
    public void insertSort(int[] a) { // 插入排序
        int len = a.length; // 获得数组的长度。即外循环次数。
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                // 从尾部开始交换位置，直到到达合适位置。 此时插入完成。
                exch(a, j, j - 1);  // 交换相邻的数字，实现插入。
            }
        }
    }*/


    /*public class Insert
    {
        public static void main(String[] args)
        {
            int[] ins = {2,3,5,1,23,6,78,34};
            int[] ins2 = sort(ins);
            for(int in: ins2){
                System.out.println(in);
            }
        }

        public static int[] sort(int[] ins){

            for(int i=1; i<ins.length; i++){
                for(int j=i; j>0; j--){
                    if(ins[j]<ins[j-1]){
                        int temp = ins[j-1];
                        ins[j-1] = ins[j];
                        ins[j] = temp;
                    }
                }
            }
            return ins;
        }
    }*/



    /*public class Insertion
    {
        public static void sort(Comparable[] a)
        {
            //将a[]按升序排列
            int N=a.length;
            for (int i=1;i<N;i++)
            {
                //将a[i]插入到a[i-1]，a[i-2]，a[i-3]……之中
                for(int j=i;j>0&&(a[j].compareTo(a[j-1])<0);j--)
                {
                    Comparable temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                }
            }
        }
    }*/