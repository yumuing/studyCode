import java.text.ParseException;
import java.util.TreeMap;

public class Account {

    private String name;
    private TreeMap<Integer,Ticket> accoutTickets;

    public Account(String name) {
        this.accoutTickets = new TreeMap<Integer,Ticket>();
        this.name = name;
    }
    //增加tickets内车票,ticket为所要购买的车票对象，trainNumber为车票班次，bookedNumber为要购买的票数
    public void addTicket(Ticket ticket,int trainNumber,int bookedNumber){
        //此时的tickets中的每个车票信息中的bookedNumber改为账户对象的购买票数，不是原本的已订票人数。
        //已购买过该车票，this.bookedNumber+=bookedNumber
        if (accoutTickets.get(trainNumber) == null) {
            ticket.setBookedNumber(ticket.getBookedNumber()+bookedNumber);
            Ticket ticket1 = new Ticket(ticket);
            accoutTickets.put(trainNumber,ticket1);
            accoutTickets.get(trainNumber).setBookedNumber(bookedNumber);
        }
        else {
            if (accoutTickets.containsKey(trainNumber)){
                ticket.setBookedNumber(ticket.getBookedNumber()+bookedNumber);
                //调出tickets中的车票，再修改车票信息，而不能对ticket直接修改。
                accoutTickets.get(trainNumber).setBookedNumber(accoutTickets.get(trainNumber).getBookedNumber()+bookedNumber);
            }
            //未购买过该车票，bookedNumber=bookedNumber
            else{
                //未购买要购买就加入tickets中，再修改车票信息
                Ticket ticket1 = new Ticket(ticket);
                ticket.setBookedNumber(ticket.getBookedNumber()+bookedNumber);
                accoutTickets.put(trainNumber,ticket1);
                accoutTickets.get(trainNumber).setBookedNumber(bookedNumber);
            }
        }
    }
    //删除tickets内车票,trainNumber为班次，returnTickets为退票数
    public void deleteTicket(int trainNumber,int returnTickets){
        //该账户还未买票，休眠1000ms，等待其他线程完成买票
        if (accoutTickets.get(trainNumber)==null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            //原先只购买了一张票
            if (accoutTickets.get(trainNumber).getBookedNumber()==1){
                        accoutTickets.remove(trainNumber);
            }else{
                      accoutTickets.get(trainNumber).setBookedNumber(accoutTickets.get(trainNumber).getBookedNumber()-returnTickets);
            }
        }

    }
    //购票并更新已订票人数
    public void  updateBookedNumber(TreeMap <Integer,Ticket> ticket, int trainNumber,int bookedNumber){
        Ticket t1 = ticket.get(trainNumber);
        try {
            if(t1.ticletIsEmpty()||t1.timeIsOver()||t1.getBookedNumber()+bookedNumber>t1.getRateCapacity()){
                //购买的票数超过剩下的票数或已超过发车时间！
                System.out.println(name+"班次"+trainNumber+"已无余票或已超过发车时间！");
                return;
            }
            else{
                addTicket(ticket.get(trainNumber),trainNumber,bookedNumber);
                System.out.println(name+"已购买了"+bookedNumber+"张票"+"班次为："+t1.getTrainNumber()+"已订票人数为："
                        +t1.getBookedNumber()+"该账户已订该班次票数为："+accoutTickets.get(trainNumber).getBookedNumber());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //退票并更新已订票人数
    public void returnTicket(TreeMap <Integer,Ticket> ticket, int trainNumber, int returnTickets,Account act){
        if (ticket != null && act.accoutTickets.get(trainNumber)!= null) {
                Ticket t1 = act.accoutTickets.get(trainNumber);
                try {
                    if (t1.timeIsOver()||t1.getBookedNumber()==0){
                        System.out.println(t1.getTrainNumber()+"班次已过发车时间或无订票，无法退票！");
                    }else{
                        ticket.get(trainNumber).setBookedNumber(ticket.get(trainNumber).getBookedNumber()-returnTickets);
                        deleteTicket(trainNumber,returnTickets);
                        if (accoutTickets.get(trainNumber)!=null){
                            System.out.println("您已退票成功！"+"当前"+"班次为："+t1.getTrainNumber()+"已订票人数为："
                                    +ticket.get(trainNumber).getBookedNumber()+"当前账户已订该班次票数为："
                                    +accoutTickets.get(trainNumber).getBookedNumber());
                        }else{
                            System.out.println("您已退票成功！"+"当前"+"班次为："+t1.getTrainNumber()+"已订票人数为："
                                    +ticket.get(trainNumber).getBookedNumber()+"当前账户已订该班次票数为：0");
                        }

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }else{
            return;
        }

    }

    @Override
    public String toString() {
        return "账户{" +
                "账户名为：" + name + '\'' +
                ", 车票："+"\n" + accoutTickets +
                '}';
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<Integer, Ticket> getTickets() {
        return accoutTickets;
    }

    public void setTickets(TreeMap<Integer, Ticket> tickets) {
        this.accoutTickets = tickets;
    }
}

