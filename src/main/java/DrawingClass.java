import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class DrawingClass {
    private ArrayList<PastPayment> localList;
    private int ID;
    private float price;
    private String description;
    private Date date;

    public DrawingClass(ArrayList<PastPayment> pastPayment){
        this.localList = pastPayment;
    }

    private ArrayList<PastPayment> returnPaymentsInMonth(int month){
        ArrayList<PastPayment> turboLocalList = new ArrayList<PastPayment>();
        for (PastPayment pp : localList){
            if(pp.getPaymentDate().getMonth() == month)
                turboLocalList.add(pp);
        }
        return turboLocalList;
    }

    private ArrayList<PastPayment> returnPaymentBetweenDates(int month,int dayOne,int dayLast)
    {
        ArrayList<PastPayment> turboLocalList = new ArrayList<PastPayment>();
        for(PastPayment pp : localList){
            if(pp.getPaymentDate().getMonth() == month && pp.getPaymentDate().getDay() >= dayOne && pp.getPaymentDate().getDay() <= dayLast)
                turboLocalList.add(pp);
        }
        return turboLocalList;
    }

    public void DebugPaymentsInMonth(int month){
        ArrayList<PastPayment> pastPayments = new ArrayList<PastPayment>();
        pastPayments = returnPaymentsInMonth(month);
        for (PastPayment pp : pastPayments ) {
            System.out.println(pp.toString());
        }
    }

    public void DebugPaymentsBetweenDates(int month,int dayOne,int dayLast){
        ArrayList<PastPayment> pastPayments = new ArrayList<PastPayment>();
        pastPayments = returnPaymentBetweenDates(month,dayOne,dayLast);
        for (PastPayment pp : pastPayments ) {
            System.out.println(pp.toString());
        }
    }

}