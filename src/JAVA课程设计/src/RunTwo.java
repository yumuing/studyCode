import java.util.TreeMap;
public class RunTwo implements Runnable{
    //退票线程
    private String name;
    private TreeMap<Integer,Ticket> ticket;
    private  int trainNumber;
    private  int returnTickets;
    private  Account act;
    public RunTwo(String name, TreeMap<Integer, Ticket> ticket, int trainNumber, int returnTickets, Account act) {
        this.name = name;
        this.ticket = ticket;
        this.trainNumber = trainNumber;
        this.returnTickets = returnTickets;
        this.act = act;
    }
    @Override
    public void run() {
        for(int i=0;i<30;i++){
            synchronized (ticket.get(trainNumber)){
                System.out.printf(name);
                act.returnTicket(ticket,trainNumber,returnTickets,act);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
