package com.infinityappsolutions.webdesigner.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.infinityappsolutions.webdesigner.dao.DAOFactory;
import com.infinityappsolutions.webdesigner.dao.IConnectionDriver;

public class TestDAOFactory extends DAOFactory implements IConnectionDriver {

	private static DAOFactory testInstance;

	public static DAOFactory getTestInstance() {
		if (testInstance == null)
			testInstance = new TestDAOFactory();
		return testInstance;
	}

	private TestDAOFactory() {

	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/webdesigner", "webdesigner", "mytestpassword");
	}
}
