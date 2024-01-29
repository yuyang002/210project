package ui;

import model.Account;
import model.GiftCard;
import persistence.Saver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveGiftCardView extends JFrame implements ActionListener {
    JTextField giftCardNameField;
    ListView listView;
    Account account;
    private static final String FINISH_ACTION = "FINISH_ACTION";
    private static final String FINISH_SAVE_ACTION = "FINISH_SAVE_ACTION";

    // EFFECTS: constructs a window to type in the name of giftCard when removing a giftCard
    public RemoveGiftCardView(ListView listView, Account account) {
        super("Remove a GiftCard");
        this.listView = listView;
        this.account = account;
        this.setWindow();

        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: sets the window for removing the giftCard
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: set up all the labels and buttons inside the window
    private void setLabelsFieldsButtons() {
        JLabel giftCardNameLabel = new JLabel("Enter giftCard name: ");
        giftCardNameLabel.setBounds(48, 40, 400, 20);
        add(giftCardNameLabel);
        giftCardNameLabel.setForeground(Color.darkGray);

        giftCardNameField = new JTextField(30);
        giftCardNameField.setBounds(50, 70, 300, 20);
        add(giftCardNameField);


        JButton finishButton = new JButton("Finish without saving");
        finishButton.setBounds(170,210,300,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);

        JButton finishAndSaveButton = new JButton("Finish and Save");
        finishAndSaveButton.setBounds(170,240,300,20);
        add(finishAndSaveButton);
        finishAndSaveButton.setActionCommand(FINISH_SAVE_ACTION);
        finishAndSaveButton.addActionListener(this);
        finishAndSaveButton.setForeground(Color.darkGray);
    }

    // MODIFIES: this
    // EFFECTS: the actions when someone clicks "FINISH_ACTION" or "FINISH_SAVE_ACTION" button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String name = giftCardNameField.getText();
            account.removeGiftCard(name);
            listView.dispose();
            new ListView(account);
            dispose();
        }
        if (action.equals(FINISH_SAVE_ACTION)) {
            String name = giftCardNameField.getText();
            account.removeGiftCard(name);
            Saver.save(account);
            listView.dispose();
            new ListView(account);
            dispose();
        }
    }
}


