package smog.missive;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Calendar;
import org.apache.xmlbeans.XmlObject;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import smog.exception.SmogException;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.MessageType;
import smog.schema.sem.MissiveDocument;
import smog.schema.sem.MissiveType;
import smog.schema.sem.PriorityCode;
import smog.schema.sem.ReceiverIdentifier;
import smog.schema.sem.SimpleTestRequest;
import smog.utils.Utils;

/**
 * DocumentBaseTest tests the base class {@link DocumentBase} of all missive documents
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
@RunWith(JUnit4.class)
public class DocumentBaseTest {

    private DocumentBase documentBase;
    private Calendar messageExpiry;
    private String messageId;
    private String messageVersion;
    private String missiveId;
    private BigInteger missiveOrder;
    private PriorityCode.Enum missivePriority;
    private MissiveType.Enum missiveType;
    private String missiveVersion;
    private String outputFilename;
    private ReceiverIdentifier recipient;
    private String recipientIBAN;
    private Calendar sendDateTime;
    private BICorIBAN sender;
    private String senderIBAN;

    /**
     * DocumentBaseTest default constructor
     */
    public DocumentBaseTest() {

        // Message expiry
        this.messageExpiry = Calendar.getInstance();

        // Message ID
        this.messageId = Utils.generateMessageId();

        // Missive and message version
        this.messageVersion = "1206";
        this.missiveVersion = "1206";

        // Missive header values
        this.missiveId = Utils.generateMissiveId();
        this.missiveOrder = BigInteger.ONE;
        this.missivePriority = PriorityCode.NORMAL;
        this.missiveType = MissiveType.NOMINAL;
        this.sendDateTime = Calendar.getInstance();

        // Setup IBANs
        this.recipientIBAN = "QX51TESTBLEUXIG52I8X2445SGSUWESM";
        this.senderIBAN = "QX15TESTVERTXIG5244X2I85SGSUWESM";

        // Initialise the missive recipient
        this.recipient = ReceiverIdentifier.Factory.newInstance();
        this.recipient.setIBAN(this.recipientIBAN);

        // Initialise the missive sender
        this.sender = BICorIBAN.Factory.newInstance();
        this.sender.setIBAN(this.senderIBAN);

        // Output filename
        this.outputFilename = "test_missive_envelope.xml";
    }

    /**
     * Set up a clean instance of DocumentBase before each test
     */
    @Before
    public void setUp() {

        // DocumentBase instance
        this.documentBase = new DocumentBase(this.messageVersion, this.missiveVersion, this.missiveId,
                this.missiveOrder, this.missivePriority, this.missiveType, this.recipient, this.sender,
                this.sendDateTime, this.messageId, this.messageExpiry);
    }

    /**
     * Clean up environment after test
     */
    @After
    public void tearDown() {

        // Test file
        File testFile = new File(this.outputFilename);

        // Remove test file if it exists
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Test of addSchemaLocation method, of class DocumentBase.
     */
    @Test
    public void testAddSchemaLocation() {

        String namespaceUri = "http://www.w3.org/2001/XMLSchema-instance";
        String localPart = "schemaLocation";
        String prefix = "xsi";
        String value = "http://www.sepamail.eu/xsd/bleedingEdge ../../xsd/sepamail_missive.xsd";
        this.documentBase.addSchemaLocation(namespaceUri, localPart, prefix, value);
        assertEquals(value, this.documentBase.getSchemaLocation(namespaceUri, localPart));
    }

    /**
     * Test of getMessageExpiry method, of class DocumentBase.
     */
    @Test
    public void testGetMessageExpiry() {
        assertEquals(this.messageExpiry.getTime(), this.documentBase.getMessageExpiry().getTime());
    }

    /**
     * Test of getMessageId method, of class DocumentBase.
     */
    @Test
    public void testGetMessageId() {
        assertEquals(this.messageId, this.documentBase.getMessageId());
    }

    /**
     * Test of getMessageVersion method, of class DocumentBase.
     */
    @Test
    public void testGetMessageVersion() {
        assertEquals(this.messageVersion, this.documentBase.getMessageVersion());
    }

    /**
     * Test of getMissiveId method, of class DocumentBase.
     */
    @Test
    public void testGetMissiveId() {
        assertEquals(this.missiveId, this.documentBase.getMissiveId());
    }

    /**
     * Test of getMissiveOrder method, of class DocumentBase.
     */
    @Test
    public void testGetMissiveOrder() {
        assertEquals(this.missiveOrder, this.documentBase.getMissiveOrder());
    }

    /**
     * Test of getMissivePriority method, of class DocumentBase.
     */
    @Test
    public void testGetMissivePriority() {
        assertEquals(this.missivePriority, this.documentBase.getMissivePriority());
    }

    /**
     * Test of getMissiveType method, of class DocumentBase.
     */
    @Test
    public void testGetMissiveType() {
        assertEquals(this.missiveType, this.documentBase.getMissiveType());
    }

    /**
     * Test of getMissiveVersion method, of class DocumentBase.
     */
    @Test
    public void testGetMissiveVersion() {
        assertEquals(this.missiveVersion, this.documentBase.getMissiveVersion());
    }

    /**
     * Test of getMissiveXmlObject method, of class DocumentBase.
     */
    @Test
    public void testGetMissiveXmlObject() {

        // Get the missive XML object which represents the missive envelope
        XmlObject missiveXmlObject = this.documentBase.getMissiveXmlObject();
        assertTrue((missiveXmlObject != null) &&
                (missiveXmlObject.getClass().getGenericSuperclass().getClass().isInstance(XmlObject.class)));
    }

    /**
     * Test of getRecipient method, of class DocumentBase.
     */
    @Test
    public void testGetRecipient() {
        assertTrue(this.recipient.toString().equals(this.documentBase.getRecipient().toString()));
    }

    /**
     * Test of getSchemaLocation method, of class DocumentBase.
     */
    @Test
    public void testGetSchemaLocation() {

        String namespaceUri = "http://www.w3.org/2001/XMLSchema-instance";
        String localPart = "schemaLocation";
        String prefix = "xsi";
        String value = "http://www.sepamail.eu/xsd/bleedingEdge ../../xsd/sepamail_missive.xsd";
        this.documentBase.addSchemaLocation(namespaceUri, localPart, prefix, value);
        assertEquals(value, this.documentBase.getSchemaLocation(namespaceUri, localPart));
    }

    /**
     * Test of getSendDateTime method, of class DocumentBase.
     */
    @Test
    public void testGetSendDateTime() {
        assertEquals(this.sendDateTime.getTime(), this.documentBase.getSendDateTime().getTime());
    }

    /**
     * Test of getSender method, of class DocumentBase.
     */
    @Test
    public void testGetSender() {
        assertTrue(this.sender.toString().equals(this.documentBase.getSender().toString()));
    }

    /**
     * Test of removeMessageExpiry method, of class DocumentBase.
     */
    @Test
    public void testRemoveMessageExpiry() {

        // Remove the message expiry element from the missive envelope
        this.documentBase.removeMessageExpiry();
        assertNull(this.documentBase.getMessageExpiry());
    }

    /**
     * Test of save method, of class DocumentBase.
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {

        // File output stream to which content will be written
        FileOutputStream fos = new FileOutputStream(this.outputFilename);

        // Save the file
        this.documentBase.save(fos);

        // Test file
        File testFile = new File(this.outputFilename);

        // Check if the file exist and that it has content
        assertTrue(testFile.exists() && (testFile.length() > 0));
    }

    /**
     * Test of setMessageExpiry method, of class DocumentBase.
     */
    @Test
    public void testSetMessageExpiry() {

        Calendar newMessageExpiry = Calendar.getInstance();
        this.documentBase.setMessageExpiry(newMessageExpiry);
        assertEquals(newMessageExpiry.getTime(), this.documentBase.getMessageExpiry().getTime());
    }

    /**
     * Test of setMessageId method, of class DocumentBase.
     */
    @Test
    public void testSetMessageId() {

        String newMessageId = Utils.generateMessageId();
        this.documentBase.setMessageId(newMessageId);
        assertEquals(newMessageId, this.documentBase.getMessageId());
    }

    /**
     * Test of setMessageVersion method, of class DocumentBase.
     */
    @Test
    public void testSetMessageVersion() {

        String version = "1305";
        this.documentBase.setMessageVersion(version);
        assertEquals(version, this.documentBase.getMessageVersion());
    }

    /**
     * Test of setMissiveId method, of class DocumentBase.
     */
    @Test
    public void testSetMissiveId() {

        String newMissiveId = Utils.generateMissiveId();
        this.documentBase.setMissiveId(newMissiveId);
        assertEquals(newMissiveId, this.documentBase.getMissiveId());
    }

    /**
     * Test of setMissiveOrder method, of class DocumentBase.
     */
    @Test
    public void testSetMissiveOrder() {

        BigInteger newMissiveOrder = new BigInteger("5");
        this.documentBase.setMissiveOrder(newMissiveOrder);
        assertEquals(newMissiveOrder, this.documentBase.getMissiveOrder());
    }

    /**
     * Test of setMissivePriority method, of class DocumentBase.
     */
    @Test
    public void testSetMissivePriority() {

        PriorityCode.Enum newPriorityCode = PriorityCode.HIGH;
        this.documentBase.setMissivePriority(newPriorityCode);
        assertEquals(newPriorityCode, this.documentBase.getMissivePriority());
    }

    /**
     * Test of setMissiveType method, of class DocumentBase.
     */
    @Test
    public void testSetMissiveType() {

        MissiveType.Enum newMissiveType = MissiveType.SERVICE;
        this.documentBase.setMissiveType(newMissiveType);
        assertEquals(newMissiveType, this.documentBase.getMissiveType());
    }

    /**
     * Test of setMissiveVersion method, of class DocumentBase.
     */
    @Test
    public void testSetMissiveVersion() {

        String version = "1305";
        this.documentBase.setMissiveVersion(version);
        assertEquals(version, this.documentBase.getMissiveVersion());
    }

    /**
     * Test of setRecipient method, of class DocumentBase.
     */
    @Test
    public void testSetRecipient() {

        ReceiverIdentifier newRecipient = ReceiverIdentifier.Factory.newInstance();
        newRecipient.setIBAN("QX51TESTORANGEXIG52I8X2445SGSUWESM");
        this.documentBase.setRecipient(newRecipient);
        assertTrue(newRecipient.toString().equals(this.documentBase.getRecipient().toString()));
    }

    /**
     * Test of setSendDateTime method, of class DocumentBase.
     */
    @Test
    public void testSetSendDateTime() {

        Calendar newSendDateTime = Calendar.getInstance();
        newSendDateTime.set(2011, 6, 21);
        this.documentBase.setSendDateTime(newSendDateTime);
        assertEquals(newSendDateTime.getTime(), this.documentBase.getSendDateTime().getTime());
    }

    /**
     * Test of setSender method, of class DocumentBase.
     */
    @Test
    public void testSetSender() {

        BICorIBAN newSender = BICorIBAN.Factory.newInstance();
        newSender.setIBAN("QX58TESTORANGEX8KH8P5TCYAGM22M38NQ");
        this.documentBase.setSender(newSender);
        assertTrue(newSender.toString().equals(this.documentBase.getSender().toString()));
    }

    /**
     * Test of toString method, of class DocumentBase.
     */
    @Test
    public void testToString() throws Exception {

        String expResult = this.documentBase.toString();

        // Create a missive document based on the string value of the DocumentBase instance
        MissiveDocument missiveDocument = MissiveDocument.Factory.parse(expResult);

        // Check if we could retrieve the missive ID from the parsed string
        assertTrue(this.missiveId.equals(missiveDocument.getMissive().getSepamailMissive001().getMsvId()));
    }

    /**
     * Test of validate method, of class DocumentBase.
     */
    @Test
    public void testValidateFails() throws Exception {

        boolean expResult;
        try {
            expResult = this.documentBase.validate();
        } catch (SmogException ex) {
            expResult = false;
        }
        assertFalse(expResult); // Missive does not contain a message type
    }

    /**
     * Test of validate method, of class DocumentBase.
     */
    @Test
    public void testValidateFailsAgain() throws Exception {

        boolean expResult;

        try {

            // Set a message type for the missive envelope
            ((MissiveDocument) this.documentBase.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgHdr().setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

            expResult = this.documentBase.validate();

        } catch (SmogException ex) {
            expResult = false;
        }

        assertFalse(expResult); // Missive contains a message type but does not contain a message
    }

    /**
     * Test of validate method, of class DocumentBase.
     */
    @Test
    public void testValidateSucceeds() throws Exception {

        boolean expResult;

        try {

            // Set a message type for the missive envelope
            ((MissiveDocument) this.documentBase.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgHdr().setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

            // Create a simple.request@test message
            SimpleTestRequest simpleTestRequest = SimpleTestRequest.Factory.newInstance();

            // Add a test ID to the simple.request@test message
            simpleTestRequest.setTestId("4C5899CB731");

            // Add a message to the missive envelope
            ((MissiveDocument) this.documentBase.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().setSimpleTestRequest(simpleTestRequest);

            expResult = this.documentBase.validate();

        } catch (SmogException ex) {
            expResult = false;
        }

        assertTrue(expResult); // Missive contains a message type and a valid message
    }
}
