import java.util.Scanner;

class matrix{
    int left,right,above,under;//矩形左右上下边
    boolean flag;//判断是否已建过
}
public class MatrixCoverOne {

// k个矩形（1<=k<=4）全部覆盖，矩形的边平行于坐标轴。
// 当k=2时，可用如图二的两个矩形s1，s2覆盖，s1，s2面积和为4。
// 问题是当n个点坐标和k给出后，怎样才能使得覆盖所有点的k个矩形的面积之和为最小呢。
// 约定：覆盖一个点的矩形面积为0；覆盖平行于坐标轴直线上点的矩形面积也为0。各个矩形必须完全分开（边线与顶点也都不能重合）。
// 总结：n个点使用最多k个矩形覆盖，求最小总面积
    //ans 设置为最大值是保证第一次判断时，val总比ans小
    static int n,k,x,y,val,ans=Integer.MAX_VALUE;
    static int[] Xindex = new int[50];
    static int[] Yindex = new int[50];
    static matrix[] matrixs = new matrix[5];
    //判断矩形i是否与已有矩形相交
    public static boolean coverJudge(int i,int j){
        //矩形四个顶点(left,under) (right,under) (left,above) (right,under) 点只要有一个在其他矩形中即相交
        //判断右下顶点不在其他矩形中，在则返回true
        if(matrixs[i].left<=matrixs[j].left
                && matrixs[i].right>=matrixs[j].left
                //其他矩形左边在矩阵i中
                && matrixs[i].above>=matrixs[j].above
                && matrixs[i].under<=matrixs[j].above)
                //其他矩形上边在矩阵i中
            return true;
        //判断右上顶点不在其他矩形中，在则返回true
        if(matrixs[i].left<=matrixs[j].right
                && matrixs[i].right>=matrixs[j].right
                //其他矩形左边在矩阵i中
                && matrixs[i].above>=matrixs[j].above
                && matrixs[i].under<=matrixs[j].above)
                //其他矩形下边在矩阵i中
            return true;
        //判断左下顶点不在其他矩形中，在则返回true
        if(matrixs[i].left<=matrixs[j].left
                && matrixs[i].right>=matrixs[j].left
                //其他矩形右边在矩阵i中
                && matrixs[i].above>=matrixs[j].under
                && matrixs[i].under<=matrixs[j].under)
                //其他矩形上边在矩阵i中
            return true;
        //判断左上顶点不在其他矩形中，在则返回true
        if(matrixs[i].left<=matrixs[j].right
                && matrixs[i].right>=matrixs[j].right
                //其他矩形右边在矩阵i中
                && matrixs[i].above>=matrixs[j].under
                && matrixs[i].under<=matrixs[j].under)
                //其他矩形下边在矩阵i中
            return true;
        return false;
    }
    public static boolean judge(){
        //循环判断矩阵i，是否与之前的矩阵相交，是则返回true
        for(int i=1;i<=k;i++){
            if(matrixs[i].flag){
                for(int j=1;j<i;j++) {
                    if (matrixs[j].flag){
                        if (coverJudge(i, j)) return true;
                    }

                }
            }

        }
        return false;
    }




    //递归
    public static void dfs(int now){
        //存在矩形相交,寻找下一矩形
        if(judge()){
            return ;
        }
        //不相交则累加当前所有矩形面积，每次递归判断一次
        val=0;
        for(int i=1;i<=k;i++){
            if(matrixs[i].flag){
                //矩形面积总和
                val+=(matrixs[i].right-matrixs[i].left)*(matrixs[i].above-matrixs[i].under);
            }
        }
        //总和已经大于最小ans，不再继续，进行剪枝，提高效率
        if(val>ans){
            return ;
        }
        //递归终止点，已遍历结束，val仍小于ans，ans=val
        if(now==n){
            ans=val;
            return ;
        }
        //k个矩形
        for(int i=1;i<=k;i++){
            //该点未加入矩形中，设为该点为矩形，
            // flag=false进入if语句,矩形未创建，开始执行if语句
            if(!matrixs[i].flag){
                //第一次递归设置每个矩形边为点对应坐标
                //第一次进入now为 0 当now
                matrixs[i].right = matrixs[i].left=Yindex[now];
                matrixs[i].above = matrixs[i].under=Xindex[now];
                matrixs[i].flag=true;
                //进入else语句
                //设置十字矩形，每第一次递归
                dfs(now+1);
                //递归全部结束，依次回溯，全设置flag=false，不进入else语句，再次进入if语句
                matrixs[i].flag=false;
            }
            //进行第二次递归，寻找最小的矩形面积，
            else{
                //a,b （left，right）相连为y轴方向；c，d （under above）相连为x轴方向
                int a=matrixs[i].left,
                    b=matrixs[i].right,
                    c=matrixs[i].under,
                    d=matrixs[i].above;
                //第一次进入now为1，i为1
                matrixs[i].left=Math.min(matrixs[i].left,Yindex[now]);
                matrixs[i].right=Math.max(matrixs[i].right,Yindex[now]);
                matrixs[i].under=Math.min(matrixs[i].under,Xindex[now]);
                matrixs[i].above=Math.max(matrixs[i].above,Xindex[now]);
                //第一次进入确定第一个点与其他点所构成的矩形下一个矩形结果入口
                dfs(now+1);
                //回溯，查看该节点下另一个路径是否满足条件
                matrixs[i].left=a;
                matrixs[i].right=b;
                matrixs[i].under=c;
                matrixs[i].above=d;
            }
        }

    }

    public static void main(String[] args) {
        //输入语句
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            n = input.nextInt();
            k = input.nextInt();
            if(k<1 || k>4 || n<0 || n>50){
                return;
            }
            for (int i = 0; i<n;i++){
                x = input.nextInt();
                y = input.nextInt();
                if (x<0 || y<0 || y>500){
                    return;
                }
                Xindex[i] = x;
                Yindex[i] = y;
                matrixs[i] = new matrix();
            }
            //递归开始
            dfs(0);
            //输出语句
            System.out.println(ans);
        }
    }
}
