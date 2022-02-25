import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private int trainNumber;//班次
    private String departureTime;//发车时间
    private String startStation;//起点站
    private String railwayTerminal;//终点站
    private int runTime;//行车时间
    private int rateCapacity;//额度容量
    private int bookedNumber;//已订票人数

    public Ticket() {
    }

    public Ticket(Ticket ticket){
        this.trainNumber=ticket.getTrainNumber();
        this.departureTime=ticket.getDepartureTime();
        this.startStation=ticket.getStartStation();
        this.railwayTerminal=ticket.getRailwayTerminal();
        this.runTime=ticket.getRunTime();
        this.rateCapacity=ticket.getRateCapacity();
        this.bookedNumber=ticket.getBookedNumber();
    }
    public Ticket(int trainNumber, String departureTime, String startStation, String railwayTerminal,
                  int runTime, int rateCapacity, int bookedNumber) {
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.startStation = startStation;
        this.railwayTerminal = railwayTerminal;
        this.runTime = runTime;
        this.rateCapacity = rateCapacity;
        this.bookedNumber = bookedNumber;
    }
    @Override
    public String toString() {
        return "车票信息：" +
                "班次为：" + trainNumber +
                "；发车时间为：" + departureTime + '\'' +
                "； 起点站为：" + startStation + '\'' +
                "； 终点站为：" + railwayTerminal + '\'' +
                "； 行车时间为：" + runTime +
                "； 额定载量为：" + rateCapacity +
                "； 已订票数为：" + bookedNumber ;
    }
    //判断是否超过时间,是返回true，否则为false
    public boolean timeIsOver() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // new Date()为获取当前系统时间,利用Date的compareTo方法比较时间
      Date d1 = new Date();
      Date d2 = df.parse(this.departureTime);
      if (d1.compareTo(d2)>=0){
          return true;
      }
      else{
         return false;
      }
    }

    //判断车票是否已售完，是返回true，否为false
    public boolean ticletIsEmpty(){
        return this.bookedNumber == this.rateCapacity;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getRailwayTerminal() {
        return railwayTerminal;
    }

    public void setRailwayTerminal(String railwayTerminal) {
        this.railwayTerminal = railwayTerminal;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getRateCapacity() {
        return rateCapacity;
    }

    public void setRateCapacity(int rateCapacity) {
        this.rateCapacity = rateCapacity;
    }

    public int getBookedNumber() {
        return bookedNumber;
    }

    public void setBookedNumber(int bookedNumber) {
        this.bookedNumber = bookedNumber;
    }
}
