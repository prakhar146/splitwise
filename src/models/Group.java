package models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String name;
    private final List<Member> members;
    private final List<Expense> expenses;

    public Group(List<Member> members, String name) {
        this.members = members;
        expenses = new ArrayList<>();
        this.name = name;
    }

    public Group(String name) {
        this.members = new ArrayList<>();
        this.name = name;
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
