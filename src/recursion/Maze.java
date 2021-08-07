package recursion;

public class Maze {
    public static void main(String[] args) {
        //初始化二维数组，来表示迷宫
        int[][] maze = new int[8][7];
        /*for (int i = 0; i < maze.length; i++) {//其实这种嵌套循环比两次for循环时间复杂度高
            for (int j = 0; j < maze[i].length; j++) {
                if (i == 0 || i == maze.length -1
                        || j == 0 || j == maze[i].length - 1)
                    maze[i][j] = 1;
            }
        }*/
        //设置左右墙体。
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][maze[0].length - 1] = 1;
        }
        //设置上下墙体
        for (int i = 0; i < maze[0].length; i++) {
            maze[0][i] = 1;
            maze[maze.length - 1][i] = 1;
        }
        //设置两个突出的墙体
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[2][2] = 1;

        setWay(1,1, maze);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }


    }
    /*
        标记含义：1：墙体  2：可以走通的路径  3：走过但是走不通的路径(所以这里有回溯的过程)
        寻找顺序是：下 - 右 - 上 - 左
     */
    public static boolean setWay(int y, int x, int[][] maze) {
        if (maze[6][5] == 2)//找到出口处，说明这条路是可以走通的
            return true;
        else {//还没有找到出口，就要按照寻找顺序，依次递归寻找下去
            //暂且认为当前格子是 走到出口的 必经之路
            if (maze[y][x] == 0) {//当前要走的格子 是0，意味着没走过。可以试试。
                maze[y][x] = 2;
                if (setWay(y + 1, x, maze)) //尝试走 下面的格子
                    return true;
                else if (setWay(y, x + 1, maze)) //尝试走 右面的格子
                    return true;
                else if (setWay(y - 1, x, maze)) //尝试走 上面的格子
                    return true;
                else if (setWay(y, x - 1, maze)) //尝试走 左面的格子
                    return true;
                else {//四个方向全碰壁了，就说明这个格子是 走不到 出口 的
                    maze[y][x] = 3;//在当前格子处 走不到 出口
                    return false;
                }
            } else/* if (maze[y][x] != 2)*/
                //如果当前格子是墙体、走不通 来时经过的格子。就返回false，不用尝试这个格子
                return false;
        }
    }
}