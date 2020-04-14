package com.hexaware.MLP212.model;
import com.hexaware.MLP212.persistence.DbConnection;
import com.hexaware.MLP212.persistence.LeavesDAO;
import com.hexaware.MLP212.persistence.EmployeeDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * enum for LeaveTypes.
 */
enum LeaveType {


  /**
   * EARNED for EL.
  */
  EL,

  /**
   * EARNED for SL.
  */
  SL,
  /**
   * EARNED for ML.
  */
  ML
}

/**
 * enum for Leave Status.
 */
enum LeaveStatus {
  /**
   * PENDING for pending.
  */
  PENDING,
  /**
   * APPROVED for Approved.
  */
  APPROVED,
  /**
   * DENIED for Denied.
  */
  REJECTED
}

/**
 * Leaves class to store employee personal details.
 * @author hexware
 */
public class Leaves {
  /**
   * empid to store employee id.
   */
  private int empId;
  private int leaveId;

  /**
   * leaveFrom to store leave Start date.
   */
  private Date leaveFrom;
  /**
   * LEAVE_EDATE to store leave End date.
   */
  private Date leaveTo;
  /**
   * leaveAppliedDate to store leave no of days.
   */
  private Date leaveAppliedDate;
  /**
   * leavestatus to store leave status.
   */
  private String leaveStatus;
  /**
   * leavereason to store leave reason.
   */
  private String leaveReason;

  /**
   * leaveappliedon to store leave appliedon.
   */

