package DataStructures.tree;

import com.sun.xml.internal.ws.message.stream.StreamHeader;

public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        Node03 root = new Node03(1, "tom");
        Node03 node2 = new Node03(3, "jack");
        Node03 node3 = new Node03(6, "smith");
        Node03 node4 = new Node03(8, "mary");
        Node03 node5 = new Node03(10, "king");
        Node03 node6 = new Node03(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.left=node2;
        root.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes(root);

        //测试: 以10号节点测试
        Node03 leftNode = node5.left;
        Node03 rightNode = node5.right;
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1

        threadedBinaryTree.infixOrder();

        threadedBinaryTree.threadedInfixOrder();
    }
}

class ThreadedBinaryTree {
    Node03 root = null;
    public ThreadedBinaryTree(Node03 root) {
        this.root = root;
    }

    //为了实现线索化，需要保留当前节点的前驱节点
    Node03 pre = null;

    //对二叉树进行中序线索化
    public void threadedNodes(Node03 node) {
        if (node.left != null) {
            threadedNodes(node.left);
        }
        //对当前节点进行线索化
        //处理当前节点的前驱节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        if (node.right != null) {
            threadedNodes(node.right);
        }
    }

    //中序遍历线索二叉树
    public void threadedInfixOrder() {
        Node03 curNode = root;
        while (curNode != null) {
            while (curNode.leftType == 0)
                curNode = curNode.left;
            System.out.println(curNode);
            while (curNode.rightType == 1) {
                curNode = curNode.right;
                System.out.println(curNode);
            }
            curNode = curNode.right;
        }
    }

    public void infixOrder() {
        root.infixOrder();
    }
}

class Node03 {
    public int no;
    public String name;
    public Node03 left;
    public Node03 right;
    public int leftType;
    public int rightType;

    public Node03(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //中序遍历线索二叉树
    public void infixOrder() {
        if (this.left != null && this.leftType != 1)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null && this.rightType != 1)
            this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "Node03{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}