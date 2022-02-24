import java.util.Scanner;

public class Oula {
    // 在数论，对正整数n，欧拉函数是小于等于n的正整数中与n互质的数的数目
    // 单个欧拉函数取值
    public static int f1(int n){
        int res = n;
        for (int i = 2;i*i<=n;i++){
            if (n % i==0){
                res = res / i*(i-1);//res/i
                while (n % i == 0){
                    n/=i;
                }
            }
        }
        if (n>1){
            res = res/n*(n-1);
        }
        return res;
    }
    //区间内欧拉函数取值
    public static int[] f2(int n){
        int[] count = new int[n+1];
        for (int i = 1;i <= n;i++){
            count[i]=i;
        }
        for (int i =2 ;i <= n;i++){
            if (count[i] == i){
                for (int j = i;j <= n;j+=i){
                    count[j] = count[j]/i*(i-1);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.close();
        //求单个欧拉值
        System.out.println(f1(n));
        //求小于等于n的欧拉值
        int[] count = f2(n);
        for (int i = 1;i<count.length;i++){
            System.out.println(count[i]);
        }
    }
}
