package dao.custom;

import java.sql.SQLException;

public interface QueryDAO {
    void searchOrderByOrderID(String Id)throws ClassNotFoundException, SQLException;
}
