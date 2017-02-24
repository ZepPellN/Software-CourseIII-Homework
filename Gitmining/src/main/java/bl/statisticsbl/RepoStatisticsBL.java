package bl.statisticsbl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import bean.RepoRadarBean;
import data.DataFactory;
import dataservice.repodataservice.RepoDataService;
import utility.Language;

public class RepoStatisticsBL {
	private RepoDataService repoData;
	/* store RepoPO categorized by Language */
	private Map<Language, List<Pair<Integer, Integer>>> languageListMap;

	public RepoStatisticsBL() {
		repoData = DataFactory.createRepoDataInstance();
	}

	// ===================private=====================
	public RepoRadarBean getRadar(String full_name) {
		RepoRadarBean radarPO = repoData.queryRankOfRepo(full_name);
		if (radarPO == null) {
			return new RepoRadarBean(0, 0, 0, 0, 0, 0);
		}
		return radarPO;
	}

	public Map<String, Integer> getRepoActivity(String repo) {
		return repoData.queryCommitOfRepo(repo);
	}

	// ===================public===================
	public Map<Language, Integer> getLanguage() {
		languageListMap = repoData.staticsLanguage();

		Map<Language, Integer> result = new HashMap<Language, Integer>();

		Language[] languages = { Language.C, Language.HTML, Language.Java, Language.JavaScript, Language.Perl,
				Language.PHP, Language.Python, Language.Ruby, Language.Shell, Language.Others };

		List<Pair<Integer, Integer>> year_count = null;
		int total = 0;
	
		for (int i = 0; i < languages.length; i++) {
			year_count = languageListMap.get(languages[i]);
			total = 0;
			if (year_count != null) {
				for (Pair<Integer, Integer> count : year_count) {
					total += count.getRight();
				}
			}
			result.put(languages[i], total);
		}

		return result;
	}

	public Map<Integer, Integer> getLanguage(Language language) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		if(languageListMap == null){
			this.getLanguage();
		}
		List<Pair<Integer, Integer>> year_counts = languageListMap.get(language);

		for (Pair<Integer, Integer> year_count : year_counts) {
			result.put(year_count.getLeft(), year_count.getRight());
		}

		return result;
	}

	public Map<Integer, Integer> getCreateTime() {
		return repoData.staticsCreated_at();
	}

	public Map<Integer, Integer> getRepoFork(int interval) {
		Map<Integer, Integer> forksMap = repoData.staticsForks_Count();
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		int[] count = new int[10000 / interval];
		for (int fork_num : forksMap.keySet()) {
			if(fork_num >= 10000 / interval){
				count[10000 / interval - 1] += forksMap.get(fork_num);
			}else{
				count[fork_num / interval] += forksMap.get(fork_num);
			}
		}

		for (int i = 0; i < count.length; i++) {
			result.put(i * interval, count[i]);
		}

		return result;
	}

	public Map<Integer, Integer> getRepoStar(int interval) {
		Map<Integer, Integer> starsMap = repoData.staticsStargazers_count();
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		int[] count = new int[20000 / interval];
		for (int star_num : starsMap.keySet()) {
			if(star_num >= 20000 / interval){
				count[20000 / interval - 1] += starsMap.get(star_num);
			}else{
				count[star_num / interval] += starsMap.get(star_num);
			}
		}

		for (int i = 0; i < count.length; i++) {
			result.put(i * interval, count[i]);
		}

		return result;
	}
}