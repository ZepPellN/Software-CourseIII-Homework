package blTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import bl.BlFactory;
import bl.statisticsbl.UserStatisticsBL;

public class UserStaticsBLTest {
	private static UserStatisticsBL bl;
	
	@BeforeClass
	public static void init() {
		bl = BlFactory.createUserStatisticsBLInstance();
	}
	
	@Test
	public void test() {
		assertTrue(bl.getScore("ffi").getEnthusiasm() > 0);
		assertTrue(bl.getScore("sfsefafgsrgstg").getEnthusiasm() == 0);
		assertTrue(bl.getUserActivity("ffi").size() > 0);
		assertNotNull(bl.getOwnerType());
		assertNotNull(bl.getRepository(5));
		assertNotNull(bl.getCreated_at());
	}
}