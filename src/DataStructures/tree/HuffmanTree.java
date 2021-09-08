package DataStructures.tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        HuffmanNode root = creatHuffmanTree(arr);
        //先序遍历哈夫曼树
        root.preOrder();
        System.out.println(root);
    }

    public static HuffmanNode creatHuffmanTree(int[] arr) {
        //将传入的数组变成一个个节点，并存储在List中，方便排序等操作。
        List<HuffmanNode> huffmanNodeList = new ArrayList<HuffmanNode>();
        for (int i = 0; i < arr.length; i++) {
            HuffmanNode huffmanNode = new HuffmanNode(arr[i]);
            huffmanNodeList.add(huffmanNode);
        }
        //HuffmanNode huffmanParent = new HuffmanNode(0);
        // -----因为之后需要将parent添加到集合中。所以需要创建不同的parent，
        // 修改这个parent对象，集合中的parent也改变了，添加了也相当于没加。
        while (huffmanNodeList.size() > 1) {
            //将节点排序，以找到最小值和次小值
            Collections.sort(huffmanNodeList);//Collections是一个工具类
            //取出权值最小的节点（二叉树）
            HuffmanNode leftNode = huffmanNodeList.get(0);
            //取出权值次小的节点（二叉树）
            HuffmanNode rightNode = huffmanNodeList.get(1);
            //将权值较小的两个节点（二叉树）组成一个新的二叉树
            //huffmanParent.setValue(leftNode.getValue() + rightNode.getValue());
            HuffmanNode huffmanParent = new HuffmanNode(leftNode.getValue() + rightNode.getValue());
            huffmanParent.setLeft(leftNode);
            huffmanParent.setRight(rightNode);
            //将已经组成新树的节点（二叉树根节点）删除
            huffmanNodeList.remove(leftNode);
            huffmanNodeList.remove(rightNode);
            //将新树根节点添加到集合中。
            huffmanNodeList.add(huffmanParent);
            // 再次排序，删除两个最小节点而产生一个新树，直至剩下一个根节点
        }
        return huffmanNodeList.get(0);
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    private int value;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.value - node.value;//返回值是正的，则if语句执行调换，最终结果是 升序
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }

    public void preOrder() {
      /*  System.out.println(this);

        if (this.left != null)
            this.left.preOrder();

        if (this.right != null)
            this.right.preOrder();*/
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }
}