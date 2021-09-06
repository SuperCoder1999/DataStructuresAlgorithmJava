package DataStructures.sort;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
    快速排序：
    时间复杂度：n*log2(n)
    耗时：1s（8w数据）
 */

public class QuickSort {
    public static void main(String[] args) {
        /*int[] array2 = {2, 1, 4, 2, 56, 7, 4, 3};
        quickSort(array2,0, array2.length - 1);
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
        System.out.println("快速排序开始时时间：" + date1Str);

        quickSort(array2, 0, array2.length - 1);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) //说明分治的部分只有一个元素，就不用排序了。
            return;
        //依照枢纽元完成划分，并找到枢纽元应该在的位置
        int pivot = partition(nums, left, right);
        //以枢纽元作为分界点，左右部分进行分治
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        //枢纽元取的是左边第一个元素
        int pivot = nums[left];
        int l = left;//两个指针，实现双向扫描分区
        int r = right;

        /*将传入的数组范围，依照枢纽元进行划分。小于枢纽元的放在左边，大于的放在右边。
        当扫描完毕(即 l==r时)代表划分完成
         */
        while (l < r) {
            while (l < r && pivot <= nums[r])//找到右边小于pivot的元素
                r--;
            while (l < r && nums[l] <= pivot)//找到左边大于pivot的元素
                l++;

            //当不是因为划分完毕而同时停止寻找 l 和 r。说明两个元素需要交换
            if (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        /*因为找的过程中 l 和 r 一定会交集，而这个交集一定是找完左边和右边的情况下产生的。
        产生交集就退出循环。
          此时，l 或 r 的位置就是 pivot应该在的位置。 */
        int temp = nums[l];
        nums[l] = nums[left];
        nums[left] = temp;
        return l;
    }
}

//    public static void main(String[] args) {
//        int[] arr = {-9,78,23,23,23,23, 23,900, 4561};
//
//        /*//测试快排的执行速度
//        // 创建要给80000个的随机的数组
//        int[] arr = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
//        }*/
//
//        System.out.println("排序前");
//        Date data1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(data1);
//        System.out.println("排序前的时间是=" + date1Str);
//
//        quickSort(arr, 0, arr.length-1);
//
//        Date data2 = new Date();
//        String date2Str = simpleDateFormat.format(data2);
//        System.out.println("排序前的时间是=" + date2Str);
//        System.out.println("arr=" + Arrays.toString(arr));
//    }
//
//    public static void quickSort(int[] arr,int left, int right) {
//        int l = left; //左下标
//        int r = right; //右下标
//        //pivot 中轴值
//        int pivot = arr[(left + right) / 2];
//        int temp = 0; //临时变量，作为交换时使用
//        //while循环的目的是让比pivot 值小放到左边
//        //比pivot 值大放到右边
//        while( l < r) {
//            //在pivot的左边一直找,找到大于等于pivot值,才退出
//            while( arr[l] < pivot) {
//                l += 1;
//            }
//            //在pivot的右边一直找,找到小于等于pivot值,才退出
//            while(arr[r] > pivot) {
//                r -= 1;
//            }
//            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
//            //小于等于pivot值，右边全部是大于等于pivot值
//            if( l >= r) {//这里应该不会出现大于的情况
//                break;
//            }
//
//            //交换
//            temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//
//            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
//            if(arr[l] == pivot) {
//                r -= 1;
//            }
//            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
//            if(arr[r] == pivot) {
//                l += 1;
//            }
//        }
//
//        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
//        if (l == r) {
//            l += 1;
//            r -= 1;
//        }
//        //向左递归
//        if(left < r) {
//            quickSort(arr, left, r);
//        }
//        //向右递归
//        if(right > l) {
//            quickSort(arr, l, right);
//        }
//
//
//    }
//}
