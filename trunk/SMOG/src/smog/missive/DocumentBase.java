package smog.missive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import smog.exception.SmogException;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.Message;
import smog.schema.sem.MessageBody;
import smog.schema.sem.MessageHeader;
import smog.schema.sem.Missive;
import smog.schema.sem.MissiveDocument;
import smog.schema.sem.MissiveHeader;
import smog.schema.sem.MissiveType;
import smog.schema.sem.PriorityCode;
import smog.schema.sem.ReceiverIdentifier;
import smog.schema.sem.SepamailMessage001;
import smog.schema.sem.SepamailMissive001;

/**
 * DocumentBase is the base class for all classes that represent missive documents
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class DocumentBase implements DocumentInterface {

    protected Message message;
    protected MessageBody messageBody;
    protected MessageHeader messageHeader;
    protected Missive missive;
    protected MissiveDocument missiveDocument;
    protected MissiveHeader missiveHeader;
    protected SepamailMessage001 sepamailMessage001;
    protected SepamailMissive001 sepamailMissive001;
    private HashMap<String, String> suggestedPrefixes;

    /**
     * DocumentBase constructor
     *
     * @param messageVersion The message version
     * @param missiveVersion The missive document version
     * @param missiveId The ID of the missive
     * @param missiveOrder The order of the missive
     * @param priorityCode The priority of the missive
     * @param missiveType The type of the missive
     * @param receiverIdentifier The missive recipient
     * @param sender The missive sender
     * @param sendDateTime The date and time at which the missive was sent
     * @param messageId The ID of the message
     * @param messageExpiry The date and time of the message expiry
     */
    public DocumentBase(String messageVersion, String missiveVersion, String missiveId, BigInteger missiveOrder,
            PriorityCode.Enum priorityCode, MissiveType.Enum missiveType, ReceiverIdentifier receiverIdentifier,
            BICorIBAN sender, Calendar sendDateTime, String messageId, Calendar messageExpiry) {

        // Initialise class attributes
        this.message = Message.Factory.newInstance();
        this.messageBody = MessageBody.Factory.newInstance();
        this.messageHeader = MessageHeader.Factory.newInstance();
        this.missive = Missive.Factory.newInstance();
        this.missiveDocument = MissiveDocument.Factory.newInstance();
        this.missiveHeader = MissiveHeader.Factory.newInstance();
        this.sepamailMessage001 = SepamailMessage001.Factory.newInstance();
        this.sepamailMissive001 = SepamailMissive001.Factory.newInstance();
        this.suggestedPrefixes = new HashMap<>();

        // Set the message version
        this.message.setVersion(messageVersion);

        // Set the missive version
        this.missive.setVersion(missiveVersion);

        // Set default values for the sem:sepamail_missive_001 element
        this.sepamailMissive001.setMsvId(missiveId);
        this.sepamailMissive001.setMsvOrd(missiveOrder);
        this.sepamailMissive001.setMsvPri(priorityCode);
        this.sepamailMissive001.setMsvTyp(missiveType);

        // Set the properties of the missive header
        this.missiveHeader.setRcv(receiverIdentifier);
        this.missiveHeader.setSnd(sender);
        this.missiveHeader.setSndDtTm(sendDateTime);

        // Set the message ID and message type in the message header
        this.messageHeader.setMsgId(messageId);
        if (messageExpiry != null) {
            this.messageHeader.setMsgExpiry(messageExpiry);
        }

        // Initialise the suggested prefixes map
        this.suggestedPrefixes.put("http://www.w3.org/2000/09/xmldsig#", "ds");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:acmt.023.001.01", "acmt023");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:acmt.024.001.01", "acmt024");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:pain.009.001.01", "pain009");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:pain.012.001.01", "pain012");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:pain.013.001.01", "pain013");
        this.suggestedPrefixes.put("urn:iso:std:iso:20022:tech:xsd:pain.014.001.01", "pain014");
        this.suggestedPrefixes.put("http://xsd.sepamail.eu/1206/", "sem");

        // Create the missive
        this.createMissive();
    }

    /**
     * Add a schema location attribute to the root node of the XML fragment
     *
     * @param namespaceUri Namespace URI of the schema location
     * @param localPart Local part of the schema location
     * @param prefix Prefix of the namespace URI for the schema location
     * @param value Value of the schema location
     */
    public void addSchemaLocation(String namespaceUri, String localPart, String prefix, String value) {

        // Cursor to traverse the XML fragment document
        XmlCursor cursor = this.missiveDocument.newCursor();

        // Find the start node of the fragment document
        while (cursor.hasNextToken()) {

            // Check if we are at the beginning of the document
            if (cursor.isStart()) {

                // Move to the first token
                cursor.toNextToken();

                // Insert schema location attribute
                cursor.insertAttributeWithValue(new QName(namespaceUri, localPart, prefix), value);

                // Exit the loop
                break;

            } else {

                // Move to next token
                cursor.toNextToken();
            }
        }

        // Dispose cursor
        cursor.dispose();
    }

    /**
     * Build the missive envelope
     */
    @Override
    public void build() {

        // Create the missive
        this.createMissive();
    }

    /**
     * Get the expiry of the message of the missive document
     *
     * @return The expiry of the message of the missive document
     */
    public Calendar getMessageExpiry() {

        // Check if the message expiry has been defined
        if (this.messageHeader.isSetMsgExpiry()) {
            return this.messageHeader.getMsgExpiry();
        }

        return null;
    }

    /**
     * Get the missive document message ID
     *
     * @return The missive document message ID
     */
    public String getMessageId() {

        // Get the message ID
        return this.messageHeader.getMsgId();
    }

    /**
     * Get the missive document message version
     *
     * @return The missive document message version
     */
    public String getMessageVersion() {

        // Get the missive document message version
        return this.message.getVersion();
    }

    /**
     * Get the ID of the missive document
     *
     * @return The ID of the missive document
     */
    public String getMissiveId() {

        // Get the missive ID
        return this.sepamailMissive001.getMsvId();
    }

    /**
     * Get the missive document order
     *
     * @return The missive document order
     */
    public BigInteger getMissiveOrder() {

        // Get the missive order
        return this.sepamailMissive001.getMsvOrd();
    }

    /**
     * Get the missive document priority
     *
     * @return The missive document priority
     */
    public PriorityCode.Enum getMissivePriority() {

        // Get the missive document priority
        return this.sepamailMissive001.getMsvPri();
    }

    /**
     * Get the missive document type
     *
     * @return The missive document type
     */
    public MissiveType.Enum getMissiveType() {

        // Get the missive document type
        return this.sepamailMissive001.getMsvTyp();
    }

    /**
     * Get the missive document version
     *
     * @return The missive document version
     */
    public String getMissiveVersion() {

        // Get the missive document version
        return this.missive.getVersion();
    }

    /**
     * Get the recipient of the missive document
     *
     * @return The recipient of the missive document
     */
    public ReceiverIdentifier getRecipient() {

        // Get the recipient of the missive document
        return this.missiveHeader.getRcv();
    }

    /**
     * Get the schema location of a missive document
     *
     * @param namespaceUri Namespace URI of the schema location
     * @param localPart Local part of the schema location
     * @return Schema location value of the missive document
     */
    public String getSchemaLocation(String namespaceUri, String localPart) {

        String schemaLocation = null;

        // Cursor to traverse the XML fragment document
        XmlCursor cursor = this.missiveDocument.newCursor();

        // Find the start node of the fragment document
        while (cursor.hasNextToken()) {

            // Check if we are at the beginning of the document
            if (cursor.isStart()) {

                // Get the value of the attribute
                schemaLocation = cursor.getAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance",
                        "schemaLocation"));

                // Exit the loop
                break;

            } else {

                // Move to next token
                cursor.toNextToken();
            }
        }

        // Dispose cursor
        cursor.dispose();

        // Return the schema location value
        return schemaLocation;
    }

    /**
     * Get the date and time at which the document was sent
     *
     * @return The date and time at which the document was sent
     */
    public Calendar getSendDateTime() {

        // Get the date and time at which the missive document was sent
        return this.missiveHeader.getSndDtTm();
    }

    /**
     * Get the sender of the missive document
     *
     * @return The sender of the missive document
     */
    public BICorIBAN getSender() {

        // Get the sender of the missive document
        return this.missiveHeader.getSnd();
    }

    /**
     * Get the XML object which represents the missive document
     *
     * @return XML object which represents the missive document
     */
    public XmlObject getMissiveXmlObject() {

        // Get the XML object which represents the missive document
        return this.missiveDocument;
    }

    /**
     * Remove the message expiry date and time from the message header
     */
    public void removeMessageExpiry() {

        // Remove the message expiry date and time
        this.messageHeader.unsetMsgExpiry();
    }

    /**
     * Save content to output stream
     *
     * @param os Output stream to which content will be written
     * @throws IOException
     */
    public void save(OutputStream os) throws IOException {

        // Write string content to output stream
        os.write(this.toString().getBytes());
        os.flush();
        os.close();
    }

    /**
     * Set the expiry date and time of the message of the missive document
     *
     * @param messageExpiry Expiry date and time of the message
     */
    public void setMessageExpiry(Calendar messageExpiry) {

        // Check if the message expiry date and time has been specified
        if (messageExpiry != null) {

            // Set the message expiry date and time
            this.messageHeader.setMsgExpiry(messageExpiry);

        } else {

            // Remove the message expiry specification from the header
            this.messageHeader.unsetMsgExpiry();
        }

        // Build the missive document
        this.build();
    }

    /**
     * Set the ID of the message of the missive document
     *
     * @param messageId ID of the message of the missive document
     */
    public void setMessageId(String messageId) {

        // Set the ID of the message
        this.messageHeader.setMsgId(messageId);

        // Build the missive document
        this.build();
    }

    /**
     * Set the version of the message of the missive document
     *
     * @param version The version of the message of the missive document
     */
    public void setMessageVersion(String version) {

        // Set the message version
        this.message.setVersion(version);

        // Build the missive document
        this.build();
    }

    /**
     * Set the ID of the missive document
     *
     * @param missiveId The ID of the missive document
     */
    public void setMissiveId(String missiveId) {

        // Set the ID of the missive document
        this.sepamailMissive001.setMsvId(missiveId);

        // Build the missive document
        this.build();
    }

    /**
     * Set the order of the missive document
     *
     * @param missiveOrder The order of the missive document
     */
    public void setMissiveOrder(BigInteger missiveOrder) {

        // Set the order of the missive
        this.sepamailMissive001.setMsvOrd(missiveOrder);

        // Build the missive document
        this.build();
    }

    /**
     * Set the priority of the missive document
     *
     * @param priority The priority of the missive document
     */
    public void setMissivePriority(PriorityCode.Enum priority) {

        // Set the priority of the missive document
        this.sepamailMissive001.setMsvPri(priority);

        // Build the missive document
        this.build();
    }

    /**
     * Set the type of the missive document
     *
     * @param type The type of the missive document
     */
    public void setMissiveType(MissiveType.Enum type) {

        // Set the type of the missive document
        this.sepamailMissive001.setMsvTyp(type);

        // Build the missive document
        this.build();
    }

    /**
     * Set the version of the missive document
     *
     * @param version The version of the missive document
     */
    public void setMissiveVersion(String version) {

        // Set the missive document version
        this.missive.setVersion(version);

        // Build the missive document
        this.build();
    }

    /**
     * Set the recipient of the missive document
     *
     * @param recipient The recipient of the missive document
     */
    public void setRecipient(ReceiverIdentifier recipient) {

        // Set the recipient of the missive document
        this.missiveHeader.setRcv(recipient);

        // Build the missive document
        this.build();
    }

    /**
     * Set the date and time at which the missive document was sent
     *
     * @param sendDateTime The date and time at which the missive document was sent
     */
    public void setSendDateTime(Calendar sendDateTime) {

        // Set the date and time at which the missive document was sent
        this.missiveHeader.setSndDtTm(sendDateTime);

        // Build the missive document
        this.build();
    }

    /**
     * Set the sender of the missive document
     *
     * @param sender The sender of the missive document
     */
    public void setSender(BICorIBAN sender) {

        // Set the sender of the missive document
        this.missiveHeader.setSnd(sender);

        // Build the missive
        this.build();
    }

    /**
     * String representation of the XML document
     *
     * @return String representation of the XML document
     */
    @Override
    public String toString() {

        // Add schema location attribute
        this.addSchemaLocation("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation", "xsi",
                "http://xsd.sepamail.eu/1206/ xsd/sepamail_missive.xsd ");

        // XML options instance
        XmlOptions options = new XmlOptions();

        // Set the properties of the XML options
        options.setSavePrettyPrint();
        options.setSaveSuggestedPrefixes(this.suggestedPrefixes);
        options.setUseCDataBookmarks();

        try {

            // Build XML document using the constructed XML object
            SAXBuilder sxb = new SAXBuilder();
            Document xmlDocument =
                    sxb.build(new InputStreamReader(new ByteArrayInputStream(missiveDocument.xmlText(options).getBytes()),
                        "UTF-8"));

            // Pretty print the XML document
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            xmlOutputter.output(xmlDocument, output);

            // String with XML content
            return new String(output.toByteArray());

        } catch ( JDOMException | IOException ex) {

            // TODO: error logging
            System.out.println(ex.getMessage());
        }

        return null;
    }

    /**
     * Check if the missive document is valid
     *
     * @return Whether the missive document is valid
     * @throws SmogException
     */
    @Override
    public boolean validate() throws SmogException {

        // List of validation messages
        ArrayList<XmlError> validationMessages = new ArrayList<>();

        // Validate the missive document
        boolean isValid = this.missiveDocument.validate(new XmlOptions().setErrorListener(validationMessages));

        // Check if the missive document is valid
        if (isValid) {
            return true;
        } else {

            // Validation message
            String validationMessage = "";

            // Iterator for validation messages
            Iterator<XmlError> iterator = validationMessages.iterator();

            // Build the complete error message
            while(iterator.hasNext()) {

                // Add validation message separtor
                if (!validationMessage.equals("")) {
                    validationMessage += "\n";
                }

                // Concatenate validation messages
                validationMessage += iterator.next().getMessage();
            }

            throw new SmogException(validationMessage);
        }
    }

    /**
     * Assemble the missive document using its components
     */
    protected void assembleDocument() {

        // Create the missive
        this.createMissive();
    }

    /**
     * Create the missive
     */
    private void createMissive() {

        // Add the message header and body to its container
        this.sepamailMessage001.setMsgBdy(this.messageBody);
        this.sepamailMessage001.setMsgHdr(this.messageHeader);

        // Add the message container to the missive body
        this.message.setSepamailMessage001(this.sepamailMessage001);

        // Add the missive body and header to their respective container
        this.sepamailMissive001.setMsvBdy(this.message);
        this.sepamailMissive001.setMsvHdr(this.missiveHeader);

        // Add the missive container to its root element
        this.missive.setSepamailMissive001(this.sepamailMissive001);

        // Add the root element to the document element
        this.missiveDocument.setMissive(this.missive);
    }
}
