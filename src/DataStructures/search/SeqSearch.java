package DataStructures.search;

import java.util.ArrayList;
import java.util.List;

/*
    顺序查找
 */

public class SeqSearch {
    public static void main(String[] args) {
        int[] array2 = {2, 1, 4 ,2, 56, 7, 4, 3};
        int index = seqSearch(array2, 4);
        System.out.println(index);

        //验证查找多个相同数值
        int[] nums = {2, 1, 4 ,2, 56, 7, 4, 3};
        List list = seqSearchs(nums,2);
        System.out.println(list);
    }

    /*顺序查找，找到一个就返回下标所引，找不到返回-1(因为-1不在索引内)
    如果找到多个
     */
    public static int seqSearch(int[] nums, int value) {
        for (int i = 0; i < nums.length; i++) {
            if (value == nums[i])
                return i;
        }
        return -1;
    }

    /*找多个数据如下；如果采用数组进行返回，则需要遍历两个，第一次用于求数组的长度
    创建数组后，第二次遍历存放数值*/
    public static List<Integer> seqSearchs(int[] nums, int value) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (value == nums[i])
                list.add(i);
        }
        return list;
    }
}