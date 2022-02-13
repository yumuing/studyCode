import java.util.Scanner;

public class IsPrimeNumber {
    public static boolean IsPrime(int a){
        //单一数字效率客观，范围内多个数字判断效率较低，采用素数筛选法解决
        if (a == 1){
            //1既不是质数也不是合数
            return false;
        }
        // 在 2 到 sqrt(a) 中，找不到可以整除的数就为质数
        // 利用i*i<=a,代替i <= sqrt(a),提高效率
        for (int i = 2 ;i*i<=a;i++){
            if (a%i==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int a ;
        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        if (IsPrime(a)==true){
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
