package switchHosts.frame;

import switchHosts.Utils;
import switchHosts.frame.listener.ListenerService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {

    static final URL addIcon = MainFrame.class.getResource("resource/icons/add.png");

    private final JPanel textPanel = new JPanel();

    private ListenerService listenerService;

    /**
     * 创建主面板
     */
    public MainFrame() {
        super("switch hosts");
        setSize(1300, 780);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        // 菜单面板
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("帮助");
        JMenuItem item1 = new JMenuItem("使用说明");
        JMenuItem item2 = new JMenuItem("关于");
        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // 先创建右边的panel，显示出文本域
        //createTextPanel();

        // 创建左边的panel，显示按钮和列表
        createLeftPanel();

        // 显示frame
        this.setVisible(true);
    }

    /***
     * 左边的panel
     */
    private void createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280, this.getHeight()));

        // 按钮
        JPanel leftUpPanel = new JPanel();
        leftUpPanel.setPreferredSize(new Dimension(280, 35));
        JButton addBtn = new JButton("添加");
        addBtn.setIcon(new ImageIcon(addIcon));
        leftUpPanel.add(addBtn);
        leftUpPanel.add(new JButton("删除"));


        // 文件列表
        JPanel leftDownPanel = new JPanel();
        leftDownPanel.setPreferredSize(new Dimension(200, this.getHeight() - 100));
        leftDownPanel.setLayout(new BorderLayout());
        createFieldList(leftDownPanel);

        leftPanel.add(leftUpPanel, BorderLayout.NORTH);
        leftPanel.add(leftDownPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
    }

    /**
     * 创建文件列表panel
     *
     * @param panel panel
     */
    private void createFieldList(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane();
        JTabbedPane table = new JTabbedPane();
        table.addTab("hosts1", textPanel);
        scrollPane.add(table);
        panel.add(scrollPane);
    }

    /**
     * text panel
     */
    private void createTextPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280, 30));
        rightPanel.setLayout(new BorderLayout());

        // 右上名称
        JPanel rightUpPanel = new JPanel();
        rightUpPanel.add(new JLabel("Hosts名称:"));
        JTextField nmeTextField = new JTextField(20);
        nmeTextField.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        rightUpPanel.add(new JButton("保存并应用"));

        rightUpPanel.add(nmeTextField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        TextArea textArea = new TextArea(30, 50);
        scrollPane.setViewportView(textArea);
        //sizeWindowOnScreen(panel, 0.6, 0.6);

        rightPanel.add(rightUpPanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * @param txtPanel   panel
     * @param widthRate  宽度比例
     * @param heightRate 高度比例
     */
    private void sizeWindowOnScreen(JPanel txtPanel, double widthRate, double heightRate) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        txtPanel.setSize(new Dimension((int) (screenSize.width * widthRate), (int) (screenSize.height * heightRate)));
    }
}
