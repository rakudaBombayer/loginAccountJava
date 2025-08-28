package model14;


import dao2.AccountsDAO;

//ユーザー情報の登録可否を判定する部品,　バリデーション等(8/28,9/1にここから行う)



public class UserLogic {
	
	public boolean execute(Account accountInfo) {
		
		AccountsDAO dao = new AccountsDAO();
		
		Account account = dao.registerAccount(accountInfo);
		
		return account != null; 
	}
	
}