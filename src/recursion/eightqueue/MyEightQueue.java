package recursion.eightqueue;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

public class MyEightQueue {

    //定义一个max表示 共有多少个皇后
    static final int MAX = 8;
    static int countSuccess = 0;//记录所有成功的放置位置
    static int judgeCount = 0;//记录所有判断冲突的次数
    /*定义数组 pos ，保存皇后放置位置的结果。pos[0]=0 代表第 0+1 行的皇后放在 0+1位置。
    数组元素范围 最好是 [0, 7] 因为这样 在判断”冲突“时就方便
     */
    static int[] pos = new int[MAX];//为了静态方法能调用，就用static修饰了。static修饰不影响使用

    public static void main(String[] args) {
        check(0);
        System.out.println("所有成功的放置方法" + countSuccess);
        System.out.printf("一共判断冲突的次数%d次", judgeCount);
    }

    //放置 第 n行皇后
    @SuppressWarnings({"all"})
    public static void check(int n) {
        //当放置完第7+1行后，该第8+1行了。到了这一行说明前8行找到了。即找到一种情况
        if (n == MAX) {
            print();//打印前8行放置的位置
            return;//递归结束
        }
        //没有 放置完成，则在该n行，进行for循环 尝试 [0,7]位置的可能性
        for (int i = 0; i < MAX; i++) {
            //先假设可以成功
            pos[n] = i;
            //进行判断是否冲突，如果成功进行下一行的放置
            if (judge(n)) {
                check(n + 1);
            }
            //如果和之前的皇后冲突。for循环继续执行，尝试位置: i++
        }
        /*如果for循环中都不可能，就【回溯】return 上一层函数(即上一行),即check(n)
        在check(n)中会再次循环，尝试位置: i++
         */

        //当回溯到第1行的MAX-1位置，就会终止这个check()程序返回main函数
    }

    //判断 第 n 行是否和前 n-1行皇后 有冲突
    @SuppressWarnings({"all"})
    public static boolean judge(int n) {//传入行数 n，就可以查看到 0-n行的所有皇后位置
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /* pos[n] == pos[i] 表示 前n-1行种有元素和第n行 在同一列。
               Math.abs(n - i) == Math.abs(pos[n] - pos[i]：表示前n-1行种有元素和第n行 在同一对角线
               因为数组元素范围 是 [0, 7]。所以两元素 行之间的差 刚好等于 列之间的差
             */
            if (pos[n] == pos[i] || Math.abs(n - i) == Math.abs(pos[n] - pos[i]))
                return false;
        }
        return true;
    }

    //定义方法，展示每一组结果
    public static void print() {
        countSuccess++;
        for (int i = 0; i < MAX; i++) {
            System.out.print(pos[i] + " ");
        }
        System.out.println();
    }
}
