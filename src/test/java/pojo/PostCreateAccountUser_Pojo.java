package pojo;

public class PostCreateAccountUser_Pojo {

	private Object userName;
	private Object password;

	//Constructor
	public PostCreateAccountUser_Pojo(Object userName, Object password) {
		this.userName = userName;
		this.password = password;
	}
	
	//Getter and Setter
	public Object getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Object getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
