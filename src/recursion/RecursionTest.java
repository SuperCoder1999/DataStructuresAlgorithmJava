package recursion;

public class RecursionTest {

    public static void main(String[] args) {

        //通过打印问题，回顾递归调用机制
        test(4);

        //int res = factorial(3);
        //System.out.println("res=" + res);
    }

    /*打印问题.
        情况一：没有else。此时if运行结束 就会 输出。if的运行结束需要等到递归下去的函数return
        情况二：有else.此时if运行结束不会执行 输出。所以只会有一次输出机会，即递归出口时。
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } //else {
        System.out.println("n=" + n);
        // }
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3
        }
    }
}
