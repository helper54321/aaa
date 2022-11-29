package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3308/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "password1234";
		
		
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection success!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
