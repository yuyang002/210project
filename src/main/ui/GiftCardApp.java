package ui;

import model.Account;
import model.GiftCard;
import persistence.Loader;
import persistence.Saver;

import java.util.ArrayList;
import java.util.Scanner;

// gift cards management application
public class GiftCardApp {
    private Account account;
    private Scanner input;

    // EFFECTS: runs the GiftCard application
    public GiftCardApp() {
        runGiftCard();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGiftCard() {
        boolean keepGoing = true;
        String command = null;

        init();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                displayChoice();
                command = input.next();
                command = command.toLowerCase();
                if (command.equals("y")) {
                    doSaveGiftCards();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you! Have a nice day!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddGiftCard();
        } else if (command.equals("r")) {
            doRemoveGiftCard();
        } else if (command.equals("p")) {
            doPayBill();
        } else if (command.equals("g")) {
            doPrintGiftCards();
        } else if (command.equals("l")) {
            doLoadGiftCards();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        account = new Account("wyy", 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> addGiftCard");
        System.out.println("\tr -> removeGiftCard");
        System.out.println("\tp -> payBill");
        System.out.println("\tg -> printGiftCards");
        System.out.println("\tl -> loadGiftCards");
        System.out.println("\tq -> quit and decide to saveGiftCards or not");
    }

    private void displayChoice() {
        System.out.println("\tDo you want to save the information of this account to file? y -> yes, n -> no ");
    }

    // MODIFIES: this
    // EFFECTS: add a gift card to the account
    private void doAddGiftCard() {
        System.out.println("Enter the name of the gift card you want to add:");
        String giftCardName = input.next();
        System.out.println("Enter the balance of the gift card you want to add:");
        double giftCardBalance = input.nextDouble();

        if (giftCardBalance >= 0.0) {
            GiftCard giftCard = new GiftCard(giftCardName, giftCardBalance);
            account.addGiftCard(giftCard);
        } else {
            System.out.println("Cannot deposit negative amount...\n");
        }

        printBalance(account);
    }

    // MODIFIES: this
    // EFFECTS: remove a gift card from the account
    private void doRemoveGiftCard() {
        System.out.println("Enter the name of the gift card you want to remove:");
        String giftCardName = input.next();
        ArrayList<String> giftCardNameList = new ArrayList<>();
        for (GiftCard gc : account.getGiftCards()) {
            giftCardNameList.add(gc.getName());
        }
        if (giftCardNameList.contains(giftCardName)) {
            account.removeGiftCard(giftCardName);
            printBalance(account);
        } else {
            System.out.println("The gift card is not found in this account.");
        }

    }

    // MODIFIES: this
    // EFFECTS: pay for the bill using gift card in this account
    private void doPayBill() {
        System.out.println("Enter the amount of the bill you need to pay for: ");
        double amount = input.nextDouble();
        if (amount <= account.totalBalance()) {
            account.payForTheBill(amount);
            System.out.println("The total balance in this account after paying for this bill: " + account.getBalance());
        } else {
            System.out.println("The balance in this account is not enough to pay for this bill!");
        }
    }


    // EFFECTS: save the gift cards in the account to the file
    private void doSaveGiftCards() {
        Saver.save(account);
    }

    // EFFECTS: load the gift cards in the account from the file and print the current balance
    private void doLoadGiftCards() {
        Loader.load(account);
        printBalance(account);
    }

    // EFFECTS: prints balance of account to the screen
    private void printBalance(Account account) {
        System.out.printf("Balance: $%.2f\n", account.getBalance());
    }

    // EFFECTS: print the gift card list in this account
    public void doPrintGiftCards() {
        System.out.println("List of gift cards in this account: ");
        for (GiftCard gc : account.getGiftCards()) {
            System.out.println("Name: " + gc.getName() + "  Balance: " + gc.getBalance());
        }
    }

}
