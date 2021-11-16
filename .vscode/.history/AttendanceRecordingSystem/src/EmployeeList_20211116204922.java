package ex12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmployeeList {
	private ArrayList<Employee> list;

	public EmployeeList() {
		list = new ArrayList<Employee>();
	}

	public void add(Employee employee) {
		list.add(employee);
	}

	public void remove(Employee employee) {
		list.remove(employee);
	}

	public Employee findName(String name) {
		for(Employee employee : list) {
			if(name.equals(employee.getName())) {
				return employee;
			}
		}
		return null;
	}

	public String[] getNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Employee employee : list) {
			names.add(employee.getName());
		}
		String[] nameList = new String[names.size()];
		for(int i = 0;i < names.size();i ++) {
			nameList[i] = names.get(i);
		}
		return nameList;
	}

	public void setPayment(int payment) {
		for(Employee employee : list) {
			employee.setPayment(payment);
		}
	}

	public void save(String fileName) {
		try {
			File file = new File(fileName);
			PrintWriter writer = new PrintWriter(new BufferedWriter
	                (new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));
			for(Employee emplo : list) {
				writer.println(emplo);
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}