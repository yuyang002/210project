package model;


// Represents a gift card with a name and balance
public class GiftCard {
    private String name;
    private double balance;

    public GiftCard(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: update the balance of the gift card by decreasing the amount in the balance
    public void payBill(double amount) {
        this.balance = this.balance - amount;
    }

    // EFFECTS: returns true if the two gift cards have the same name and same balance, otherwise returns false
    public boolean giftCardEqual(GiftCard gc) {
        if (gc.getName().equals(this.name) && gc.getBalance() == this.balance) {
            return true;
        } else {
            return false;
        }
    }


}
