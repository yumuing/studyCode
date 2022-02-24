import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//边结点类
class edgeNode{
    int index;   //边终点对应的下标
    edgeNode next;      //下一个结点
    int edgeWeight;     //权值
    public edgeNode(int index,edgeNode next,int edgeWeight){
        this.index = index;
        this.next = next;
        this.edgeWeight = edgeWeight;
    }
}
//顶点结点类
class vertexNode{
    char date;      //顶点元素
    edgeNode firstNode;     //第一个边结点
    public vertexNode(char date,edgeNode firstNode){
        this.date = date;
        this.firstNode = firstNode;
    }
}
//图邻接表
class GraphT{
    int GType;     //图的类型（0:无向图  1：有向图）
    int verNum;     //顶点数
    int edgeNum;    //边数
    vertexNode[] vertex; //所有的顶点

    public GraphT(int GType, int verNum, int edgeNum) {
        this.GType = GType;
        this.verNum = verNum;
        this.edgeNum = edgeNum;
        this.vertex = new vertexNode[verNum];
    }
}
//边信息
class edge{
    char began; //起点
    char end;   //终点
    int weight;    //权值

    public edge(char began, char end, int weight) {
        this.began = began;
        this.end = end;
        this.weight = weight;
    }
}

public class graphTable {
    //查找对应下标
    public static int getIndex(GraphT g,char target){
        for (int i = 0;i < g.verNum;i++){
            if (g.vertex[i].date == target){
                return i;
            }
        }
        return -1;
    }

    //插入一条边
    public static int insertEdge(GraphT g, edge edge){
        int began = getIndex(g,edge.began);
        int end = getIndex(g,edge.end);
        if (began == -1 || end == -1){//无效边
            return -1;
        }
        //前插法、后插法，这里使用前插法，无需遍历
        g.vertex[began].firstNode = new edgeNode(end,g.vertex[began].firstNode, edge.weight);
        if (g.vertex[began].firstNode==null){
            return -2;
        }
        if(g.GType == 0){
            g.vertex[end].firstNode = new edgeNode(began,g.vertex[end].firstNode,edge.weight);
        }
        ++g.edgeNum;
        return 0;
    }
    //创建图
    public static void createGraph(GraphT g,char[] vertex,
                                   int verNum, edge[] edges,
                                   int edgeNum,int gType){
        g.verNum = 0;
        g.edgeNum = 0;
        g.GType = gType;
        for(int i = 0;i<verNum;i++){
            g.vertex[i] = new vertexNode(vertex[i],null);
            ++ g.verNum;
        }
        for (int i = edgeNum-1;i >= 0;--i){
            int value = insertEdge(g,edges[i]);
            if (value != 0){
                //插入一条边失败
                System.out.println("插入边失败");
            }
        }
    }
    //展示图
    public static void graphShow(GraphT g){
        System.out.println("邻接表如下：");
        for(int i = 0;i < g.verNum;i++){
            edgeNode firstNode = g.vertex[i].firstNode;
            if (firstNode == null){
                System.out.println(g.vertex[i].date+"->NULL");
                continue;
            }
            System.out.print(g.vertex[i].date+"->"+firstNode.index);
            while (firstNode.next!=null){
                System.out.print("->"+firstNode.next.index);
                firstNode = firstNode.next;
            }
            System.out.println();
        }
    }

    //广度优先遍历 BFS
    public static char[] BFS(GraphT g){
        boolean[] visited = new boolean[g.verNum];
        Queue<Integer> queue = new LinkedList<>();
        char[] res = new char[g.verNum];
        int j = 0;
        for (int i = 0; i < g.verNum;i++){
            if (!visited[i]){
                visited[i] = true;
                res[j++] = g.vertex[i].date;
                queue.add(i);
                while (!queue.isEmpty()){
                    queue.remove();
                    edgeNode firstNode = g.vertex[i].firstNode;
                    while (firstNode != null){
                        if (!visited[firstNode.index]){
                            visited[firstNode.index] = true;
                            res[j++] = g.vertex[firstNode.index].date;
                            queue.add(firstNode.index);
                        }
                        firstNode = firstNode.next;
                    }
                }
            }
        }
        return res;
    }
    //深度优先遍历算法 DFS
    public static void DFSserver(GraphT g,boolean[] visited,int i) {
        edgeNode firstNode = g.vertex[i].firstNode;
        while (firstNode!=null){
            if (!visited[firstNode.index]){
                DFSserver(g,visited,firstNode.index);
            }
            firstNode = firstNode.next;
        }
    }
    //深度优先遍历操作
    public static char[] DFS(GraphT g){
        char res[] = new char[g.verNum];
        boolean[] visited = new boolean[g.verNum];
        int j = 0;
        for(int i = 0;i < g.verNum;i++){
            if (!visited[i]){
                visited[i] = true;
                res[j++] = g.vertex[i].date;
                DFSserver(g,visited,i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入图的类型！（0：无向图；1：有向图）");
        int gType = input.nextInt();//图的类型
        System.out.println("请输入顶底数");
        int vertNum = input.nextInt();//顶点数
        System.out.println("请输入边数");
        int edgeNum = input.nextInt();//边数
        char[] vertex = new char[vertNum];//顶点字符
        edge[] edge = new edge[edgeNum];//创建边结点数组
        GraphT g = new GraphT(gType,vertNum,edgeNum);
        //输入顶点
        for (int i = 0;i<vertNum;i++){
            System.out.println("请输入"+i+"个结点");
            vertex[i] = input.next().charAt(0);
        }
        for (int i = 0;i<edgeNum;i++){
            System.out.println("请输入起点");
            char began = input.next().charAt(0);
            System.out.println("请输入终点");
            char end = input.next().charAt(0);
            System.out.println("请输入权值");
            int weight = input.next().charAt(0);
            edge[i] = new edge(began,end,weight);
        }
        input.close();
        createGraph(g,vertex,vertNum,edge,edgeNum,1);
        graphShow(g);
        System.out.println("广度优先遍历如下：");
        char[] BFSres = BFS(g);
        char[] DFSres = DFS(g);
        for (char item: BFSres) {
            System.out.print(item+" ");
        }
        System.out.println();
        System.out.println("深度优先遍历如下");
        for (char item: DFSres) {
            System.out.print(item+" ");
        }

    }
}
