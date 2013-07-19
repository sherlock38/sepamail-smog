package smog.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

/**
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Utils {

    /**
     * Generate a unique Missive ID which has a length of 16 characters
     *
     * @return Missive ID which has a length of 16 characters
     */
    public static String generateMissiveId() {

        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Break the UUID into components
        String [] uuidParts = uuid.toString().split("-");

        // Build the missive ID
        String missiveId = uuidParts[0] + uuidParts[1] + uuidParts[2];

        return missiveId.toUpperCase();
    }

    /**
     * Generate a unique Message ID which has a length of 32 characters
     *
     * @return Message ID which has a length of 32 characters
     */
    public static String generateMessageId() {

        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Break the UUID into components
        String [] uuidParts = uuid.toString().split("-");

        // Build the message ID
        String messageId = uuidParts[0] + uuidParts[1] + uuidParts[2] + uuidParts[3] + uuidParts[4];

        return messageId.toUpperCase();
    }

    /**
     * Get the content of a file
     *
     * @param file File for while content needs to be obtained
     * @return File content as byte array
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] getFileContent(File file) throws FileNotFoundException, IOException {

        // File input stream
        FileInputStream fis = new FileInputStream(file);

        // File content
        return IOUtils.toByteArray(fis);
    }

    /**
     * Get content of file encoded in Base64 wrapped in an XML object
     *
     * @param file File that needs to be encoded and wrapped in XML object
     * @return XML object containing encoded file content
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static XmlObject getFileContentAsXml(File file) throws FileNotFoundException, IOException {

        // XML fragment that will contain the file content
        XmlObject xo = XmlObject.Factory.newInstance();

        // Read and encode file content
        String encodedContent = Base64.encodeBase64String(getFileContent(file));

        // Create cursor
        XmlCursor c = xo.newCursor();

        // Move to start of document
        c.toStartDoc();

        // Set content of object
        c.setTextValue(encodedContent);

        // Dispose cursor
        c.dispose();

        return xo;
    }

    /**
     * Get content of byte array encoded in Base64 wrapped in an XML object
     *
     * @param content Byte array content to be encoded and wrapped in XML object
     * @return XML object containing encoded file content
     */
    public static XmlObject getByteContentAsXml(byte[] content) {

        // XML fragment that will contain the file content
        XmlObject xo = XmlObject.Factory.newInstance();

        // Read and encode file content
        String encodedContent = Base64.encodeBase64String(content);

        // Create cursor
        XmlCursor c = xo.newCursor();

        // Move to start of document
        c.toStartDoc();

        // Set content of object
        c.setTextValue(encodedContent);

        // Dispose cursor
        c.dispose();

        return xo;
    }
}
