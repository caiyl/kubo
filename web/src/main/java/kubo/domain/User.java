package kubo.domain;

public class User {

	private String id;
	private String userName;
	private String userNameCn;
	private String password;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!userName.equals(user.userName)) return false;
		return password.equals(user.password);

	}

	@Override
	public int hashCode() {
		int result = userName.hashCode();
		result = 31 * result + password.hashCode();
		return result;
	}
}