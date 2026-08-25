package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDao;
import model.entity.Member;

/**
 * 刪除會員
 * 1.必須要登入
 * 2.role 必須是 ADMIN
 * */
@WebServlet("/delete")
public class MemberDeleteController extends HttpServlet {
	
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
		
		// 判斷 role 是否是 ADMIN
		Member member = (Member)session.getAttribute("member");
		if(!member.getRole().equalsIgnoreCase("ADMIN")) {
			req.setAttribute("title", "會員刪除");
			req.setAttribute("message", "會員刪除: 權限不足");
			req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
			return;
		}
		
		// 刪除會員
		Integer id = Integer.valueOf(req.getParameter("id"));
		memberDao.delete(id);
		req.setAttribute("title", "會員刪除");
		req.setAttribute("message", "會員刪除: 成功");
		req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
	}
	
}
