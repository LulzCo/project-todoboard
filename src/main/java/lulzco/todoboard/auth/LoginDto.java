package lulzco.todoboard.auth;

import lombok.Data;

@Data
public class LoginDto {
    private String userId;
    private String password;
}
