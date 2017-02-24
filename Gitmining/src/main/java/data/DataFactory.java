package data;

import data.logdata.LogData;
import data.repodata.RepoData;
import data.userdata.UserData;
import dataservice.logdataservice.LogDataService;
import dataservice.repodataservice.RepoDataService;
import dataservice.userdataservice.UserDataService;

public class DataFactory {
	public static RepoDataService createRepoDataInstance(){
		return RepoData.getInstance();
	}
	
	public static UserDataService createUserDataInstance(){
		return UserData.getInstance();
	}
	
	public static LogDataService createLogDataInstance(){
		return LogData.getInstance();
	}
}
