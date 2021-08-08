package DataStructures.sort;

public class InsertSort {
    public static void main(String[] args) {

    }

    /*
        方法一：从尾到头一个一个交换
        方法二：老师的，一个一个往后调。
        我想的：先找位置，再往后调
     */
    public static void insertSort(int[] nums) {

    }
}

    /**
     *
     * Description: 插入排序Java代码版
     *
     * @Title: insertSort
     * @param a 待排序数组。
     *            void
     *//*
    public void insertSort(int[] a) { // 插入排序
        int len = a.length; // 获得数组的长度。即外循环次数。
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                // 从尾部开始交换位置，直到到达合适位置。 此时插入完成。
                exch(a, j, j - 1);  // 交换相邻的数字，实现插入。
            }
        }
    }*/


    /*public static void InsertSort(int[] arr)
    {
        int i, j;
        int n = arr.Length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = 1; i < n; i++)
        {
            j = i;
            target = arr[i];

            while (j > 0 && target < arr[j - 1])
            {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = target;
        }
    }*/


    /*public class Insert
    {
        public static void main(String[] args)
        {
            int[] ins = {2,3,5,1,23,6,78,34};
            int[] ins2 = sort(ins);
            for(int in: ins2){
                System.out.println(in);
            }
        }

        public static int[] sort(int[] ins){

            for(int i=1; i<ins.length; i++){
                for(int j=i; j>0; j--){
                    if(ins[j]<ins[j-1]){
                        int temp = ins[j-1];
                        ins[j-1] = ins[j];
                        ins[j] = temp;
                    }
                }
            }
            return ins;
        }
    }*/



    /*public class Insertion
    {
        public static void sort(Comparable[] a)
        {
            //将a[]按升序排列
            int N=a.length;
            for (int i=1;i<N;i++)
            {
                //将a[i]插入到a[i-1]，a[i-2]，a[i-3]……之中
                for(int j=i;j>0&&(a[j].compareTo(a[j-1])<0);j--)
                {
                    Comparable temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                }
            }
        }
    }*/