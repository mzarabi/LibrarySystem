
public abstract class Product implements ProductInterface, LibraryInterface, Comparable<Product> {

	protected int id;
	protected String title;
	protected int value;
	protected String status = "";
	private String stamp = "";

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

	public String printInfo() {
		return String.format(id + stamp + title + ". " + status + "\n");

	}

	public int compareTo(Product product) {
		return Integer.compare(id, product.id);
	}

	public String csvRecord() {

		return null;
	}

}
