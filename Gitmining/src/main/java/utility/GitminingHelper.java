package utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import dao.CommitDao;
import dao.EventDao;
import dao.LogDao;
import dao.RepoDao;
import dao.UserDao;
import dao.imp.CommitDaoImp;
import dao.imp.EventDaoImp;
import dao.imp.LogDaoImp;
import dao.imp.RepoDaoImp;
import dao.imp.UserDaoImp;

@Service
public class GitminingHelper {
	private static GitminingHelper instance = null;
	private final static String CHARSET = Charsets.UTF_8.name();

	private UserDao userDao;
	private RepoDao repoDao;
	private EventDao eventDao;
	private CommitDao commitDao;
	private LogDao logDao;
	
	private GitminingHelper() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDaoImp) context.getBean("UserDaoImp");
		repoDao = (RepoDaoImp) context.getBean("RepoDaoImp");
		eventDao = (EventDaoImp) context.getBean("EventDaoImp");
		commitDao = (CommitDaoImp) context.getBean("CommitDaoImp");
		logDao = (LogDaoImp) context.getBean("LogDaoImp");
	};

	/**
	 * get the instance of GitminingHelper.
	 * 
	 * @return
	 */
	public static GitminingHelper getInstance() {
		return instance == null ? (instance = new GitminingHelper()) : instance;
	}

	// /**
	// * initialize repository and user names.
	// */
	// public void initData() {
	// String info = sendGet("http://gitmining.net/api/repository/names");
	// String[] names = info.substring(1, info.length() - 1).replaceAll("\"",
	// "").split(",");
	//
	// // Value in the returning table named repos;
	// String userName = null;
	// // Interim instance of UserPO.
	// ArrayList<String> userNames = new ArrayList<String>();
	// int count = 0;
	// int total = names.length;
	// for (String name : names) {
	// saveRepo(name);
	//
	// userName = name.split("/")[0];
	// if(!userNames.contains(userName)){
	// userNames.add(userName);
	// saveUser(userName);
	// }
	// System.out.println((double)(count++) * 1000 / total);
	// }
	// System.out.println("Repo And User Completed!");
	// repoDao.setHotRepos();
	// System.out.println("hot");
	// userDao.setRank();
	// System.out.println("rank");
	// repoDao.setRank();
	// System.out.println("Total Completed!");
	// }

	public void routineTask() {
		List<String> repos = repoDao.getAllNames();
		for (String repo : repos) {
			updateCommit(repo);
		}
		List<String> users = userDao.getAllNames();
		for (String user : users) {
			updateEvent(user);
		}

	}

	// /**
	// * get the po of one user based on the login name.
	// * @param userName
	// * @return
	// */
	// private void saveUser(String userName) {
	// String info =
	// GitminingHelper.sendGet("https://api.github.com/users/hanifor" +
	// userName);
	//
	// if (!info.equals("")) {
	// Map<String, String> variables = GitminingHelper.parseJson(info);
	// userDao.addUser(variables);
	// updateEvent(userName);
	// }
	// }

	// /**
	// * get the po of one repository based on the full name.
	// * @return
	// */
	// private void saveRepo(String fullName) {
	// String info =
	// GitminingHelper.sendGet("http://gitmining.net/api/repository/" +
	// fullName);
	//
	// if (!info.equals("")) {
	// Map<String, String> variables = GitminingHelper.parseJson(info);
	// repoDao.addRepo(variables);
	// updateCommit(fullName);
	// }
	// }

	/**
	 * send a 'Get' request to url.
	 * 
	 * @param url
	 * @return Json data form
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("authorization",
					"Basic " + new String(Base64.encodeBase64("Hanifor:371071798@qq.com".getBytes(CHARSET)), CHARSET));
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + url);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * transform the data in the form of jsonString into Map.
	 */
	public static Map<String, String> parseJson(String jsonObj) {
		// parse jasonString.
		JSONObject jsonObject = new JSONObject(jsonObj);
		Map<String, String> result = new HashMap<String, String>();
		Iterator<String> iterator = jsonObject.keys();
		String key = null;
		String value = null;

		while (iterator.hasNext()) {
			key = iterator.next();
			value = jsonObject.get(key).toString();
			result.put(key, value);
		}

		return result;
	}

	/**
	 * transform the data in the form of jsonArray into Map<String, String>[]
	 * 
	 * @param <T>
	 */
	private static Map<String, String>[] parseJsonArray(String jsonArr) {
		// parse jasonString.
		if (!jsonArr.startsWith("[")) {
			return null;
		}
		JSONArray jsonArray = new JSONArray(jsonArr);
		if (jsonArray.length() == 0) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, String>[] results = new Map[jsonArray.length()];
		JSONObject jsonObject = null;
		Iterator<String> iterator = null;
		String key = null;
		String value = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			results[i] = new HashMap<String, String>();
			iterator = jsonObject.keys();

			while (iterator.hasNext()) {
				key = iterator.next();
				value = jsonObject.get(key).toString();
				results[i].put(key, value);
			}
		}

		return results;
	}

	/**
	 * get the data of commits from github.
	 * 
	 * @param fullname
	 * @return
	 */
	private void updateCommit(String fullname) {
		Map<String, String>[] maps = null;
		Date date = commitDao.queryLatestDate(fullname);
		if (date == null) {
			date = Date.valueOf("1999-12-31");
		}
		String data = sendGet("https://api.github.com/repos/" + fullname + "/commits?page=1&per_page=100");
		maps = parseJsonArray(data);
		if (maps != null) {
			commitDao.addCommit(fullname, maps, date);
		}
	}

	/**
	 * get the data of events from github.
	 * 
	 * @param fullname
	 * @return
	 */
	private void updateEvent(String userName) {
		Map<String, String>[] maps = null;
		String data = sendGet("https://api.github.com/users/" + userName + "/events?page=1&per_page=100");
		Date date = eventDao.queryLatestDate(userName);
		if (date == null) {
			date = Date.valueOf("1999-12-31");
		}
		maps = parseJsonArray(data);
		if (maps != null) {
			eventDao.addEvent(userName, maps, date);
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public RepoDao getRepoDao() {
		return repoDao;
	}

	public EventDao getEventDao() {
		return eventDao;
	}

	public CommitDao getCommitDao() {
		return commitDao;
	}

	public LogDao getLogDao() {
		return logDao;
	}
}