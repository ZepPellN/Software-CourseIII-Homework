package dataTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import bean.UserAssessBean;
import bean.UserBean;
import data.userdata.UserData;

public class UserDataTest {
	private static UserData data;

	@BeforeClass
	public static void init() {
		data = UserData.getInstance();
	}

	@Test
	public void testSearchVague() {
		assertNotNull(data.searchVague("mo"));
	}

	@Test
	public void testSearchPrecise() {
		UserBean user = data.searchPrecise("1stvamp");
		assertEquals(user.getLogin(), "1stvamp");
		assertNotNull(user.getReposName());
	}

	@Test
	public void testQueryEventOfUser() {
		assertNotNull(data.queryEventOfUser("1stvamp"));
	}

	@Test
	public void testQueryRankOfUser() {
		UserAssessBean assess = data.queryRankOfUser("1stvamp");
		assertEquals(assess.getActivity(), 0.5, 0.5);
		assertEquals(assess.getEnthusiasm(), 0.5, 0.5);
		assertEquals(assess.getPopularity(), 0.5, 0.5);
	}

	@Test
	public void testStaticsOwnerType() {
		assertNotNull(data.staticsOwnerType());
	}

	@Test
	public void testStaticsCreated_at() {
		assertNotNull(data.staticsCreated_at());
	}

	@Test
	public void testStaticsRepo() {
		assertNotNull(data.staticsRepo());
	}
}