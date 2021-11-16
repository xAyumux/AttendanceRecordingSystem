package ex12;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SalaryTable extends JFrame {
	DefaultTableModel model;
	String[] name;
	JButton read, save;

	String[] columnNames = {"名前", "出勤時刻", "退勤時刻", "勤務時間(分)",
			"時給", "給料"};
	EmployeeList empList;

	public SalaryTable(String title, EmployeeList empList) {
		super(title);
		this.empList = empList;
		name = empList.getNames();
		model = new SalaryTableModel(columnNames, 0);
		JTable table = new JTable();
		table.setModel(model);
		table.setRowHeight(40);
		JScrollPane scr = new JScrollPane(table);
		getContentPane().add(scr);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		read = new JButton(new ReadAction());
		buttons.add(read);
		save = new JButton(new SaveAction());
		buttons.add(save);
		getContentPane().add(buttons, BorderLayout.NORTH);
	}

	class SalaryTableModel extends DefaultTableModel {
		SalaryTableModel(String[] columnNames, int rowNum) {
			super(columnNames, rowNum);
		}
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}

	class ReadAction extends AbstractAction {
		ReadAction() {
			putValue(Action.NAME, "読み込み");
		}
		public void actionPerformed(ActionEvent e) {
			model.setRowCount(0);
			name = empList.getNames();
			for(int i = 0;i < name.length;i ++) {
				Employee emplo = empList.findName(name[i]);
				Object[] data = {name[i], emplo.getAttendTime(),
						emplo.getLeaveTime(), emplo.getWorkingTime(),
						emplo.getPayment(), emplo.getWage()};
				model.addRow(data);
			}
		}
	}

	class SaveAction extends AbstractAction {
		SaveAction() {
			putValue(Action.NAME, "保存");
		}
		public void actionPerformed(ActionEvent e) {
			empList.save("SalaryTable.csv");
		}
	}
}
