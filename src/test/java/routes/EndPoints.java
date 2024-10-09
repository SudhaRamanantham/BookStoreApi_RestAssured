package routes;

public enum EndPoints {

	CreateAccountUserEP("/Account/v1/User"), GenerateTokenEP("/Account/v1/GenerateToken"),
	GetUserByUserIDEP("/Account/v1/User/{UserId}"),
	GetStoreBooksEP("/BookStore/v1/Books"),
	CreateBookForUserEP("/BookStore/v1/Books"),
	DeleteUserByUserIdEP("/Account/v1/User/{UserId}");

	private String path;

	EndPoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	// Method to dynamically replace {UserId}
	public String getPathWithUserId(String UserId) {
		return this.path.replace("{UserId}", UserId);
	}

}
