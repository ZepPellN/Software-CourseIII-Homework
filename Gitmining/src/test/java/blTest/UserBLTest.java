package blTest;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import bean.UserListBean;
import bl.BlFactory;
import bl.userbl.UserBL;

public class UserBLTest {
	private static UserBL bl;
	
	@BeforeClass
	public static void init() {
		bl = BlFactory.createUserBLInstance();
	}
	
	@Test
	public void test() {
		List<UserListBean> users = bl.searchVague("mo");
		assertNotNull(bl.searchPrecise(users.get(0).getLogin()));
	}
}