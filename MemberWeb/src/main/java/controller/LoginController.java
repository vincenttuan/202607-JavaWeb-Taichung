package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDao;
import model.entity.Member;
import model.util.SHA256Util;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private MemberDao memberDao = new MemberDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/login.jsp");
		//rd.forward(req, resp);
		
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 取得表單資料
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		
		// 取得認證碼
		HttpSession session = req.getSession();
		String sessionCode = (String)session.getAttribute("code");
		
		// 拿到認證碼之後立即清除 session 中的認證碼
		session.setAttribute("code", null);
		
		// 比對認證碼
		if(!code.equals(sessionCode)) {
			resp.getWriter().print("認證碼比對失敗");
			return;
		}
		
		// 取得鹽與哈希
		String salt = SHA256Util.generateSalt();
		String hash = SHA256Util.hash(password, salt);
		
		String html = """
					username: %s<p/>
					password: %s<p/>
					salt: %s<p/>
					hash: %s<p/>
				""".formatted(username, password, salt, hash);
		
		resp.getWriter().print(html);
		
		try {
			Member member = memberDao.login(username, password);
			// 登入成功後要將 member 寫入到 session 儲存
			session.setAttribute("member", member);
			resp.getWriter().print("登入成功, " + member);
		} catch (Exception e) {
			resp.getWriter().print(e.getMessage());
		}
		
		
		
	}
	
}
