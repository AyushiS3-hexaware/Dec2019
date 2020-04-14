package com.hexaware.MLP212.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import java.util.Scanner;
import com.hexaware.MLP212.model.Employee;
import com.hexaware.MLP212.model.Leaves;


/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner sc = new Scanner(System.in, "UTF-8");
  private int id;


  /**
   * display menu.
   */
  public final void menu()  {
    System.out.println("|----------------------------------|");
    System.out.println("|-------Leave Management System----|");
    System.out.println("|----------------------------------|");
    System.out.println("1. Login");
    System.out.println("2. Exit");
    System.out.println("Enter your choice:");
    int choice1 = sc.nextInt();

    if (choice1 == 1) {
      System.out.println("Enter your Employee Id");
      id = sc.nextInt();
      Employee employee = Employee.listById(id);
      if (employee == null) {
        System.out.println();
        System.out.println("--------------No Such Employee---------------");
        System.out.println("---------------------------------------------");
        System.out.println("-----please enter correct employee id--------");
        System.out.println("---------------------------------------------");
        menu();
      } else {
        mainMenu(choice1);
      }
    } else {
      Runtime.getRuntime().halt(0);
    }
  }
  /**
   * @param choice1 display the option.
   */
  public final  void mainMenu(final int choice1)  {

    System.out.println("|----------------------------------|");
    System.out.println("|-------Leave Management System----|");
    System.out.println("|----------------------------------|");
    System.out.println("1. Employee Details");
    System.out.println("2. Leave system");
    System.out.println("3. Approve Leaves");
    System.out.println("4. New Register");
    System.out.println("5. Logout");
    System.out.println("Enter your choice:");
    int menuOption = sc.nextInt();
    mainMenuDetails(menuOption);

  }

  /**
   * @param selectedOption display the option.
   */
  public final void mainMenuDetails(final int selectedOption)  {
    switch (selectedOption) {
      case 1:
        System.out.println("|----------------------------------|");
        System.out.println("|---------Employees Section--------|");
        System.out.println("|----------------------------------|");
        System.out.println("1. List All Employees Info");
        System.out.println("2. Display Employee Info");
        System.out.println("3. My Manager Details");
        System.out.println("4. Back");
        System.out.println("5. Logout");
        System.out.println("Enter your choice:");
        int submenuOption1 = sc.nextInt();
        empSection(submenuOption1);
        break;


      case 2:
        System.out.println("|----------------------------------|");
        System.out.println("|----------Leave Section-----------|");
        System.out.println("|----------------------------------|");
        System.out.println("1. Apply Leave");
        System.out.println("2. Employee Leave History");
        System.out.println("3. All Leaves");
        System.out.println("4. Back");
        System.out.println("5. Logout");
        System.out.println("Enter your choice:");
        int submenuOption3 = sc.nextInt();
        levSection(submenuOption3);
        break;
      case 3:
        approveDeny();
        break;
      case 4:
        registeremp();
        System.out.println();
        System.out.println();
        mainMenu(1);
        break;
      case 5:
        System.out.println();
        System.out.println();
        menu();
        break;
      default:
        System.out.println();
        System.out.println("Invalid entry");
    }
    System.out.println();
    System.out.println();
    mainMenu(1);
  }

  /**
   * @param submenuOption1 display the option.
   */
  public final void empSection(final int submenuOption1) {
    switch (submenuOption1) {
      case 1:
        listAllDetails();
        break;

      case 2:
        listEmployeeDetail();
        break;

      case 3:
        managerInfo();
        break;

      case 4:
        System.out.println();
        System.out.println();
        mainMenu(1);

        break;
      case 5:
        System.out.println();
        System.out.println();
        menu();
        break;

      default:
        System.out.println("Incorrect entry");

    }
    System.out.println();
    System.out.println();
    mainMenuDetails(1);
  }

  /**
   * @param submenuOption3 display option.
   */
  public final void levSection(final int submenuOption3)  {
    switch (submenuOption3) {

      case 1:
        applyLeave();
        break;

      case 2:
        leaveHistory();
        break;

      case 3:
        listAllleave();
        break;

      case 4:
        System.out.println();
        System.out.println();
        mainMenu(1);
        break;

      case 5:
        System.out.println();
        System.out.println();
        menu();
        System.out.println();
        System.out.println();
        break;

      default:
        System.out.println("Incorrect entry");

    }
    System.out.println();
    System.out.println();
    mainMenuDetails(2);
    System.out.println();
    System.out.println();
  }

  private void listAllDetails() {
    Employee[] employee = Employee.listAll();
    System.out.println();
    System.out.println();
    for (Employee e : employee) {
      System.out.println();
      System.out.println(e);
    }
  }


  private void listEmployeeDetail() {
    Employee employee = Employee.listById(id);
    System.out.println();
    System.out.println();
    System.out.println(employee);
    System.out.println();
  }

  private void managerInfo() {
    Employee employee = Employee.listmngId(id);
    System.out.println();
    System.out.println();
    System.out.println(employee);
    System.out.println();
  }

  private void leaveHistory() {
    Leaves[] leaves = Leaves.leaveHis(id);
    if (leaves.length == 0) {
      System.out.println("Sorry, No such employee History..");
    } else {
      System.out.println();
      for (Leaves e : leaves) {
        System.out.println(e);
      }
    }
    System.out.println();
    System.out.println();
    mainMenuDetails(2);
    System.out.println();
    System.out.println();
  }

  private void listAllleave() {
    Leaves[] lvall = Leaves.listAll();
    System.out.println();
    System.out.println();
    for (Leaves e : lvall) {
      System.out.println(e);
    }
  }
 /**
  * For Approval and Denial.
  */
  private void approveDeny() {
    Employee employee1 = Employee.listBymngId(id);
    if (employee1 == null) {
      System.out.println();
      System.out.println("|------------------------------------|");
      System.out.println("|-----------ACCESS DENIED------------|");
      System.out.println("|--ONLY MANAGER CAN APPROVE/DENIED---|");
      System.out.println("|------------------------------------|");
      System.out.println();
    } else {
      listpendingleave(id);
      try {
        System.out.println("Enter a leave ID:");
        int leaveId = sc.nextInt();
        leaveid(leaveId);
        System.out.println("Press  \n 1 for APPROVE \n 2 for DENY  ");
        int dec = sc.nextInt();
        String res = Leaves.approveleave(id, leaveId, dec);
        System.out.println();
        System.out.println(res);
        System.out.println();
      } catch (NullPointerException e) {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("************INVALID LEAVE ID*************");
        System.out.println("*****************************************");
        System.out.println();
      }
    }
  }

  private void applyLeave() {
    Employee emp = Employee.listById(id);
    System.out.println("|----------------------------------|");
    System.out.println("|---------------------------------|");
    System.out.println("|------------Apply Leave-----------|");
    System.out.println("|----------------------------------|");
    System.out.println("|----------------------------------|");
    System.out.println();
    System.out.println();
    System.out.println("****************************************************");
    System.out.println("            " + "Welcome " + emp.getEname());
    System.out.println("          " + " Avaliable leave balance: " + emp.getBalanceLeave());
    System.out.println("****************************************************");
    String sDate = null;
    String eDate = null;
    while (true) {
      try {
        System.out.println("Enter Start Date of your leave (yyyy/MM/dd): ");
        sDate = sc.next();
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
        sdfrmt.setLenient(false);
        sdfrmt.parse(sDate);
        System.out.println("Enter End Date of your leave (yyyy/MM/dd): ");
        eDate = sc.next();
        SimpleDateFormat sdfrm = new SimpleDateFormat("yyyy/MM/dd");
        sdfrm.setLenient(false);
        sdfrm.parse(eDate);
        break;
      } catch (ParseException ex) {
        System.out.println("------------------------------");
        System.out.println("Please enter in correct date/format.");
        System.out.println("------------------------------");
      }
    }
    System.out.println("Please mention the reason for taking leave: ");
    String leaveReason = sc.next();
    String r = null;
    r = Leaves.applyleaves(id, sDate, eDate, leaveReason);
    System.out.println();
    System.out.println();
    System.out.println(r);
    System.out.println();
  }

  private static void listpendingleave(final int id) {
    Leaves[] pending = Leaves.listPending(id);
    System.out.println();
    System.out.println();
    System.out.println("|---------------------------------------------|");
    System.out.println("|------Leaves yet to Approve or Denied--------|");
    System.out.println("|---------------------------------------------|");
    for (Leaves e : pending) {
      System.out.println(e);
    }
    System.out.println(
        "|-------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println();
  }
  private static void leaveid(final int lid) {
    Leaves leave = Leaves.listById(lid);
    System.out.println();
    System.out.println();
    System.out.println(leave);
    System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println();
  }
  /**
   * register employ.
   */
  private void registeremp() {
    Employee employee = Employee.listBymngId(id);
    if (employee == null) {
      System.out.println();
      System.out.println();
      System.out.println("|------------------------------------|");
      System.out.println("|--SORRY, ONLY MANAGER CAN REGISTER--|");
      System.out.println("|------------------------------------|");
      System.out.println();
      System.out.println();
    } else {
      System.out.println();
      System.out.println("|----------------------------------|");
      System.out.println("|-------NEW REGISTRATION-----------|");
      System.out.println("|----------------------------------|");
      System.out.println("Enter new Employee Id");
      int empid = sc.nextInt();
      System.out.println("Enter Name ");
      String name = sc.next();
      System.out.println("Enter Gender");
      String gender = sc.next();
      System.out.println("Enter Dept");
      String dept = sc.next();
      System.out.println("Enter Email");
      String email = sc.next();
      System.out.println("Enter Contactno");
      long contactno = sc.nextInt();
      Date date = new Date();
      int balanceleave = 15;
      Employee.insertdata(empid, name, gender, dept, date, email, contactno, balanceleave, id);
      System.out.println("|----------------------------------|");
      System.out.println("|-------Updated successfully-------|");
      System.out.println("|----------------------------------|");
    }
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.menu();
  }
}
