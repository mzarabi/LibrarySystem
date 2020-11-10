import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CustomerLibrary extends ProductLibrary implements LibraryInterface {

	public void addCustomer(Customer customer) {
		customers.add(customer);

	}

	public void removeCustomer(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getID() == id) {
				customers.remove(i);
				writeCustomerToFile();
			}
		}
	}

	public void checkOutProduct(int ID) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < customers.size(); j++) {
				if (products.get(i).getId() == ID) {
					if (customers.get(j).getID() == ID) {
						System.out.println("Cannot lend " + products.get(i).getTitle()
								+ " to another customer. It is already borrowed by " + customers.get(j).getName()
								+ "\n");
						return;
					}

				}
			}
		}
		System.out.println("\nEnter customer name:");
		String name = sc.nextLine();

		System.out.println("\nEnter customer number:");
		String number = sc.nextLine();

		Customer customer = new Customer(ID, name, number);
		customers.add(customer);
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < customers.size(); j++) {
				if (products.get(i).getId() == ID) {
					if (customers.get(j).getID() == ID) {
						String status = "** Currently borrowed by: " + name + ", Phone number: " + number + " **";
						products.get(i).setStatus(status);
						writeProductToFile();
						writeCustomerToFile();
						System.out.println("Successfully lended " + products.get(i).getTitle() + " to "
								+ customers.get(j).getName() + "\n");

						return;

					}
				}
			}
		}
	}


	public void checkInProduct(int id) {
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < customers.size(); j++)
				if (products.get(i).getId() == id) {
					if (customers.get(j).getID() == id) {
						products.get(i).setStatus("(in stock)");
						System.out.println("Succesfully returned " + products.get(i).getTitle() + " from "
								+ customers.get(j).getName() + "\n");
						customers.remove(j);
						writeProductToFile();
						writeCustomerToFile();

					}
				}
		}
	}

	public void writeCustomerToFile() {

		String filePath = "customer.csv";
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath);
		} catch (IOException e) {
			System.out.println("Could not create file");
		}

		for (Customer customers : customers) {
			try {
				fw.write(customers.csvRecord());
			} catch (IOException e) {
				System.out.println("Could not write to file");
			}
		}
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println("Problem closing writer");
		}
	}

	public Customer readCustomerFile() {
		FileReader fr = null;
		try {
			fr = new FileReader("customer.csv");
		} catch (IOException e) {
			System.out.println("Could not find file");
		}
		Scanner sc = new Scanner(fr);
		while (sc.hasNextLine()) {
			String csvRecord = sc.nextLine();
			customers.add(Customer.parseCustomer(csvRecord));
		}
		return null;
	}
}
