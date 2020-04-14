package com.hexaware.MLP212.model;
import com.hexaware.MLP212.persistence.DbConnection;
import com.hexaware.MLP212.persistence.EmployeeDAO;
import java.util.Objects;
import java.util.Date;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empId;
  private String eName;
  private String eGender;
  private String eDept;
  private Date eDoj;
  private String eEmail;
  private long eContactNo;
  private int eBalanceLeave;
  private int eManagerId;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId) && Objects.equals(eName, emp.eName) && Objects.equals(eGender, emp.eGender)
        && Objects.equals(eDept, emp.eDept) && Objects.equals(eDoj, emp.eDoj) && Objects.equals(eEmail, emp.eEmail)
        && Objects.equals(eContactNo, emp.eContactNo) && Objects.equals(eBalanceLeave, emp.eBalanceLeave)
        && Objects.equals(eManagerId, emp.eManagerId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId);
  }

  /**
   * @param argEmpId        to store employeeid.
   * @param argEname        to store name.
   * @param argGender       to store gender.
   * @param argDept         to store department.
   * @param argDoj          to store date of joining.
   * @param argEmail        to store email.
   * @param argContactno    to store contact number.
   * @param argBalanceLeave to store balance leave.
   * @param argManagerid    to store manager id.
   */
  public Employee(final int argEmpId, final String argEname, final String argGender, final String argDept,
      final Date argDoj, final String argEmail, final long argContactno, final int argBalanceLeave,
      final int argManagerid) {

    this.empId = argEmpId;
    this.eName = argEname;
    this.eGender = argGender;
    this.eDept = argDept;
    this.eDoj = argDoj;
    this.eEmail = argEmail;
    this.eContactNo = argContactno;
    this.eBalanceLeave = argBalanceLeave;
    this.eManagerId = argManagerid;
  }
  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return this.empId;
  }

  /**
   * @param ename to store name.
   */
  public final void setEname(final String ename) {
    this.eName = ename;
  }

  /**
   * @param gender to store gender.
   */
  public final void setGender(final String gender) {
    this.eGender = gender;
  }

  /**
   * @param dept to store department.
   */
  public final void setDept(final String dept) {
    this.eDept = dept;
  }

  /**
   * @param doj to store date of joining.
   */
  public final void setDoj(final Date doj) {
    this.eDoj = doj;
  }

  /**
   * @param email to store employee's email.
   */
  public final void setEmail(final String email) {
    this.eEmail = email;
  }

  /**
   * @param contactno to store contact number.
   */
  public final void setContactno(final long contactno) {
    this.eContactNo = contactno;
  }

  /**
   * @param balanceLeave to store employee's balance leave.
   */
  public final void setBalanceLeave(final int balanceLeave) {
    this.eBalanceLeave = balanceLeave;
  }

  /**
   * @param managerid to store employee's manager id.
   */
  public final void setManagerid(final int managerid) {
    this.eManagerId = managerid;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's name.
   */
  public final String getEname() {
    return eName;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's gender.
   */
  public final String getGender() {
    return eGender;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's department.
   */
  public final String getDept() {
    return eDept;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's date of joining.
   */

  public final Date getDoj() {
    return eDoj;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's email.
   */
  public final String getEmail() {
    return eEmail;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's contact number.
   */

  public final long getContactno() {
    return eContactNo;

  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's balance leave.
   */

  public final int getBalanceLeave() {
    return eBalanceLeave;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's manager id.
   */
  public final int getManagerid() {
    return eManagerId;
  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {
    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * @param id to get employee details.
   * @return Employee
   */

  public static Employee listById(final int id) {
    return dao().find(id);
  }

  /**
   * @param id to get manager details.
   * @return employee.
   */
  public static Employee listBymngId(final int id) {
    return dao().find2(id);
  }

  /**
   * @param id to get manager details.
   * @return employee.
   */
  public static Employee listmngId(final int id) {
    return dao().find1(id);
  }
  /**
   * @param id for id.
   * @return balance.
   */
  public static Integer balance(final Integer id) {
    return dao().bal(id);
  }
  /**
   * @param empId for id.
   * @param bal update balance.
   */
  public static void updateBal(final int empId, final int bal) {
    dao().updateBal(empId, bal);

  }
  /**
   * @param argEmpId        to store employeeid.
   * @param argEname        to store name.
   * @param argGender       to store gender.
   * @param argDept         to store department.
   * @param date            to store date of joining.
   * @param argEmail        to store email.
   * @param argContactno    to store contact number.
   * @param argBalanceLeave to store balance leave.
   * @param argManagerid    to store manager id.
   */
  public static void insertdata(final int argEmpId, final String argEname, final String argGender, final String argDept,
      final Date date, final String argEmail, final long argContactno, final int argBalanceLeave,
      final int argManagerid) {
    dao().insert(argEmpId, argEname, argGender, argDept, date, argEmail, argContactno, argBalanceLeave, argManagerid);
  }

  @Override
  public final String toString() {
    return String.format("%10s %20s %5s %10s %15s %28s %13s %5s %5s",
    getEmpId(), getEname(), getGender(), getDept(), getDoj(), getEmail(), getContactno(), getBalanceLeave(), getManagerid());
  }
}
