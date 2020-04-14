package com.hexaware.MLP212.persistence;

import com.hexaware.MLP212.model.Leaves;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.Date;
import java.util.List;

/**
 * The DAO class for Leaves.
 */
public interface LeavesDAO {
    /**
     * return all the details of all the Leaves.
     * @return the Leaves array.
     */
  @SqlQuery("SELECT * FROM LEAVES")
  @Mapper(LeavesMapper.class)
  List<Leaves> list();

    /**
     * return all the details of the selected Leaves.
     * @param leaveid the id of the Leaves.
     * @return the Leaves object.
     */
  @SqlQuery("SELECT * FROM LEAVES WHERE LEAVE_ID = :leaveid")
  @Mapper(LeavesMapper.class)
  Leaves find(@Bind("leaveid") int leaveid);

   /**
    * return all the details of the selected Leavesid.
    * @return leave with pending status.
   */
  @SqlQuery("SELECT * FROM LEAVES WHERE LEAVE_STATUS in ('pending')")
  @Mapper(LeavesMapper.class)
  List<Leaves> listByLeave();

    /**
     * return all the details of the selected employee.
     *
     * @param empID     the id of the employee.
     * @param startDate leaveStartDate of employee.
     * @param endDate   leaveEndDate of employee.
     * @return the total record count
    */
  @SqlQuery("SELECT COUNT(*) FROM LEAVES " + " WHERE EMP_ID= :empID AND (LEAVE_FROM BETWEEN :startDate AND :endDate "
      + " OR LEAVE_TO BETWEEN :startDate AND :endDate)")
  int count(@Bind("empID") int empID, @Bind("startDate") String startDate, @Bind("endDate") String endDate);
     /**
      * return all the details of the selected employee.
      *
      * @param empID the id of the employee
      * @return the employee object
     */
  @SqlQuery("SELECT * FROM LEAVES WHERE EMP_ID = :empID")
  @Mapper(LeavesMapper.class)
  List<Leaves> empHistory(@Bind("empID") int empID);
    /**
     * return Manager the details of the Leave Id.
     * @param leaveID the id of the employee.
     * @return the ManagerID value.
     */

  @SqlQuery("SELECT E1.EMP_ID FROM EMPLOYEE E1  "
      + " JOIN EMPLOYEE E2 ON E1.Emp_ID=E2.MGR_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVES "
      + "   WHERE LEAVE_ID=:leaveID)")
    int showManager(@Bind("leaveID") int leaveID);

    /**
     * return all the details of the selected Leaves.
     * @param empid the id of the Leaves.
     * @return the Leaves object.
     */

  @SqlQuery("SELECT * FROM LEAVES WHERE " + " EMP_ID IN " + " (SELECT E2.EMP_ID FROM " + " EMPLOYEE E1 INNER JOIN "
       + " EMPLOYEE E2 ON E1.EMP_ID = E2.EMP_MANAGER_ID " + " WHERE E1.EMP_ID = :empid)  AND "
       + " LEAVE_STATUS ='PENDING' ")
  @Mapper(LeavesMapper.class)
  List<Leaves> pending(@Bind("empid") int empid);

    /**
     * @param leavestatus Updated Manager Status data.
     * @param leaveid the id of the Leaves.
     */
  @SqlUpdate("UPDATE LEAVES SET LEAVE_STATUS = :leavestatus WHERE "
       + "LEAVE_ID = :leaveid")
  void comment(@Bind("leavestatus") String leavestatus,
       @Bind("leaveid") int leaveid);
    /**
     * @param sDate   Updated Manager Response data.
     * @param eDate   Updated Manager Status data.
     * @param days    the id of the Leaves
     * @param ltype   the id of the Leaves
     * @param lstatus the id of the Leaves
     * @param reason  the id of the Leaves
     * @param leaveid the id of the Leaves
     */
  @SqlUpdate("UPDATE LEAVES SET START_DATE = :startDate, END_DATE = :endDate,"
       + " NO_OF_DAYS = :nodays, LEAVE_TYPE= :leavetype,LEAVE_REASON= :leavereason WHERE " + "LEAVE_ID = :leaveid")
  void updateLeave(@Bind("startDate") String sDate, @Bind("endDate") String eDate, @Bind("nodays") int days,
       @Bind("leavetype") String ltype, @Bind("leavestatus") String lstatus, @Bind("leavereason") String reason,
       @Bind("leaveid") int leaveid);

    /**
     * insert all the details of the employee leave.
     * @param empId the employee id of the employee.
     * @param sDate the start date of the employee.
     * @param eDate the end date of the employee.
     * @param days the number of days of the employee.
     * @param ltype the leaveType details of employee.
     * @param lstatus the LeaveStatus details of employee.
     * @param reason the reason for applying.
     * @param appliedDate the applied date of the employee.
     */

     /**
      * @param ename the employee ename of the employee.
      * @param leaveFrom the LeaveFrom details of employee.
      * @param leaveTo the LeaveTo details of employee.
      * @param leaveAppliedDate the LeaveAppliedDate details of employee.
      * @param leaveStatus the LeaveStatus details of employee.
      * @param leaveReason the LeaveReason details of employee.
      */
  @SqlUpdate("insert into LEAVES (EMP_ID, LEAVE_FROM, LEAVE_TO, LEAVE_APPLIED_DATE,"
         + " LEAVE_STATUS, LEAVE_REASON) values (:empId, :date1, :date2, :date, :lstatus, :reason)")

  void insert(@Bind("empId") int ename, @Bind("date1") String leaveFrom, @Bind("date2") String leaveTo,
            @Bind("date") Date leaveAppliedDate, @Bind("lstatus") String leaveStatus,
            @Bind("reason") String leaveReason);

   /**
    * close the connection.
    */
  void close();

}
