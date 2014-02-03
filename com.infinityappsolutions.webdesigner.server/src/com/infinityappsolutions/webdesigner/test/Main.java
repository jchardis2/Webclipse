//package com.infinityappsolutions.webdesigner.test;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import com.infinityappsolutions.webdesigner.database.tools.DAOBean;
//import com.infinityappsolutions.webdesigner.database.tools.DAOBean.DatabaseTable;
//import com.infinityappsolutions.webdesigner.project.tools.ProjectGenerator;
//
//public class Main {
//	public static void main(String args[]) {
//		DAOBean daoBean = new DAOBean();
//		daoBean.setConnection();
//		try {
//			ArrayList<DatabaseTable> dbTables = daoBean.getDatabaseTables();
//			ProjectGenerator generator = new ProjectGenerator(
//					System.getProperty("PROJECTS_LOCATION"));
//			generator.createProject(dbTables);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
