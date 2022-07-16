import models.Expense;
import models.Group;
import models.Member;
import services.GroupImpl;
import services.GroupInterface;

import java.util.Map;

public class Splitwise {
    public static void main(String[] args) {
        test();
    }

    static void test() {
        Group g1 = new Group("Spanish Connection");
        Member alonso = new Member(3, "Marcos Alonso", "clinicalfinisher@chelseafc");
        Member azpi = new Member(28, "Cesar Azpi", "realdave@chelseadc");
        Member kepa = new Member(1, "Kepa Arrizabalaga", "biscuitHands@chelseadc");
        Member cesc = new Member(4, "Cesc Fabregas", "magician@chelseadc");
//        g1.addMember(alonso);
//        g1.addMember(azpi);
//        g1.addMember(kepa);
//        g1.addMember(cesc);

        int amount1 = 1000;
        Expense e1 = new Expense(
                amount1,
                Map.of(azpi, 0.5 * (amount1), cesc, 0.5 * (amount1)),
                Map.of(azpi, 0.5 * (amount1), cesc, 0.5 * (amount1)),
                "Wimbledon"
        );

        int amount2 = 1500;
        Expense e2 = new Expense(
                amount2,
                Map.of(cesc, 0.75 * (amount2), alonso, 0.25 * (amount2)),
                Map.of(alonso, 0.25 * (amount2), cesc, 0.25 * (amount2), azpi, 0.25 * (amount2), kepa, 0.25 * (amount2)),
                "UCL"
        );

        int amount3 = 2500;
        Expense e3 = new Expense(
                amount2,
                Map.of(cesc, 1.00 * (amount3)),
                Map.of(kepa, 1.00 * (amount3)),
                "Kepa Birthday"
        );


        GroupInterface espanyol = new GroupImpl(g1);
        espanyol.addMember(alonso);
        espanyol.addMember(azpi);
        espanyol.addMember(cesc);
        espanyol.addMember(kepa);
        espanyol.addExpense(e1);
        espanyol.addExpense(e2);
        espanyol.addExpense(e3);
        espanyol.showDashboard();

    }
}
