package demo05.thread_safe_navigable_maps;

/**
 * 存储在navigable map中的一个联系人
 **/
public class Contact {
	private String name;
	private String phone;

	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

}
