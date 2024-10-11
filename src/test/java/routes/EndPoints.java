package routes;

public enum EndPoints {

	CreateAccountUserEP("/Account/v1/User"), GenerateTokenEP("/Account/v1/GenerateToken"),
	GetUserByUserIDEP("/Account/v1/User/{UserId}"), GetStoreBooksEP("/BookStore/v1/Books"),
	CreateBookForUserEP("/BookStore/v1/Books"), GetBookByIsbnEP("/BookStore/v1/Book?ISBN={Isbn1}"),
	PutBookWAnotherIsbnEP("/BookStore/v1/Books/{Isbn}"), DeleteUserByUserIdEP("/Account/v1/User/{UserId}");

	private String path;

	EndPoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	// Method to dynamically replace {UserId}for PathParam -> /Account/v1/User/{UserId}
	public String getPathWUserId(String paramValue) {
		return this.path.replace("{UserId}", paramValue);
	}

	public String getPathWIsbn(String paramValue) {
		return this.path.replace("{Isbn}", paramValue);
	}

}
