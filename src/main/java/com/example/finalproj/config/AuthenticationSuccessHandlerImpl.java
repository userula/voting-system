package com.example.finalproj.config;

import com.example.finalproj.repository.AccountRepository;
import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
    private AccountRepository accountRepository;

    @Autowired
    public AuthenticationSuccessHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String userName = "";
        if(authentication.getPrincipal() instanceof Principal) {
            userName = ((Principal)authentication.getPrincipal()).getName();

        }else {
            userName = ((Account)authentication.getPrincipal()).getUsername();
        }
        Account account = accountRepository.findByUsername(userName);
        httpServletRequest.getSession().setAttribute("user", account);
        httpServletResponse.sendRedirect("/home");
    }
}
