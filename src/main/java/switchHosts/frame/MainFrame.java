package switchHosts.frame;

import switchHosts.frame.listener.ListenerService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton addBtn, deleteBtn, applyBtn = new JButton();

    private JList<String> listPanel = new JList<>();

    private String[] hostsName;

    private ListenerService listenerService;

    /**
     * 创建主面板
     */
    public MainFrame() {
        super("switch hosts");
        setSize(1000, 780);
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

        // 创建左边的panel，显示按钮和列表
        createLeftPanel();

        // 创建右边的panel，显示出文本域
        createRightPanel();

        // 显示frame
        setVisible(true);
    }

    /***
     * 添加、删除、应用按钮和hosts列表
     */
    private void createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280, this.getHeight()));

        // 按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(280, 35));
        buttonPanel.add(addBtn = new JButton("添加"));
        buttonPanel.add(deleteBtn = new JButton("删除"));
        buttonPanel.add(applyBtn = new JButton("应用"));

        // 文件列表
        hostsName = new String[]{"hosts1", "hosts2", "hosts3", "hosts4"};
        listPanel = new JList<>(hostsName);
        listPanel.setBorder(BorderFactory.createTitledBorder("HOSTS 列表"));

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(200, this.getHeight() - 100));
        jScrollPane.setViewportView(listPanel);

        leftPanel.add(buttonPanel, BorderLayout.NORTH);
        leftPanel.add(jScrollPane, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        // 添加面板事件
        addActionEvent();
    }

    /**
     * hosts名称和文本域
     */
    private void createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280, this.getHeight()));
        rightPanel.setLayout(new BorderLayout());

        // 名称和保存按钮
        JPanel namePanel = new JPanel();
        JTextField nmeTextField = new JTextField(20);
        nmeTextField.setBorder(BorderFactory.createTitledBorder("名称"));
        namePanel.add(nmeTextField);

        JScrollPane scrollPane = new JScrollPane();
        TextArea textArea = new TextArea(30, 50);
        scrollPane.setViewportView(textArea);

        rightPanel.add(namePanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);
    }

    /**
     * 添加事件
     */
    private void addActionEvent() {
        // 添加按钮
        addBtn.addActionListener(actionEvent -> listenerService.clickAddBtn());

        // 删除按钮
        deleteBtn.addActionListener(actionEvent -> listenerService.clickDeleteBtn());

        // 应用按钮
        applyBtn.addActionListener(actionEvent -> listenerService.clickApplyBtn());

        // 列表选择事件
        listPanel.addListSelectionListener(listSelectionEvent -> listenerService.selectList());

    }

}
