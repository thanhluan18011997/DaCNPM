package server.unigo.security;

import lombok.Data;

@Data
public class LoginOutput {
    private String accessToken;
    private String tokenType = "Bearer";
    private String status = "Ok";
    private boolean block = false;
    private String role;

    public LoginOutput(String accessToken,String role) {
        this.accessToken = accessToken;
        this.role=role;
    }
}
