package br.applogin.applogin.service.Autenticator;

import br.applogin.applogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        System.out.println("INTERCEPTANDO...");

        String uri = request.getRequestURI();

// LIBERA LOGIN (corrigido)
        if (uri.equals("/login") || uri.equals("/login/")) {
            return true;
        }

// OUTRAS ROTAS PÚBLICAS
        if (uri.startsWith("/registerUser") || uri.startsWith("/logar") || uri.startsWith("/error")) {
            return true;
        }

// ARQUIVOS ESTÁTICOS
        if (uri.contains(".css") || uri.contains(".js") || uri.contains(".png") || uri.contains(".jpg")) {
            return true;
        }

        if (CookieService.getCookie(request, "userId") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}