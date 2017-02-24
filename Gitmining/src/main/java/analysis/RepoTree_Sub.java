package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import dao.RepoDao;
import utility.GitminingHelper;

public class RepoTree_Sub {
	private String row_y;
	private String row_r;
	private ArrayList<String> names;
	private ArrayList<String> repos;
	private ArrayList<String> rows_y;
	private ArrayList<String> rows_r;
	private final static String PATH_P = "matlab/P.txt";
	private final static String PATH_R = "matlab/R.txt";
	private final static String PATH_Y = "matlab/Y.txt";
	private static final String PATH_MATLAB = System.getProperty("user.dir") + "/matlab/";
	private static final String CMD = "matlab -nojvm -nodesktop -nosplash -r repos";
	private final int Recommend_Count = 10;
	
	public RepoTree_Sub() {
		repos = new ArrayList<String>();
		rows_y = new ArrayList<String>();
		rows_r = new ArrayList<String>();
		names = new ArrayList<String>();
	}

	/**
	 * Get all names of all users involved
	 * 
	 * @param repo
	 */
	public void addRepo(String full_name) {
		row_y = "";
		row_r = "";
		for (int i = 0; i < names.size(); i++) {
			row_y += "0 ";
			row_r += "0 ";
		}
		String user = null;
		String[] users = null;

		// owner
		user = GitminingHelper.sendGet("http://www.gitmining.net/api/repository/" + full_name + "/item/owner_name");
		this.setName(user, 5);

		// collaborators
		user = GitminingHelper.sendGet("http://www.gitmining.net/api/repository/" + full_name + "/collaborators/login");
		users = user.substring(1, user.length() - 1).replaceAll("\"", "").split(",");
		for (int i = 0; i < users.length; i++) {
			this.setName(users[i], 4);
		}

		// contributors
		user = GitminingHelper.sendGet("http://www.gitmining.net/api/repository/" + full_name + "/contributors/login");
		users = user.substring(1, user.length() - 1).replaceAll("\"", "").split(",");
		for (int i = 0; i < users.length; i++) {
			this.setName(users[i], 3);
		}

		// stargazers
		user = GitminingHelper.sendGet("http://www.gitmining.net/api/user/" + full_name + "/stargazers/names");
		users = user.substring(1, user.length() - 1).replaceAll("\"", "").split(",");
		for (int i = 0; i < users.length; i++) {
			this.setName(users[i], 2);
		}

		/// subscribers
		user = GitminingHelper.sendGet("http://www.gitmining.net/api/user/" + full_name + "/subscribers/names");
		users = user.substring(1, user.length() - 1).replaceAll("\"", "").split(",");
		for (int i = 0; i < users.length; i++) {
			this.setName(users[i], 1);
		}

		repos.add(full_name);
		rows_y.add(row_y);
		rows_r.add(row_r);
	}

	/**
	 * Add a user's name to the list alphabetically
	 * 
	 * @param userName
	 */
	private void setName(String user_name, int point) {
		int index = names.indexOf(user_name);
		if (index == -1) {
			names.add(user_name);
			row_y += (point + " ");
			row_r += ("1 ");
		} else {
			row_y = row_y.substring(0, index * 2) + point + row_y.substring(index * 2 + 1);
			row_r = row_r.substring(0, index * 2) + "1" + row_r.substring(index * 2 + 1);
		}
	}

	public void complementRepo(RepoDao repoDao) {
		File file_Y = new File(PATH_Y);
		File file_R = new File(PATH_R);
		File file_P = new File(PATH_P);
//		 ==============load data================
		FileWriter fw_Y = null;
		FileWriter fw_R = null;

		try {
			file_Y.createNewFile();
			file_R.createNewFile();
			file_P.createNewFile();
			fw_Y = new FileWriter(file_Y);
			fw_R = new FileWriter(file_R);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int length = names.size();
		for (int i = 0; i < rows_y.size(); i++) {
			row_y = rows_y.get(i);
			row_r = rows_r.get(i);
			for (int j = row_y.length() / 2; j < length; j++) {
				row_y += "0 ";
				row_r += "0 ";
			}
			try {
				fw_Y.append(row_y.substring(0, row_y.length() - 1) + "\n");
				fw_R.append(row_r.substring(0, row_r.length() - 1) + "\n");
			} catch (IOException e) {
			}
		}
		try {
			fw_Y.close();
			fw_R.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File fr = null;
		
		if (invokeMatlab()) {
			fr = new File(PATH_P);
		} else {
			fr = new File(PATH_Y);
		}

		BufferedReader br_P = null;
		BufferedReader br_Y = null;
		try {
			br_P = new BufferedReader(new FileReader(fr));
			br_Y = new BufferedReader(new FileReader(file_Y));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Object[]> paras = new ArrayList<Object[]>();
		List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>(repos.size());
		String row_P = null;
		String row_Y = null;
		for (int i = 0; i < repos.size(); i++) {
			try {
				row_P = br_P.readLine();
				row_Y = br_Y.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			paras.add(new Object[]{repos.get(i), row_Y.substring(0, row_Y.length() - 1), row_P.substring(0, row_P.length() - 1)});
			pairs.add(Pair.of(repos.get(i), row_P));
		}
		
		try {
			br_P.close();
			file_P.delete();
			file_Y.delete();
			file_R.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		repoDao.addRepoAnalysis(paras);
		paras = new ArrayList<Object[]>(repos.size() * Recommend_Count);
		for (int i = 0; i < pairs.size(); i++) {
			List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>(repos.size() - 1);
			Pair<String, String> base = pairs.remove(0);
			List<Integer> validPos = new ArrayList<Integer>();
			String row_base = base.getRight();
			for (int j = 0; j < row_base.length(); j += 2) {
				if (row_base.charAt(i) != '0') {
					validPos.add(j);
				}
			}

			int count;
			int total;
			int temp;
			for (Pair<String, String> pair : pairs) {
				count = 0;
				total = 0;
				row_P = pair.getRight();
				for (Integer pos : validPos) {
					temp = (row_base.charAt(pos) - '0') * (row_P.charAt(pos) - '0');
					total += temp;
					if (temp > 4) {
						count++;
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", pair.getLeft());
				map.put("total", total);
				map.put("count", count);
				lists.add(map);
			}

			lists.sort(new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return (int) o2.get("total") - (int) o1.get("total");
				}
			});

			for (int j = 0; j < Recommend_Count; j++) {
				paras.add(new Object[]{base.getLeft(), lists.get(j).get("name"), lists.get(j).get("count")});
			}
		
			pairs.add(base);
		}
		repoDao.addRepoRecommend(paras);
	}

	/**
	 * process matrix in matlab.
	 */
	private boolean invokeMatlab() {
		System.out.println("begin invoking matlab");
		int[] end = { 62, 62, 32 };
		int ptr = 0;
		int receive = -1;

		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec(CMD, null, new File(PATH_MATLAB));
		} catch (IOException e1) {
			return false;
		}

		while (true) {
			try {
				receive = proc.getInputStream().read();
			} catch (IOException e) {
				return false;
			}
			if (receive == end[ptr]) {
				ptr++;
			} else {
				ptr = 0;
			}
			if (ptr == 3) {
				break;
			}
		}

		proc.destroyForcibly();
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
			return false;
		}

		System.out.println("Matlab finishes processing");
		return true;
	}
}