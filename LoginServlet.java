package servlet14;

import java.io.IOException;

import dao2.AccountsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model14.Account;
import model14.Login;
import model14.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//リクエストパラメーターの実行
		request.setCharacterEncoding("UTF-8");
	
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
	
	
		//ログイン処理の実行
		Login login = new Login(userId, pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		
		
		
		//loginにはいっているユーザーIDとパスワードを使ってテーブルを検索
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.findByLogin(login);
		String name = account.getName();

	
		//
		if(result) {
		    HttpSession session = request.getSession();
		    session.setAttribute("userId", userId); // ← 修正済み
		    session.setAttribute("name", name);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginOK.jsp");
		    dispatcher.forward(request, response);
		} else {
			
			//
			response.sendRedirect("LoginServlet");
		}
	
		
	}

}