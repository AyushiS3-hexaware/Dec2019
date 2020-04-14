package com.hexaware.MLP212.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.MLP212.model.Leaves;

/**
 * Mapper class to map from result set to Leavedetails object.
 */
public class LeavesMapper implements ResultSetMapper<Leaves> {
    /**
   * @param idx the index
   * @param rs the resultset
   * @param ctx the context
   * @return the mapped Leavedetails object
   * @throws SQLException in case there is an error in fetching data from the resultset
   */
  public final Leaves map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException {
    /**
     * @return Leavedetails
     */
    return new Leaves(rs.getInt("EMP_ID"),
    rs.getInt("LEAVE_ID"),
    rs.getDate("LEAVE_FROM"),
    rs.getDate("LEAVE_TO"),
    rs.getDate("LEAVE_APPLIED_DATE"),
    rs.getString("LEAVE_STATUS"),
    rs.getString("LEAVE_REASON"));
  }
}
