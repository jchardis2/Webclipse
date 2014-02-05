package com.infinityappsolutions.webdesigner.database.tools;

import java.util.HashMap;

public class ColumnTypeMap {
	HashMap<String, Class<?>> columnTypeHashMap;
	public static final String TYPE_VARCHAR = "VARCHAR";
	public static final String TYPE_BIGINT = "BIGINT";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_BOOLEAN = "BOOLEAN";

	public ColumnTypeMap() {
		columnTypeHashMap = new HashMap<String, Class<?>>();
		columnTypeHashMap.put("VARCHAR", String.class);
		columnTypeHashMap.put("BIGINT", Long.class);
		columnTypeHashMap.put("BOOLEAN", Boolean.class);
	}

	public Class<?> getClass(String type) {
		return columnTypeHashMap.get(type);
	}

	public Class<?> setClass(String type, Class<?> _class) {
		return columnTypeHashMap.put(type, _class);
	}
}