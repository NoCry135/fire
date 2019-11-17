//package com.ca.fire.until;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//public class DbInfoConfig {
//	private static final Logger logger = LoggerFactory.getLogger(DbInfoConfig.class);
//
//	private String url;
//	private String username;
//	private String password;
//
//	private List<String> usernames = new ArrayList<String>();
//	private List<String> passwords = new ArrayList<String>();
//	private List<String> urls = new ArrayList<String>();
//
//	@Getter
//	@Setter
//	public class DataSourceGroup {
//		private String key;
//		private DataSource masterDataSource;
//		private DataSource slaveDataSource;
//		private DataSource historyDataSource;
//	}
//
//	@Resource
//	private DataSourceConfig dataSourceConfig;
//
//	public String[] getAllUrls() {
//		String[] uls = null;
//
//		if (urls.size() > 1) {
//			uls = urls.toArray(new String[0]);
//		} else {
//			if (!StringUtils.isEmpty(url)) {
//				uls = new String[] {url};
//			}
//		}
//
//		return uls;
//	}
//
//	public List<DataSource> buildDataSource() throws Exception {
//		String[] uns;
//		String[] pws;
//		String[] uls;
//
//		if (urls.size() > 1) {
//			if (usernames.size() != passwords.size()) {
//				String errorMsg = "master database username and password is not matched.";
//				logger.error(errorMsg);
//				throw new Exception(errorMsg);
//			}
//
//			uls = urls.toArray(new String[0]);
//			uns = usernames.toArray(new String[0]);
//			pws = passwords.toArray(new String[0]);
//		} else {
//			uls = new String[] {url};
//			uns = new String[] {username};
//			pws = new String[] {password};
//		}
//
//		return buildDataSource(uls, uns, pws);
//	}
//
//	private List<DataSource> buildDataSource(String[] uls, String[] usernames, String[] passwords) {
//		List<DataSource> dss = new ArrayList<>();
//		for (int idx=0, size=uls.length; idx < size; idx ++) {
//			// 可以配置1个通用用户名及密码,密码取最后一个
//			int unIdx = idx >= usernames.length ? usernames.length-1 : idx;
//			int pwIdx = idx >= passwords.length ? passwords.length-1 : idx;
//			dss.add(dataSourceConfig.buildDataSource(uls[idx], usernames[unIdx], passwords[pwIdx]));
//		}
//		return dss;
//	}
//}
