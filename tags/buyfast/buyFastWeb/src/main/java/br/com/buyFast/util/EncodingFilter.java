package br.com.buyFast.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Classe responsável pelo filtro que passará caracteres fora do padrão utilizado
 * pelo sistema para o padrão UTF-8.
 */
public class EncodingFilter implements Filter {

	/**
	 * O tipo da codificação.
	 */
	public String encoding = "UTF-8"; 
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
