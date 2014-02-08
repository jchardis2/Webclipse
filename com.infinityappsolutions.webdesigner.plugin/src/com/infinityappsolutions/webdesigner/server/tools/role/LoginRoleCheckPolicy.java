package com.infinityappsolutions.webdesigner.server.tools.role;

import java.security.Principal;
import java.security.acl.Group;

import org.eclipse.jetty.jaas.RoleCheckPolicy;

public class LoginRoleCheckPolicy implements RoleCheckPolicy {

	@Override
	public boolean checkRole(String arg0, Principal arg1, Group arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
