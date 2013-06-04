package smog.missive;

import java.util.Calendar;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import smog.exception.SmogException;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.MissiveDocument;
import smog.schema.sem.ReceiverIdentifier;

/**
 * SepamailGenericDocumentTest tests the generic missive document class {@link SepamailGenericDocument}
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SepamailGenericDocumentTest {

    private String message;
    private Calendar messageExpiry;
    private String messageType;
    private ReceiverIdentifier recipient;
    private Calendar sendDateTime;
    private BICorIBAN sender;
    private SepamailGenericDocument sepamailGenericDocument;
    private XmlObject xmlMessage;

    /**
     * SepamailGenericDocumentTest default constructor
     */
    public SepamailGenericDocumentTest() {

        // Message type
        this.messageType = "simple.request@test";

        // Message content
        this.message = "<sem:SimpleTestRequest xmlns:sem=\"http://xsd.sepamail.eu/1206/\"><sem:TestId>9B4895CC137</sem:TestId><sem:Text>Test text</sem:Text></sem:SimpleTestRequest>";

        // Message expiry
        this.messageExpiry = Calendar.getInstance();

        // Initialise the missive recipient
        this.recipient = ReceiverIdentifier.Factory.newInstance();
        this.recipient.setIBAN("QX51TESTBLEUXIG52I8X2445SGSUWESM");

        // Missive header values
        this.sendDateTime = Calendar.getInstance();

        // Initialise the missive sender
        this.sender = BICorIBAN.Factory.newInstance();
        this.sender.setIBAN("QX58TESTORANGEX8KH8P5TCYAGM22M38NQ");

        // Get message content from message string
        try {
            this.xmlMessage = XmlObject.Factory.parse(this.message);
        } catch (XmlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Set up a clean instance of SepamailGenericDocument before each test
     */
    @Before
    public void setUp() {

        // Make a copy of the XML message
        XmlObject tempXmlMessage = this.xmlMessage.copy();

        // SepamailGenericDocument instance
        this.sepamailGenericDocument = new SepamailGenericDocument(this.recipient, this.sender, this.sendDateTime,
                this.messageType, this.messageExpiry, this.xmlMessage);

        // Set the XML message
        this.xmlMessage = tempXmlMessage;
    }

    /**
     * Test of build method, of class SepamailGenericDocument.
     */
    @Test
    public void testBuild() {

        // Build the generic missive document
        this.sepamailGenericDocument.build();

        // When the build method is invoked, the message is added to the generic document - so we test if we get the
        // message that we passed to the constructor
        assertEquals(this.xmlMessage.toString(),
                ((MissiveDocument) this.sepamailGenericDocument.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().toString());
    }

    /**
     * Test of getGenericSepamailMessage method, of class SepamailGenericDocument.
     */
    @Test
    public void testGetGenericSepamailMessage() {
        assertEquals(this.xmlMessage.toString(), this.sepamailGenericDocument.getGenericSepamailMessage().toString());
    }

    /**
     * Test of getMessageType method, of class SepamailGenericDocument.
     */
    @Test
    public void testGetMessageType() {
        assertEquals(this.messageType, this.sepamailGenericDocument.getMessageType());
    }

    /**
     * Test of setGenericSepamailMessage method, of class SepamailGenericDocument.
     */
    @Test
    public void testSetGenericSepamailMessage() {

        // Generate a message from XML string and set it to the generic missive document and check if the generic
        // missive contains the set message
        try {
            XmlObject newXmlMessage = XmlObject.Factory.parse("<sem:SimpleTestRequest xmlns:sem=\"http://xsd.sepamail.eu/1206/\"><sem:TestId>4C5899CB731</sem:TestId></sem:SimpleTestRequest>");
            XmlObject tempNewXmlMessage = newXmlMessage.copy();
            this.sepamailGenericDocument.setGenericSepamailMessage(newXmlMessage);
            assertEquals(tempNewXmlMessage.toString(),
                    ((MissiveDocument) this.sepamailGenericDocument.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().toString());
        } catch (XmlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Test of setMessageType method, of class SepamailGenericDocument.
     */
    @Test
    public void testSetMessageType() {
        String newMessageType = "a.new.message@type";
        this.sepamailGenericDocument.setMessageType(newMessageType);
        assertEquals(newMessageType, this.sepamailGenericDocument.getMessageType());
    }

    /**
     * Test of validate method, of class SepamailGenericDocument.
     */
    @Test
    public void testValidateSucceeds() throws Exception {
        Boolean result;
        try {
            result = this.sepamailGenericDocument.validate();
        } catch (SmogException ex) {
            result = false;
        }
        assertTrue(result);
    }

    /**
     * Test of validate method, of class SepamailGenericDocument.
     */
    @Test
    public void testValidateSucceedsAgain() throws Exception {
        Boolean result;
        try {
            this.sepamailGenericDocument.setMessageType("a.new.message@type"); // An unknown message type
            result = this.sepamailGenericDocument.validate();
        } catch (SmogException ex) {
            result = false;
        }
        assertTrue(result);
    }

    /**
     * Test of validate method, of class SepamailGenericDocument.
     */
    @Test
    public void testValidateFails() throws Exception {
        Boolean result;
        try {
            this.sepamailGenericDocument.setMessageType(""); // A blank message type
            result = this.sepamailGenericDocument.validate();
        } catch (SmogException ex) {
            result = false;
        }
        assertFalse(result);
    }

    /**
     * Test of validate method, of class SepamailGenericDocument.
     */
    @Test
    public void testValidateFailsAgain() throws Exception {
        Boolean result;
        try {
            this.sepamailGenericDocument.setMessageType(null); // A null message type
            result = this.sepamailGenericDocument.validate();
        } catch (SmogException ex) {
            result = false;
        }
        assertFalse(result);
    }
}
