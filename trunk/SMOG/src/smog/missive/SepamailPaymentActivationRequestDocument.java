package smog.missive;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import smog.missive.structures.SemHURADocument;
import smog.missive.structures.SemRequestAndComplements;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.Document;
import smog.schema.sem.MessageType;
import smog.schema.sem.MissiveType;
import smog.schema.sem.PaymentActivationRequest;
import smog.schema.sem.PriorityCode;
import smog.schema.sem.ReceiverIdentifier;
import smog.schema.sem.RequestAndComplements;
import smog.schema.sem.RequestHeader;
import smog.schema.sem.SepamailMessagePaymentActivationRequest001;
import smog.utils.Utils;

/**
 * SepamailPaymentActivationRequestDocument creates an payment activation request document.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SepamailPaymentActivationRequestDocument extends DocumentBase implements DocumentInterface {

    // Class attributes
    private PaymentActivationRequest paymentActivationRequest;
    private RequestHeader requestHeader;
    private ArrayList<SemHURADocument> semHuraDocuments;
    private ArrayList<SemRequestAndComplements> semRequestAndComplements;
    private SepamailMessagePaymentActivationRequest001 sepamailMessagePaymentActivationRequest001;

    /**
     * SepamailPaymentActivationRequestDocument constructor
     *
     * @param receiverIdentifier The activation.request@payment.activation recipient
     * @param sender The activation.request@payment.activation sender
     * @param sendDateTime The date and time at which the activation.request@payment.activation is being sent
     * @param messageExpiry The date and time at which the message expires
     * @param creditDateTime The credit date and time
     */
    public SepamailPaymentActivationRequestDocument(ReceiverIdentifier receiverIdentifier, BICorIBAN sender,
            Calendar sendDateTime, Calendar messageExpiry, Calendar creditDateTime) {

        // Initialise the parent class
        super("1206", "1206", Utils.generateMissiveId(), BigInteger.ONE, PriorityCode.NORMAL, MissiveType.NOMINAL,
                receiverIdentifier, sender, sendDateTime, Utils.generateMessageId(), messageExpiry);

        // Initialise class attributes
        this.paymentActivationRequest = PaymentActivationRequest.Factory.newInstance();
        this.requestHeader = RequestHeader.Factory.newInstance();
        this.semHuraDocuments = new ArrayList<>();
        this.semRequestAndComplements = new ArrayList<>();
        this.sepamailMessagePaymentActivationRequest001 =
                SepamailMessagePaymentActivationRequest001.Factory.newInstance();

        // Set the credit date and time
        this.requestHeader.setCreDtTm(creditDateTime);

        // Set the message type
        this.messageHeader.setMsgTyp(MessageType.ACTIVATION_REQUEST_PAYMENT_ACTIVATION);

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * SepamailPaymentActivationRequestDocument constructor
     *
     * @param missiveId The ID of the missive
     * @param missiveOrder The order of the missive
     * @param priorityCode The priority of the missive
     * @param missiveType The type of the missive
     * @param receiverIdentifier The activation.request@payment.activation recipient
     * @param sender The activation.request@payment.activation sender
     * @param sendDateTime The date and time at which the activation.request@payment.activation is being sent
     * @param messageId ID of the message
     * @param messageExpiry The date and time at which the message expires
     * @param creditDateTime The credit date and time
     */
    public SepamailPaymentActivationRequestDocument(String missiveId, BigInteger missiveOrder,
            PriorityCode.Enum priorityCode, MissiveType.Enum missiveType, ReceiverIdentifier receiverIdentifier,
            BICorIBAN sender, Calendar sendDateTime, String messageId, Calendar messageExpiry,
            Calendar creditDateTime) {

        // Initialise the parent class
        super("1206", "1206", missiveId, missiveOrder, priorityCode, missiveType, receiverIdentifier, sender,
                sendDateTime, messageId, messageExpiry);

        // Initialise class attributes
        this.paymentActivationRequest = PaymentActivationRequest.Factory.newInstance();
        this.requestHeader = RequestHeader.Factory.newInstance();
        this.semHuraDocuments = new ArrayList<>();
        this.semRequestAndComplements = new ArrayList<>();
        this.sepamailMessagePaymentActivationRequest001 =
                SepamailMessagePaymentActivationRequest001.Factory.newInstance();

        // Set the credit date and time
        this.requestHeader.setCreDtTm(creditDateTime);

        // Set the message type
        this.messageHeader.setMsgTyp(MessageType.ACTIVATION_REQUEST_PAYMENT_ACTIVATION);

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * Add the given HURA document to the list of HURA documents
     *
     * @param semHuraDocument HURA document
     */
    public void addHURADocument(SemHURADocument semHuraDocument) {

        // Add the document to the list of HURA documents
        this.semHuraDocuments.add(semHuraDocument);

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * Add the given request and complements element to the list of request and complement elements
     *
     * @param requestAndComplements Request and complements element
     */
    public void addRequestAndComplements(SemRequestAndComplements requestAndComplements) {

        // Add the given request and complements element to the list of request and complements elements
        this.semRequestAndComplements.add(requestAndComplements);

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * Clear the list of HURA documents
     */
    public void clearHuraDocuments() {

        // Clear the list of HURA documents
        this.semHuraDocuments.clear();

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * Clear the list of request and complements elements
     */
    public void clearRequestAndComplements() {

        // Clear the list of request and complements elements
        this.semRequestAndComplements.clear();

        // Build the activation.request@payment.activation missive
        this.createMissive();
    }

    /**
     * Build the missive document
     */
    @Override
    public void build() {

        // Build the missive using fragments
        this.createMissive();
    }

    /**
     * Get the list of HURA documents
     *
     * @return List of HURA documents
     */
    public ArrayList<SemHURADocument> getSemHURADocuments() {

        // Get the list HURA documents
        return this.semHuraDocuments;
    }

    /**
     * Get the HURA document at the specified index
     *
     * @param index Index of HURA document
     * @return HURA document at the specified index
     */
    public SemHURADocument getSemHURADocumentsAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.semHuraDocuments.size()) {

            // HURA document at specified index
            return this.semHuraDocuments.get(index);
        }

        // HURA document could not be found
        return null;
    }

    /**
     * Get the list of request and complements elements
     *
     * @return List of request and complements elements
     */
    public ArrayList<SemRequestAndComplements> getSemRequestAndComplements() {

        // Get the list of request and complements elements
        return this.semRequestAndComplements;
    }

    /**
     * Get the request and complements element at the specified index
     *
     * @param index Index of request and complements element
     * @return Request and complements element at the specified index
     */
    public SemRequestAndComplements getSemRequestAndComplementsAtIndex(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.semRequestAndComplements.size()) {

            // Request and complements element at specified index
            return this.semRequestAndComplements.get(index);
        }

        // The request and complements element could not be found
        return null;
    }

    /**
     * Remove the HURA document at the specified index
     *
     * @param index Index of the HURA document
     * @return Whether the HURA document could be removed
     */
    public boolean removeHURADocumentAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.semHuraDocuments.size()) {

            // Remove the HURA document from the list
            this.semHuraDocuments.remove(index);

            return true;
        }

        // HURA document could not be removed
        return false;
    }

    /**
     * Remove the request and complements element at the given index
     *
     * @param index Index of request and complements element
     * @return Whether the request and complements element could be removed
     */
    public boolean removeSemRequestAndComplements(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.semRequestAndComplements.size()) {

            // Remove the request and complements element from the list
            this.semRequestAndComplements.remove(index);

            return true;
        }

        // Request and complements element could not be removed
        return false;
    }

    /**
     * Build the HURA document message segment in the message header
     */
    private void buildHuraDocuments() {

        // Remove the HURA documents from the message header
        for (int i = this.requestHeader.sizeOfDocumentsArray() - 1; i > -1; i--) {
            this.requestHeader.removeDocuments(i);
        }

        // Check if the list of HURA documents is not empty
        if (!this.semHuraDocuments.isEmpty()) {

            // Array of HURA document
            Document[] documents = new Document[this.semHuraDocuments.size()];

            // Build the array of documents
            for (int i = 0; i < this.semHuraDocuments.size(); i++) {
                documents[i] = this.semHuraDocuments.get(i).getDocument();
            }

            // Set the HURA document segment of the message
            this.requestHeader.setDocumentsArray(documents);
        }
    }

    /**
     * Build the request and complements message segment of the message
     */
    private void buildRequestAndComplements() {

        // Remove existing request and complements
        for (int i = this.sepamailMessagePaymentActivationRequest001.sizeOfReqComplArray() - 1; i > -1; i--) {
            this.sepamailMessagePaymentActivationRequest001.removeReqCompl(i);
        }

        // Check if the list of request of complements is not empty
        if (!this.semRequestAndComplements.isEmpty()) {

            // Array of request and complements message segments
            RequestAndComplements[] requestAndComplements =
                    new RequestAndComplements[this.semRequestAndComplements.size()];

            // Build the list of request and complements message segments
            for (int i = 0; i < this.semRequestAndComplements.size(); i++) {
                requestAndComplements[i] = this.semRequestAndComplements.get(i).getRequestAndComplements();
            }

            // Add the array of request and complements message segments to the payment activation request message
            this.sepamailMessagePaymentActivationRequest001.setReqComplArray(requestAndComplements);
        }
    }

    /**
     * Build the missive
     */
    private void createMissive() {

        // Set the number of requests
        this.requestHeader.setNbOfRequests(BigInteger.valueOf(this.semRequestAndComplements.size()));

        // Add HURA documents to the message header
        this.buildHuraDocuments();

        // Add request and request complements to the payment activation request message
        this.sepamailMessagePaymentActivationRequest001.setHeader(this.requestHeader);

        // Add request and complements to the payment activation request message
        this.buildRequestAndComplements();

        // Add the payment activation request message to the wrapper
        this.paymentActivationRequest.setSepamailMessagePaymentActivationRequest001(this.sepamailMessagePaymentActivationRequest001);

        // Add the activation.request@payment.activation to the message body
        this.messageBody.setPaymentActivationRequest(this.paymentActivationRequest);

        // Build the rest of the missive document
        this.assembleDocument();
    }
}
