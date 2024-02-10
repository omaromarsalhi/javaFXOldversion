package pidev.javafx.Model.Contrat;

import java.sql.Timestamp;
import java.util.Date;

public class Contrat {

    private int idContrat;
    private String title;
    private int idPartA;
    private int idPartB;
    private int idItem;
    private String effectiveDate;
    private String terminationDate;
    private String purpose;
    private String termsAndConditions;
    private PaymentMethod paymentMethod;

    public Contrat(int idContrat, String title, int idPartA, int idPartB, int idItem, String effectiveDate, String terminationDate, String purpose, String termsAndConditions, PaymentMethod paymentMethod) {
        this.idContrat = idContrat;
        this.title = title;
        this.idPartA = idPartA;
        this.idPartB = idPartB;
        this.idItem = idItem;
        this.effectiveDate = effectiveDate;
        this.terminationDate = terminationDate;
        this.purpose = purpose;
        this.termsAndConditions = termsAndConditions;
        this.paymentMethod = paymentMethod;
    }


    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdPartA() {
        return idPartA;
    }

    public void setIdPartA(int idPartA) {
        this.idPartA = idPartA;
    }

    public int getIdPartB() {
        return idPartB;
    }

    public void setIdPartB(int idPartB) {
        this.idPartB = idPartB;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
