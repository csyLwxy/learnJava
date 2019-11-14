package todolist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class Editor {

	/**
	 * 添加task界面
	 * @param frame
	 * @param model
	 */
	public static void addTask(JFrame frame, JPanel panel, DefaultTableModel model) {

		JPanel addPanel = new JPanel(new BorderLayout(5, 5));
		
		// 输入提示
		JPanel addLabel = new JPanel(new GridLayout(0, 1, 2, 2));
		addLabel.add(new JLabel("Title", SwingConstants.RIGHT));
		addLabel.add(new JLabel("Description", SwingConstants.RIGHT));
		addLabel.add(new JLabel("Date", SwingConstants.RIGHT));
		addLabel.add(new JLabel("Priority", SwingConstants.RIGHT));
		addPanel.add(addLabel, BorderLayout.WEST);

		// 设置输入文本框
		JPanel addTestFields = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField title = new JTextField();
		addTestFields.add(title);
		JTextField description = new JTextField();
		addTestFields.add(description);
		JTextField date = new JTextField();
        addTestFields.add(date);
        JComboBox<String> priority = new JComboBox<>();
        priority.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4"}));
		addTestFields.add(priority);
		addPanel.add(addTestFields, BorderLayout.CENTER);

		int addConfirm = JOptionPane.showConfirmDialog(frame, addPanel, "Add", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (addConfirm == JOptionPane.OK_OPTION) {
			if (!title.getText().isEmpty()) {
				Object[] row = new Object[4];
				row[0] = title.getText();
				row[1] = description.getText();
				row[2] = date.getText();
				row[3] = priority.getSelectedItem().toString();
				model.addRow(row);
			} else {
				JOptionPane.showMessageDialog(panel, "Title is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 编辑task的界面
	 * @param frame
	 * @param model
	 * @param n
	 */
	public static void editTask(JFrame frame, JPanel panel, DefaultTableModel model, int n) {

		JPanel editPanel = new JPanel(new BorderLayout(5, 5));

		// 输入提示
		JPanel editLabel = new JPanel(new GridLayout(0, 1, 2, 2));
		editLabel.add(new JLabel("Title", SwingConstants.RIGHT));
		editLabel.add(new JLabel("Description", SwingConstants.RIGHT));
		editLabel.add(new JLabel("Date", SwingConstants.RIGHT));
		editLabel.add(new JLabel("Priority", SwingConstants.RIGHT));
		editPanel.add(editLabel, BorderLayout.WEST);

		// 设置编辑文本框
		JPanel editTestFields = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField title = new JTextField(model.getValueAt(n, 0).toString());
		editTestFields.add(title);
		JTextField description = new JTextField(model.getValueAt(n, 1).toString());
		editTestFields.add(description);
		JTextField date = new JTextField(model.getValueAt(n, 2).toString());
		editTestFields.add(date);
		JComboBox<String> priority = new JComboBox<>();
		priority.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4"}));
		priority.setSelectedIndex(Integer.parseInt(model.getValueAt(n, 3).toString()) - 1);
		editTestFields.add(priority);
		editPanel.add(editTestFields, BorderLayout.CENTER);

		int editConfirm = JOptionPane.showConfirmDialog(frame, editPanel, "Edit", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (editConfirm == JOptionPane.OK_OPTION) {
			if (!title.getText().isEmpty()) {
				model.setValueAt(title.getText(), n, 0);
				model.setValueAt(description.getText(), n, 1);
				model.setValueAt(date.getText(), n, 2);
				model.setValueAt(priority.getSelectedItem().toString(), n, 3);
			} else {
				JOptionPane.showMessageDialog(editPanel, "Title is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}