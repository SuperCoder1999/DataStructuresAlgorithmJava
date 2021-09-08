package DataStructures;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //转码的原内容
        String content = "i like like like java do you like a java";
        //统计内容中每个字符出现次数。因为需要记录字符及出现次数两个数据，所以采用map
        byte[] bytes = content.getBytes();//将String 拆解为单个字符
        ArrayList<HuffmanNode> huffmanNodeArrayList = getHuffmanArrayList(bytes);
        HuffmanNode root = createHuffmanTree(huffmanNodeArrayList);
        root.preOrder();

       /* getHuffmanString(root, "", stringBuilder);
        System.out.println(huffmanString);

        byte[] zip = zip(bytes, huffmanString);
        System.out.println(Arrays.toString(zip));*/

        byte[] zipBytes = huffmanZip(bytes);
        System.out.println(Arrays.toString(zipBytes));

        byte[] decodeBytes = decode(getHuffmanString(root), zipBytes);
        System.out.println(new String(decodeBytes));
    }

    //解码
    public static byte[] decode(Map<Byte, String> huffmanString, byte[] zipBytes) {
        //将zipBytes中的字节数根据赫夫曼码转换为一个个字节
        //1.先将byte[]类型拼接成String
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zipBytes.length; i++) {
            String str = byteToString((i == zipBytes.length - 1), zipBytes[i]);
            stringBuilder.append(str);
        }
        System.out.println("stirng" + stringBuilder);

        //2.在stringBuilder中根据赫夫曼码，还原原数据 对应的byte[]
        ArrayList<Byte> bytes = new ArrayList<>();
        Map<String, Byte> decodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanString.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0; i < stringBuilder.length(); ) {
            boolean flag = true;
            int count = 1;
            while (flag) {
                String temp = stringBuilder.substring(i, i + count);
                if (decodeMap.get(temp) == null) {
                    count++;
                } else {
                    bytes.add(decodeMap.get(temp));
                    flag = false;
                }
            }
            i = i + count;
        }
        byte[] resBytes = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            resBytes[i] = bytes.get(i);
        }
        return resBytes;
    }

    //将一个个byte变成String，返回后拼接起来。
    public static String byteToString(boolean flag, byte b) {
        int temp = b;
        if (!flag)
            temp |= 256;//256 - 1 0000 0000 .是为了转换String后还有8位。因为转换的时候前面的0会省略
        String s = Integer.toBinaryString(temp);
        if (!flag)//如果不是最后一位。因为最后一位在变为byte时并没有强制取八位，说明前面的0都省略了（当然其数值也不会超过256）
            return s.substring(s.length() - 8);
        else
            return s;
    }

    //包装所有步骤，调用一次实现压缩
    public static byte[] huffmanZip(byte[] bytes) {
        ArrayList<HuffmanNode> huffmanNodeArrayList = getHuffmanArrayList(bytes);

        HuffmanNode root = createHuffmanTree(huffmanNodeArrayList);

        Map<Byte, String> map = getHuffmanString(root);
        byte[] zip = zip(bytes, map);
        return zip;
    }


    public static byte[] zip(byte[] bytes, Map<Byte, String> HuffmanString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(HuffmanString.get(b));
        }
        //System.out.println(stringBuilder.toString());
        //将stringBuilder中的每8个字符变成一个字符。即二进制转10进制,以缩小空间
        byte[] zipBytes = new byte[(stringBuilder.length() + 7) / 8];//length() + 7 恰好包含所有字符
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte = null;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);//一直截到最后
            } else
                strByte = stringBuilder.substring(i, i + 8);
            zipBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return zipBytes;
    }


    public static Map<Byte, String> getHuffmanString(HuffmanNode node) {
        Map<Byte, String> huffmanString = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        if (node == null)
            return null;
        else {
            getHuffmanString(node.left, "0", stringBuilder, huffmanString);
            getHuffmanString(node.right, "1", stringBuilder, huffmanString);
        }
        return huffmanString;
    }


    //因为之后每次都需要传入一个stringBuilder所以，在外部创建一个StringBuilder为了第一次可以传入StringBuilder。这个StringBuilder
    //并没有改变。因为每次都创建一个新的StringBuilder，然后在新的String上添加，而上次的String并没有改变
    public static void getHuffmanString(HuffmanNode node, String direction,
                                        StringBuilder stringBuilder, Map<Byte, String> huffmanString) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //这个构造器复制stringBuilder内容到一个新的StringBuilder对象中
        stringBuilder2.append(direction);
        //遍历左右字节点，直到遇到叶子
        if (node != null) { //只遍历到叶子节点
            if (node.data == null) { //当前不是叶子节点就向左右遍历，将路径添加到stringBuilder2中
                getHuffmanString(node.left, "0", stringBuilder2, huffmanString);
                getHuffmanString(node.right, "1", stringBuilder2, huffmanString);
            } else //遇到叶子节点,此时stringBuilder2已经拼接好了。将k-y添加到map即可。
                huffmanString.put(node.data, stringBuilder2.toString());
        }
    }


    public static ArrayList<HuffmanNode> getHuffmanArrayList(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            //搜索map中是否已经存在正在添加的字符
            Integer existence = map.get(b);//HashMap中的get(Key)返回的是Value
            if (existence == null) {//说明正在添加的字符没有被添加过
                map.put(b, 1);
            } else
                map.put(b, existence + 1);//存在后，在value基础上增加一次
        }
        //每一个数据和其权值都准备好。可以创建一个List 节点集合。方便构建赫夫曼树
        ArrayList<HuffmanNode> huffmanNodeArrayList = new ArrayList<HuffmanNode>();
        //将map中的k-y都取出来。以entry的形式
        Set<Map.Entry<Byte, Integer>> entrySet = map.entrySet();// --- 集合那一节需要改进
        for (Map.Entry<Byte, Integer> entry : entrySet) {
            huffmanNodeArrayList.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        return huffmanNodeArrayList;
        Byte
    }


    public static HuffmanNode createHuffmanTree(ArrayList<HuffmanNode> huffmanNodeArrayList) {
        //构建赫夫曼树
        while (huffmanNodeArrayList.size() > 1) {
            //排序
            Collections.sort(huffmanNodeArrayList);
            //选择两个最小的节点(二叉树树根)
            HuffmanNode leftNode = huffmanNodeArrayList.get(0);
            HuffmanNode rightNode = huffmanNodeArrayList.get(1);
            HuffmanNode parentNode = new HuffmanNode(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            huffmanNodeArrayList.remove(leftNode);
            huffmanNodeArrayList.remove(rightNode);
            huffmanNodeArrayList.add(parentNode);
        }
        return huffmanNodeArrayList.get(0);
    }
}


class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data;//视频中类型是 Byte
    int weight;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
}