package com.spring.cloud.gateway.demo.api.gateway.filter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	@Autowired
	Environment env;
	
	public AuthorizationHeaderFilter() {
		super(Config.class);
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {

			HttpHeaders headers = exchange.getRequest().getHeaders();
			if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {

				String token = headers.get(HttpHeaders.AUTHORIZATION).get(0).toString();
				String tokenValue = token.replace("Bearer", "");
			
				isJwtValid(tokenValue, exchange);
								return chain.filter(exchange);

			}
			else {

				return onError(exchange, "UNAUTHORIZED ACCESS", HttpStatus.UNAUTHORIZED);
			}


		};
	}

	private Mono<Void> onError(ServerWebExchange exchange, String string, HttpStatus unauthorized) {
		ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
		response.setStatusCode(unauthorized);
		return ((ReactiveHttpOutputMessage) response).setComplete();
		// TODO Auto-generated method stub

	}

	private boolean isJwtValid(String jwt, ServerWebExchange exchange) {

			String subject = null;
		try {
			subject = Jwts.parser()
					.setSigningKey(env.getProperty("token.value"))
					.parseClaimsJws(jwt)
					.getBody()
					.getSubject();
			
					if (!subject.isBlank() || subject != null) {
				return true;
			}
			
			
		}catch (ExpiredJwtException ex) {
			System.out.println("**************************************");
			System.out.println("Expired JWT token");
			System.out.println("**************************************");

			// Call the jwt token generate method.
			System.out.println("**************************************");
			System.out.println("JWT Claims" +ex.getClaims().getSubject());
			System.out.println("**************************************");

		} catch (SignatureException ex) {
			System.out.println("**************************************");
			System.out.println("Invalid JWT Signature");
			System.out.println("**************************************");
		} catch (MalformedJwtException ex) {
			System.out.println("**************************************");
			System.out.println("Invalid JWT token");
			System.out.println("**************************************");
		} catch (UnsupportedJwtException ex) {
			System.out.println("**************************************");
			System.out.println("Unsupported JWT exception");
			System.out.println("**************************************");
		} catch (IllegalArgumentException ex) {
			System.out.println("**************************************");
			System.out.println("Jwt claims string is empty");
			System.out.println("**************************************");
		}
//			catch (Exception ex) {
//			System.out.println("**************************************");
//			System.out.println("Jwt claims string is empty");
//			System.out.println("**************************************");
//		}
		return false;
	}

	protected ServerWebExchange refreshJWTToken(String subject, ServerWebExchange exchange) {

		 String token = Jwts.builder()
		 .setSubject(subject)
		 .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("600000000000")))
		 .signWith(SignatureAlgorithm.HS512, env.getProperty("token.value"))
		 .compact(); 
		 
		 
		//Builder request = exchange.mutate().request(exchange.getRequest().mutate().header("token", token).build());
		//Builder request2 = exchange.mutate().request(exchange.getRequest().mutate().header("userId", token).build());

		 //HttpHeaders headers = exchange.getRequest().getHeaders();
		 //String newtokenvalue = headers.get(HttpHeaders.AUTHORIZATION).set(0, token);
		
		
	
		System.out.println("**********************************************");
		System.out.println("Refreshed JWT = " + token);
		System.out.println("*********************************************");	
		//super.successfulAuthentication(request, response, chain, authResult);
		
		isJwtValid(token, exchange);
		return exchange;
	}

	
}