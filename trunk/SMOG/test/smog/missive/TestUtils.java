package smog.missive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * TestUtils groups static utility methods used throughout the test classes of SMOG.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class TestUtils {

    /**
     * Create a file using its Base64 encoded string
     *
     * @param filename Name of file to which data will be written
     * @param encodedData Base64 encoded content of file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void createDataFileFromEncodedString(String filename, String encodedData)
            throws FileNotFoundException, IOException {

        // Decode the data
        byte[] decodedData = Base64.decodeBase64(encodedData);

        // File output stream to write data to file
        FileOutputStream fos = new FileOutputStream(new File(filename));

        // Write data to file
        IOUtils.write(decodedData, fos);
    }
}
