package stepDefinitions.db;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static utilities.DatabaseUtilities.*;

public class US134 {
    String query;

    @Given("user connects to the database")
    public void userConnectsToTheDatabase() throws SQLException {
        getConnection();
    }

    @Then("user verifies {string} table has the following columns")
    public void userVerifiesTableHasTheFollowingColumns(String tableName, DataTable dataTable) throws SQLException {
        List<String> requiredColumns = dataTable.column(0);
//        List<String> requiredColumns = dataTable.asList();

        query = "SELECT * FROM " + tableName;
        createStatement();
        createResultSet(query);

        //column -> sutun
        ResultSetMetaData rsmd = rs.getMetaData();

        for (String requiredColumn : requiredColumns) { //id

            boolean varMi = false;

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (requiredColumn.equals(rsmd.getColumnName(i))) {
                    System.out.println(requiredColumn + " - " + rsmd.getColumnName(i));
                    varMi = true;
                    break;
                }
            }

            Assert.assertTrue(varMi);
        }

    }

    @Then("user verifies all data are unique in {string} column of {string} table")
    public void userVerifiesAllDataAreUniqueInColumnOfTable(String columnName, String tableName) throws SQLException {
        query = "SELECT " + columnName + " FROM " + tableName;
        createStatement();
        createResultSet(query);
//        createResultSet("SELECT email FROM user");
        List<String> data = new ArrayList<>();

        while (rs.next()) {
            data.add(rs.getString(columnName));
        }
        //set ->
        HashSet<String> uniqueData = (HashSet<String>) data;

        Assert.assertEquals(uniqueData.size(), data.size());
    }
}
