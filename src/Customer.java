public class Customer {

	private String name;
	private String number;
	private int ID;

	public Customer(int ID, String name, String number) {
		super();
		this.ID = ID;
		this.name = name;
		this.number = number;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String csvRecord() {
		return String.format("%d,%s,%s", ID, name, number + "\n");
	}

	public static Customer parseCustomer(String csvRecord) {
		String[] values = csvRecord.split(",");
		int ID = Integer.parseInt(values[0]);
		String name = values[1];
		String number = values[2];
		return new Customer(ID, name, number);
	}

}
