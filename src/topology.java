import java.util.*;

public class topology {
    public static char[] foundTopology(GraphM g){
        Queue<Integer> queue = new LinkedList<>();
        HashMap<String,Integer> hashMap= new HashMap<>();
        char[] res = new char[g.VertexNum];
        //计算每个结点入度值
        for (int i = 0;i < g.VertexNum;i++){
            String key = String.valueOf(g.Vertex[i]);
            int value = 0;
            for (int j = 0;j < g.VertexNum;j++){
                if (g.EdgeWeight[j][i]!=0){
                   value++;
                }
            }
            if (value==0){
                queue.add(i);
            }
            hashMap.put(key,value);
        }
        int j = 0;
        while(!queue.isEmpty()){
            int getKey = queue.remove();
            res[j++] = g.Vertex[getKey];
            for (int k = 0;k < g.VertexNum;k++){
                String getPointKey = String.valueOf(g.Vertex[k]);
                int getPointValue = hashMap.get(getPointKey);
                if (g.EdgeWeight[getKey][k] != 0 && getPointValue != 0){
                    hashMap.put(getPointKey,getPointValue-1);
                    if (getPointValue-1==0){
                        queue.add(k);
                    }
                }
            }

        }
        if (j == g.VertexNum){
            return res;
        }
        return null;
    }

    public static char[] foundTopology(GraphT g){
        Queue<Integer> queue = new LinkedList<>();
        HashMap<String,Integer> hashMap= new HashMap<>();
        char[] res = new char[g.verNum];
        //计算每个结点的入度值
        for (int i = 0;i < g.verNum;i++){
            vertexNode v = g.vertex[i];
            edgeNode firstNode = v.firstNode;
            if (hashMap.get(String.valueOf(v.date)) == null){
                hashMap.put(String.valueOf(v.date),0);
            }
            while (firstNode != null){
                String key = String.valueOf(g.vertex[firstNode.index].date);
                if (hashMap.get(key) == null){
                    hashMap.put(key,0);
                }
                hashMap.put(key,hashMap.get(key)+1);
                firstNode = firstNode.next;
            }
        }
//        for(Map.Entry<String,Integer> entry:hashMap.entrySet()){
//            if (entry.getValue()==0){
//                String i =
//                queue.add(graphTable.getIndex(g,[]))
//            }
//        }
        int j = 0;
        while(!queue.isEmpty()){
            int getKey = queue.remove();
            vertexNode v = g.vertex[getKey];
            edgeNode firstnode = v.firstNode;
            String getPointKey = String.valueOf(g.vertex[firstnode.index].date);
            int getPointValue = hashMap.get(getPointKey);
            res[j++] = v.date;
            while (firstnode != null && getPointValue != 0){
                hashMap.put(getPointKey,getPointValue-1);
                if (getPointValue-1==0){
                    queue.add(firstnode.index);
                }
                firstnode = firstnode.next;
            }
        }
        if (j == g.verNum){
            return res;
        }
        return null;
    }

    public static void main(String[] args) {
        int Gtype; //图的类型（0:无向图  1：有向图）
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
        GraphT gt = new GraphT(Gtype,VertexNum,EdgeNum);
        graphMaterix.creatGraph(gm);
        char[] GMres = foundTopology(gm);
        if (GMres == null){
            System.out.println("无拓扑序列！");
        }
        else {
            for (char item:GMres) {
                System.out.print(item+" ");
            }
        }
//        System.out.println();
//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入图的类型！（0：无向图；1：有向图）");
//        int gType = input.nextInt();//图的类型
//        System.out.println("请输入顶底数");
//        int vertNum = input.nextInt();//顶点数
//        System.out.println("请输入边数");
//        int edgeNum = input.nextInt();//边数
//        char[] vertex = new char[vertNum];//顶点字符
//        edge[] edge = new edge[edgeNum];//创建边结点数组
//        GraphT gt = new GraphT(gType,vertNum,edgeNum);
//        //输入顶点
//        for (int i = 0;i<vertNum;i++){
//            System.out.println("请输入"+i+"个结点");
//            vertex[i] = input.next().charAt(0);
//        }
//        for (int i = 0;i<edgeNum;i++){
//            System.out.println("请输入起点");
//            char began = input.next().charAt(0);
//            System.out.println("请输入终点");
//            char end = input.next().charAt(0);
//            System.out.println("请输入权值");
//            int weight = input.next().charAt(0);
//            edge[i] = new edge(began,end,weight);
//        }
//        input.close();
//        graphTable.createGraph(gt,vertex,vertNum,edge,edgeNum,1);
//        char[] GTres = foundTopology(gt);
//        if (GTres == null){
//            System.out.println("无拓扑序列！");
//        }
//        else {
//            for (char item:GTres) {
//                System.out.print(item+" ");
//            }
//        }
    }
}
