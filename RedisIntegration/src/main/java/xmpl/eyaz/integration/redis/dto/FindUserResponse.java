package xmpl.eyaz.integration.redis.dto;

public class FindUserResponse {

    private boolean userStatus;

    public FindUserResponse() {
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}
