package questions;

//知识点：计算 ： (1-1) * 2 / 0; 会报错 ; 计算过程没有短路机制

public class Test02 {
    public static void main(String[] args) {
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i != 0)
             nums[i] = 1;
        }
        int b = 2;
        int c = 0;
        int res = (nums[1] - nums[2]) * b / nums[0];
        System.out.println(res);
    }
}
