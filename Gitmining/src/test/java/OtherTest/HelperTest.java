package OtherTest;

import org.junit.BeforeClass;
import org.junit.Test;
import dao.CommitDao;
import dao.EventDao;
import utility.GitminingHelper;

public class HelperTest {
	private static GitminingHelper helper;

	@BeforeClass
	public static void init() {
		helper = GitminingHelper.getInstance();
	}

	@Test
	public void test() {
		CommitDao commit = helper.getCommitDao();
		EventDao event = helper.getEventDao();
		commit.queryLatestDate("go-gl-legacy/gl");
		event.queryLatestDate("go-gl-legacy");
	}
}