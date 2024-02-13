package pidev.javafx.Model.Wrapper;

import pidev.javafx.Model.Contrat.Contract;
import pidev.javafx.Model.MarketPlace.Product;
import pidev.javafx.Model.MarketPlace.Transaction;

public class LocalWrapper {

    private Product product;
    private Transaction transaction;
    private Contract contract;

    public LocalWrapper() {
    }

    public LocalWrapper(Product product, Transaction transaction, Contract contract) {
        this.product = product;
        this.transaction = transaction;
        this.contract = contract;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
