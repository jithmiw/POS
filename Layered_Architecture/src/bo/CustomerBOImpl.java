package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
=======
public class CustomerBOImpl {
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(customerDTO);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

<<<<<<< HEAD
    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }
=======
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
}
