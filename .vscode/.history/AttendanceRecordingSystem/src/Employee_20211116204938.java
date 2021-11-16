public class Employee {
	private String name;         //社員名
	private String attendTime;   //出勤時間
	private String leaveTime;    //退勤時間
	private float workingTime;   //勤務時間
	private int wage;            //給料
	private int payment;         //時給
	private float millisA;       //出勤時間のミリ秒
	private float millisL;       //退勤時間のミリ秒

	public Employee(String name, String attendTime, String leaveTime,
			float workingTime, int wage, int payment,
			float millisA, float millisL){
		this.name = name;
		this.attendTime = attendTime;
		this.leaveTime = leaveTime;
		this.workingTime = workingTime;
		this.wage = wage;
		this.payment = payment;
		this.millisA = millisA;
		this.millisL = millisL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttendTime() {
		return attendTime;
	}

	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public float getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(float workingTime) {
		this.workingTime = workingTime;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
	public float getMillisA() {
		return millisA;
	}

	public void setMillisA(float millisA) {
		this.millisA = millisA;
	}
	public float getMillisL() {
		return millisL;
	}

	public void setMillisL(float millisL) {
		this.millisL = millisL;
	}

	public String toString() {
		String str = name + "," + attendTime + "," + leaveTime + ","
				+ workingTime + "," + payment + "," + wage;
		return str;
	}
}
