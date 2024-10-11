package pojo;

public class PutBookWAnotherIsbn_Pojo {

	private String userId;
	private String isbn;
	
	//Constructor
	public PutBookWAnotherIsbn_Pojo(String userId,String isbn){
		this.userId = userId;
		this.isbn = isbn;
	}
	
	//Getter and Setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
