package com.ca.fire.until;

public class DynamicDataSourceHolder {

	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }
    
    public static void removeDataSource() {
        holder.remove();
    }

    public static String getDataSource() {
        return holder.get();
    }
	
}
