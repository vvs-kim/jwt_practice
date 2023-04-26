package jwt.practice.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jwt.practice.config.security.dto.LoginDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;

        final LoginDTO loginDTO;
        try {
            // 사용자 요청 정보로 UserPasswordAuthentication 발급
            loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            System.out.println("loginDTO = " + loginDTO);
            authRequest = new UsernamePasswordAuthenticationToken(loginDTO.getLoginId(), loginDTO.getPassword());
            System.out.println("authRequest = " + authRequest);
        } catch (IOException e) {
            throw new NotValidException();
        }
        setDetails(request, authRequest);

        // AuthenticationManager 에게 전달 -> AuthenticationProvider 의 인증 메서드 실행
        return this.getAuthenticationManager().authenticate(authRequest);

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
