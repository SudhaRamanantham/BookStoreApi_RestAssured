package pojo;

public class PostCreateAccountUser_Pojo {

	private String userName;
	private String password;

	//Constructor
	public PostCreateAccountUser_Pojo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	//Getter and Setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
