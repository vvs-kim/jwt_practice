package jwt.practice.app.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    private String email;
    private String pw;
}
