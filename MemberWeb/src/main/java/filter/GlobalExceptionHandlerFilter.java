package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 全域例外處理器
 * 攔截所有的 HTTP 請求, 統一捕捉 Controller 所拋出的例外
 * */
@WebFilter("/*")
public class GlobalExceptionHandlerFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 
		try {
			// 執行後續的 Controller
			chain.doFilter(request, response);
		} catch (Exception e) {
			
			request.setAttribute("title", "錯誤處理");
			request.setAttribute("message", e.getMessage());
			
			request.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(request, response);
		}
		
	}
	
}
