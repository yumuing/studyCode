//鸡兔同笼
//现在有一批鸡和兔子关在同一个笼子里面，数头有125，数脚324。请问这个笼子里面最后有多少只鸡？多少个兔子？
public class chicken_rabbit {
    public static void main(String[] args) {
        //注：first >= second，此处first为兔子，second为鸡
        int head = 125,foot = 324,first = 0,second = 0,firstFoot =4,secondFoot=2;
        //解题方法一：假设法：全是某种动物
        // 假设笼子里都是兔子
        //全是兔子，脚总数为4*head，可以得出多出几只脚，多余的脚除以单只兔子和鸡脚数之差即可算出鸡数
        second = (firstFoot* head-foot) / (firstFoot-secondFoot);
        first = head - second;
        System.out.println("解题方法一：假设法\n鸡的数目为"+second+"\n兔子的数目为"+first);
        //解题方法二：砍腿法：砍去多余腿
        //砍去兔子的两条腿，计算砍去几条腿，砍去的腿除以差值即可算出兔子数
        //初始化鸡兔数目，防止被其他算法影响
        second = first =0;
        first = (foot-secondFoot*head) / (firstFoot-secondFoot);
        second = head - first;
        System.out.println("解题方法二：砍腿法\n鸡的数目为"+second+"\n兔子的数目为"+first);
    }
}
