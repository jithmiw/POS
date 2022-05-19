package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = SQLUtil.executeQuery("SELECT Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice FROM Orders INNER JOIN OrderDetails ON Orders.oid=OrderDetails.oid WHERE Orders.oid=?;", id);
        ArrayList<CustomDTO> orderRecords = new ArrayList();

        while (rst.next()) {
            orderRecords.add(new CustomDTO(rst.getString(1), LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
        }
        return orderRecords;
    }
}
