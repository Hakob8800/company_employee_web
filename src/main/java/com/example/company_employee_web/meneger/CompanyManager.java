package com.example.company_employee_web.meneger;


import com.example.company_employee_web.model.Company;
import com.example.company_employee_web.db.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {

    private Connection connection = ConnectionProvider.getInstance().getConnection();

    public void save(Company company) {
        String sql = "INSERT INTO company(name,country) VALUES(?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, company.getName());
            ps.setString(2, company.getCountry());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                company.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from company where id = " + id);
            if (resultSet.next()) {
                return getCompanyFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from company");
            while (resultSet.next()) {
                companyList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public List<Company> getByCountry(String country) {
        List<Company> companyList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from company where country = ?");
            ps.setString(1, country);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                companyList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public void removeById(int companyId) {
        String sql = "DELETE FROM company WHERE id = " + companyId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Company company) {
        String sql = "UPDATE company SET name = ?, country = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,company.getName());
            statement.setString(2,company.getCountry());
            statement.setInt(3,company.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setCountry(resultSet.getString("country"));
        return company;
    }
}
