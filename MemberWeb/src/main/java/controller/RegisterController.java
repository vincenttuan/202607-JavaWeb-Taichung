package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MemberDao;
import model.util.SHA256Util;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	
	private MemberDao memberDao = new MemberDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 接收資料
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String role = req.getParameter("role");
		
		// Hash 與 salt
		String salt = SHA256Util.generateSalt();
		String hash = SHA256Util.hash(password, salt);
		
		String html = """
				username = %s<p />
				password = %s<p />
				email = %s<p />
				role = %s<p />
				salt = %s<p />
				hash = %s<p />
				""".formatted(username, password, email, role, salt, hash);
		
		resp.getWriter().print(html);
		
		// 儲存
		memberDao.register(username, email, role, salt, hash);
		
		
	}
	
}
