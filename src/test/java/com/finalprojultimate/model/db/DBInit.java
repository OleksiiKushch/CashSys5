package com.finalprojultimate.model.db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBInit {
    public static void startUp() throws URISyntaxException, IOException, SQLException {
        URL url1 = DatabaseTest.class.getClassLoader()
                .getResource("cashsys_project_test.sql");
        URL url2 = DatabaseTest.class.getClassLoader()
                .getResource("cashsys_data_test.sql");
        URL url3 = DatabaseTest.class.getClassLoader()
                .getResource("cashsys_test_data.sql");

        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = String.join("", str1);

        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        String sql2 = String.join("", str2);

        List<String> str3 = Files.readAllLines(Paths.get(url3.toURI()));
        String sql3 = String.join("", str3);

        DirectConnectionBuilder connectionBuilder = new DirectConnectionBuilder();
        try (Connection con = connectionBuilder.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        }
    }
}
