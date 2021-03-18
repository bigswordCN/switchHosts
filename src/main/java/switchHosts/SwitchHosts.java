package switchHosts;

import switchHosts.frame.MainView;

public class SwitchHosts {
    public static void main(String[] args) {
        // startApp
        javax.swing.SwingUtilities.invokeLater(MainView::new);
    }
}
