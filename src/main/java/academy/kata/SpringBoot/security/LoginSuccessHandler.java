package academy.kata.SpringBoot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            httpServletResponse.sendRedirect("/admin/");
            return;
        }
        httpServletResponse.sendRedirect("/user/");
    }
}


//@Component
//public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                                        HttpServletResponse httpServletResponse,
//                                        Authentication authentication) throws IOException {
//        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//
//        boolean admin = false;
//        for (GrantedAuthority auth : authentication.getAuthorities()) {
//            System.out.println("Authorities: " + auth);
//            if ("ROLE_ADMIN".equals(auth.getAuthority())){
//                admin = true;
//            }
//        }
//        if(admin){
//            httpServletResponse.sendRedirect("/admin/");
//        }else{
//            httpServletResponse.sendRedirect("/user/");
//        }
//    }
//}