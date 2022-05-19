package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.dao.custom.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean placeOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = DBConnection.getDbConnection().getConnection();
        /*if order id already exist*/
        if (orderDAO.exist(dto.getOrderId())) {

        }

        connection.setAutoCommit(false);
        boolean save = orderDAO.save(new Orders(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId()));

        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detailDTO : dto.getOrderDetails()) {
            boolean save1 = orderDetailsDAO.save(new OrderDetails(detailDTO.getOrderId(), detailDTO.getItemCode(), detailDTO.getQty(), detailDTO.getUnitPrice()));
            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//              Search & Update item
            ItemDTO item = searchItem(detailDTO.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

//              update item
            System.out.println(item);
            boolean update = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }
        return allItems;
    }
}