  public Leaves() {

  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId, leaveFrom, leaveTo, leaveAppliedDate, leaveStatus, leaveReason);
  }

  /**
   * @param argempId            to get employee id.
   * @param argleaveId          to lget leave id.
   * @param argleaveFrom        to get leave from.
   * @param argleaveTo          to get leave to.
   * @param argleaveAppliedDate to get leave applied date.
   * @param argLeaveStatus      to get leave status.
   * @param argLeaveReason      to get leave reason.
   */
  public Leaves(final int argempId, final int argleaveId, final Date argleaveFrom, final Date argleaveTo,
      final Date argleaveAppliedDate, final String argLeaveStatus, final String argLeaveReason) {
    this.empId = argempId;
    this.leaveId = argleaveId;
    this.leaveFrom = argleaveFrom;
    this.leaveTo = argleaveTo;
    this.leaveAppliedDate = argleaveAppliedDate;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
  }

  /**
   * Gets the EmployeeName.
   *
   * @return this Employee's Name.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * set the employee id.
   *
   * @param argEmpId to set employee Name.
   */

  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * @return leave id.
   */
  public final int getLeaveId() {
    return leaveId;
  }

 /**
   * Gets the Leave start date.
   * @return this Leave start date.
   */
  public final Date getleaveFrom() {
    return leaveFrom;
  }

  /**
   * set the leave from.
   * @param argleaveFrom to set Leave start date.
   */
  public final void setleaveFrom(final Date argleaveFrom) {
    this.leaveFrom = argleaveFrom;
  }

  /**
   * Gets the EmployeeEmail.
   * @return this Employee's email.
   */
  public final Date getleaveTo() {
    return leaveTo;
  }

  /**
   *set the leave to.
   * @param argleaveTo to set employee id.
   */
  public final void setleaveTo(final Date argleaveTo) {
    this.leaveTo = argleaveTo;
  }

  /**
   * Gets the EmployeeMob.
   * @return this Employee's Mob.
   */
  public final Date getleaveAppliedDate() {
    return leaveAppliedDate;
  }

  /**
   *set leave applied date.
   * @param argleaveAppliedDate to set employee Mob.
   */
  public final void setleaveAppliedDate(final Date argleaveAppliedDate) {
    this.leaveAppliedDate = argleaveAppliedDate;
  }

  /**
   * Gets the EmployeeLeavbal.
   * @return this Employee's Leavbal.
   */
  public final String getLeaveStatus() {
    return leaveStatus;
  }

  /**
   *set leave status.
   * @param argLeaveStatus to set employee Leavbal.
   */
  public final void setLeaveStatus(final String argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }

  /**
   * Gets the EmployeeMgrid.
   * @return this Employee's Mgrid.
   */
  public final String getLeaveReason() {
    return leaveReason;
  }

  /**
   *set leave reason.
   * @param argLeaveReason to set employee Mgrid.
   */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Leaves ld = (Leaves) obj;
    if (Objects.equals(empId, ld.empId)
        && Objects.equals(leaveId, ld.leaveId)
        && Objects.equals(leaveFrom, ld.leaveFrom)
        && Objects.equals(leaveTo, ld.leaveTo)
        && Objects.equals(leaveAppliedDate, ld.leaveAppliedDate)
        && Objects.equals(leaveStatus, ld.leaveStatus)
        && Objects.equals(leaveReason, ld.leaveReason)) {
      return true;
    }
    return false;
  }

  /**
   * The dao for Leaves.
   */
  private static LeavesDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeavesDAO.class);
  }
  /**
   * @return connection.
   */
  public static EmployeeDAO edao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Leaves[] listAll() {

    List<Leaves> es = dao().list();
    return es.toArray(new Leaves[es.size()]);
  }

  /**
   * list all employee leave details.
   * @param mgrId id to get employee leave Details.
   * @return all employee leave details
   */
  public static Leaves[] listPending(final int mgrId) {

    List<Leaves> e = dao().pending(mgrId);
    return e.toArray(new Leaves[e.size()]);
  }

  /**
   * list employee details by id.
   * @param leaveId id to get employee details.
   * @return Employee
   */
  public static Leaves listById(final int leaveId) {
    return dao().find(leaveId);
  }

  /**
   * list employee details by id.
   * @param empId id to get employee details.
   * @return Employee
   */
  public static Leaves[] leaveHis(final int empId) {
    List<Leaves> es = dao().empHistory(empId);
    return es.toArray(new Leaves[es.size()]);
  }

  /**
   * returns ManagerId for. LeaveId value.
   * @param leaveId id to get employee manager details.
   * @return int.
   */
  public static int showManager(final int leaveId) {
    return dao().showManager(leaveId);
  }

  /**
   * NO of overlping Leaves.
   * @param empId     id to get employee details.
   * @param startDate id to get employee details.
   * @param endDate   id to get employee details.
   * @return count of no of employee.
   */


  /**
   * @param leaveId to pass leaveId.
   * @param status to update status.
   */
  public static void update(final int leaveId, final String status) {
    dao().comment(status, leaveId);
  }

  /**
   *
   * @param empId to pass empid.
   * @param date1 to pass start date.
   * @param date2 to pass end date.
   * @param date to pass today date
   * @param lstatus is leave status
   * @param reason is reason for leave.
   */
  public static void applyLeave(final int empId, final String date1, final String date2, final Date date, final String lstatus, final String reason) {
    dao().insert(empId, date1, date2, date, lstatus, reason);
  }
  /**
   *
   * @param id pass id.
   * @param date1 pass date1.
   * @param date2 pass date 2.
   * @return count of leave.
   */
  public static int leaveexist(final int id, final String date1, final String date2) {
    return dao().count(id, date1, date2);
  }
  /**
   *
   * @param id pass id.
   * @param lid leave id.
   * @param dec pass dec.
   * @return string.
   */
  public static String approveleave(final int id, final int lid, final int dec) {
    String res = null;
    Leaves leave1 = Leaves.listById(lid);
    int empid = leave1.getEmpId();
    Date sdate = leave1.getleaveFrom();
    Date edate = leave1.getleaveTo();
    Employee employee = Employee.listById(empid);
    int balance = employee.getBalanceLeave();
    Calendar start = Calendar.getInstance();
    start.setTime(sdate);
    Calendar end = Calendar.getInstance();
    end.setTime(edate);
    int count = 0;
    for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek == 1 || dayOfWeek == 7) {
        count++;
      }
    }
    long diff = edate.getTime() - sdate.getTime();
    long days = diff / (1000 * 60 * 60 * 24);
    days = days + 1;
    long noday = days - count;
    int noOfDays = (int) noday;
    int bal =  balance + noOfDays;
    if (dec == 1) {
      Leaves.update(lid, "APPROVED");
      res = "----Leave Approved Successfully-----";
    } else if (dec == 2) {
      Leaves.update(lid, "DENIED");
      Employee.updateBal(empid, bal);
      res = "----Leave Denied Successfully-------";
    } else {
      res = "--------------INVALID ENTRY--------------";
    }
    return res;
  }
  /**
   *
   * @param id pass id.
   * @param date1 start date.
   * @param date2 end date.
   * @param reason reason for leave.
   * @return string.
   */
  public static String applyleaves(final int id, final String date1, final String date2, final String reason) {
    String s = null;
    try {
      Employee emp = Employee.listById(id);
      int balance = emp.getBalanceLeave();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      Date tdate = new Date();
      Date stDate = sdf.parse(date1);
      Date enDate = sdf.parse(date2);
      Calendar start = Calendar.getInstance();
      start.setTime(stDate);
      Calendar end = Calendar.getInstance();
      end.setTime(enDate);
      int count = 0;
      for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
          count++;
        }
      }
      long updLeave = 0;
      long checkbal = 0;
      long diff = enDate.getTime() - stDate.getTime();
      long days = diff / (1000 * 60 * 60 * 24);
      days = days + 1;
      updLeave = days - count;
      int bal = (int) updLeave;
      int leaves = Leaves.leaveexist(id, date1, date2);
      long diff2 = stDate.getTime() - tdate.getTime();
      long startend = diff2 / (1000 * 60 * 60 * 24);
      startend = startend + 1;
      checkbal = balance - bal;
      int rbal = (int) checkbal;
      String prntday = "";
      if (bal == 1) {
        prntday = "day";
      } else {
        prntday = "days";
      }
      if (startend <= 0) {
        s = "    FROM DATE SHOULD BE >= TODAYS DATE     ";
      } else if (rbal < 0) {
        s = "-----Can't Apply for " + updLeave + " Days, Your Balance leave is only " + emp.getBalanceLeave() + " Days-----";
      } else if (days <= 0) {
        s = "----------Invalid end date-------";
      } else if (leaves != 0) {
        s = "      LEAVE ALREADY EXIST FOR THE SELECTED DATE     ";
      } else {
        if (id == 3001) {
          String lstatus = "APPROVED";
          Leaves.applyLeave(id, date1, date2, tdate, lstatus, reason);
          Employee.updateBal(id, rbal);
          s = "Applying leave from: " + date1 + " to " + date2 + " for: " + bal + " " + prntday;
        } else {
          String lstatus = "PENDING";
          Leaves.applyLeave(id, date1, date2, tdate, lstatus, reason);
          Employee.updateBal(id, rbal);
          s = "Applying leave from: " + date1 + " to " + date2 + " for: " + bal + " " + prntday;
        }
      }
    } catch (ParseException e) {
      e.printStackTrace();
      s = "invalid format";
    }
    return s;
  }
  @Override
  public final String toString() {
    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------");
    return "| EmpID =" + getEmpId() + "| LeaveId = " + getLeaveId() + "| LeaveFrom =" + getleaveFrom() + "| LeaveTo ="
        + getleaveTo() + "| AppliedDate =" + getleaveAppliedDate() + "| LeaveStatus =" + getLeaveStatus() + "| Reason ="
        + getLeaveReason();
  }
}
