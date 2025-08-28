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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {


private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
		dispatcher.forward(request, response);
		
}


//パラメーター渡しになっているものの解消から始める、AccountsDAOで定義した　registerAccount（）がつかえそう。(午後から始める)
protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//リクエストパラメーターの実行
		request.setCharacterEncoding("UTF-8");
	
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		
		int age = Integer.parseInt(ageStr);
		
		
		// ユーザー情報をAccountオブジェクトにまとめる
	    Account accountInfo = new Account(userId, pass, mail, name, age);

	    // 登録処理の実行
	    AccountsDAO dao = new AccountsDAO();
	    Account registered = dao.registerAccount(accountInfo);
	
		//
		if(registered != null) {
		    HttpSession session = request.getSession();
		    session.setAttribute("userId", userId); // ← 修正済み
		    session.setAttribute("name", name);


		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userOK.jsp");
		    dispatcher.forward(request, response);
		} else {
			
			//
			response.sendRedirect("UserServlet");
		}
	
		
	}

}