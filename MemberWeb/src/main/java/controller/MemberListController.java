package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDao;
import model.entity.Member;

/**
 * 會員資料列表
 * 只有 role=ADMIN 可以看
 * */
@WebServlet("/list")
public class MemberListController extends HttpServlet {
	
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
		
		// 判斷 role=ADMIN ?
		Member member = (Member)session.getAttribute("member");
		if(!member.getRole().equals("ADMIN")) {
			// 重導到結果頁
			String title = "權限";
			String message = "權限不足";
			req.setAttribute("title", title);
			req.setAttribute("message", message);
			req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
			return;
		}
		
		List<Member> members = memberDao.findAll();
		//resp.getWriter().print(members);
		req.setAttribute("members", members);
		req.getRequestDispatcher("/WEB-INF/view/member-list.jsp").forward(req, resp);
	}
	
}
