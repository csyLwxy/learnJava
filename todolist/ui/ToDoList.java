package todolist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import todolist.panel.ButtonPanel;


public class ToDoList extends JFrame {

	private JPanel panel;
	private JTable table;
	private DefaultTableModel model;

	// ToDoList class
	public ToDoList() {

		initSet();
		initTable();

		JPanel buttonPanel = new ButtonPanel(this);

		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(0, 0, 880, 200);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePane, buttonPanel);

		add(splitPane);
		pack();
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @return the model
	 */
	public DefaultTableModel getModel() {
		return model;
	}

	/**
	 * 一些框架默认的设置
	 */
	private void initSet() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		Image image = new ImageIcon("./todolist/source/image/icon.png").getImage();
		setIconImage(image);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyToDoList");
		setVisible(true);
	}

	/**
	 * 设置 todolist 表格的 title
	 */
	private void initTable() {
		table = new JTable();
		String[] columns = { "Title", "Description", "Date", "Priority" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		table.setDragEnabled(true);
	}
}