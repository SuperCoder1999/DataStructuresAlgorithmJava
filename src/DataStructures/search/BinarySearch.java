package DataStructures.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
       /* int[] array2 = {2, 1, 4 ,2, 56, 7, 4, 3};
        int index = binarySearch(array2, 4);
        System.out.println(index);*/

        int arr[] = {1, 1, 1, 1, 8, 10, 89,1000,1000, 1234, 12345};
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1);
        System.out.println("resIndexList=" + resIndexList);
    }

    //迭代 的方式 实现二分查找
    public static int binarySearch(int[] nums, int value) {
        int left = 0;
        int right = nums.length;
        int mid = 0;

        /*最后一次传入的情况是 left=right，此时mid=left=right。value只和一个数字比较
        找到了就返回mid。如果没找到就说明不在数组内。由于每次迭代都会将mid+或-1。
    所以在最后一次的下一次递归中，left 和 right 交叉开了。
         */
        while (left <= right) {
            mid = (left + right) / 2;
            if (value < nums[mid])
                right = mid - 1;
            else if (nums[mid] < value)
                left = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    /*
    1.递归出口：递归的最后一次 应该是 left=right，此时mid=left=right。value只和一个数字比较。
    找到了就返回mid。没找到就说明没有。由于每次递归都会将mid+或-1。
    所以在最后一次的下一次递归中，left 和 right 交叉开了。
    2.递归返回情况：递归的返回值就是上一层递归的返回值。最里层的递归出口有两个：
        1)找到最后遇到nums[mid]=value,返回mid。这个内层函数的返回值mid也会变成
        外层函数的返回值。
        2)当范围缩小到1个元素(即最内层递归)还有没找到。下一次递归就会触发(left>right)，表明找不到value
     */
    public static int binarySearchRecursion(int[] nums, int left, int right, int value) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        if (nums[mid] < value)
            return binarySearchRecursion(nums, mid + 1,right, value);
        else if (value < nums[mid])
            return binarySearchRecursion(nums, left, mid - 1, value);
        else
            return -1;
    }

    /*二分查找，找到所有 等于要找值的 元素下标。
    1.原理：因为二分查找的数组都是有序的。所以找到一个后，说明相等的就在这个元素的前后。
    只要将这个元素前后遍历即可。
     */
    public static List<Integer> binarySearch2(int[] nums, int left, int right, int value) {
        ArrayList<Integer> list = new ArrayList<>();//存储下标的 集合
        if (left > right)
            return null;//一个元素也没找到，返回空的 List
        int mid = (left + right) / 2;
        if (value < nums[mid])
            return binarySearch2(nums, left, mid - 1, value);
        else if (nums[mid] < value)
            return binarySearch2(nums, mid + 1, right, value);
        else {
            /*找到了一个元素(下标为mid)，而前后可能还有相等的元素。遍历前后元素，
            将相等的值存入list中。遍历到数组两端、或发现有不相等的，说明所有相等
            的元素均已找到。
             */
            int l = mid;//往左边遍历，顺便将mid添加到list中
            int r = mid + 1;//往右边遍历
            while (l >= 0 && nums[l] == value) {
                list.add(l);
                l--;
            }
            while (r < nums.length && nums[r] == value) {
                list.add(r);
                r++;
            }
            return list;
        }
    }
}
