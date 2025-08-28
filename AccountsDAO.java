package dao2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model14.Account;
import model14.Login;

public class AccountsDAO {
	
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/dokotsubu";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "sql";
	
	
    public Account findByLogin(Login login) {
    	
    	Account account = null;
    	
    	try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    	
    	
    	//データベースに接続
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNTS WHERE USER_ID = ? AND PASS = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            
            pStmt.setString(1, login.getUserId());
            pStmt.setString(2, login.getPass());
            
            ResultSet rs = pStmt.executeQuery();
            
            
            if (rs.next()) {
                String userId = rs.getString("USER_ID");
                String pass = rs.getString("PASS");
                
                String mail = rs.getString("MAIL");
                String name = rs.getString("NAME");
                
                int age = rs.getInt("AGE");
                
                account = new Account(userId, pass, mail, name, age);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return account;  	
    	
    }
    
    
    public Account registerAccount(Account accountInfo) {
    	
    	Account account = null;
    	
    	try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    	
    	
    	//データベースに接続
    	
    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO ACCOUNTS(USER_ID, PASS, MAIL, NAME, AGE) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            
            pStmt.setString(1, accountInfo.getUserId());
            pStmt.setString(2, accountInfo.getPass());
            pStmt.setString(3, accountInfo.getMail());
            pStmt.setString(4, accountInfo.getName());
            pStmt.setInt(5, accountInfo.getAge());
            
            int result = pStmt.executeUpdate();

            if (result == 1) {
            	account = accountInfo; // 登録成功時に返す
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return account;  	
    	
    }
    
    
    
}