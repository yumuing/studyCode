import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        TreeMap<Integer,Ticket> ticket = new TreeMap<>();
        //车票列表
        Ticket[] t={
                new Ticket(1,"2021-7-8 23:00:00","普宁","广州",3,
                        30,10),
                new Ticket(2,"2021-7-7 23:10:00","广州","普宁",1,
                        40,10),
                new Ticket(3,"2021-7-6 21:20:00","湛江","佛山",2,
                        30,20),
                new Ticket(4,"2021-7-7 23:40:00","深圳","上海",4,
                        50,50)
        };
        int i=0;
        for(Ticket t1:t){
            ticket.put(++i,t1);
        }
        Account act1 =new Account("账户一");
        Account act2 =new Account("账户二");
        Account act3 =new Account("账户三");
        Account act4 =new Account("账户一");
        Account act5 =new Account("账户一");
        act1.updateBookedNumber(ticket,2,20);
        /*
        RunOne r1 = new RunOne("购票线程一",ticket, 1,act1,1);
        RunTwo r2 = new RunTwo("退票线程二",ticket, 2,1,act2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

         */
    }

}
