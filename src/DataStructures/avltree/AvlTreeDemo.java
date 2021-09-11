package DataStructures.avltree;
/*
    平衡二叉排序树
 */

public class AvlTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
        //int[] arr = { 10, 11, 7, 6, 8, 9 };//混合旋转 左边情况
        int[] arr = {10, 7, 20, 19, 18, 21};
        AvlBinarySortTree avlBinarySortTree = new AvlBinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            avlBinarySortTree.addNode(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlBinarySortTree.infixOrder();
        System.out.println("----");
        System.out.println(avlBinarySortTree.root.height());
        System.out.println(avlBinarySortTree.root.leftHeight());
        System.out.println(avlBinarySortTree.root.rightHeight());
    }
}

class AvlBinarySortTree {
    public Node root = null;

    //删除根节点中右节点上最小节点
    public int delRightTreeMin(Node node) {
        Node targetNode = node;
        while (targetNode.left != null)
            targetNode = targetNode.left;
        delNode(targetNode.value);
        /*这里删除最小节点可以说是和删除任意节点一样的操作
        1.当最小节点上只有一个分支(这个分支必定是右分支)，则属于删除只有一个分支的情况
        2.当最小节点是叶子节点，就属于叶子节点的情况
         */
        return targetNode.value;
    }
    // 27-23 20-20 24-24 16-25 40-36

    //删除根节点中左分支上最大节点
    public int delLeftTreeMax(Node node) {
        Node targetNode = node;
        while (targetNode.right != null)
            targetNode = targetNode.right;
        delNode(targetNode.value);
        /*和删除左分支的分析一样，最终情况要么是只有左分支。要么是叶子节点 */
        return targetNode.value;
    }

    //按照数值删除二叉排序树中的节点
    public void delNode(int value) {
        if (root == null) {
            System.out.println("二叉排序树为空");
            return;
        }
        Node node = search(value);
        if (node == null) {
            System.out.println("no find node");
            return;
        }
        Node parentNode = searchParent(value);
        /*if (parentNode == null) { --- 在这里考虑太早了。万一删除的就是根节点，其他节点保留呢
            //前面已经排除找不到的情况。现在找到了，但是找不到其父节点，说明node就是root
            System.out.println("找不到parent");
            root = null;
            return;
        }*/
        //当删除的节点是叶子节点
        if (node.left == null && node.right == null) {
            if (parentNode == null) {//说明只有根节点一个节点
                root = null;
                return;
            }
            if (parentNode.left != null && parentNode.left.value == value)
                parentNode.left = null;
            if (parentNode.right != null && parentNode.right.value == value)
                parentNode.right = null;
        } else if (node.left != null && node.right != null) {
            //先排除掉有两个节点的，因为一个节点的情况不太好划分
            /*int minVal = delRightTreeMin(node.right);
            node.value = minVal;*/
            int maxVal = delLeftTreeMax(node.left);
            node.value = maxVal;
        } else {
            if (parentNode != null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    if (node.left != null)
                        parentNode.left = node.left;
                    else
                        parentNode.left = node.right;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    if (node.left != null)
                        parentNode.right = node.left;
                    else
                        parentNode.right = node.right;
                }
            } else if (node.left != null)//这时node就是root
                root = node.left;
            else
                root = node.right;
        }
    }

    //按照数值查找节点
    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    //按照数值查找节点的父节点
    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    //向二叉排序树添加节点
    public void addNode(Node node) {
        if (root == null)
            root = node;
        else
            root.addNode(node);
        //原本是在Node类中进行调整，我认为应该在树中进行调整
        adjust();
    }

    public void adjust() {
        if (root.leftHeight() - root.rightHeight() > 1) {
            if (root.left != null && root.left.rightHeight() > root.left.leftHeight()) {
                leftRotate(root.left);
            }
            rightRotate(root);
            return;
        }
        if (root.rightHeight() - root.leftHeight() > 1) {
            if (root.right != null && root.right.leftHeight() > root.right.rightHeight()){
                rightRotate(root.right);
            }
            leftRotate(root);
            return;
        }
    }

    public void leftRotate(Node root) {
        Node newNode = new Node(root.value);
        newNode.left = root.left;
        newNode.right = root.right.left;
        root.value = root.right.value;
        root.right = root.right.right;
        root.left = newNode;
    }

    public void rightRotate(Node root) {
        Node newNode = new Node(root.value);
        newNode.right = root.right;
        newNode.left = root.left.right;
        root.value = root.left.value;
        root.left = root.left.left;
        root.right = newNode;
    }

    //中序遍历二叉排序树
    public void infixOrder() {
        if (root == null)
            System.out.println("二叉排序树为空___");
        else
            root.infixOrder();
    }
}



class Node { // 哪个调用了这个类中的函数，则成员变量就是属于哪个节点的。如：root.add(node)，其中left\right都是root的
    public int value;
    public Node left;
    public Node right;

    public int leftHeight() {
        if (left == null)
            return 0;
        return left.height();
    }

    public int rightHeight() {
        if (right == null)
            return 0;
        return right.height();
    }

    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height() , this.right == null ? 0 : this.right.height()) + 1;
    }

    //按照数值查找节点的父节点 ---- 应该可以用一个方法 返回数值所在节点和其父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value))
            return this;
        else {
            if (this.left != null && value < this.value)
                return this.left.searchParent(value);
            else if (this.right != null && this.value < value)
                return this.right.searchParent(value);
            else return null;
        }
    }

    //按照数值查找节点
    public Node search(int value) {
        if (this.value == value)
            return this;
        else if (value <= this.value) {
            if (this.left != null)
                return this.left.search(value);
            else
                return null;
        } else {
            if (this.right != null)
                return this.right.search(value);
            else
                return null;
        }
    }

    //向二叉排序树中添加节点
    public void addNode(Node node) {
        if (node.value <= this.value) {
            if (this.left == null)
                this.left = node;
            else
                this.left.addNode(node);
        } else {
            if (this.right == null)
                this.right = node;
            else
                this.right.addNode(node);
        }
    }

    //中序遍历二叉排序树
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this.value);
        if (this.right != null)
            this.right.infixOrder();
    }

    public Node(int value) {
        this.value = value;
    }
    /*
        少年不识愁滋味，为赋新词强说愁。
        一个人的苦几人能懂？
        有些愁本不算大
        但无人懂就好苦
        所谓，哑巴吃黄连用来形容最苦，就因为说不出
        说不出的苦有个名字
        叫孤独
        人总是会孤独的，所以 人 爱上层楼 仅与夕阳为舞
     */

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}