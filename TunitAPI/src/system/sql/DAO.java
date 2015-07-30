package system.sql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;

public interface DAO {
	
	@SqlUpdate("create table test (id INT NOT NULL, name VARCHAR(45) NULL, PRIMARY KEY (id));")
	void createTable(@Define("table") String tableName);
	
	@SqlUpdate("insert into new_table (id, name) values (:id, :name)")
	void insert(@Bind("id") int id, @Bind("name") String name);
	
	@SqlQuery("select name from <table> where id = :id")
	void findNameById(@Define("table") String table, @Bind("id") int id);
	
	/**
	 * close with no args is used to close the connection
	 */
	void close();

}
