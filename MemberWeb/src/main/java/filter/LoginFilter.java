package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/delete", "/list", "/profile"})
public class LoginFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// 判斷是否有登入
		HttpSession session = req.getSession();
		// 注意, 登入成功一定會有 member 的 session 物件
		if(session.getAttribute("member") == null) {
			// 重導到登入頁面
			resp.sendRedirect("/MemberWeb/login");
			return;
		}
		
		// 往下交付
		chain.doFilter(req, resp);
		
	}
	
}
