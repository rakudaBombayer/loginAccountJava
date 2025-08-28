package model14;

import dao2.AccountsDAO;

public class LoginLogic {
	
	public boolean execute(Login login) {
		
		AccountsDAO dao = new AccountsDAO();
		
		Account account = dao.findByLogin(login);
		
		return account != null; 
	}
	
}