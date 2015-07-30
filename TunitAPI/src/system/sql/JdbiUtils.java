package system.sql;

import org.skife.jdbi.v2.DBI;
import org.testng.annotations.Test;

public class JdbiUtils {
	
	@Test
	public void insertResults () {
		DBI dbi = new DBI("jdbc:mysql://192.168.12.27:3306/livecams", "root", "111");
		DAO dao = dbi.open(DAO.class);
		
		//examples for usage
		dao.createTable("test_table");
		dao.insert(2, "hello");
		dao.findNameById("web_sites", 100);
	}
}
