package com.hexaware.MLP212.persistence;
import com.hexaware.MLP212.model.Employee;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * @param empID to display manager details.
   * @return return manager details.
   */
  @SqlQuery("select * from employee where EMP_ID = ( select EMP_MANAGER_ID from employee where EMP_ID = :empID)")
  @Mapper(EmployeeMapper.class)
  Employee find1(@Bind("empID") int empID);

  /**
   * @param mngID to display manager details.
   * @return return manager details.
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_MANAGER_ID = :mngID")
  @Mapper(EmployeeMapper.class)
  Employee find2(@Bind("mngID") int mngID);



  /**
   * @param argEmpId tp store employee id.
   * @param argEname to store employee name.
   * @param argGender to store genger.
   * @param argDept to store department
   * @param date to store date.
   * @param argEmail to store email.
   * @param argContactno to store conatct number.
   * @param argBalanceLeave to store balance leave.
   * @param argManagerid to store manager id.
   */
  @SqlUpdate("insert into employee values (:argEmpId, :argEname, :argGender,"
      + ":argDept, :argDoj, :argEmail,  :argContactno, :argBalanceLeave, :argManagerid)")
  void insert(@Bind("argEmpId") int argEmpId,
      @Bind("argEname") String argEname,
      @Bind("argGender") String argGender,
      @Bind("argDept") String argDept,
      @Bind("argDoj") java.util.Date date,
      @Bind("argEmail") String argEmail,
      @Bind("argContactno") long argContactno,
      @Bind("argBalanceLeave") int argBalanceLeave,
      @Bind("argManagerid") int argManagerid);

  /**
   *
   * @param empID pass id.
   * @return return balance.
   */
  @SqlQuery("SELECT EMP_BALANCE_LEAVE FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Integer bal(@Bind("empID") int empID);

  /**
   * return all the details of the selected employee.
   * @param empID        the id of the employee
   * @param reaminingBal id of the employee.
   */
  @SqlUpdate("Update employee set EMP_BALANCE_LEAVE = :remainingBal where EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  void updateBal(@Bind("empID") int empID, @Bind("remainingBal") int reaminingBal);

  /**
  * close with no args is used to close the connection.
  */
  void close();


}
