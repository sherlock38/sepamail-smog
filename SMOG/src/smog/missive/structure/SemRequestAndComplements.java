package smog.missive.structure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import smog.schema.p13.CreditorPaymentActivationRequestV01;
import smog.schema.p13.GroupHeader45;
import smog.schema.p13.PartyIdentification43;
import smog.schema.p13.PaymentInstruction5;
import smog.schema.sem.GuaranteeType;
import smog.schema.sem.PaymentConditions;
import smog.schema.sem.PaymentMethod;
import smog.schema.sem.RelationType;
import smog.schema.sem.RequestAndComplements;
import smog.schema.sem.RequestComplements;
import smog.schema.sem.TransferNature;

/**
 * SemRequestAndComplements represents a SEPAmail request and elements element.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SemRequestAndComplements {

    // Class attributes
    private BigDecimal controlSum;
    private Calendar creationDateTime;
    private CreditorPaymentActivationRequestV01 creditorPaymentActivationRequest;
    private ArrayList<String> customReferences;
    private GroupHeader45 groupHeader;
    private PartyIdentification43 initiatingParty;
    private String messageId;
    private PaymentConditions paymentConditions;
    private GuaranteeType.Enum paymentGuarantee;
    private PaymentMethod.Enum otherPaymentMethod;
    private ArrayList<Pain013PaymentInformation> paymentInformation;
    private RelationType.Enum relationType;
    private RequestAndComplements requestAndComplements;
    private RequestComplements requestComplements;
    private String title;
    private TransferNature.Enum transferNature;

    /**
     * SemRequestAndComplements constructor
     *
     * @param messageId Point to point reference to unambiguously identify the message
     * @param creationDateTime Date and time at which a payment instruction was created
     * @param controlSum Total of all individual amounts included in the message
     * @param initiatingParty Party initiating the creditor payment activation request
     * @param otherPaymentMethod Payment method requested by creditor
     * @param paymentConditions Specific payment conditions
     * @param paymentGuarantee Payment guarantee to be applied to this transaction
     * @param relationType Type of relation between creditor and debtor
     * @param title Payment description
     * @param transferNature Nature of the generated payment
     */
    public SemRequestAndComplements(String messageId, Calendar creationDateTime, BigDecimal controlSum,
            PartyIdentification43 initiatingParty, PaymentMethod.Enum otherPaymentMethod,
            PaymentConditions paymentConditions, GuaranteeType.Enum paymentGuarantee, RelationType.Enum relationType,
            String title, TransferNature.Enum transferNature) {

        // Initialise class attributes
        this.controlSum = controlSum;
        this.creationDateTime = creationDateTime;
        this.creditorPaymentActivationRequest = CreditorPaymentActivationRequestV01.Factory.newInstance();
        this.customReferences = new ArrayList<>();
        this.groupHeader = GroupHeader45.Factory.newInstance();
        this.initiatingParty = initiatingParty;
        this.messageId = messageId;
        this.otherPaymentMethod = otherPaymentMethod;
        this.paymentConditions = paymentConditions;
        this.paymentGuarantee = paymentGuarantee;
        this.paymentInformation = new ArrayList<>();
        this.relationType = relationType;
        this.requestAndComplements = RequestAndComplements.Factory.newInstance();
        this.requestComplements = RequestComplements.Factory.newInstance();
        this.title = title;
        this.transferNature = transferNature;

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Add a custom reference to the request complements
     *
     * @param customReference Custom reference
     * @return Whether the custom reference was successfully added
     */
    public boolean addCustomReference(String customReference) {

        // Check if the custom reference already exists in the list of custom references
        if (!this.customReferences.contains(customReference)) {

            // Add the custom reference to the request complements
            this.customReferences.add(customReference);

            // Build the request complements message segment
            this.buildRequestComplements();

            // Set the complements of the request and complements message segment
            this.setRacComplements();

            return true;
        }

        // Custom reference could nout be added
        return false;
    }

    /**
     * Add the given payment information to the list of payment information
     *
     * @param paymentInformation Payment information
     */
    public void addPaymentInformation(Pain013PaymentInformation paymentInformation) {

        // Add the payment information to the list
        this.paymentInformation.add(paymentInformation);

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Clear the list of custom references
     */
    public void clearCustomReferences() {

        // Clear the list of custom references
        this.customReferences.clear();

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Clear the list of payment information
     */
    public void clearPaymentInformation() {

        // Clear the list of payment information
        this.paymentInformation.clear();

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

   /**
     * Compare SemRequestAndComplements objects based on the message ID
     *
     * @param other Object that needs to be compared to the current SemRequestAndComplements instance
     * @return Whether the given object is equal to the current SemRequestAndComplements instance
     */
    @Override
    public boolean equals(Object other) {

        // Comparison result
        boolean result = false;

        // Check if the given object is not null
        if (other != null) {

            // Check if the given object is of the same type
            if (other instanceof SemRequestAndComplements) {

                // Check if we have same message ID
                return this.messageId.equals(((SemRequestAndComplements) other).messageId);
            }
        }

        return result;
    }

    /**
     * Generate a hash for a SemRequestAndComplements object based on its message ID
     *
     * @return Hash for SemRequestAndComplements object based on the message ID
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.messageId);
        return hash;
    }

    /**
     * Get the control sum
     *
     * @return Control sum
     */
    public BigDecimal getControlSum() {

        // Get the control sum
        return this.controlSum;
    }

    /**
     * Get the list of custom references
     *
     * @return List of custom references
     */
    public ArrayList<String> getCustomReferences() {

        // Get the list of custom references
        return this.customReferences;
    }

    /**
     * Get the creation date and time
     *
     * @return Creation date and time
     */
    public Calendar getCreationDateTime() {

        // Get the creation date and time
        return this.creationDateTime;
    }

    /**
     * Get the initiating party
     *
     * @return Initiating party
     */
    public PartyIdentification43 getInitiatingPary() {

        // Get the initiating party
        return this.initiatingParty;
    }

    /**
     * Get the message ID
     *
     * @return Message ID
     */
    public String getMessageId() {

        // Get the message ID
        return this.messageId;
    }

    /**
     * Get the payment guarantee
     *
     * @return Payment guarantee
     */
    public GuaranteeType.Enum getPaymentGuarantee() {

        // Get the payment guarantee
        return this.paymentGuarantee;
    }

    /**
     * Get the list of payment information
     *
     * @return List of payment information
     */
    public ArrayList<Pain013PaymentInformation> getPaymentInformation() {

        // Get the list of payment information
        return this.paymentInformation;
    }

    /**
     * Get the of payment information at the specified index
     *
     * @param index Index of payment information
     * @return Payment information at specified index
     */
    public Pain013PaymentInformation getPaymentInformationAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.paymentInformation.size()) {

            // Payment information at specified index
            return this.paymentInformation.get(index);
        }

        // Payment information could not be obtained
        return null;
    }

    /**
     * Get the relation type
     *
     * @return Relation type
     */
    public RelationType.Enum getRelationType() {

        // Get the relation type
        return this.relationType;
    }

    /**
     * Get the request and complements message segment
     *
     * @return Request and complements message segment
     */
    public RequestAndComplements getRequestAndComplements() {

        // Get the request and complements message segment
        return this.requestAndComplements;
    }

    /**
     * Get the request complements title
     *
     * @return Request complements title
     */
    public String getTitle() {

        // Get the request complements title
        return this.title;
    }

    /**
     * Get the transfer nature
     *
     * @return Transfer nature
     */
    public TransferNature.Enum getTransferNature() {

        // Get the transfer nature
        return this.transferNature;
    }

    /**
     * Remove the given custom reference from the request complements
     *
     * @param customReference Custom reference that needs to be removed
     * @return Whether the custom reference could be removed
     */
    public boolean removeCustomReference(String customReference) {

        // Check if custom reference can be removed
        if (this.customReferences.remove(customReference)) {

            // Build the request complements message segment
            this.buildRequestComplements();

            // Set the complements of the request and complements message segment
            this.setRacComplements();

            return true;
        }

        // Custom reference could not be removed
        return false;
    }

    /**
     * Remove the payment information at the specified index
     *
     * @param index Index of payment information
     * @return Whether the payment was successfully removed
     */
    public boolean removePaymentInformationAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.paymentInformation.size()) {

            // Remove payment information at specified index
            this.paymentInformation.remove(index);

            // Build the group header message segment
            this.buildGroupHeader();

            // Build the creditor payment activation request message segment
            this.buildCreditorPaymentActivationRequest();

            // Set the request of the request and complements message segment
            this.setRacRequest();

            return true;
        }

        // Payment information could not be removed
        return false;
    }

    /**
     * Set the control sum
     *
     * @param controlSum Control sum
     */
    public void setControlSum(BigDecimal controlSum) {

        // Set the control sum
        this.controlSum = controlSum;

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Set the creation date and time
     *
     * @param creationDateTime Creation date and time
     */
    public void setCreationDateTime(Calendar creationDateTime) {

        // Set the creation date and time
        this.creationDateTime = creationDateTime;

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Set the initiating party
     *
     * @param intiatingParty Initiating party
     */
    public void setInitiatingPary(PartyIdentification43 intiatingParty) {

        // Set the initiating party
        this.initiatingParty = intiatingParty;

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Set the message ID
     *
     * @param messageId Message ID
     */
    public void setMessageId(String messageId) {

        // Set the message ID
        this.messageId = messageId;

        // Build the group header message segment
        this.buildGroupHeader();

        // Build the creditor payment activation request message segment
        this.buildCreditorPaymentActivationRequest();

        // Set the request of the request and complements message segment
        this.setRacRequest();
    }

    /**
     * Set the other payment method
     *
     * @param otherPaymentMethod Other payment method
     */
    public void setOtherPaymentMethod(PaymentMethod.Enum otherPaymentMethod) {

        // Set the other payment method
        this.otherPaymentMethod = otherPaymentMethod;

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Set the payment guarantee
     *
     * @param paymentGuarantee Payment guarantee
     */
    public void setPaymentGuarantee(GuaranteeType.Enum paymentGuarantee) {

        // Set payment guarantee
        this.paymentGuarantee = paymentGuarantee;

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Set the relation type
     *
     * @param relationType Relation type
     */
    public void setRelationType(RelationType.Enum relationType) {

        // Set the relation type
        this.relationType = relationType;

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Set the request complements title
     *
     * @param title Request complements title
     */
    public void getTitle(String title) {

        // Set the request complements title
        this.title = title;

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Set the transfer nature
     *
     * @param transferNature Transfer nature
     */
    public void setTransferNature(TransferNature.Enum transferNature) {

        // Set the transfer nature
        this.transferNature = transferNature;

        // Build the request complements message segment
        this.buildRequestComplements();

        // Set the complements of the request and complements message segment
        this.setRacComplements();
    }

    /**
     * Build the creditor payment activation request message segment
     */
    private void buildCreditorPaymentActivationRequest() {

        // Set the group header of the creditor payment activation request
        this.creditorPaymentActivationRequest.setGrpHdr(this.groupHeader);

        // Remove all payment information message segments from the creditor payment activation request
        for (int i = this.creditorPaymentActivationRequest.sizeOfPmtInfArray() - 1; i > -1; i--) {
            this.creditorPaymentActivationRequest.removePmtInf(i);
        }

        // Check that the list of payment information is not empty
        if (!this.paymentInformation.isEmpty()) {

            // Array of payment information message segments
            PaymentInstruction5[] paymentInstructions = new PaymentInstruction5[this.paymentInformation.size()];

            // Build the array of payment information
            for (int i = 0; i < this.paymentInformation.size(); i++) {
                paymentInstructions[i] = this.paymentInformation.get(i).getPaymentInstruction();
            }

            // Add the payment information message segments to the creditor payment activation request message segment
            this.creditorPaymentActivationRequest.setPmtInfArray(paymentInstructions);
        }
    }

    /**
     * Build the group header message segment
     */
    private void buildGroupHeader() {

        // Add the message ID to the group header
        this.groupHeader.setMsgId(this.messageId);

        // Set the message creation date and time
        this.groupHeader.setCreDtTm(this.creationDateTime);

        // Set the number of transactions
        this.groupHeader.setNbOfTxs(Integer.valueOf(this.paymentInformation.size()).toString());

        // Check if the control sum has been specified
        if (this.controlSum != null) {

            // Set the control sum
            this.groupHeader.setCtrlSum(this.controlSum);

        } else {

            // Remove the control sum from the group header message segment
            if (this.groupHeader.isSetCtrlSum()) {
                this.groupHeader.unsetCtrlSum();
            }
        }

        // Set the initiating party
        this.groupHeader.setInitgPty(this.initiatingParty);
    }

    /**
     * Build the request complements message segment
     */
    private void buildRequestComplements() {

        // Remove custom references from the request complements message segment
        for (int i = this.requestComplements.sizeOfCustRefArray() - 1; i > -1; i--) {
            this.requestComplements.removeCustRef(i);
        }

        // Check if the list of custom references is not empty
        if (!this.customReferences.isEmpty()) {

            // Array of custom references
            String[] customRefs = new String[this.customReferences.size()];

            // Build the array of custom references
            for (int i = 0; i < this.customReferences.size(); i++) {
                customRefs[i] = this.customReferences.get(i);
            }

            // Add the array of custom references to the request complements message segment
            this.requestComplements.setCustRefArray(customRefs);
        }

        // Check if the other payment method has been defined
        if (this.otherPaymentMethod != null) {

            // Add the other payment method to the request complements message segment
            this.requestComplements.setOtherPmtMtd(this.otherPaymentMethod);

        } else {

            // Remove the other payment method from the request complements message segment
            if (this.requestComplements.isSetOtherPmtMtd()) {
                this.requestComplements.unsetOtherPmtMtd();
            }
        }

        // Set the payment conditions of the request complements message segment
        this.requestComplements.setPmtCond(this.paymentConditions);

        // Check if the payment guarantee has been defined
        if (this.paymentGuarantee != null) {

            // Add the payment guarantee to the request complements message segment
            this.requestComplements.setPmtGuarantee(this.paymentGuarantee);

        } else {

            // Remove the payment guaratee from the request complements segment
            if (this.requestComplements.isSetPmtGuarantee()) {
                this.requestComplements.unsetPmtGuarantee();
            }
        }

        // Set the relation type in the request complements message segment
        this.requestComplements.setRltnType(this.relationType);

        // Set the title of the request complements message segment
        this.requestComplements.setTitle(title);

        // Check if the transfer nature has been defined
        if (this.transferNature != null) {

            // Add the transfer nature to the request complements message segment
            this.requestComplements.setTrfNature(this.transferNature);

        } else {

            // Remove the transfer nature from the request complements message segment
            if (this.requestComplements.isSetTrfNature()) {
                this.requestComplements.unsetTrfNature();
            }
        }
    }

    /**
     * Set the complements of the request and complements message segment
     */
    private void setRacComplements() {

        // Set the complements of the request and complements message segment
        this.requestAndComplements.setComplements(this.requestComplements);
    }

    /**
     * Set the request of the request and complements message segment
     */
    private void setRacRequest() {

        // Set the request of the request and complements message segment
        this.requestAndComplements.setRequest(this.creditorPaymentActivationRequest);
    }
}
