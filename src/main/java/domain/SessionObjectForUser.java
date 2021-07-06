package domain;

public class SessionObjectForUser {
    private final int userId;
    private final Role userRole;

    public SessionObjectForUser(int userId, Role userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public Role getUserRole() {
        return userRole;
    }

}
