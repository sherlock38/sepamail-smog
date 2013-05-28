package smog.missive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.MessageType;
import smog.schema.sem.MissiveType;
import smog.schema.sem.PriorityCode;
import smog.schema.sem.ReceiverIdentifier;
import smog.schema.sem.SimpleTestRequest;
import smog.utils.Utils;

/**
 * SepamailTestRequestDocument creates a SEPAmail simple.request@test document.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SepamailTestRequestDocument extends DocumentBase implements DocumentInterface {

    // Class attributes
    private SimpleTestRequest simpleTestRequest;

    /**
     * SepamailTestRequestDocument constructor
     *
     * @param receiverIdentifier The simple.request@test recipient
     * @param sender The simple.request@test sender
     * @param sendDateTime The date and time at which the simple.request@test is being sent
     * @param testId The test ID of the simple.request@test message
     * @param messageExpiry The date and time at which the message expires
     * @param text The text content of the simple.request@test message
     * @param dataFile The data content of the simple.request@test message
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SepamailTestRequestDocument(ReceiverIdentifier receiverIdentifier, BICorIBAN sender, Calendar sendDateTime,
            String testId, Calendar messageExpiry, String text, File dataFile) throws FileNotFoundException,
            IOException {

        // Initialise the parent class
        super("1206", "1206", Utils.generateMissiveId(), BigInteger.ONE, PriorityCode.NORMAL, MissiveType.NOMINAL,
                receiverIdentifier, sender, sendDateTime, Utils.generateMessageId(), messageExpiry);

        // Initialise class attributes
        this.simpleTestRequest = SimpleTestRequest.Factory.newInstance();

        // Set the message type
        this.messageHeader.setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

        // Set the properties of the SimpleTestRequest element
        if (dataFile != null) {
            // Check if data files has been set
            this.simpleTestRequest.setData(Utils.getFileContent(dataFile));
        }
        this.simpleTestRequest.setTestId(testId);
        this.simpleTestRequest.setText(text);

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * SepamailTestRequestDocument constructor
     *
     * @param missiveId The ID of the missive
     * @param missiveOrder The order of the missive
     * @param priorityCode The priority of the missive
     * @param missiveType The type of the missive
     * @param receiverIdentifier The simple.request@test recipient
     * @param sender The simple.request@test sender
     * @param sendDateTime The date and time at which the simple.request@test is being sent
     * @param testId The test ID of the simple.request@test message
     * @param messageExpiry The date and time at which the message expires
     * @param text The text content of the simple.request@test message
     * @param dataFile The data content of the simple.request@test message
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SepamailTestRequestDocument(String missiveId, BigInteger missiveOrder, PriorityCode.Enum priorityCode,
            MissiveType.Enum missiveType, ReceiverIdentifier receiverIdentifier, BICorIBAN sender,
            Calendar sendDateTime, String testId, Calendar messageExpiry, String text, File dataFile)
            throws FileNotFoundException, IOException {

        // Initialise the parent class
        super("1206", "1206", missiveId, missiveOrder, priorityCode, missiveType, receiverIdentifier, sender,
                sendDateTime, Utils.generateMessageId(), messageExpiry);

        // Initialise class attributes
        this.simpleTestRequest = SimpleTestRequest.Factory.newInstance();

        // Set the message type
        this.messageHeader.setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

        // Set the properties of the SimpleTestRequest element
        if (dataFile != null) {
            // Check if data files has been set
            this.simpleTestRequest.setData(Utils.getFileContent(dataFile));
        }
        this.simpleTestRequest.setTestId(testId);
        this.simpleTestRequest.setText(text);

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * Build the missive document
     */
    @Override
    public void build() {

        // Build the missive document using fragments
        this.createDocument();
    }

    /**
     * Get the data of the simple.request@test message
     *
     * @return The data of the simple.request@test message
     */
    public byte[] getData() {

        // Check if data has been set
        if (this.simpleTestRequest.isSetData()) {
            return this.simpleTestRequest.getData();
        }

        return null;
    }

    /**
     * Get the test ID of the simple.request@test message
     *
     * @return The test ID of the simple.request@test message
     */
    public String getTestId() {

        // Get the test ID of the simple.request@test message
        return this.simpleTestRequest.getTestId();
    }

    /**
     * Get the text content of the simple.request@test message
     *
     * @return The text content of the simple.request@test message
     */
    public String getText() {

        // Check if the text content has been set
        if (this.simpleTestRequest.isSetText()) {
            return this.simpleTestRequest.getText();
        }

        return null;
    }

    /**
     * Remove the data content from the simple.request@test element
     */
    public void removeData() {

        // Remove the data content from the message
        this.simpleTestRequest.unsetData();

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * Remove the text content from the simple.request@test element
     */
    public void removeText() {

        // Remove the text content from the message
        this.simpleTestRequest.unsetText();

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * Set the data content of the simple.request@test message
     *
     * @param dataFile File containing the content of the data element of the simple.request@test message
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setData(File dataFile) throws FileNotFoundException, IOException {

        // Check if the data file has been specified
        if (dataFile == null) {
            this.simpleTestRequest.unsetData();
        } else {
            this.simpleTestRequest.setData(Utils.getFileContent(dataFile));
        }

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * Set the test ID of the simple.request@test message
     *
     * @param testId The simple.request@test message test ID
     */
    public void setTestId(String testId) {

        // Set the test ID
        this.simpleTestRequest.setTestId(testId);

        // Build the document
        this.createDocument();
    }

    /**
     * Set the text content of the simple.request@test message
     *
     * @param text The simple.request@test text content
     */
    public void setText(String text) {

        // Check if the text content has been specified
        if (text == null) {
            this.simpleTestRequest.unsetText();
        } else {
            this.simpleTestRequest.setText(text);
        }

        // Build the document
        this.createDocument();
    }

    /**
     * Build a missive document
     */
    private void createDocument() {

        // Add the simple.request@test message to the message body
        this.messageBody.setSimpleTestRequest(this.simpleTestRequest);

        // Build the rest of the missive document
        this.assembleDocument();
    }
}
