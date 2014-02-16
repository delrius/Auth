package ua.kiev.naukma.auth.server.db;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectivityMgr {

    private DataSource ds;
    private InitialContext ic;

    public DatabaseConnectivityMgr() {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/USERDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public boolean insertData(String query, String... params) {
        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i< params.length; i++) {
                ps.setString(i+1, params[i]);
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ArrayList<String>> getData(String query, int numberOfColumns, String... params) {
        List<ArrayList<String>> result = new ArrayList<>();
        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i< params.length; i++) {
                ps.setString(i+1, params[i]);
            }
            try (ResultSet resultSet = ps.executeQuery()) {
                ArrayList<String> items = new ArrayList<>(numberOfColumns);
                while (resultSet.next()) {
                    for (int i = 0; i < numberOfColumns; i++) {
                        items.add(resultSet.getString(i+1));
                    }
                }
                if (!items.isEmpty()) {
                    result.add(items);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
