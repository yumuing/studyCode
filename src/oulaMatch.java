import java.util.Scanner;

public class oulaMatch {
//    最小公倍数
    public static int gcd(int a, int b){
        //a>=b
        //辗转相除法
        if (b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
//        计算小于等于n与n互质的数个数
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        int[] res = new int[n];
        input.close();
        for (int i = 2;i<=n;i++){
            for (int j = 1;j <= i;j++){
                if (gcd(i,j)==1){
//                    互质
                    res[j]++;
                }
            }
        }
        for (int i = 0;i<n;i++){
            System.out.println(res[i]);
        }
    }
}
