package ui;

import model.Account;
import model.Event;
import model.EventLog;
import persistence.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame implements ActionListener  {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    private static final String VIEW_LIST_ACTION = "VIEW_LIST_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";
    private final Account account = new Account("wyy", 0);
    private ListView listView;

    // EFFECTS: run the application
    public MainView() {
        super("GiftCard Application");
        this.setWindow();
        this.setBackgroundImage();
        Loader.load(account);
        this.setUpLabelsAndButtons();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: sets the window of the application
    private void setWindow() {
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
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
    // EFFECTS: set up the all the labels and buttons inside the window
    private void setUpLabelsAndButtons() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JButton viewListButton = new JButton("View list");
        viewListButton.setBounds(BUTTON_POSITION, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(viewListButton);
        viewListButton.setActionCommand(VIEW_LIST_ACTION);
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.black);

        JButton quitAppButton = new JButton("Quit GiftCard Application");
        quitAppButton.setBounds(BUTTON_POSITION, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: the actions when someone clicks "VIEW_LIST_ACTION" or "QUIT_APP_ACTION" button
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(VIEW_LIST_ACTION)) {
            listView = new ListView(account);
        } else if (action.equals(QUIT_APP_ACTION)) {
            listView.dispose();
            dispose();
            printLog();
        }
    }

    public void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

}

