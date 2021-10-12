package tr.com.foodstore.userservice.model.dto.userservice;

import tr.com.foodstore.userservice.model.dto.AbstractServiceDto;

public class AuthenticationDto extends AbstractServiceDto {
    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
