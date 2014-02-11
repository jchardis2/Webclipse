package com.infinityappsolutions.webdesigner.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.infinityappsolutions.webdesigner.beans.OrgUsersBean;

public class OrgUserLoader implements BeanLoader<OrgUsersBean> {

	@Override
	public List<OrgUsersBean> loadList(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrgUsersBean loadSingle(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrgUsersBean loadSingle(ResultSet rs, OrgUsersBean b)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps,
			OrgUsersBean bean) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadCommon(ResultSet rs, OrgUsersBean b) throws SQLException {
		// TODO Auto-generated method stub

	}

}
