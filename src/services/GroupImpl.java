package services;

import models.Expense;
import models.Group;
import models.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class GroupImpl implements GroupInterface{
    Group group;
    Map<Member, Double> dashboard;

    public GroupImpl(Group group) {
        this.group = group;
        dashboard = new HashMap<>();
    }

    @Override
    public void addMember(Member member) {
        group.addMember(member);
    }

    @Override
    public boolean deleteMember(int memberId) {
        List<Member> members = group.getMembers();
        int memberToRemove = IntStream.range(0, members.size())
                        .filter(idx -> members.get(idx).getId() == memberId)
                                .findFirst().orElse(-1);

        if (memberToRemove > -1) {
            Member m = members.get(memberToRemove);
            // get dashboard, if expense not settles don't remove
            double memberBalance = dashboard.get(m);
            if(memberBalance != 0) {
                System.out.println("Settle up balance for " + m.getName() + " Current Balance -> " + memberBalance);
                return false;
            }
            members.remove(memberToRemove);
            return true;
        }
        return false;
    }

    @Override
    public void addExpense(Expense expense) {
        group.addExpense(expense);
        updateDashboard();
    }

    @Override
    public void showDashboard() {
        System.out.println("*** Group " + group.getName() + " ****");
        double totalExpense = 0;
        for (Expense e: group.getExpenses()) {
            totalExpense += e.getAmount();
        }
        System.out.println("___________________");
        System.out.println("Total Expense -> " + totalExpense);
        System.out.println("___________________");
        for(Member m: dashboard.keySet()) {
            double balance = dashboard.get(m);
            System.out.println(m.getName() + " -> " + balance);
        }
        System.out.println("**********");
    }

    @Override
    public void showAllExpenses() {
        System.out.println("*** Group " + group.getName() + " ****");
        for (Expense e: group.getExpenses()) {
            System.out.println(e);
        }
        System.out.println("**********");
    }

    public void updateDashboard() {
        for (Member member: group.getMembers()) {
//            int amountPaid = getAmountPaid(member);
//            int amountToPay = getAmountToPay(member);
//            int balance = amountPaid - amountToPay;
            Double balance = getBalance(member);
            dashboard.put(member, balance);
        }
    }

    private double getBalance(Member member) {
        double balance = 0;
        for (Expense expense: group.getExpenses()) {
            Map<Member, Double> expenseBalance = expense.getBalance();
            Double amtOwed = expenseBalance.get(member);
            if(amtOwed != null) {
                balance += expenseBalance.get(member);
            }
        }
        return balance;
    }

//    private int getAmountPaid(Member member) {
//        int amount = 0;
//        for (Expense expense: group.getExpenses()) {
//            Map<Member, Double> paidBy = expense.getPaidBy();
//            if(paidBy.containsKey(member)) {
//                double contribution = paidBy.get(member);
//                amount += contribution;
//            }
//        }
//        return amount;
//    }
//
//    private int getAmountToPay(Member member) {
//        int amount = 0;
//        for(Expense expense: group.getExpenses()) {
//            Map<Member, Double> share = expense.getShare();
//            if (share.containsKey(member)) {
//                double amountOwed = share.get(member);
//                amount += amountOwed;
//            }
//        }
//        return amount;
//    }
}
