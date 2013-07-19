package smog.missive;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.MissiveDocument;
import smog.schema.sem.ReceiverIdentifier;

/**
 * SepamailTestRequestDocumentTest tests the missive document class {@link SepamailTestRequestDocument} for
 * simple.request@test messages.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SepamailTestRequestDocumentTest {

    private String messageDataFilename;
    private String messageEncodedData;
    private Calendar messageExpiry;
    private String messageTestId;
    private String messageText;
    private ReceiverIdentifier recipient;
    private Calendar sendDateTime;
    private BICorIBAN sender;
    private SepamailTestRequestDocument sepamailTestRequestDocument;

    /**
     * SepamailTestRequestDocumentTest default constructor
     */
    public SepamailTestRequestDocumentTest() {

        // Message test ID
        this.messageTestId = "9B4895CC137";

        // Message data filename
        this.messageDataFilename = "test_data_file_1.png";

        // Message expiry
        this.messageExpiry = Calendar.getInstance();

        // Message text
        this.messageText = "simple.request@test test text.";

        // Message encoded data
        this.messageEncodedData = "iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL" +
                "2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90FHQYiBduya34AAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJTV" +
                "BXgQ4XAAAADklEQVQY02NgGAWDEwAAAZoAAQuinR8AAAAASUVORK5CYII=";

        // Initialise the missive recipient
        this.recipient = ReceiverIdentifier.Factory.newInstance();
        this.recipient.setIBAN("QX51TESTBLEUXIG52I8X2445SGSUWESM");

        // Missive header values
        this.sendDateTime = Calendar.getInstance();

        // Initialise the missive sender
        this.sender = BICorIBAN.Factory.newInstance();
        this.sender.setIBAN("QX58TESTORANGEX8KH8P5TCYAGM22M38NQ");
    }

    /**
     * Set up a clean instance of the simple.request@test missive object
     */
    @Before
    public void setUp() {

        try {

            // Create data file
            TestUtils.createDataFileFromEncodedString(this.messageDataFilename, this.messageEncodedData);

            // Data file instance
            File dataFile = new File(this.messageDataFilename);

            // Create a simple.request@test missive object instance
            this.sepamailTestRequestDocument = new SepamailTestRequestDocument(this.recipient, this.sender,
                    this.sendDateTime, this.messageTestId, this.messageExpiry, this.messageText, dataFile);

        } catch (IOException ex) {

            // Display exception message
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Clean up environment after a test
     */
    @After
    public void tearDown() {

        // SEPAmail simple.request@test message data file
        File dataFile = new File(this.messageDataFilename);

        // Delete the data file if it exists
        if (dataFile.exists()) {
            dataFile.delete();
        }
    }

    /**
     * Test of build method, of class SepamailTestRequestDocument.
     *
     * @throws Exception
     */
    @Test
    public void testBuild() throws Exception {

        // Build the complete missive XML object
        this.sepamailTestRequestDocument.build();

        // After the build method, the missive is assembled using the fragments that make up the message. We test if the
        // missive XML object contains the required message parts - first we get those parts
        String currentMessageTestId = ((MissiveDocument)this.sepamailTestRequestDocument.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().getSimpleTestRequest().getTestId();
        String currentMessageText = ((MissiveDocument)this.sepamailTestRequestDocument.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().getSimpleTestRequest().getText();
        byte[] currentData = ((MissiveDocument)this.sepamailTestRequestDocument.getMissiveXmlObject()).getMissive().getSepamailMissive001().getMsvBdy().getSepamailMessage001().getMsgBdy().getSimpleTestRequest().getData();

        // Get actual file data
        byte[] actualData = IOUtils.toByteArray(new FileInputStream(new File(this.messageDataFilename)));

        // Test if all parts are equal in the complete missive XML object
        assertTrue(this.messageTestId.equals(currentMessageTestId) && this.messageText.equals(currentMessageText) &&
                Arrays.equals(currentData, actualData));
    }

    /**
     * Test of getData method, of class SepamailTestRequestDocument.
     *
     * @throws Exception
     */
    @Test
    public void testGetData() throws Exception {
        byte[] expResult = IOUtils.toByteArray(new FileInputStream(new File(this.messageDataFilename)));
        byte[] result = this.sepamailTestRequestDocument.getData();
        assertTrue(Arrays.equals(expResult, result));
    }

    /**
     * Test of getTestId method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testGetTestId() {
        assertEquals(this.messageTestId, this.sepamailTestRequestDocument.getTestId());
    }


    /**
     * Test of getText method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testGetText() {
        assertEquals(this.messageText, this.sepamailTestRequestDocument.getText());
    }

    /**
     * Test of removeData method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testRemoveData() {
        this.sepamailTestRequestDocument.removeData();
        assertNull(this.sepamailTestRequestDocument.getData());
    }

    /**
     * Test of removeText method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testRemoveText() {
        this.sepamailTestRequestDocument.removeText();
        assertNull(this.sepamailTestRequestDocument.getText());
    }

    /**
     * Test of setData method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testSetData() throws Exception {

        // New Base 64 encoded message data string
        String newDataFileContent = "iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/" +
                "oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB90FHwcrGiyzf34AAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJ" +
                "TVBXgQ4XAAAADElEQVQI12NgoBMAAABpAAHAHfL4AAAAAElFTkSuQmCC";

        // New data filename
        String newDataFilename = "test_data_file_2.png";

        // Create new data file
        TestUtils.createDataFileFromEncodedString(newDataFilename, newDataFileContent);

        // New data file
        File newDataFile = new File(newDataFilename);

        // Set the data of the message
        this.sepamailTestRequestDocument.setData(newDataFile);

        // Delete the new data file
        if (newDataFile.exists()) {
            newDataFile.delete();
        }

        // Test if the data set in the message equals the new data file content
        assertTrue(Arrays.equals(Base64.decodeBase64(newDataFileContent), this.sepamailTestRequestDocument.getData()));
    }

    /**
     * Test of setTestId method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testSetTestId() {
        String newTestId = "4C5899CB731";
        this.sepamailTestRequestDocument.setTestId(newTestId);
        assertEquals(newTestId, this.sepamailTestRequestDocument.getTestId());
    }

    /**
     * Test of setText method, of class SepamailTestRequestDocument.
     */
    @Test
    public void testSetText() {
        String newMessagetext = "This is another test message.";
        this.sepamailTestRequestDocument.setText(newMessagetext);
        assertEquals(newMessagetext, this.sepamailTestRequestDocument.getText());
    }
}
