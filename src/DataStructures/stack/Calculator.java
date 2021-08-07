package DataStructures.stack;

/*
    制作计算器的步骤：
    1.有两个栈分别存放 数字 和 运算符
    2.对于输入的字符串 从头到尾先取数字，将数字入栈、运算符入栈、再将数字入栈、再将运算符入栈。
    3.运算符入栈的条件是：1.栈空；2.栈顶的运算符比入栈的运算符优先级低。
    4.如果优先级不高于栈顶 就将栈顶的符号取出 然后从取出数字栈中取两个数字，进行计算，计算结果入数字栈
    再次判断 入栈的 运算符 是否高于 栈顶的。
    5.直到字符串遍历完。再将运算符栈、数字栈中的剩余元素取出计算。最终得到结果

    优化：可以将expression 以数字和运算符依次存入List中，这样从左向右遍历方便。
 */

public class Calculator {
    public static void main(String[] args) {
        String expression = "70*2*2-5+1-500+3-4";
        //定义存放数字和运算符的栈
        MyStack numStack = new MyStack(10);
        MyStack operatorStack = new MyStack(10);


        //将字符串入栈
        int index = 0;
        while (true) {
            char ch = '0';
            ch = expression.charAt(index);
            if (isOperator(ch)) {//是运算符，要入栈
                while (true) {//防止 * * 后面 + 的情况，需要在检测依次后再检测
                    if (operatorStack.isEmpty()) {//栈空，就直接入栈
                        operatorStack.push(ch);
                        break;
                    } else if (priority((char) operatorStack.peek()) < priority(ch)) {//比较优先级
                        //这里优先级相同的也不能放在一起。因为，如果都入栈了，最终的计算则需要依次从左到右计算。最好用队列来解决
                        operatorStack.push(ch);//其实前两种情况可以合并在一起了。
                        break;
                    } else {//栈顶优先级比 入栈的运算符 高,取出栈顶运算符和两个数字栈的数字进行计算
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        char operator = (char) operatorStack.pop();
                        int res = cal(num1, num2, operator);
                        numStack.push(res);//将结果入栈
                    }
                }
            } else {//不是运算符，要进入 数字栈
                String values = "";
                values += ch;
                while (true) {// 防止 多位 数字
                    if (index == expression.length() - 1) {
                        break;
                    }
                    else {
                        if (!isOperator(expression.charAt(index + 1))) {
                            values += expression.charAt(index + 1);
                            index++;
                        } else
                            break;
                    }
                }
                numStack.push(Integer.parseInt(values));
            }
            index++;
            if (index == expression.length())
                break;
        }//将expression全部记入栈中，将剩余的逐个取出并计算
        while (!operatorStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char operator = (char) operatorStack.pop();
            int res = cal(num1, num2, operator);
            numStack.push(res);//将结果入栈
        }
        System.out.println(numStack.pop());
    }

    //计算 运算符栈顶 和 其他两个数字的结果
    public static int cal(int n1, int n2, char operator) {
        if (operator == '+')
            return n1 + n2;
        else if (operator == '-')
            return n2 - n1;
        else if (operator == '*')
            return n1 * n2;
        else
            return n2 / n1;
    }

    //比较运算符的优先级
    //根据返回值判断优先级高低，值越大优先级越高
    public static int priority (char ch) {
        int value = 0;
        if (ch == '+' || ch == '-')
            return 0;
        else if (ch == '*' || ch == '/')
            return 1;
        else
            return -1;//-1代表 其他情况。这个程序目前只考虑+-*/
    }

    //判断是否是 运算符
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

}

class MyStack {
    private int maxSize;
    private int[] array;
    private int top = -1;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //返回栈顶的值
    public int peek() {
        return array[top];
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
}