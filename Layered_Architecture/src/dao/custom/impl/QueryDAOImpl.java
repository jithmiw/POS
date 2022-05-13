package dao.custom.impl;

import dao.custom.QueryDAO;

import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public void searchOrderByOrderID(String Id)throws ClassNotFoundException, SQLException {
        String sql = "SELECT Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice FROM Orders INNER JOIN OrderDetails ON Orders.oid=OrderDetails.oid WHERE Orders.oid=\"OID-001\";";
    }
}
