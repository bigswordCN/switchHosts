package switchHosts.frame.listener;

import java.awt.event.ActionListener;

public interface ListenerService extends ActionListener {
    /**
     * 点击保存按钮
     */
    void clickAddBtn();

    /**
     * 点击删除按钮
     */
    void clickDeleteBtn();

    /**
     * 点击应用按钮
     */
    void clickApplyBtn();

    /**
     * hosts列表选择事件
     */
    void selectList();
}
