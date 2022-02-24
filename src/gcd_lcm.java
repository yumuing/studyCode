import java.util.Scanner;

public class gcd_lcm {
    //求最大公约数gcd以及最大公倍数lcm
    // 36 24 36/24
    // 24 12 24/12
    // 0 结束最大公约数为12
    // 求最小公倍数
    // lcm(a, b) = (a * b)/gcd(a, b)
    public static int gcd(int a, int b){
        //a>=b
        //辗转相除法
        if (b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
        int a,b;
        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        b = input.nextInt();
        int gcd = gcd(a,b);
        System.out.println(gcd);
        //lcm(a, b) = (a * b)/gcd(a, b)
        System.out.println(a / gcd * b);
    }
}
