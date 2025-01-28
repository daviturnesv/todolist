package br.com.daviturnesv.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.daviturnesv.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if(servletPath.startsWith("/tasks/")){
                    //Pegar a autenticação
                    var autorizacao = request.getHeader("Authorization");
                    var aut_cript = autorizacao.substring("Basic".length()).trim();
                    byte[] aut_descript = Base64.getDecoder().decode(aut_cript); //Decodifica "aut_cript"
                    var aut_string = new String(aut_descript);



                    String[] credenciais = aut_string.split(":");
                    String username = credenciais[0];
                    String password = credenciais[1];

                    //System.out.println("Credenciais:");
                    //System.out.println(username);
                    //System.out.println(password);

                    //Validar usuario

                    var user = this.userRepository.findByUsername(username);
                    if(user == null){
                        response.sendError(401, "Usuario sem credenciais");
                    }else{
                        //validar senha
                        var verif_senha = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if(verif_senha.verified){
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);
                        }else{
                            response.sendError(401, "Senha incorreta");
                        }
                    }
                }else{
                    filterChain.doFilter(request, response);
                }
                
    }


}
