package DataStructures.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static final int maxSize = 20;

    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr, 1234));// 0
    }

    // 创建一个斐波那契数列，用于之后找中点。
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] nums, int key) {
        /*斐波那契 查找也是二分查找的进阶。 */
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;// 中点，之后在斐波那契数列中找

        //将数组nums 变成斐波那契数列中 一个 数字的长度。
        int[] temp = null;//在不改变nums的情况下。对nums扩容
        //找到扩容的斐波那契数列 中的数字
        int[] fib = fib();//得到斐波那契数列
        int k = 0;
        while (high > fib[k] - 1) {//因为 nums的下标都是 -1的。所以对应 斐波那契数列中的目标数字也要 -1
            k++;
        }//当while循环结束后，high=fib[k]-1。代表：第k个斐波那契数列元素数字 大于等于nums数组长度。
        //将nums扩容，复制到temp中。
        temp = Arrays.copyOf(nums, fib[k]);
        //将temp中后面的 0 都赋值为nums的最后一个数字，保持temp的升序
        for (int i = nums.length; i < fib[k]; i++) {
            temp[i] = nums[nums.length - 1];
        }

        //完成准备工作，开始利用“二分”查找进行查找。
        while (low <= high) {
            mid = low + fib[k - 1] - 1;//斐波那契数列中的元素都应该-1.来对应nums中的查找方式
            if (temp[mid] < key) {
                low = mid + 1;
                k-=2;
                /*因为分割点是下标在fib[k-1]-1和fib[k-2]-1 中的fib[k-1]-1最后一个元素
                当该查找第二段时，第二段的长度是fib[k-2]，所以k 需要-2*/
            }
            else if (key < temp[mid]) {
                high = mid - 1;
                k--;//和上一种情况同理，这次该查找前一段 数组
            } else {
                /*因为mid是在斐波那契数列中找的下标。这个下标一定小于扩列后的数组长度。
                但是很可能大于nums原数组的长度。
                特别是当查找最后一个数字时，因为这时候high一直是nums.length-1。而mid可能因为斐波那契数列而超过nums.length-1
                 */
                return mid < high ? mid : high;
            }
        }
        return -1;
    }
}
