package models;

import java.util.HashMap;
import java.util.Map;

public class Expense {
    private final int amount;
    private final String activity;
    private final Map<Member, Double> paidBy;
    private final Map<Member, Double> share;
    private final Map<Member, Double> balance;

    public Expense(int amount, Map<Member, Double> paidBy, Map<Member, Double> share, String activity) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.share = share;
        this.activity = activity;
        this.balance = new HashMap<>();
        this.balanceShare();
    }

    public int getAmount() {
        return amount;
    }

    public Map<Member, Double> getShare() {
        return share;
    }


    @Override
    public String toString() {
        return "Expense{\n" +
                "activity=" + activity +
                ", amount=" + amount +
                ", PAID BY" + printMap(paidBy, "") +
                "SHARE" + printMap(share, "") +
                "BALANCE" + printMap(balance, "") +
                '}' + "#########\n";
    }

    public Map<Member, Double> getBalance() {
        return balance;
    }

    private String printMap(Map<Member, Double> map, String label) {
        StringBuilder sb = new StringBuilder();
        sb.append(label + " \n");
        for(Member member: map.keySet()) {
            sb.append(member.getName() + " -> " + map.get(member) + "\n");
        }
        return sb.toString();
    }

    private void balanceShare() {
        for(Member m: paidBy.keySet()) {
            double amountPaid = paidBy.get(m);
            this.balance.put(m, amountPaid);
        }

        for (Member m: share.keySet()) {
            double amountPaid = 0;
            if(paidBy.containsKey(m)) {
                amountPaid += paidBy.get(m);
            }
            double memberShare = -1 * share.get(m);
            double memberBalance = memberShare + amountPaid;
            this.balance.put(m, memberBalance);
        }
    }
}
