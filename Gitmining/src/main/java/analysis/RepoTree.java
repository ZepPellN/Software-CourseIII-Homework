package analysis;

import java.util.List;
import dao.RepoDao;
import utility.GitminingHelper;
import utility.Language;

public class RepoTree{
	private static RepoTree instance = null;
	private RepoDao repoDao;
	
	public static void main(String[] args){
		RepoTree rt = new RepoTree();
		rt.procTree();
	}
	
	/**
	 * process matrices with machine learning algorithm.
	 */
	public void procTree(){
		RepoTree_Sub subTree = null;
		for (Language language : Language.values()) {
			subTree = new RepoTree_Sub();
			List<String> repo_names = repoDao.queryRepoOfLan(language);
			int total = repo_names.size();
			int count = 0;
			for (String name : repo_names) {
				subTree.addRepo(name);
				System.out.println(count++ + "/" + total);
			}
			System.out.println("Finish adding " + language);
			subTree.complementRepo(repoDao);
			System.out.println("Finish processing " + language);
		}
		System.out.println("Finish processing all matrices");
	}
	
	/**
	 * get data from .dat file
	 */
	private RepoTree(){
		repoDao = GitminingHelper.getInstance().getRepoDao();
	}
	
	/**
	 * get the instance.
	 * @return
	 */
	public static RepoTree getInstance(){
		return instance == null ? (instance = new RepoTree()) : instance;
	}
}