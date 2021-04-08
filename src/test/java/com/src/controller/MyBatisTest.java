package com.src.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MyBatisTest {
	
	final String DRIVER = "org.mariadb.jdbc.Driver";
	final String URL = "jdbc:mariadb://127.0.0.1:3306";
	final String USER = "root";
	final String PASSWORD = "sysadmin";

	@Test
	public void mariaTest() throws Exception{
		Class.forName(DRIVER);

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			System.out.println("연결성공" + con);
		} catch(Exception e) {
		e.printStackTrace();
		}
	}
}
