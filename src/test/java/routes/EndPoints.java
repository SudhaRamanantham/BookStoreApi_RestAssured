package routes;

public enum EndPoints {

	CreateAccountUserEP("/Account/v1/User"), GenerateTokenEP("/Account/v1/GenerateToken"),
	GetUserByUserIDEP("/Account/v1/User/{UserId}"), GetStoreBooksEP("/BookStore/v1/Books"),
	CreateBookForUserEP("/BookStore/v1/Books"), GetBookByIsbnEP("/BookStore/v1/Book?ISBN={Isbn}"),
	DeleteUserByUserIdEP("/Account/v1/User/{UserId}");

	private String path;

	EndPoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	// Method to dynamically replace {UserId}
	public String getPathWithResValue(String responseValue) {
		return this.path.replace("{UserId}", responseValue);
	}

	// Method to replace path parameters like {Isbn}
	public String getPathWithParams(String paramKey, String paramValue) {
		return path.replace("{" + paramKey + "}", paramValue);
	}

}
