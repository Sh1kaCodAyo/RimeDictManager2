package com.ftwrjh.rimedictmanager2.application.node.custom;

import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作
 */
@Slf4j
public class ActionHyperlinkTableCell extends TableCell<DictionaryEntry, Void> {
    private final Hyperlink deleteButton = new Hyperlink("删除");

    // 在构造代码块或构造函数中配置按钮
    {
        deleteButton.setOnAction(event -> {
            DictionaryEntry person = getTableRow().getItem();
            if (person != null) {
                // 从 TableView 的数据列表中移除
                super.getTableView().getItems().remove(person);
                log.info("已删除: {}", person.getWord());
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        // 如果当前行是空行，则不显示按钮；否则显示
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(deleteButton);
        }
    }
}
