package switchHosts;

import switchHosts.frame.MainFrame;

public class SwitchHosts {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame startApp = new MainFrame();
        });
    }
}
