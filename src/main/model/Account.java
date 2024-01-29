package model;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

// Represents an account having an owner name and balance (in dollars)
public class Account {
    public static final String PATH = "./data/myFile.json";
    private String name;
    private double balance;
    private ArrayList<GiftCard> giftCards;

    // REQUIRES: balance >= 0
    // EFFECTS: constructs an account with given name
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        giftCards = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.totalBalance();
    }

    public ArrayList<GiftCard> getGiftCards() {
        return giftCards;
    }

    // EFFECTS: empty the savedGifCards file
    public void clearFile() {
        try {
            PrintWriter writer = new PrintWriter(PATH,"UTF-8");
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving gift card list.");
        }
    }

    // MODIFIES: this
    // EFFECTS: add the gift card to this account
    public void addGiftCard(GiftCard giftCard) {
        if (!this.giftCards.contains(giftCard)) {
            this.giftCards.add(giftCard);
            EventLog.getInstance().logEvent(new Event("A giftCard named " + giftCard.getName()
                    + " with balance " + giftCard.getBalance() + " is added to this account."));
        } else {
            System.out.println("This gift card is already in this account!");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the gift card from this account
    public void removeGiftCard(String giftCardName) {
        Iterator<GiftCard> it = this.giftCards.iterator();
        while (it.hasNext()) {
            GiftCard gc = it.next();
            if (gc.getName().equals(giftCardName)) {
                it.remove();
                EventLog.getInstance().logEvent(new Event("This giftCard named " + giftCardName
                        + " is removed from this account!"));
            }
        }

    }



    // EFFECTS: add the balances of all the giftCards in this account together and return the result
    public double totalBalance() {
        int totalBalance = 0;
        for (GiftCard gc : giftCards) {
            totalBalance += gc.getBalance();
        }
        totalBalance += this.balance;
        return totalBalance;
    }

    // MODIFIES: this
    // EFFECTS: remove the gift card with zero balance
    public void removeInvalidGiftCards() {
        Iterator<GiftCard> it = giftCards.iterator();
        while (it.hasNext()) {
            GiftCard gc = it.next();
            if (gc.getBalance() == 0) {
                it.remove();
            }
        }
    }

    // EFFECTS: get the gift card with the least balance in this account
    public GiftCard giftCardWithLeastBalance() {
        GiftCard current = giftCards.get(0);
        for (GiftCard gc : giftCards) {
            if (gc.getBalance() <= current.getBalance()) {
                current = gc;
            }
        }
        return current;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: pay the bill using the account
    public void payForTheBill(double amount) {
        while (amount > 0) {
            GiftCard gc = this.giftCardWithLeastBalance();
            if (gc.getBalance() >= amount) {
                gc.payBill(amount);
                this.removeInvalidGiftCards();
                EventLog.getInstance().logEvent(new Event("The amount of " + amount
                        + " in this bill is paid using giftCard " + gc.getName()));
                amount = 0;
            } else {
                double gcBalance = gc.getBalance();
                gc.payBill(gcBalance);
                this.removeInvalidGiftCards();
                EventLog.getInstance().logEvent(new Event("The amount of " + gcBalance
                        + " in this bill is paid using giftCard " + gc.getName()));
                amount -= gcBalance;
            }
        }



    }


    // EFFECTS: print the list of gift cards in the account
    public void printGiftCards() {
        for (GiftCard gc : giftCards) {
            System.out.println(gc.getName() + gc.getBalance());
        }
    }

}
