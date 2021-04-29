package server.unigo.security;

import lombok.Data;

@Data
public class LoginOutput {
    private String accessToken;
    private String tokenType = "Bearer";
    private String status = "Ok";

    public LoginOutput(String accessToken) {
        this.accessToken = accessToken;
    }
}
