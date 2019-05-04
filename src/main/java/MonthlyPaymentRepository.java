import java.util.ArrayList;

public class MonthlyPaymentRepository {

    private ArrayList<MonthlyPayment> monthlyRepo = new ArrayList<MonthlyPayment>();

    public void AddToRepo(MonthlyPayment payment) {
        monthlyRepo.add(payment);
    }

    public void DeletePayment(int ID) {
        for(MonthlyPayment payment : monthlyRepo) {
            if(payment.getPaymentID() == ID) {
                monthlyRepo.remove(payment);
            }
        }
    }

    public ArrayList<MonthlyPayment> getRepo() {
        return monthlyRepo;
    }
}