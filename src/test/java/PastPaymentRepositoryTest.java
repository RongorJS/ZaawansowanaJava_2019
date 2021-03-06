import logic.Categories;
import logic.PastPayment;
import logic.PastPaymentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PastPaymentRepositoryTest {

    private PastPaymentRepository pastRepo;
    private PastPayment pastPayment;
    private PastPayment pastPayment2;

    @Before
    public void setUp() {
        Date date = new Date(2019,3,12);
        Date date2 = new Date(2018, 4, 10);
        pastPayment = new PastPayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", date);
        pastPayment2 = new PastPayment("pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2", date2);
        pastPayment.setID(1);
        pastPayment2.setID(2);
        this.pastRepo = new PastPaymentRepository();
        pastRepo.addToRepository(pastPayment);
        pastRepo.addToRepository(pastPayment2);
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,Kosmetyki,bardzo pszne,04/12/3919 00:00:00\n" +
                "2,pszne obiad2,14.1,Sprzet,bardzo pszne2,05/10/3918 00:00:00\n", pastRepo.toString());
    }

    @Test
    public void addToRepository() {
        assertEquals(pastRepo.getPastPayments().size(), 2);
    }

    @Test
    public void deletePayment() {
        pastRepo.deletePayment(1);
        assertEquals("2,pszne obiad2,14.1,Sprzet,bardzo pszne2,05/10/3918 00:00:00\n", pastRepo.toString());
    }

    @Test
    public void getRepo() {
        assertEquals(pastRepo.getPastPayments().get(0), pastPayment);
        assertEquals(pastRepo.getPastPayments().get(1), pastPayment2);
    }
}