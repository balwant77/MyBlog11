package com.myblog.myblog11.security;

import antlr.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, //Request may have Jwt token
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token=getJWTfromRequest(request); //this will fetch the jwt token from request
        if(StringUtils.hasText(token)&&tokenProvider.validateToken(token)) {



        }
        }
    }


    //Bearer <accessToken>

    private String getJWTfromRequest(HttpServletRequest request){

        String bearerToken= request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }


}
//JwtToken provider will generate the token when first time user will login
//for the second time login we have to provide the JwtToken with request and JwtAuthenticationfilter class will validate the token. It takes the token from the request.
//from the token extract the username, based on the username call the loadbyusername, loadbyusername will give username password which we will put in user object.
//then the user object we will give to UsernamePasswordAuthenticationToken class where comparison will happen . If everything is correct, set the token into security context class. It is like setting up the session.