package pidev.javafx.Controller.Crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.javafx.Model.Contrat.Contract;
import pidev.javafx.Model.MarketPlace.Bien;
import pidev.javafx.Model.MarketPlace.Product;
import pidev.javafx.Model.MarketPlace.Transaction;
import pidev.javafx.Model.MarketPlace.TransactionMode;
import pidev.javafx.Model.Wrapper.LocalWrapper;

import java.sql.*;

public class CrudWrapper implements CrudInterface<LocalWrapper>{

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private static CrudTransaction instance;


    @Override
    public void addItem(LocalWrapper variable) {

    }

    @Override
    public void updateItem(LocalWrapper variable) {

    }

    @Override
    public ObservableList<LocalWrapper> selectItems() {
        String sql = "SELECT * FROM products p JOIN transactions t ON p.idProduct=t.idTransaction JOIN contracts c on t.idContract=c.idContract";
        connect = ConnectionDB.connectDb();
        ObservableList<LocalWrapper> wrapperList = FXCollections.observableArrayList();
        Product product=null;
        Transaction transaction=null;
        Contract contract=null;


        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                new LocalWrapper(result.getInt("idTransaction"),
                        result.getInt("idProd"),
                        result.getInt("idContract"),
                        result.getInt("idSeller"),
                        result.getInt("idBuyer"),
                        result.getFloat("pricePerUnit"),
                        result.getInt("quantity"),
                        TransactionMode.valueOf(result.getString("transactionMode")),
                        result.getTimestamp("timeStamp") );

                wrapperList.add( new LocalWrapper(product, transaction, contract ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Wrapper findById(int id) {
        return null;
    }

    @Override
    public Wrapper selectFirstItem() {
        return null;
    }

    @Override
    public void deleteItem(int id) {

    }
}
