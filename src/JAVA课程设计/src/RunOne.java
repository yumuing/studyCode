import java.util.TreeMap;

public class RunOne implements Runnable{
    //购票线程
    private String name;
    private TreeMap<Integer,Ticket> ticket;
    private int trainNumber;
    private Account act;
    private int bookedNumber;
    public RunOne(String name, TreeMap<Integer, Ticket> ticket, int trainNumber, Account act, int bookedNumber) {
        this.name = name;
        this.ticket = ticket;
        this.trainNumber = trainNumber;
        this.act = act;
        this.bookedNumber = bookedNumber;
    }
    @Override
    public void run() {
        for(int i=0;i<30;i++){
            synchronized (ticket.get(trainNumber)){
                act.updateBookedNumber(ticket,trainNumber,bookedNumber);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(act);
    }
}
