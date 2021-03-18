package switchHosts.frame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

public class MainView extends JFrame {

    private JButton addBtn, deleteBtn, applyBtn = new JButton();

    private JList<String> listPanel = new JList<>();

    private final String localDir = System.getProperty("user.home") + "/.switchHosts";

    /**
     * 创建主面板
     */
    public MainView() {
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

        // 读取hosts文件
        processLocalFile();

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
        listPanel = new JList<>();
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
        addBtn.addActionListener(actionEvent -> clickAddBtn());

        // 删除按钮
        deleteBtn.addActionListener(actionEvent -> clickDeleteBtn());

        // 应用按钮
        applyBtn.addActionListener(actionEvent -> clickApplyBtn());

        // 列表选择事件
        listPanel.addListSelectionListener(listSelectionEvent -> selectList());
    }

    private void selectList() {
    }

    private void clickApplyBtn() {
    }

    private void clickDeleteBtn() {
        if (null != listPanel.getSelectedValue()) {
            String fileName = listPanel.getSelectedValue();
            if (!new File(localDir + "/" + fileName).delete()) {
                System.out.println("delete error");
            }
            // 重新读取hosts文件
            readHostsFile();
        }
    }

    /**
     * 点击添加按钮,添加一个新的hosts文件
     */
    private void clickAddBtn() {
        createNewTxtFile(false);
        readHostsFile();
    }

    /**
     * 读取本地hosts文件
     */
    private void processLocalFile() {
        //String osArch = System.getProperty("os.arch");
        //String osName = System.getProperty("os.name");

        // 在user目录下创建.switchHosts目录用于存放hosts文件
        try {
            File localFile = new File(localDir);
            // 如果目录不存在就新建一个并读取
            if (!localFile.exists()) {
                if (localFile.mkdirs()) {
                    createNewTxtFile(true);
                }
            } else {
                // 如果hosts文件不存在就创建一个
                File[] files = localFile.listFiles();
                if (null == files || files.length == 1) {
                    createNewTxtFile(true);
                }
            }
            readHostsFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取刷新hosts文件
     */
    private void readHostsFile() {
        File file = new File(localDir);
        Vector<String> vector = new Vector<>();
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            vector.add(listFile.getName());
        }
        // 移除macOS中的.Ds_store文件
        vector.remove(".DS_Store");

        // 重新加载到JList中
        listPanel.setListData(vector);
    }

    /**
     * 创建一个新的txt文件
     *
     * @param isFirst 是否是第一次创建
     */
    private void createNewTxtFile(boolean isFirst) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String txtName = sdf.format(System.currentTimeMillis());
        String path = localDir + "/" + txtName + UUID.randomUUID().toString().substring(0, 4) + ".txt";

        if (isFirst) {
            try (FileWriter fw = new FileWriter(path, true); BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("#输入hosts，每行一个");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File file = new File(path);
                if (!file.createNewFile()) {
                    System.out.println("createNewFile error");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
