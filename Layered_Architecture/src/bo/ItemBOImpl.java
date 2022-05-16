package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = new ItemDAOImpl();

    @Override
=======
public class ItemBOImpl {
    private final ItemDAO itemDAO = new ItemDAOImpl();

>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(itemDTO);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(itemDTO);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> caca4539cdd90cfbfd074deac32d5277f5ecae75
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }
}
