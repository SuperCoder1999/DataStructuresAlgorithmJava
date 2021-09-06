package DataStructures.search;

/*
    插值查找

    问题：不知道为什么 不会遇到 left=right的情况。另外这个公式出自高数中拉格朗日中值定理那一章附近
        int mid = left + (right - left) * (value - nums[left]) / (nums[right] - nums[left]);
 */

public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

        int index = insertValueSearch(arr, 0, arr.length - 1, 123);
        //int index = binarySearch(arr, 0, arr.length, 1);
        System.out.println("index = " + index);

        //System.out.println(Arrays.toString(arr));
    }

    /*插值查找是二分查找的变形。插值查找每次从"自适应"mid处开始查找。而二分查找每一次都是折半。
    插值查找中自适应 mid = low+(value-a[low])/(a[high]-a[low])
    插值查找，名字的含义，就是依据 value的值，将分割点插入到数组中，再分段查找。重复操作。
     */
    public static int insertValueSearch(int[] nums, int left, int right, int value) {
        //由于每次分割点都是由value计算而来。如果value过大、或者过小(负数)，将导致分割点越界。
        if (value < nums[left] || value > nums[right] || left > right)
            return -1;
        System.out.println(left + " " + right);
        //不知道为什么 不会遇到 left=right的情况。另外这个公式出自高数中拉格朗日中值定理那一章附近
        int mid = left + (right - left) * (value - nums[left]) / (nums[right] - nums[left]);
        //之后的步骤和二分查找相同
        if (value < nums[mid])
            return insertValueSearch(nums, left, mid - 1, value);
        else if (nums[mid] < value)
            return insertValueSearch(nums, mid + 1, right, value);
        else
            return mid;
    }
}
