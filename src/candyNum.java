import java.util.Scanner;

public class candyNum {
    /**
    有n个小朋友围坐成一圈。老师给每个小朋友随机发偶数个糖果，然后进行下面的游戏：
    每个小朋友都把自己的糖果分一半给左手边的孩子。
    一轮分糖后，拥有奇数颗糖的孩子由老师补给1个糖果，从而变成偶数。
    反复进行这个游戏，直到所有小朋友的糖果数都相同为止。
    你的任务是预测在已知的初始糖果情形下，老师一共需要补发多少个糖果。
    输入
    程序首先读入一个整数N(2< N< 100)，表示小朋友的人数。
    接着是一行用空格分开的N个偶数（每个偶数不大于1000，不小于2）
    输出
    要求程序输出一个整数，表示老师需要补发的糖果数。
     输入
     3
     2 2 4
     输出
     4
    **/
    public static int candyRes(int[] candy,int n){
        int res = 0;
        int num = 0;
        for (int i = 0;i < n;i++){
            //第一个
            if (i==0){
                num = candy[0]/2;
                candy[0] -= num;
                candy[1] += num;
            }
            //中间轮转
            else if (i<n-1){
                candy[i] += num;
                num = candy[i]/2;
                candy[i] -= num;
                candy[i+1] += num;
            }
            else {
                candy[n-1] += num;
                num = candy[n-1]/2;
                candy[n-1] -= num;
                candy[0] += num;
            }
        }
        for (int item:candy) {
            if (item % 2 != 0){
                item++;
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] candy = new int[n];
        for (int i = 0;i < n;i++){
            candy[i] = in.nextInt();
        }

        in.close();
    }

}
