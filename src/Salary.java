import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Salary extends JFrame {
	JFrame table;
	JPanel pane;
	JButton attendButton, leaveButton, tableButton, addButton;
	NumericField paymentField;
	DefaultListModel model;
	JList list;
	ActionListener actionListener = new PaymentFieldAction();

	String[] name;
	EmployeeList empList;
	int payment = 0;

	public static void main(String[] args) {
		JFrame w = new Salary("Salary");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(400, 300);
		w.setVisible(true);
	}

	public Salary( String title ) {
		super(title);
		pane = (JPanel)getContentPane();
		empList = new EmployeeList();

		model = new DefaultListModel();
		list = new JList(model);
		JScrollPane sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("一覧"));
		pane.add(sc,BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,4));
		attendButton = new JButton(new AttendAction());
		buttons.add(attendButton);
		leaveButton = new JButton(new LeaveAction());
		buttons.add(leaveButton);
		tableButton = new JButton(new TableAction());
		buttons.add(tableButton);
		addButton = new JButton(new AddNameAction());
		buttons.add(addButton);
		paymentField = new NumericField();
		paymentField.setBorder(new TitledBorder("時給"));
		paymentField.addActionListener(actionListener);
		buttons.add(paymentField);
		pane.add(buttons,BorderLayout.NORTH);
	}

	class AttendAction extends AbstractAction {
		AttendAction() {
			putValue(Action.NAME, "出勤");
		}
		public void actionPerformed(ActionEvent e) {
			Object msg = "名前を選んで下さい";
			Object[] sel = empList.getNames();
			String ans = (String)JOptionPane.showInputDialog(pane, msg, null,
					JOptionPane.QUESTION_MESSAGE, null, sel, null);
			if(ans != null) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
				LocalDateTime time = LocalDateTime.now();
				String strTime = time.format(dtf);
				Employee emplo = empList.findName(ans);
				emplo.setAttendTime(strTime);

				float f = System.currentTimeMillis();
				emplo.setMillisA(f);
				System.out.println(f);

				DefaultListModel model = (DefaultListModel)list.getModel();
				String str = "出勤 " + ans + " " + strTime;
				model.addElement(str);
			}
		}
	}

	class LeaveAction extends AbstractAction {
		LeaveAction() {
			putValue(Action.NAME, "退勤");
		}
		public void actionPerformed(ActionEvent e) {
			Object msg = "名前を選んで下さい";
			Object[] sel = empList.getNames();
			String ans = (String)JOptionPane.showInputDialog(pane, msg, null,
					JOptionPane.QUESTION_MESSAGE, null, sel, null);
			if(ans != null) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
				LocalDateTime time = LocalDateTime.now();
				String strTime = time.format(dtf);
				Employee emplo = empList.findName(ans);
				emplo.setLeaveTime(strTime);

				float f = System.currentTimeMillis();
				emplo.setMillisL(f);
				System.out.println(f);

				DefaultListModel model = (DefaultListModel)list.getModel();
				String str = "退勤 " + ans + " " + strTime;
				model.addElement(str);

				float i = f - emplo.getMillisA();
				System.out.println(i);
				float wkTime = i / 1000 / 60;
				emplo.setWorkingTime(wkTime);
				System.out.println(wkTime);
				int p = payment / 60;
				int s = (int)wkTime * p;
				emplo.setWage(s);
				System.out.println("給料 " + s);
			}
		}
	}

	class TableAction extends AbstractAction {
		TableAction() {
			putValue(Action.NAME, "表");
		}
		public void actionPerformed(ActionEvent e) {
			table = new SalaryTable("Table", empList);
			table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			table.setSize(700, 300);
			table.setLocation(400, 0);
			table.setVisible(true);
		}
	}

	class NumericField extends JTextField {
		String validValues = "0123456789\b";
		public NumericField() {
			enableEvents(AWTEvent.KEY_EVENT_MASK);
		}

		protected void processKeyEvent(KeyEvent e) {
			char chr = e.getKeyChar();
			int code = e.getKeyCode();
			if(code == 0 && validValues.indexOf(chr) == -1) {
				e.consume();
			}
			super.processKeyEvent(e);
		}
	}

	class PaymentFieldAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField source = (JTextField)e.getSource();
			String str = source.getText();
			if(source == paymentField) {
				payment = Integer.parseInt(str);
				empList.setPayment(payment);
				name = empList.getNames();
				for(int i = 0;i < name.length;i ++) {
					Employee emplo = empList.findName(name[i]);
					int p = emplo.getPayment() / 60;
					int s = (int)emplo.getWorkingTime() * p;
					emplo.setWage(s);
				}
			}
		}
	}

	class AddNameAction extends AbstractAction {
		AddNameAction(){
			putValue(Action.NAME,"社員登録");
		}
		public void actionPerformed(ActionEvent e) {
			Object msg = "";
			String ans = JOptionPane.showInputDialog(pane, msg);
			if(!ans.isEmpty() && ans != null) {
				empList.add(new Employee(ans, " ", " ", 0, 0, payment,
						0, 0));
			}
		}
	}
}