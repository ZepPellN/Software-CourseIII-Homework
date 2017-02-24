package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.RepoRadarBean;

public class RepoRadarMapper implements RowMapper<RepoRadarBean>{
	public RepoRadarBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new RepoRadarBean(rs.getDouble("Contributor"), rs.getDouble("Size"), rs.getDouble("Star"), rs.getDouble("Fork"), rs.getDouble("Issue"), rs.getDouble("Subscriber"));
	}
}