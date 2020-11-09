
public class Product implements ProductInterface, Comparable<Product> {

	protected int id;
	protected String title;
	protected int value;
	protected String status = "(in stock)";

	public Product(int id, String title, int value, String status) {
		super();
		this.id = id;
		this.title = title;
		this.value = value;
		this.status = status;

	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String printInfo() {
		return String.format(id + " (type): " + title + ". " + status + "\n");

	}

	public int compareTo(Product product) {
		return Integer.compare(id, product.id);
	}

	@Override
	public String csvRecord() {

		return null;
	}

}
