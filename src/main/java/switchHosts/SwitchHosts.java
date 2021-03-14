package switchHosts;

import switchHosts.frame.MainFrame;

public class SwitchHosts {
    public static void main(String[] args) {
        // startApp
        javax.swing.SwingUtilities.invokeLater(MainFrame::new);
    }
}
