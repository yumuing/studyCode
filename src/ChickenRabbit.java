import java.util.Scanner;

//鸡兔同笼
//现在有一批鸡和兔子关在同一个笼子里面，数头有125，数脚324。请问这个笼子里面最后有多少只鸡？多少个兔子？
public class ChickenRabbit {
    public static void main(String[] args) {
        //注：first >= second，此处first为兔子，second为鸡
        int head ,foot,first,second,firstFoot,secondFoot;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("请输入头数！");
            head = input.nextInt();
            System.out.println("请输入腿数！");
            foot = input.nextInt();
            if(head >= foot || head <= 0){
                System.out.println("数据不正确！重新输入");
            }
            else{
                break;
            }
        }
       while (true){
           System.out.println("请输入第一只动物腿数！");
           firstFoot = input.nextInt();
           System.out.println("请输入第二只动物腿数！");
           secondFoot = input.nextInt();
           //进行基本数据校验保证程序正常运行，不出现除以零的情况
           if(firstFoot == secondFoot || (firstFoot < secondFoot && firstFoot> 0)){
               System.out.println("数据无意义！重新输入");
           }
           else{
               break;
           }
       }
        //解题方法一：假设法：全是某种动物
        // 假设笼子里都是兔子
        //全是兔子，脚总数为4*head，可以得出多出几只脚，多余的脚除以单只兔子和鸡脚数之差即可算出鸡数
        second = (firstFoot* head-foot) / (firstFoot-secondFoot);
        if(second<0 || second > head){
            System.out.println("头与腿数目关系存在错误！");
            System.exit(0);
        }
        first = head - second;
        System.out.println("解题方法一：假设法\n第一只动物的数目为"+second+"\n第二只动物的数目为"+first);
        //解题方法二：砍腿法：砍去多余腿
        //砍去兔子的两条腿，计算砍去几条腿，砍去的腿除以差值即可算出兔子数
        //初始化鸡兔数目，防止被其他算法影响
        second = 0;
        first =0;
        first = (foot - (secondFoot * head)) / (firstFoot - secondFoot);
        if(first<0 || first > head){
            System.out.println("头与腿数目关系存在错误！");
            System.exit(0);
        }
        second = head - first;
        System.out.println("解题方法二：砍腿法\n第一只动物的数目为"+second+"\n第二只动物的数目为"+first);
    }
}
