package todolist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class ToDoList extends JFrame {

	private JPanel panel;

	// ToDoList class
	public ToDoList() {

		// 设置 todolist 表格的 title
		JTable table = new JTable();
		String[] columns = { "Title", "Description", "Date", "Priority" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		table.setDragEnabled(true);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		// 添加 “Add” 按键
		JButton add = new JButton("Add");
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		add.addActionListener(event -> {
			addTask(this, model);
		});
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(add);

		// 添加 “Edit” 按键
		JButton edit = new JButton("Edit");
		edit.setAlignmentX(Component.CENTER_ALIGNMENT);
		edit.addActionListener(event -> {
			int row = table.getSelectedRow();
			if (row >= 0) {
				editTask(this, model, row);
			} else {
				JOptionPane.showMessageDialog(buttonPanel, "You haven't chosen an entry to modify!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(edit);

		// 添加 “Delete” 按键
		JButton del = new JButton("Delete");
		del.setAlignmentX(Component.CENTER_ALIGNMENT);
		del.addActionListener(event -> {
			int row = table.getSelectedRow();
			if (row >= 0) {
				int delConfirm = JOptionPane.showConfirmDialog(buttonPanel, "Are you sure you want to delete this entry?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (delConfirm == 0) {
					model.removeRow(row);
				}
			} else {
				JOptionPane.showMessageDialog(buttonPanel, "You haven't chosen an entry to delete!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
        });
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(del);
		
		// 添加 “Help” 按键
		JButton help = new JButton("Help");
		help.setAlignmentX(Component.CENTER_ALIGNMENT);
		String helpMessage = "-Add new a new entry with 'Add' -button\n"
						+ "-Edit an existing entry by clicking on it in the list and pressing the 'Edit' -button\n"
						+ "-Delete an entry from the list by clicking on it and pressing the 'Delete' -button\n"
						+ "-Reorganize the list by column by clicking on it in the list";
		help.addActionListener(event -> {
			JOptionPane.showMessageDialog(buttonPanel, helpMessage, "Help", JOptionPane.PLAIN_MESSAGE);
		});
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(help);

		// 添加 “Top” 按键
		JButton top = new JButton("Top");
		top.setAlignmentX(Component.CENTER_ALIGNMENT);
		top.addActionListener(event -> {
			int row = table.getSelectedRow();
			if (row > 0) {
				int delConfirm = JOptionPane.showConfirmDialog(buttonPanel, "Are you sure you want to top this entry?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (delConfirm == 0) {
					model.moveRow(row, row, 0);
				}
			} else if (row == 0) {
				JOptionPane.showMessageDialog(buttonPanel, "It has already be the top", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(buttonPanel, "You haven't chosen an entry to delete!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(top);

		buttonPanel.add(Box.createVerticalGlue());

		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(0, 0, 880, 200);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePane, buttonPanel);

		add(splitPane);
		initSet();
		pack();
	}

	/**
	 * 添加task界面
	 * @param frame
	 * @param model
	 */
	private void addTask(JFrame frame, DefaultTableModel model) {

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
		JTextField priority = new JTextField();
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
				row[3] = priority.getText();
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
	private void editTask(JFrame frame, DefaultTableModel model, int n) {

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
		JTextField priority = new JTextField(model.getValueAt(n, 3).toString());
		editTestFields.add(priority);
		editPanel.add(editTestFields, BorderLayout.CENTER);

		int editConfirm = JOptionPane.showConfirmDialog(frame, editPanel, "Edit", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (editConfirm == JOptionPane.OK_OPTION) {
			if (!title.getText().isEmpty()) {
				model.setValueAt(title.getText(), n, 0);
				model.setValueAt(description.getText(), n, 1);
				model.setValueAt(date.getText(), n, 2);
				model.setValueAt(priority.getText(), n, 3);
			} else {
				JOptionPane.showMessageDialog(editPanel, "Title is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 一些框架默认的设置
	 */
	private void initSet() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		Image image = new ImageIcon("./todolist/icon.png").getImage();
		setIconImage(image);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyToDoList");
		setVisible(true);
	}
}