package DataStructures.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //转码的原内容

       /* //String content = "i like like like java do you like a java";
        String content = "1100101001010101010000000000000000000011111111111100000000000101010101000000000000111000000000000" +
                "1000000000000010101010101111111111111010100000000000000000";
        //统计内容中每个字符出现次数。因为需要记录字符及出现次数两个数据，所以采用map
        byte[] bytes = content.getBytes();//将String 拆解为单个字符
        ArrayList<HuffmanNode> huffmanNodeArrayList = getHuffmanArrayList(bytes);
        HuffmanNode root = createHuffmanTree(huffmanNodeArrayList);
        root.preOrder();*/

       /* getHuffmanString(root, "", stringBuilder);
        System.out.println(huffmanString);

        byte[] zip = zip(bytes, huffmanString);
        System.out.println(Arrays.toString(zip));*/
/*
        byte[] zipBytes = huffmanZip(bytes);
        //System.out.println(Arrays.toString(zipBytes));

        byte[] decodeBytes = decode(getHuffmanString(root), zipBytes);
        System.out.println(new String(decodeBytes));*/


        //测试压缩文件
		String srcFile = "e://03.png";
		String dstFile = "e://03.zip";//小文件越压缩越大

		zipFile(srcFile, dstFile);
		System.out.println("压缩文件ok~~");

        String srcZipFile = "e://03.zip";
        String unzipFile = "e://03.png";
        unzipFile(srcZipFile, unzipFile);
        System.out.println("解压成功");

        //test();
    }

    public static void test() {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        byte[] zipByte = huffmanZip(bytes);
        //将byte数组和字典存入文件
        String srcFile = "e://srcFile.zip";
        //创建输出流
        ObjectOutputStream objectOutputStream = null;
        OutputStream outputStream = null;
        try {

            outputStream = new FileOutputStream(srcFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(zipByte);
            System.out.println("压缩后：" + Arrays.toString(zipByte));
            objectOutputStream.writeObject(huffmanMap);
            System.out.println(huffmanMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //解压
        String zipFile = "e://srcFile.zip";
        String dstFile = "e://unZipFile.txt";
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            System.out.println(Arrays.toString(huffmanBytes));
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            System.out.println(huffmanCodes);
            //解码
            byte[] unzipBytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(unzipBytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }


    //解压文件
    public static void unzipFile(/*String srcZipFile, String unzipFile*/String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            System.out.println(huffmanBytes.length);
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            System.out.println(huffmanCodes.size());
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }

        /*InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(srcZipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            byte[] bytes = (byte[])objectInputStream.readObject();
            Map<Byte, String> stringMap = (Map<Byte, String>)objectInputStream.readObject();
            byte[] decodeBytes = decode(stringMap, bytes);
            outputStream = new FileOutputStream(unzipFile);
            outputStream.write(decodeBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectInputStream.close();
                outputStream.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }*/
    }

    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            System.out.println(huffmanBytes.length);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanMap);
            System.out.println(huffmanMap.size());


        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }

    }

    /*//将一个文件进行压缩
    public static void zipFile(String srcFile, String desFile) {
        //创建输入流
        InputStream inputStream = null;
        //创建输出流
        ObjectOutputStream objectOutputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            ArrayList arrayList = getHuffmanArrayList(bytes);
            HuffmanNode root = createHuffmanTree(arrayList);
            Map stringMap = getHuffmanString(root);
            byte[] zipByte = huffmanZip(bytes);

            outputStream = new FileOutputStream(desFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(zipByte);
            objectOutputStream.writeObject(stringMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                inputStream.close();
                objectOutputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }*/

    public static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //递增的取出 key 1
                if (i + count > stringBuilder.length()) {
                    System.out.println("找不到");
                }
                String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null) {//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            System.out.print(b + " ");
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }
    /*//解码
    public static byte[] decode(Map<Byte, String> huffmanString, byte[] zipBytes) {
        //将zipBytes中的字节数根据赫夫曼码转换为一个个字节
        //1.先将byte[]类型拼接成String
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zipBytes.length; i++) {
            String str = byteToString((i == zipBytes.length - 1), zipBytes[i]);
            stringBuilder.append(str);
        }

        //2.在stringBuilder中根据赫夫曼码，还原原数据 对应的byte[]
        ArrayList<Byte> bytes = new ArrayList<>();
        Map<String, Byte> decodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanString.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0; i < stringBuilder.length(); ) {
            boolean flag = true;
            int count = 1;
            Byte b = null;
            while (flag) {

                if (i + count > stringBuilder.length()) {
                    System.out.println("没找到");
                }
                String temp = stringBuilder.substring(i, i + count);
                b = decodeMap.get(temp);
                if (b == null) {
                    count++;
                } else {

                    flag = false;
                }
            }
            bytes.add(b);
            i = i + count;
        }
        byte[] resBytes = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            resBytes[i] = bytes.get(i);
        }
        return resBytes;
    }*/

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

        huffmanMap = getHuffmanString(root);
        byte[] zip = zip(bytes, huffmanMap);
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

    static Map<Byte, String> huffmanMap = null;
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