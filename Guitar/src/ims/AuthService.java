package ims;

public class AuthService {
    private static final String ADMIN_USERNAME = "X-Codex";
    private static final String ADMIN_PASSWORD = "adminXXX";

    public boolean authenticate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}