package com.ca.fire.until;

public class MyDataSourceSwitch {
	private static ThreadLocal<String> key = new ThreadLocal<String>();

	public static String getKey() {
		return key.get();
	}

	public static void setKey(String key_in) {
		MyDataSourceSwitch.key.set(key_in);
	}

	public static void clearKey() {
		key.remove();
	}
}
