package DataStructures.tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        Node root = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
		System.out.println("前序遍历"); // 1,2,3,5,4
		binaryTree.preOrder();

        //测试
		System.out.println("中序遍历");
		binaryTree.infixOrder(); // 2,1,5,3,4

		System.out.println("后序遍历");
		binaryTree.postOrder(); // 2,5,4,3,1

/*//        前序遍历
//        前序遍历的次数 ：4
		System.out.println("前序遍历方式~~~");
		Node resNode = binaryTree.preOrderSearch(5);
		if (resNode != null) {
			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
		} else {
			System.out.printf("没有找到 no = %d 的英雄\n", 5);
		}*/

/*//        中序遍历查找
//        中序遍历3次
		System.out.println("中序遍历方式~~~");
        Node resNode = binaryTree.infixOrderSearch(5);
		if (resNode != null) {
			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
		} else {
			System.out.printf("没有找到 no = %d 的英雄\n", 5);
		}*/

//        后序遍历查找
//        后序遍历查找的次数2/4次 -- 关于查找次数，我认为是进入search函数的次数。而进入的次数就可以将计数器放在第一句输出
		System.out.println("后序遍历方式~~~");
        Node resNode = binaryTree.postOrderSearch(5);
		if (resNode != null) {
			System.out.printf("找到了，信息为 no=%d name=%s\n", resNode.getNo(), resNode.getName());
		} else {
			System.out.printf("没有找到 no = %d 的英雄\n", 5);
		}

		//检验 删除节点
        binaryTree.delNode(5);
		binaryTree.preOrder();//1 2 3 4
    }
}

/**
 * 管理节点 的二叉树。
 */
class BinaryTree {
    //向一个链表一样，需要一个头节点 - root
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root != null)
            root.preOrder();
        else
            System.out.println("二叉树为空");
    }
    //中序遍历
    public void infixOrder() {
        if (root != null)
            root.infixOrder();
        else
            System.out.println("二叉树为空");
    }
    //后序遍历
    public void postOrder() {
        if (root != null)
            root.postOrder();
        else
            System.out.println("二叉树为空");
    }

    //前序遍历查找
    public Node preOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.preOrderSearch(no);
    }
    //中序遍历查找
    public Node infixOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.infixOrderSearch(no);
    }
    //后序遍历查找
    public Node postOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.postOrderSearch(no);
    }

    //递归删除节点
    public void delNode(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        if (root.getNo() == no) {
            root = null;
            return;
        }
        root.delNode(no);
    }
}

/**
 * 二叉树中的节点。这里才是二叉树操作的真正执行处
 */
class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;
    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
    //中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }
    //后续遍历
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    //前序遍历查找
    public Node preOrderSearch(int no) {
        System.out.println("找寻依次-----  ——------- ");
        Node resNode = null;
        if (this.no == no)
            return this;
        if (this.left != null)
            resNode = this.left.preOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.preOrderSearch(no);
        return resNode;
    }
    //中序遍历查找
    public Node infixOrderSearch(int no) {
        System.out.println("找寻依次-----  ——------- ");
        Node resNode = null;
        if (this.left != null)
            resNode = this.left.infixOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.no == no)
            return this;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(no);
        return resNode;
    }
    //后序遍历查找
    public Node postOrderSearch(int no) {
        System.out.println("找寻依次-----  ——------- ");
        Node resNode = null;
        if (this.left != null)
            resNode = this.left.infixOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.no == no)
            return this;
        return resNode;
    }

    //递归删除节点
    /*如删除的节点是叶子节点，则删除该节点，如果非叶子节点，则删除该子树
     */
    public void delNode(int no) {
        //因为删除节点需要用到被删除节点的前一个节点。
        //根节点在每一次进入遍历根节点的子节点前都被检验过。
        if (this.left != null && this.left.no == no)
            this.left = null;
        if (this.right != null && this.right.no == no)
            this.right = null;
        //左右节点都被检验后，需要遍历左右节点下的左右节点。依次递归
        if (this.left != null)
            this.left.delNode(no);
        if (this.right != null)
            this.right.delNode(no);
    }
}