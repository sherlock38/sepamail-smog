package smog.missive.structures;

import java.util.ArrayList;
import java.util.Calendar;
import smog.schema.p13.BranchAndFinancialInstitutionIdentification5;
import smog.schema.p13.CashAccount7;
import smog.schema.p13.ChargeBearerType1Code;
import smog.schema.p13.CreditTransferTransactionInformation14;
import smog.schema.p13.PartyIdentification43;
import smog.schema.p13.PaymentInstruction5;
import smog.schema.p13.PaymentMethod7Code;
import smog.schema.p13.PaymentTypeInformation19;

/**
 * Pain013PaymentInformation class represents a pain.013.001.01 payment information.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013PaymentInformation {

    // Class attributes
    private ChargeBearerType1Code.Enum chargeBearer;
    private ArrayList<Pain013CreditTransferTransactionInformation> creditTransferTransactionInformation;
    private PartyIdentification43 debtor;
    private CashAccount7 debtorAccount;
    private BranchAndFinancialInstitutionIdentification5 debtorAgent;
    private String id;
    private PaymentInstruction5 paymentInstruction;
    private PaymentMethod7Code.Enum paymentMethod;
    private PaymentTypeInformation19 paymentTypeInformation;
    private Calendar requestedExecutionDate;
    private PartyIdentification43 ultimateDebtor;

    /**
     * Pain013PaymentInformation constructor
     *
     * @param id Reference assigned to unambiguously identify the payment information block within the message
     * @param paymentMethod Means of payment that will be used to move the amount of money
     * @param paymentTypeInformation Type of transaction
     * @param requestedExecutionDate Date at which payment needs to be processed
     * @param debtor Party that owes an amount of money to the creditor
     * @param debtorAccount Account used to process charges associated with a transaction
     * @param debtorAgent Financial institution servicing an account for the debtor
     * @param ultimateDebtor Ultimate party that owes an amount of money to the creditor
     * @param chargeBearer Party who bears the charges associated with the processing of the payment transaction
     */
    public Pain013PaymentInformation(String id, PaymentMethod7Code.Enum paymentMethod,
            PaymentTypeInformation19 paymentTypeInformation, Calendar requestedExecutionDate,
            PartyIdentification43 debtor, CashAccount7 debtorAccount,
            BranchAndFinancialInstitutionIdentification5 debtorAgent, PartyIdentification43 ultimateDebtor,
            ChargeBearerType1Code.Enum chargeBearer) {

        // Initialise class attributes
        this.chargeBearer = chargeBearer;
        this.creditTransferTransactionInformation = new ArrayList<>();
        this.debtor = debtor;
        this.debtorAccount = debtorAccount;
        this.debtorAgent = debtorAgent;
        this.id = id;
        this.paymentInstruction = PaymentInstruction5.Factory.newInstance();
        this.paymentMethod = paymentMethod;
        this.paymentTypeInformation = paymentTypeInformation;
        this.requestedExecutionDate = requestedExecutionDate;
        this.ultimateDebtor = ultimateDebtor;

        // Set the payment information identification of the message segment
        this.setPiPaymentInformationIdentification();

        // Set the payment method of the message segment
        this.setPiPaymentMethod();

        // Set the payment type information of the message segment
        this.setPiPaymentTypeInformation();

        // Set the requested execution date of the message segment
        this.setPiRequestedExecutionDate();

        // Set the debtor of the message segment
        this.setPiDebtor();

        // Set the debtor account of the message segment
        this.setPiDebtorAccount();

        // Set the debtor agent of the message segment
        this.setPiDebtorAgent();

        // Set the ultimate debtor in the message segment
        this.setPiUltimateDebtor();

        // Set the charge bearer of the message segment
        this.setPiChargeBearer();

        // Set the credit transfer transaction information of the message segment
        this.setPiCreditTransferTransactionInformation();
    }

    /**
     * Add the given credit transfer transaction information to the payment information
     *
     * @param p013ctti Credit transfer transaction information
     */
    public void addCreditTransferTransactionInformation(Pain013CreditTransferTransactionInformation p013ctti) {

        // Add the given credit transfer transaction information to the list
        this.creditTransferTransactionInformation.add(p013ctti);

        // Set the credit transfer transaction information of the message segment
        this.setPiCreditTransferTransactionInformation();
    }

    /**
     * Clear the list of credit transfer transaction information
     */
    public void clearCreditTransferTransactionInformation() {

        // Clear the list of credit transfer transaction information
        this.creditTransferTransactionInformation.clear();

        // Set the credit transfer transaction information of the message segment
        this.setPiCreditTransferTransactionInformation();
    }

    /**
     * Get the charge bearer
     *
     * @return Charge bearer
     */
    public ChargeBearerType1Code.Enum getChargeBearer() {

        // Get the charge bearer
        return this.chargeBearer;
    }

    /**
     * Get the list of credit transfer transaction information
     *
     * @return List of credit transfer transaction information
     */
    public ArrayList<Pain013CreditTransferTransactionInformation> getCreditTransferTransactionInformation() {

        // Get the list of credit transfer transaction information
        return this.creditTransferTransactionInformation;
    }

    /**
     * Get the credit transfer transaction information at the given index
     *
     * @param index Index of credit transfer transaction information
     * @return Credit transfer transaction information at the given index
     */
    public Pain013CreditTransferTransactionInformation getCreditTransferTransactionInformationAtIndex(int index) {

        // Verify that the index is valid
        if (index > -1 && index < this.creditTransferTransactionInformation.size()) {

            // Credit transfer transaction information at given index
            return this.creditTransferTransactionInformation.get(index);
        }

        // The credit transfer transaction information could not be found
        return null;
    }

    /**
     * Get the debtor
     *
     * @return Debtor
     */
    public PartyIdentification43 getDebtor() {

        // Get the debtor
        return this.debtor;
    }

    /**
     * Get the debtor account
     *
     * @return Debtor account
     */
    public CashAccount7 getDebtorAccount() {

        // Get the debtor account
        return this.debtorAccount;
    }

    /**
     * Get the debtor agent
     *
     * @return Debtor agent
     */
    public BranchAndFinancialInstitutionIdentification5 getDebtorAgent() {

        // Get the debtor agent
        return this.debtorAgent;
    }

    /**
     * Get the payment information identification of the payment instruction
     *
     * @return Payment information identification of the payment instruction
     */
    public String getId() {

        // Get the payment information identification of the payment instruction
        return this.id;
    }

    /**
     * Get the payment method
     *
     * @return Payment method
     */
    public PaymentMethod7Code.Enum getPaymentMethod() {

        // Get the payment method
        return this.paymentMethod;
    }

    /**
     * Get the payment instruction message segment
     *
     * @return Payment instruction message segment
     */
    public PaymentInstruction5 getPaymentInstruction() {

        // Get the payment instruction message segment
        return this.paymentInstruction;
    }

    /**
     * Get the payment type information
     *
     * @return Payment type information
     */
    public PaymentTypeInformation19 getPaymentTypeInformation() {

        // Get the payment type information
        return this.paymentTypeInformation;
    }

    /**
     * Get the requested execution date
     *
     * @return Requested execution date
     */
    public Calendar getRequestedExecutionDate() {

        // Get the requested execution date
        return this.requestedExecutionDate;
    }

    /**
     * Get the ultimate debtor
     *
     * @return Ultimate debtor
     */
    public PartyIdentification43 getUltimateDebtor() {

        // Get the ultimate debtor
        return this.ultimateDebtor;
    }

    /**
     * Remove the credit transfer transaction information at the given index
     *
     * @param index Index of credit transfer transaction information
     * @return Whether the credit transfer transaction information could be removed
     */
    public boolean removeCreditTransferTransactionInformationAtIndex(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.creditTransferTransactionInformation.size()) {

            // Remove the credit transfer transaction information
            this.creditTransferTransactionInformation.remove(index);

            // Set the credit transfer transaction information of the message segment
            this.setPiCreditTransferTransactionInformation();

            return true;
        }

        // Credit transfer transaction could not be removed
        return false;
    }

    /**
     * Set the charge bearer
     *
     * @param chargeBearer Charge bearer
     */
    public void setChargeBearer(ChargeBearerType1Code.Enum chargeBearer) {

        // Set the charge bearer
        this.chargeBearer = chargeBearer;

        // Set the charge bearer of the message segment
        this.setPiChargeBearer();
    }

    /**
     * Set the debtor
     *
     * @param debtor Debtor
     */
    public void setDebtor(PartyIdentification43 debtor) {

        // Set the debtor
        this.debtor = debtor;

        // Set the debtor of the message segment
        this.setPiDebtor();
    }

    /**
     * Set the debtor account
     *
     * @param debtorAccount Debtor account
     */
    public void setDebtorAccount(CashAccount7 debtorAccount) {

        // Set the debtor account
        this.debtorAccount = debtorAccount;

        // Set the debtor account of the message segment
        this.setPiDebtorAccount();
    }

    /**
     * Set the debtor agent
     *
     * @param debtorAgent Debtor agent
     */
    public void setDebtorAgent(BranchAndFinancialInstitutionIdentification5 debtorAgent) {

        // Set the debtor agent
        this.debtorAgent = debtorAgent;

        // Set the debtor agent of the message segment
        this.setPiDebtorAgent();
    }

    /**
     * Set the payment information identification
     *
     * @param id Payment information identification
     */
    public void setId(String id) {

        // Set the ID of the payment information
        this.id = id;

        // Set the payment information identification of the message segment
        this.setPiPaymentInformationIdentification();
    }

    /**
     * Set the payment method
     *
     * @param paymentMethod Payment method
     */
    public void setPaymentMethod(PaymentMethod7Code.Enum paymentMethod) {

        // Set the payment method
        this.paymentMethod = paymentMethod;

        // Set the payment method of the message segment
        this.setPiPaymentMethod();
    }

    /**
     * Set the payment type information
     *
     * @param paymentTypeInformation Payment type information
     */
    public void setPaymentTypeInformation(PaymentTypeInformation19 paymentTypeInformation) {

        // Set the payment type information
        this.paymentTypeInformation = paymentTypeInformation;

        // Set the payment type information of the message segment
        this.setPiPaymentTypeInformation();
    }

    /**
     * Set the requested execution date
     *
     * @param requestedExecutionDate Requested execution date
     */
    public void setRequestedExecutionDate(Calendar requestedExecutionDate) {

        // Set the requested execution date
        this.requestedExecutionDate = requestedExecutionDate;

        // Set the requested execution date of the message segment
        this.setPiRequestedExecutionDate();
    }

    /**
     * Set the ultimate debtor
     *
     * @param ultimateDebtor Ultimate debtor
     */
    public void setUltimateDebtor(PartyIdentification43 ultimateDebtor) {

        // Set the ultimate debtor
        this.ultimateDebtor = ultimateDebtor;

        // Set the ultimate debtor in the message segment
        this.setPiUltimateDebtor();
    }

    /**
     * Set the charge bearer of the message segment
     */
    private void setPiChargeBearer() {

        // Check if the charge bearer has been defined
        if (this.chargeBearer != null) {

            // Set the charge bearer of the message segment
            this.paymentInstruction.setChrgBr(this.chargeBearer);

        } else {

            // Remove the charge bearer from the message segment
            if (this.paymentInstruction.isSetChrgBr()) {
                this.paymentInstruction.unsetChrgBr();
            }
        }
    }

    /**
     * Set the credit transfer transaction information of the message segment
     */
    private void setPiCreditTransferTransactionInformation() {

        // Remove all credit transfer transaction information from the message segment
        for (int i = this.paymentInstruction.sizeOfCdtTrfTxArray() - 1; i > -1; i--) {
            this.paymentInstruction.removeCdtTrfTx(i);
        }

        // Verify that the list of credit transfer transaction information is not empty
        if (!this.creditTransferTransactionInformation.isEmpty()) {

            // Array of credit transfer transaction formation message segments
            CreditTransferTransactionInformation14[] cttis =
                    new CreditTransferTransactionInformation14[this.creditTransferTransactionInformation.size()];

            // Build the array of credit transfer transaction information message segments
            for (int i = 0; i < this.creditTransferTransactionInformation.size(); i++) {
                cttis[i] = this.creditTransferTransactionInformation.get(i).getCreditTransferTransactionInformation();
            }

            // Add the credit transfer transaction information message segments to the message segment
            this.paymentInstruction.setCdtTrfTxArray(cttis);
        }
    }

    /**
     * Set the debtor of the message segment
     */
    private void setPiDebtor() {

        // Set the debtor of the message segment
        this.paymentInstruction.setDbtr(this.debtor);
    }

    /**
     * Set the debtor account of the message segment
     */
    private void setPiDebtorAccount() {

        // Check if the debtor account has been defined
        if (this.debtorAccount != null) {

            // Set the debtor account of the message segment
            this.paymentInstruction.setDbtrAcct(this.debtorAccount);

        } else {

            // Remove the debtor account from the message segment
            if (this.paymentInstruction.isSetDbtrAcct()) {
                this.paymentInstruction.unsetDbtrAcct();
            }
        }
    }

    /**
     * Set the debtor agent of the message segment
     */
    private void setPiDebtorAgent() {

        // Set the debtor agent of the message segment
        this.paymentInstruction.setDbtrAgt(this.debtorAgent);
    }

    /**
     * Set the payment information identification of the message segment
     */
    private void setPiPaymentInformationIdentification() {

        // Check if the payment information identification has been specified
        if (this.id != null) {

            // Set the payment information identification of the message segment
            this.paymentInstruction.setPmtInfId(this.id);

        } else {

            // Remove the payment information identification from the message segment
            if (this.paymentInstruction.isSetPmtInfId()) {
                this.paymentInstruction.unsetPmtInfId();
            }
        }
    }

    /**
     * Set the payment method of the message segment
     */
    private void setPiPaymentMethod() {

        // Set the payment method of the message segment
        this.paymentInstruction.setPmtMtd(this.paymentMethod);
    }

    /**
     * Set the payment type information of the message segment
     */
    private void setPiPaymentTypeInformation() {

        // Check if the payment type information has been specified
        if (this.paymentTypeInformation != null) {

            // Set the payment type information of the message segment
            this.paymentInstruction.setPmtTpInf(this.paymentTypeInformation);

        } else {

            // Remove the payment type information from the message segment
            if (this.paymentInstruction.isSetPmtTpInf()) {
                this.paymentInstruction.unsetPmtTpInf();
            }
        }
    }

    /**
     * Set the requested execution date of the message segment
     */
    private void setPiRequestedExecutionDate() {

        // Set the requested execution date of the message segment
        this.paymentInstruction.setReqdExctnDt(this.requestedExecutionDate);
    }

    /**
     * Set the ultimate debtor in the message segment
     */
    private void setPiUltimateDebtor() {

        // Check if the ultimate debtor has been specified
        if (this.ultimateDebtor != null) {

            // Set the ultimate debtor in the message segment
            this.paymentInstruction.setUltmtDbtr(this.ultimateDebtor);

        } else {

            // Remove the ultimate debtor from the message segment
            if (this.paymentInstruction.isSetUltmtDbtr()) {
                this.paymentInstruction.unsetUltmtDbtr();
            }
        }
    }
}
