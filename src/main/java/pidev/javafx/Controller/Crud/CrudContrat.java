package pidev.javafx.Controller.Crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.javafx.Model.Contrat.Contrat;
import pidev.javafx.Model.Contrat.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CrudContrat implements CrudInterface<Contrat> {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private static CrudContrat instance;

    private CrudContrat() {
    }

    public static CrudContrat getInstance() {
        if (instance == null)
            instance = new CrudContrat();
        return instance;
    }

    @Override
    public void addItem(Contrat contrat) {
        String sql = "INSERT INTO contrats (title, idPartA, idPartB, idItem, terminationDate, purpose, termsAndConditions, paymentMethod) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        connect = ConnectionDB.connectDb();

        try {
            prepare = connect.prepareStatement( sql );
            prepare.setString( 1, contrat.getTitle() );
            prepare.setInt( 2, contrat.getIdPartA() );
            prepare.setInt( 3, contrat.getIdPartB() );
            prepare.setInt( 4, contrat.getIdItem() );
            prepare.setString( 5, contrat.getTerminationDate() );
            prepare.setString( 6, contrat.getPurpose() );
            prepare.setString( 7, contrat.getTermsAndConditions() );
            prepare.setString( 8, contrat.getPaymentMethod().toString() );
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Contrat contrat) {
        try {

            String updateQuery = "UPDATE contrats SET title = ?, idPartA = ?, idPartB = ?, idItem = ?, " +
                    " terminationDate = ?, purpose = ?, termsAndConditions = ?, paymentMethod = ? " +
                    "WHERE idContrat = ?";

            connect = ConnectionDB.connectDb();

            prepare = connect.prepareStatement( updateQuery );
            prepare.setString( 1, contrat.getTitle() );
            prepare.setInt( 2, contrat.getIdPartA() );
            prepare.setInt( 3, contrat.getIdPartB() );
            prepare.setInt( 4, contrat.getIdItem() );
            prepare.setString( 5, contrat.getTerminationDate() );
            prepare.setString( 6, contrat.getPurpose() );
            prepare.setString( 7, contrat.getTermsAndConditions() );
            prepare.setString( 8, contrat.getPaymentMethod().toString() );
            prepare.setInt( 9, contrat.getIdContrat() );
            prepare.executeUpdate();
            // Handle any exceptions related to database operations
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (log, show error message, etc.)
        }
    }

    @Override
    public ObservableList<Contrat> selectItems() {
        ObservableList<Contrat> contratList = FXCollections.observableArrayList();
        try {
            // Assuming you have a database table named "contrats"
            String selectQuery = "SELECT * FROM contrats";

            connect = ConnectionDB.connectDb();


            prepare = connect.prepareStatement( selectQuery );
            result = prepare.executeQuery();
            while (result.next()) {
                contratList.add( new Contrat( result.getInt( "idContrat" ),
                        result.getString( "title" ),
                        result.getInt( "idPartA" ),
                        result.getInt( "idPartB" ),
                        result.getInt( "idItem" ),
                        result.getString( "effectiveDate" ),
                        result.getString( "terminationDate" ),
                        result.getString( "purpose" ),
                        result.getString( "termsAndConditions" ),
                        PaymentMethod.valueOf( result.getString( "paymentMethod" ) ) ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratList;
    }


    @Override
    public Contrat selectFirstItems() {
        // Similar to selectItems, retrieve the first Contrat item from the database
        // and return it.
        // You can modify the query to limit the result to one row.
        return null;
    }

    @Override
    public void deleteItem(int id) {
        try {
            String deleteQuery = "DELETE FROM contrats WHERE idContrat = ?";

            connect = ConnectionDB.connectDb();

            prepare = connect.prepareStatement( deleteQuery );
            prepare.setInt( 1, id );
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
