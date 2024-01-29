package ui;

import model.Account;
import model.GiftCard;
import persistence.Saver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ListView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private Account account;
    private static final String ADD_GiftCard_ACTION = "ADD_GiftCard_ACTION";
    private static final String REMOVE_GiftCard_ACTION = "REMOVE_GiftCard_ACTION";
    private static final String PURCHASE_ACTION = "PURCHASE_ACTION";
    private static final String SAVE_LIST = "SAVE_LIST";

    // EFFECTS: constructs the window showing the list of giftCards in the account
    public ListView(Account account) {
        this.account = account;
        this.setBackgroundImage();
        final String[] columnLabels = new String[]{
                "Name",
                "Balance"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle("my giftCard list");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set up the background of the window
    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/images/background.jpg"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs the table for the list of giftCards in the account
    private void populateTableRows() {
        for (int i = 0; i < account.getGiftCards().size(); i++) {
            GiftCard giftCard = account.getGiftCards().get(i);
            Object[] tableRow = new Object[]{
                    giftCard.getName(), // name column
                    giftCard.getBalance() // balance column
            };
            tableModel.addRow(tableRow);
        }
    }

    // MODIFIES: this
    // EFFECTS: set up the all the labels and buttons inside the window
    private void setButtons() {
        JButton addGiftCardButton = new JButton(("Add a new giftCard"));
        add(addGiftCardButton);
        addGiftCardButton.setActionCommand(ADD_GiftCard_ACTION);
        addGiftCardButton.addActionListener(this);
        addGiftCardButton.setForeground(Color.darkGray);

        JButton removeGiftCardButton = new JButton(("Remove a giftCard"));
        add(removeGiftCardButton);
        removeGiftCardButton.setActionCommand(REMOVE_GiftCard_ACTION);
        removeGiftCardButton.addActionListener(this);
        removeGiftCardButton.setForeground(Color.darkGray);

        JButton purchaseButton = new JButton(("Pay for a bill"));
        add(purchaseButton);
        purchaseButton.setActionCommand(PURCHASE_ACTION);
        purchaseButton.addActionListener(this);
        purchaseButton.setForeground(Color.darkGray);

        JButton saveButton = new JButton(("Save the current list to the file"));
        add(saveButton);
        saveButton.setActionCommand(SAVE_LIST);
        saveButton.addActionListener(this);
        saveButton.setForeground(Color.darkGray);
    }

    // MODIFIES: this
    // EFFECTS: the actions when someone clicks "ADD_GiftCard_ACTION", "REMOVE_GiftCard_ACTION",
    // "PURCHASE_ACTION" or "SAVE_LIST" button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_GiftCard_ACTION)) {
            new AddGiftCardView(this, account);
        }
        if (action.equals(REMOVE_GiftCard_ACTION)) {
            new RemoveGiftCardView(this, account);
        }
        if (action.equals(PURCHASE_ACTION)) {
            new PurchaseView(this, account);
        }
        if (action.equals(SAVE_LIST)) {
            Saver.save(account);
        }
    }
}


