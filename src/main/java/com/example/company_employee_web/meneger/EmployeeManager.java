package com.example.company_employee_web.meneger;

import com.example.company_employee_web.model.Company;
import com.example.company_employee_web.model.Employee;
import com.example.company_employee_web.db.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private final Connection connection = ConnectionProvider.getInstance().getConnection();
    private final CompanyManager companyManager = new CompanyManager();

    public void save(Employee employee) {
        String sql = "INSERT INTO employee(name,surname,email,company_id) VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,employee.getName());
            statement.setString(2,employee.getSurname());
            statement.setString(3,employee.getEmail());
            statement.setInt(4,employee.getCompany().getId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from employee where id = " + id);
            if (resultSet.next()) {
                return getEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from employee");
            while (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getAllByCompanyId(int companyId) {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from employee where company_id=" + companyId);
            while (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void removeById(int employeeId) {
        String sql = "DELETE FROM employee WHERE id = " + employeeId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, surname = ?, email = ?, company_id = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,employee.getName());
            statement.setString(2,employee.getSurname());
            statement.setString(3,employee.getEmail());
            statement.setInt(4,employee.getCompany().getId());
            statement.setInt(5,employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(1));
        employee.setName(resultSet.getString(2));
        employee.setSurname(resultSet.getString(3));
        employee.setEmail(resultSet.getString(4));
        int companyId = resultSet.getInt(5);
        employee.setCompany(companyManager.getById(companyId));
        return employee;
    }
}
