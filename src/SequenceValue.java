import java.util.Scanner;

public class SequenceValue {
    public static void main(String[] args) {
        int n,a,b,c,k;
        Scanner input = new Scanner(System.in);
        while(true){
            n = input.nextInt();
            for (int i = 0; i < n; i++){
                a = input.nextInt();
                b = input.nextInt();
                c = input.nextInt();
                k = input.nextInt();
                System.out.println(value(a,b,c,k));
            }
        }
    }

    private static int value(int a, int b, int c, int k) {
        int result ;
        //等差数列公式：An = A1 + (n-1)*d （d 为公差）
        //等比数列公式：An = A1 * r^(n-1) （r 为公比）
        //等比数列累乘可能会超时，需要运用快速幂求解幂运算。
        if ((c-b) == (b-a)){
            //等差数列
            result = a + (k-1)*(b-a) % 200920;
        }
        else{
            result = a * qp((b/a),k-1,200920);
        }
        return result;
    }

    public static int qp(int base,int power,int p){
        int result = 1;
        while (power > 0) {
            if((power & 1) == 1) {
                //(power & 1) == 1 等同于 power % 2 == 1 判断是否为奇数
                // 位运算效率高于模运算
                result = result * base % p;
            }
            //指数缩小为原来的一半，
            //若为奇数，即结果减一再折半，如下
            //power = (power -1) / 2
            //(power >>= 1) == 1 等同于 power /= 2
            //位运算效率高于模运算
            power >>= 1;
            //底数扩大为原来的平方
            base = (base * base) % p;
        }
        return result;
    }
}
