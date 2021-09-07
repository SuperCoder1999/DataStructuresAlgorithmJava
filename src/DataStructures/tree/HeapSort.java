package DataStructures.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
    堆排序：耗时：800w数据 4、5秒
 */

public class HeapSort {
    public static void main(String[] args) {
        /*//要求将数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9, 100, 11, 4, -9};
        heapSort(arr);
        System.out.println("排序后=" + Arrays.toString(arr));*/

        //计算 8w数据的耗时
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++)
            arr[i] = (int)(Math.random() * 800000) + 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前");
        System.out.println(date1Str);

        heapSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后");
        System.out.println(date2Str);
        //System.out.println("排序后=" + Arrays.toString(arr));
    }

    //通过将二叉树调整为大顶堆 然后 实现堆排序
    public static void heapSort(int arr[]) {
        //依次从底向上 调整二叉树
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustTree(arr, i, arr.length);
        }
        //调整完成后，树中的每一行都是比下面的数字大的

        //将树顶取出，即找到最大值。然后将剩余数组元素从树顶 调整一遍。这一遍中因为去掉了原本的树，所以第二行的两个元素中的一个元素是第三行的。
        //从树顶调整时，如果将第二行的数字提上去了，则将可能打乱之前大顶堆顺序。所以调用adjustTree。因为之前已经调整完毕，所以这次adjustTree
        //可以确保第二行又是最大值了。 ----- 解释确实不清楚。因为暂时想不出一个特殊情况，并进行验证。
        for (int i = arr.length - 1; i > 0; i--) {
            // 最大值是 arr[0]。将其搬到对后一个元素位置。这样只要调整传入length，即可对arr的剩余元素进行调整。
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustTree(arr, 0, i);
        }
    }

    /**
     * @param arr 需要调整的数组（二叉树）
     * @param i 从二叉树的第几个节点开始调整
     * @param length 调整数组中的哪些元素（因为在堆排序中，要将树顶元素(即数组中的第一个元素拿出来。所以树顶元素就不用再调整了。
     *               这时就创建出一个新的树，需要再次调整）
     */
    //将一个数组(二叉树)【子树】 调整为一个大顶堆
    public static void adjustTree(int arr[], int i, int length) {
        for (int j = 2 * i + 1; j < length; j = j * 2 + 1) {
            int temp = arr[i];//暂存arr[i]，用于之后的调换
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;//现在找到两个子叶子 中较大的
                //因为有外部循环，所以每一次都是完成一个个小的子树。因此只需要 在改动左节点或改动右节点递归情况下
                //调整对应侧 的子树就可以了
            }
            if (arr[i] < arr[j]) {
                arr[i] = arr[j];
                arr[j] = temp;
                i = j;// i 的作用是保留子树的根
            } else
                break;
        }
    }
}