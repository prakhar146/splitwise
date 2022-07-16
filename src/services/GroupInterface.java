package services;

import models.Expense;
import models.Member;

public interface GroupInterface {
    void addMember(Member member);
    boolean deleteMember(int memberId);
    void addExpense(Expense expense);
    void showDashboard();
    void showAllExpenses();
}
