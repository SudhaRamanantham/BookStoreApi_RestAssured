package pojo;

import java.util.List;

public class PostCreateBookForUser_Pojo {

	private String userId;
	private List<Isbn_CreateBookFUser> collectionOfIsbns; // this array connects with Isbn_CreateBookFUser.java

	// Constructor
	public PostCreateBookForUser_Pojo(String userId, List<Isbn_CreateBookFUser> collectionOfIsbns) {
		this.userId = userId;
		this.collectionOfIsbns = collectionOfIsbns;
	}

	// Getter and Setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Isbn_CreateBookFUser> getCollectionOfIsbns() {
		return collectionOfIsbns;
	}

	public void setCollectionOfIsbns(List<Isbn_CreateBookFUser> collectionOfIsbns) {
		this.collectionOfIsbns = collectionOfIsbns;
	}

}
