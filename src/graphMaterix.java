import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//图邻接矩阵结构
class GraphM {
    static final int MaxNum = 20;         //图的最大顶点数
    static final int MaxValue = 65535;    //最大值
    int GType;     //图的类型（0:无向图  1：有向图）
    int VertexNum;//顶点数量
    int EdgeNum;   //边的数量
    char[] Vertex = new char[MaxNum]; //保存顶点信息
    int[][] EdgeWeight = new int[MaxNum][MaxNum];//保存权
    public GraphM(int GType,int VertexNum,int EdgeNum){
        this.GType = GType;
        this.EdgeNum = EdgeNum;
        this.VertexNum = VertexNum;
    }

}
public class graphMaterix {
    //创建图邻接矩阵
    public static void creatGraph(GraphM gm) {
        int i, j, k;
        int weight;         //权
        char startV, endV;   //起始，终止顶点
        Scanner input = new Scanner(System.in);
        System.out.println("输入图中各顶点信息:");
        for (i = 0; i < gm.VertexNum; i++) {
            System.out.println("第" + (i + 1) + "个顶点");
            gm.Vertex[i] = (input.next().toCharArray())[0];//保存到顶点数组中
        }
        System.out.println("输入各边的顶点及权值：");
        for (k = 0; k < gm.EdgeNum; k++) {
            System.out.println("第" + (k + 1) + "条边");
            System.out.println("边的起点为：");
            startV = input.next().charAt(0);
            System.out.println("边的终点为：");
            endV = input.next().charAt(0);
            System.out.println("边的权值为：");
            weight = input.nextInt();
            for (i = 0; gm.Vertex[i] != startV; i++) ;  //在顶点数组中查找起点位置
            for (j = 0; gm.Vertex[j] != endV; j++) ;    //在顶点数组中查找终点位置
            gm.EdgeWeight[i][j] = weight;
            if (gm.GType == 0) {
                gm.EdgeWeight[j][i] = weight;
            }
        }
        input.close();
    }
    //显示图邻接矩阵
    public static void outGraph(GraphM gm){
        for(int i=0;i<gm.VertexNum;i++){
            System.out.printf(String.valueOf(gm.Vertex[i])+" "); //第一行输出顶点信息
        }
        System.out.println();
        for(int i=0;i<gm.VertexNum;i++){
            System.out.printf(String.valueOf(gm.Vertex[i])+" ");
            for(int j=0;j<gm.VertexNum;j++){
                if(gm.EdgeWeight[i][j]==gm.MaxValue){
                    System.out.printf(" ");
                }else{
                    System.out.printf(String.valueOf(gm.EdgeWeight[i][j])+" ");
                }
            }
            System.out.println();
        }
    }
    //深度优先遍历算法
    public static void DFSserver(GraphM g, boolean[] visited, int i) {
        for (int j = 0;j < g.VertexNum;j++){
            if (g.EdgeWeight[i][j] != 0 && !visited[j]){
                DFSserver(g,visited,j);
            }
        }
    }
    //深度优先遍历操作
    public static char[] DFS(GraphM g){
        char[] res = new char[g.VertexNum];
        boolean[] visited = new boolean[g.VertexNum];
        int k = 0;
        for (int i = 0;i < g.VertexNum;i++){
            if (!visited[i]){
                visited[i] = true;
                res[k++] = g.Vertex[i];
                DFSserver(g,visited,i);
            }
        }
        return res;
    }


    //广度优先遍历 BFS
    public static char[] BFS(GraphM g){
        boolean[] visited = new boolean[g.VertexNum];
        Queue<Integer> queue = new LinkedList<>();
        char[] res = new char[g.VertexNum];
        int j = 0;
        for (int i = 0; i < g.VertexNum;i++){
            if (!visited[i]){
                visited[i] = true;
                res[j++] = g.Vertex[i];
                queue.add(i);
                while (!queue.isEmpty()){
                    queue.remove();
                    for (int k = 0;k<g.VertexNum;k++){
                        if (g.EdgeWeight[i][k] != 0 && !visited[k]){
                            visited[k] = true;
                            res[j++] = g.Vertex[k];
                            queue.add(k);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int Gtype;//图的类型（0:无向图  1：有向图）
        int VertexNum;//顶点数量
        int EdgeNum;   //边的数量
        Scanner in = new Scanner(System.in);
        System.out.println("请输入图类型（0:无向图  1：有向图）");
        Gtype = in.nextInt();
        System.out.println("请输入顶点数目！");
        VertexNum = in.nextInt();
        System.out.println("请输入边数目！");
        EdgeNum = in.nextInt();
        GraphM gm = new GraphM(Gtype,VertexNum,EdgeNum);
        creatGraph(gm);
        outGraph(gm);
        char[] BFSres = BFS(gm);
        char[] DFSres = DFS(gm);
        System.out.println("广度优先遍历如下：");
        for (char item: BFSres) {
            System.out.print(item+" ");
        }
        System.out.println();
        System.out.println("深度优先遍历如下");
        for (char item: DFSres) {
            System.out.print(item+" ");
        }
        in.close();
    }
}
