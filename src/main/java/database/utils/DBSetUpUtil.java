package database.utils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBSetUpUtil {

    private static final String
            DRIVER = "com.mysql.cj.jdbc.Driver",
            URL = "jdbc:mysql://localhost/ITCompanyDataBase?&serverTimezone=UTC",
            USER = "root",
            PASSWORD = "toortoor";

    public static DataSource setUpService() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(DRIVER);
        dataSource.setJdbcUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(100);
        dataSource.setInitialPoolSize(5);
        dataSource.setAcquireIncrement(5);

        return dataSource;
    }
}