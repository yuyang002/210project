package ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundImage extends JComponent {
    private Image image;

    // MODIFIES: this
    // EFFECTS: set the background image
    public BackgroundImage(Image image) {
        this.image = image;
    }

    //EFFECTS: draw the graphics g
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, -30, -40, this);
    }
}
