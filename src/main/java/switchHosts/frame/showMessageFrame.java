package switchHosts.frame;

import javax.swing.*;
import java.awt.*;

public class showMessageFrame extends JFrame {
    private final String message;

    public showMessageFrame(String message) {
        this.message = message;
        new Thread(this::initGUI).start();
    }

    private void initGUI() {
        try {
            setUndecorated(true);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JLabel text = new JLabel("<html>" + message + "</html>", JLabel.CENTER);
            getContentPane().add(text, BorderLayout.CENTER);
            text.setBackground(new java.awt.Color(255, 251, 240));

            pack();
            setBounds(500, 300, 160, 50);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
