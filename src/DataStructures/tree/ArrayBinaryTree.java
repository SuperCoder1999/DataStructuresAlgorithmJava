package DataStructures.tree;

public class ArrayBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        BinaryTree02 binaryTree02 = new BinaryTree02(arr);
        //先序遍历
        binaryTree02.preOrder(0);
        //中序遍历
        binaryTree02.infixOrder(0);
        //后序遍历
        binaryTree02.postOrder(0);
    }
}

class BinaryTree02 {
    //二叉树用数组存储起来了。
    public int[] arr;
    //这里用数组作为每个节点。是简化版。

    public BinaryTree02(int[] arr) {
        this.arr = arr;
    }

    //用前序遍历 表示出 数组所存储的二叉树
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，二叉树为空");
            return;
        }
        //根节点先表示出来
        System.out.println(arr[index]);
        //向左遍历递归
        if ((2 * index + 1) < arr.length)
            preOrder(2 * index + 1);
        //向右遍历递归
        if ((2 * index + 2) < arr.length)
            preOrder(2 * index + 2);
    }
    //中序遍历 表示 数组所存储的二叉树
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，二叉树为空");
            return;
        }
        //向左遍历递归
        if ((2 * index + 1) < arr.length)
            infixOrder(2 * index + 1);
        //输出根节点
        System.out.println(arr[index]);
        //向右遍历递归
        if ((2 * index + 2) < arr.length)
            infixOrder(2 * index + 2);
    }
    //后序遍历 表示数组所存储的二叉树
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，二叉树为空");
            return;
        }
        //向左遍历递归
        if ((2 * index + 1) < arr.length)
            postOrder(2 * index + 1);
        //向右遍历递归
        if ((2 * index + 2) < arr.length)
            postOrder(2 * index + 2);
        //输出根节点
        System.out.println(arr[index]);
    }
}
