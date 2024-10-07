package routes;

public enum EndPoints {

	CreateAccountUserEP("/Account/v1/User"), GenerateTokenEP("/Account/v1/GenerateToken"),
	GetUserByUserIDEP("/Account/v1/User/{UserId}"),
	GetStoreBooksEP("/BookStore/v1/Books"),
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
//		if (UserId == null || UserId.isEmpty()) {
//			throw new IllegalArgumentException("UserId cannot be null or empty");
//		}
		return this.path.replace("{UserId}", UserId);
	}

}
