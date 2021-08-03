package DataStructures.sparsearray;

/*
    稀疏数组的实现：

    疑问：fori 和 foreach 哪个效率更高？答案：两者对程序的影响很小。程序的效率主要看程序设计。
 */

import java.io.*;

public class SparseArray {
    //将稀疏数组存入磁盘
    public static void saveToFile(int[][] sparseArray) throws IOException {
        File file = new File("e:/sparseArray.data");
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file);
        for (int i = 0; i < sparseArray.length; i++) {
            fileWriter.write(sparseArray[i][0] + "\t" +
                    sparseArray[i][1] + "\t" + sparseArray[i][2]);
            fileWriter.write(System.getProperty("line.separator"));// 获取当前系统换行所需要的符号 \r、\n、\r\n
        }
        fileWriter.close();
    }
    //读取磁盘中的稀疏数组
    public static int[][] readFromFile() throws IOException {
        int[][] sparseArray = null;
        File file = new File("e:/sparseArray.data");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(fileReader);
        String readLine = null;
        int cur = 0;
        while ((readLine = bufferedReader.readLine()) != null) {
            String[] tempStr = readLine.split("\t");
            if (sparseArray == null) {// 如果稀疏数组还没创建就创建
                sparseArray = new int[Integer.parseInt(tempStr[2]) + 1][3];// 创建稀疏数组
            }
            sparseArray[cur][0] = Integer.parseInt(tempStr[0]);
            sparseArray[cur][1] = Integer.parseInt(tempStr[1]);
            sparseArray[cur][2] = Integer.parseInt(tempStr[2]);
            cur++;
        }
        bufferedReader.close();//不论是读取还是写入都需要关闭流
        return sparseArray;
    }

    public static void main(String[] args) throws IOException {// 因为在saveToFile中抛出了异常
        int row = 11;
        int column = 11;
        //创建一个原始二维数组
        int[][] sourceArray1 = new int[row][column];
        sourceArray1[1][2] = 1;
        sourceArray1[2][3] = 2;
        sourceArray1[4][5] = 3;
        //输出原始数组
        System.out.println("----------原二维数组如下-----------");
        for (int[] array : sourceArray1) {
            for (int data : array) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        //将二维数组 转换为 稀疏数组
        //1. 遍历原始数组，得到非0数据的个数
        int count = 0;
        for (int[] array :
                sourceArray1) {
            for (int data :
                    array) {
                if (data > 0)
                    count++;
            }
        }
        //2. 创建对应的稀疏数组
        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = sourceArray1.length;// [0][0]存储原始数组的行数
        sparseArray[0][1] = sourceArray1[0].length;// [0][1]存储原始数组的列数
        sparseArray[0][2] = count;// [0][2]存储原始数组中非0数据的个数
        //3. 遍历原始数组，将非0数据存储到稀疏数组中
        int cur = 0;
        for (int i = 0; i < sourceArray1.length; i++) { //用fori的原因是需要用到原始数组中的坐标值
            for (int j = 0; j < sourceArray1[0].length; j++) {
                if (sourceArray1[i][j] != 0) {
                    cur++;
                    sparseArray[cur][0] = i;
                    sparseArray[cur][1] = j;
                    sparseArray[cur][2] = sourceArray1[i][j];
                }
            }
        }
       /*
       //输出稀疏数组
        System.out.println("------------稀疏数组如下------------");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        //将稀疏数组 恢复成 原始的二维数组
        //1. 先读取稀疏数组的第一行，根据第一行数据，创建原始的二维数组
        int[][] sourceArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            sourceArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //输出转换后的二维数组
        System.out.println("----------转换后的二维数组如下-----------");
        for (int[] array : sourceArray2) {
            for (int data : array) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        */


        /*
            IO版本
         */
        saveToFile(sparseArray);
        // 读取文件，将文件中的稀疏数组输出并转换为 二维数组
        int[][] sparseArray3 = readFromFile();

        //输出稀疏数组
        System.out.println("------------稀疏数组如下------------");
        for (int i = 0; i < sparseArray3.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray3[i][0], sparseArray3[i][1], sparseArray3[i][2]);
        }

        //将稀疏数组 恢复成 原始的二维数组
        //1. 先读取稀疏数组的第一行，根据第一行数据，创建原始的二维数组
        int[][] sourceArray3 = new int[sparseArray3[0][0]][sparseArray3[0][1]];
        for (int i = 1; i < sparseArray3.length; i++) {
            sourceArray3[sparseArray3[i][0]][sparseArray3[i][1]] = sparseArray3[i][2];
        }
        //输出转换后的二维数组
        System.out.println("----------转换后的二维数组如下-----------");
        for (int[] array : sourceArray3) {
            for (int data : array) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
