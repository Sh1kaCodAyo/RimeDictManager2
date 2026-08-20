package com.ftwrjh.rimedictmanager2.application.custom;

import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 * 行号单元格：悬停时显示文件路径
 */
public class LineNumberTableCell extends TableCell<DictionaryEntry, Integer> {
    @Override
    protected void updateItem(Integer lineNumber, boolean empty) {
        super.updateItem(lineNumber, empty);

        if (empty || lineNumber == null) {
            setText(null);
            setTooltip(null);
        } else {
            setText(lineNumber.toString());

            DictionaryEntry entry = getTableRow().getItem();
            if (entry != null) {
                String fullPath = entry.getFullPath();  // 获取完整路径
                // 创建 Tooltip 并设置
                Tooltip tooltip = new Tooltip(fullPath + ": 第" + lineNumber + "行");
                tooltip.setShowDelay(Duration.millis(300));
                setTooltip(tooltip);
            }
        }
    }
}
