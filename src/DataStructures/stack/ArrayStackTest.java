package DataStructures.stack;

/*
    用数组模拟栈
 */

import java.util.Scanner;

public class ArrayStackTest {
    public static void main(String[] args) {
        //测试 ArrayStack
        ArrayStack stack = new ArrayStack(4);
        System.out.println("是否为空 :" + stack.isEmpty());
        System.out.println("是否为满 :" + stack.isFull());

        String key = "";
        boolean loop = true;//在switch中break只能跳出switch。而while不能停止
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("输入数据：");
                    int value = scanner.nextInt();
                    try {
                        stack.push(value);
                    } catch (RuntimeException e){
                        System.out.println(e);
                }
                    break;
                case "pop":
                    try {
                       int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (RuntimeException e) {
                        System.out.println(e);
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//数组模拟的栈
class ArrayStack {
    private int maxSize;//栈的最大容量，也是用来创建数组大小的变量
    private int[] array;//存储数据的数组
    //栈中有两个指针，一个指向栈底(栈底是不动的)，另一个是指向栈顶刚。
    private int top = -1;
    /*栈底就是array[0]，栈顶是用变量表示的。起始值为-1，是为了存储数据
    时top指向最上面的元素。
     */

    //构造器，创建数组
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;//最有一个元素删除后，top 变为 -1
    }

    //入栈 - push
    public void push(int value) {
        if (isFull())
            throw new RuntimeException("栈满了");
        top++;
        /*先 +1 ，这样一旦有元素开始入栈，top就会一直指向 最上面的元素。
        也因为top指向最上面的元素，所以每次入栈前都要将top指向后一个空位置
        */
        array[top] = value;
    }

    //出栈 - pop，将栈顶的数据返回。并且在栈中也抹除掉
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈空");
        int value = array[top];//暂存栈顶的元素，因为要在返回array[top]前将top-1
        top--;//栈顶元素没抹除(概念上的抹除)实际array中还保存着。
        return value;
    }

    //显示栈的情况（遍历栈）。遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法list()");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, array[i]);
        }
    }
}