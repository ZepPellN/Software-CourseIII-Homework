package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.UserBean;
import utility.OwnerType;

public class UserMapper implements RowMapper<UserBean> {

	public UserBean mapRow(ResultSet rs, int rownum) throws SQLException {
		return new UserBean(rs.getString("id"), rs.getString("login"), rs.getString("name"),
				rs.getString("company"), rs.getString("location"), rs.getString("email"), rs.getString("blog"),
				rs.getString("bio"), rs.getInt("public_repos"), rs.getInt("public_gists"), rs.getInt("followers"),
				rs.getInt("following"), rs.getDate("created_at").toString(), rs.getDate("updated_at").toString(),
				rs.getBoolean("site_admin"), rs.getBoolean("hireable"), OwnerType.setType(rs.getString("type")), rs.getString("avatar"));
	}
}