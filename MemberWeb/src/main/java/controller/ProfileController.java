package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDao;
import model.util.SHA256Util;

/**
 * 個人資料
 * 要登入才可以使用
 * 若尚未登入要透過 sendRedirect() 自動引導到登入頁面
 * */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	
	private MemberDao memberDao = new MemberDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 判斷是否有登入
		HttpSession session = req.getSession();
		// 注意, 登入成功一定會有 member 的 session 物件
		if(session.getAttribute("member") == null) {
			// 重導到登入頁面
			resp.sendRedirect("/MemberWeb/login");
			return;
		}
		
		// 重導到個人資料頁 profile.jsp
		req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.valueOf(req.getParameter("id"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if(password != null) {
			// 取得新密碼的 salt 與 hash
			String salt = SHA256Util.generateSalt();
			String hash = SHA256Util.hash(password, salt);
			// 修改密碼
			memberDao.updatePassword(id, salt, hash);
		}
		
		// 修改 email
		memberDao.updateEmail(id, email);
		
		// 重導到結果頁
		String title = "會員修改";
		String message = "修改完畢";
		req.setAttribute("title", title);
		req.setAttribute("message", message);
		req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
	}
	
}
