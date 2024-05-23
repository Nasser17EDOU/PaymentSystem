/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paymentsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony NDOSS
 */
class ConnectionToDb {

    Connection connection = null;
    Statement statement = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;
//    static String userName = "";
    User user = new User();
    Employee employee = new Employee();
    SelectedEmployee selectedEmployee = new SelectedEmployee();

//    Statement ConnecDb() throws SQLException {
//        return DriverManager.getConnection("jdbc:sqlite:database.db").createStatement();
//    }
    //math login entries in databse
    public boolean loginToDb(String username, String pwd) {
        boolean b = false;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select UserName, Password From User Where UserName = '" + username + "' And Password = '" + pwd + "'");
            if (result.next()) {
                b = true;
                user.setUsername(username);
                getUserDetail(username);
            } else {
                b = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }

        return b;
    }

    //create new account when sign up
    public boolean createNewAccount(String username, String firstname, String lastname, String companyname, String pwd) {
        boolean b = false;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select UserName From User Where UserName like '" + username + "'");
            if (result.next()) {
                b = false;
            } else {
                b = true;
                statement.executeUpdate("Insert Into User (UserName, FirstName, LastName, CompanyName, Password) Values ('"
                        + username + "', '"
                        + firstname + "', '"
                        + lastname + "', '"
                        + companyname + "', '"
                        + pwd + "')"
                );
                user.setUsername(username);
                getUserDetail(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }

        return b;
    }

    //get user details
    public void getUserDetail(String username) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select * From User Where UserName = '" + username + "'");
            while (result.next()) {
                user.setFirstname(result.getString(2));
                user.setLastname(result.getString(3));
                user.setCompanyname(result.getString(4));
//                user.setPassword(result.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
    }

    //get Employee details
    public ObservableList<Employee> getListOfEmployeeDeteails() {
        ObservableList<Employee> ls = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select * From Employee Where UserName = '" + user.getUsername() + "'");
            while (result.next()) {
                ls.addAll(new Employee(result.getString(1), result.getString(3), result.getString(4), result.getDouble(5), result.getDouble(6), result.getInt(7), result.getDouble(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }

        return ls;
    }

    //get Employee Picture
    public InputStream getEmployeePicture() {
        InputStream is = null;

        try {
            if (selectedEmployee.getEmployeeId() == null) {
            } else {
                connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                statement = connection.createStatement();
                result = statement.executeQuery("Select EmployeePicture From Employee Where EmployeeId = '" + selectedEmployee.getEmployeeId() + "' And UserName = '" + user.getUsername() + "'");
                while (result.next()) {
                    is = result.getBinaryStream(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
        return is;
    }

    //check if employeeId exist already
    public boolean isEmployeeIdExist() {
        boolean b = true;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select EmployeeId From Employee Where EmployeeId = '" + selectedEmployee.getEmployeeId() + "' And UserName = '" + user.getUsername() + "'");
            if (result.next()) {
                b = true;
            } else {
                b = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
        return b;
    }

    //insert new employee details into database
    public void addNewEmployee() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
//            statement = connection.createStatement();
            pstmt = connection.prepareStatement("Insert Into Employee Values (?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, selectedEmployee.getEmployeeId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, selectedEmployee.getEmployeeFullName());
            pstmt.setString(4, selectedEmployee.getEmployeeCategory());
            pstmt.setDouble(5, selectedEmployee.getEmployeePayRatePerHour());
            pstmt.setDouble(6, selectedEmployee.getEmployeeExtraTimePayRate());
            pstmt.setDouble(7, selectedEmployee.getEmployeeExpectedTimeToWork());
            pstmt.setDouble(8, selectedEmployee.getEmployeeTaxRate());
            if (selectedEmployee.getEmployeePicture() == null) {
                pstmt.setNull(9, Types.BLOB);
            } else {
                FileInputStream fin = new FileInputStream(selectedEmployee.getEmployeePicture());
                pstmt.setBinaryStream(9, fin, (int) selectedEmployee.getEmployeePicture().length());
            }

            pstmt.execute();
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
            
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
    }

    //remove employee from database
    public void removeEmployee() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            statement.executeUpdate("Delete From Payment Where EmployeeId = '" + selectedEmployee.getEmployeeId() + "' And UserName = '" + user.getUsername() + "'");
            statement.executeUpdate("Delete From Employee Where EmployeeId = '" + selectedEmployee.getEmployeeId() + "' And UserName = '" + user.getUsername() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
    }

    //update payment into databse
    public void updatePayment(
            double paymentNetSalary,
            double paymentGrossSalary,
            double paymentTaxSalary,
            double paymentAmountAddedSalary,
            String paymentAmountAddedSalaryReason,
            double paymentAmountDeductedSalary,
            String paymentAmountDeductedSalaryReason
    ) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
//            statement = connection.createStatement();
            pstmt = connection.prepareStatement("Insert Into Payment (EmployeeId, UserName, PaymentDate, NetSalaryPayed, GrossSalary, Tax, SurplusOnNetSalary, SurplusReason, DeductionOnNetSalary, DeductionReason) Values (?,?,?,?,?,?,?,?,?,?)");
//            pstmt
            pstmt.setString(1, selectedEmployee.getEmployeeId());
            pstmt.setString(2, user.getUsername());
            pstmt.setDate(3, new Date(System.currentTimeMillis()));
            pstmt.setDouble(4, paymentNetSalary);
            pstmt.setDouble(5, paymentGrossSalary);
            pstmt.setDouble(6, paymentTaxSalary);
            pstmt.setDouble(7, paymentAmountAddedSalary);
            pstmt.setString(8, paymentAmountAddedSalaryReason);
            pstmt.setDouble(9, paymentAmountDeductedSalary);
            pstmt.setString(10, paymentAmountDeductedSalaryReason);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
            
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }
    }

    //get list of payments
    public ObservableList<Payment> getPaymentList() {
        ObservableList<Payment> ls = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
            result = statement.executeQuery("Select * From Payment Where EmployeeId = '" + selectedEmployee.getEmployeeId() + "' And UserName = '" + user.getUsername() + "'");
            while (result.next()) {
                ls.addAll(new Payment(result.getDate(4).toLocalDate(), result.getDouble(5), result.getDouble(6), result.getDouble(7), result.getDouble(8), result.getString(9), result.getDouble(10), result.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }

//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
            }
        }

        return ls;
    }

}
