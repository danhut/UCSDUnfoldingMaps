package GuiModule;

class Tax {
	double grossIncome;
	String state;
	int dependents;
	public double calcTax() {
		return 234.55;
	}
}

class NJTax extends Tax {
	double adjustForStudents (double stateTax) {
		double adjustedTax = stateTax - 500;
		return adjustedTax;
	}
	
}

public class TestTax {

	public static void main(String[] args) {
		NJTax t = new NJTax();
		t.grossIncome = 50000;
		t.dependents = 2;
		t.state = "NJ";
		double yourTax = t.calcTax();
		double totalTax = t.adjustForStudents(yourTax);
		System.out.println("Your tax is " + yourTax);
		System.out.print("your adjusted tax is " + totalTax);

	}

}
