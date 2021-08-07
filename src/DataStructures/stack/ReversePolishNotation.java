package DataStructures.stack;

/*
    实现逆波兰表达式(后缀表达式)

    思路：
    1. 1+((2+3)×4)-5 => 1 2 3 + 4 × + 5 – 用String接收逆波兰表达式。
    2. 通过String.split方法分割表达式。同时创建一个用于存放数字的栈
    3. 依次遍历分割出来的String组 如果是数字直接入栈。如果是运算符，取出栈中的栈顶和次顶元素取出计算，得到的结果入栈。
    4. 重复操作，直到遍历结束，返回栈顶元素，即使最终结果。
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        //测试 stringToList()方法
        //System.out.println(stringToList("1+((2+3)*4)-5"));

        /*String expression = "1 2 3 + 4 * + 5 -";//1+((2+3)×4)-5
        try {
            System.out.println(cal(expression));
        } catch (Exception e) {
            System.out.println(e);
        }*/

        System.out.println(parseSuffixExpression("1+((2+3)*4)-5"));
        List list = parseSuffixExpression("1+((2+3)*4)-5");
        String expression = "";
        for (int i = 0; i < list.size(); i++) {
            expression +=  list.get(i);
            if (i != list.size() - 1)
                expression += " ";
        }
        System.out.println(expression);
        try {
            System.out.println(cal(expression));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 将中缀表达式转换成后缀表达式
    /*
        思路：
        1. 需要一个栈存储 运算符。一个队列存储 操作数(找不到何时的系统队列工具来存储，暂时用List代替)
        2. 从左向右遍历中缀表达式
            1. 遍历到操作数,直接入队列
            2. 遍历到运算符，分情况入栈：1. 栈空、栈顶操作符优先级低于入栈运算符时，直接入栈
                                        否则将栈顶运算符放入队列中。并再次执行判断。直到可以入栈
                                     2. 遇到栈顶是“（”，直接入栈
            3. 遍历到"("，直接入栈
            4. 遍历到")"，将栈顶操作符出栈放入队列，直至遇到"("，并将"("出栈。
     */
    public static List<String> parseSuffixExpression(String expression) {
        //定义List 返回最终的表达式
        List<String> stringArrayList = new ArrayList<String>();
        //定义栈存储操作符
        Stack<String> stringStack = new Stack<>();
        //先将expression 拆解，方便遍历
        List<String> list = stringToList(expression);

        for (String item : list) {//遍历 表达式  -- 这里许多if - else 可以合并。
            if (item.matches("\\d+"))//如果是数字，直接加入 list
                stringArrayList.add(item);
                //不是数字要分情况
            else if (stringStack.isEmpty())// 栈空先判断，防止 peek() 报错。
                stringStack.push(item);
            else if (item.equals("("))//遍历到"("，优先考虑"("，如果第一个
                stringStack.push(item);
            else if (stringStack.peek().equals("(")) //遇到栈顶是"("，直接入栈
                stringStack.push(item);
            else if (item.equals(")")) { //遍历到")",需要出栈
                while (!stringStack.peek().equals("("))
                    stringArrayList.add(stringStack.pop());
                stringStack.pop();//将"("也出栈
            } else {//这里是遇到运算符的情况
                //先进行出栈的操作，将所有优先级高的运算符都出栈
                while (!stringStack.isEmpty() && stringStack.peek() != "(" && priority(item) <= priority(stringStack.peek())) {
                    //isEmpty()是防止peek()报错; peek()!="("，是为了防止出现  ( * 而 item=+ 的情况
                    stringArrayList.add(stringStack.pop());
                }
                //高优先级的都出栈之后，将item入栈
                stringStack.push(item);
            }
        }
        //表达式遍历结束后，将 stringStack 中的剩余符号全部 放入stringArrayList中
        while (!stringStack.isEmpty()) {
            stringArrayList.add(stringStack.pop());
        }
        return stringArrayList;
    }

    public static int priority(String str) {
        //先初始化优先级
        final int ADD = 1;
        final int SUB = 1;
        final int MUL = 2;
        final int DIV = 2;

        switch (str) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                throw new RuntimeException("运算符错误");
        }
    }

    public static List<String> stringToList(String expression) {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        int index = 0;//用于遍历expression
        char ch = '0';//用于遍历取出的字符
        String str = "";//拼接多位数字
        while (index < expression.length()) {
            ch = expression.charAt(index);
            if (ch < '0' || ch > '9') {//是操作符
                stringArrayList.add(ch + "");//将字符转换成String存入List
                index++;
            } else {//是数字
                //先将 str 重置 ""
                str = "";
                //这种while 循环，是将当前位置的数字拼接。并没有判断下一个位置是什么。如果下个是运算符，则直接跳出while循环了。
                // 如果遍历到最后一个字符的后面，循环也会在执行操作前退出。
                while (index < expression.length() && expression.charAt(index) > '0' && expression.charAt(index) < '9') {
                    str += expression.charAt(index);
                    index++;
                }
                //while循环退出说明数字已经完全拼接完成
                stringArrayList.add(str);
            }
        }
        return stringArrayList;
    }

    //传入逆波兰表达式直接计算。
    public static int cal(String expression) {
        Stack<Integer> integerStack = new Stack<Integer>();//输入int类型时会自动装箱
        if (expression == null)//防止 expression.split()报错
            throw new RuntimeException("表达式不能为null");
        String[] split = expression.split(" ");//将后缀表达式分割。便于入栈

        //分割完成，进行逆波兰表达式的运算过程
        for (String item : split) {//从左向右遍历
            if (item.matches("\\d+"))//是数字直接入栈
                /*正则表达式的运用。\d+在正则表达式中表示数字(位数>=1)
                matches(String regex) 告知此字符串是否匹配给定的正则表达式。
                 */
                integerStack.push(Integer.parseInt(item));
            else {//不是数字而是符号就要进行取出栈顶和次顶元素进行计算
                int num1 = integerStack.pop();
                int num2 = integerStack.pop();
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;//注意顺序，因为正则表达式在计算时是从左到右遍历，所以num2是被减数
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;//和减法一样
                        break;
                    default:
                        throw new RuntimeException("输入逆波兰表达式错误");
                }
                integerStack.push(res);
            }
        }
        //遍历表达式结束，返回栈顶值
        return integerStack.pop();
    }
}