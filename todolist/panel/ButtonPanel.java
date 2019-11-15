package todolist;

import javax.swing.*;
import java.awt.*;

import todolist.ToDoList;

public class ButtonPanel extends JPanel {

    public ButtonPanel(ToDoList frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 添加 “Add” 按键
		JButton add = new JButton("Add");
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		add.addActionListener(event -> {
			Editor.addTask(frame, frame.getPanel(),frame.getModel());
		});
	    add(Box.createVerticalGlue());
		add(add);

		// 添加 “Edit” 按键
		JButton edit = new JButton("Edit");
		edit.setAlignmentX(Component.CENTER_ALIGNMENT);
		edit.addActionListener(event -> {
			int row = frame.getTable().getSelectedRow();
			if (row >= 0) {
				Editor.editTask(frame, frame.getPanel(), frame.getModel(), row);
			} else {
				JOptionPane.showMessageDialog(this, "You haven't chosen an entry to modify!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		add(Box.createVerticalGlue());
		add(edit);

		// 添加 “Delete” 按键
		JButton del = new JButton("Delete");
		del.setAlignmentX(Component.CENTER_ALIGNMENT);
		del.addActionListener(event -> {
			int row = frame.getTable().getSelectedRow();
			if (row >= 0) {
				int delConfirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (delConfirm == 0) {
					frame.getModel().removeRow(row);
				}
			} else {
				JOptionPane.showMessageDialog(this, "You haven't chosen an entry to delete!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
        });
		add(Box.createVerticalGlue());
		add(del);
		
		// 添加 “Help” 按键
		JButton help = new JButton("Help");
		help.setAlignmentX(Component.CENTER_ALIGNMENT);
		String helpMessage = "-Add new a new entry with 'Add' -button\n"
						+ "-Edit an existing entry by clicking on it in the list and pressing the 'Edit' -button\n"
						+ "-Delete an entry from the list by clicking on it and pressing the 'Delete' -button\n"
						+ "-Reorganize the list by column by clicking on it in the list";
		help.addActionListener(event -> {
			JOptionPane.showMessageDialog(this, helpMessage, "Help", JOptionPane.PLAIN_MESSAGE);
		});
		add(Box.createVerticalGlue());
		add(help);

		// 添加 “Top” 按键
		JButton top = new JButton("Top");
		top.setAlignmentX(Component.CENTER_ALIGNMENT);
		top.addActionListener(event -> {
			int row = frame.getTable().getSelectedRow();
			if (row > 0) {
				int delConfirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to top this entry?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (delConfirm == 0) {
					frame.getModel().moveRow(row, row, 0);
				}
			} else if (row == 0) {
				JOptionPane.showMessageDialog(this, "It has already be the top", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "You haven't chosen an entry to delete!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
        add(Box.createVerticalGlue());
		add(top);

		add(Box.createVerticalGlue());
    }
}