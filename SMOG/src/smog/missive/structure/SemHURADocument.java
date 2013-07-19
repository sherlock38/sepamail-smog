package smog.missive.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.XmlCursor;
import smog.schema.sem.Attachment;
import smog.schema.sem.Document;
import smog.schema.sem.DocumentType;
import smog.utils.Utils;

/**
 * SemHURADocument class represents a HURA document that can be attached to a SEPAmail missive.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SemHURADocument {

    private Calendar date;
    private Document document;
    private String language;
    private String reference;
    private String title;
    private DocumentType.Enum type;

    /**
     * SemHURADocument constructor
     *
     * @param file File which contains missive attached document content
     * @param date Date and time at which file was created
     * @param language Language of the document
     * @param reference Reference of the document
     * @param title Title of the document
     * @param type Missive attached document type
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SemHURADocument(File file, Calendar date, String language, String reference, String title,
            DocumentType.Enum type) throws FileNotFoundException, IOException {

        // Initialise class attributes
        this.date = date;
        this.document = Document.Factory.newInstance();
        this.language = language;
        this.reference = reference;
        this.title = title;
        this.type = type;

        // Check if file has been given
        if (file != null) {

            // Document attachment instance
            Attachment attachment = Attachment.Factory.newInstance();

            // Attachment properties
            attachment.setData(Utils.getFileContentAsXml(file));
            attachment.setMimeType(Files.probeContentType(file.toPath()));
            attachment.setName(file.getName());

            // Attachment cursor
            XmlCursor attachmentCursor = attachment.newCursor();

            // Move to attachment data element
            attachmentCursor.toFirstChild();
            attachmentCursor.toNextSibling();
            attachmentCursor.toNextSibling();
            attachmentCursor.toNextToken();

            // Set CDATA bookmark
            attachmentCursor.setBookmark(CDataBookmark.CDATA_BOOKMARK);

            // Dispose cursor
            attachmentCursor.dispose();

            // Append attachment to missive attachment document
            this.document.setContents(attachment);
        }

        // Set date and time of document if provided
        if (this.date != null) {
            this.document.setDate(this.date);
        }

        // Set the other properties of the HURA document
        this.document.setLang(this.language);
        this.document.setReference(this.reference);
        this.document.setTitle(this.title);
        this.document.setType(this.type);
    }

    /**
     * SemHURADocument constructor
     *
     * @param content Byte array of document attachment data
     * @param date Date and time at which file was created
     * @param filename Name of file from which document attachment data was obtained
     * @param language Language of the document
     * @param mimeType MIME type of the document attachment data
     * @param reference Reference of the document
     * @param title Title of the document
     * @param type Missive attached document type
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SemHURADocument(byte[] content, Calendar date, String filename, String language, String mimeType,
            String reference, String title, DocumentType.Enum type) throws FileNotFoundException, IOException {

        // Initialise class attributes
        this.date = date;
        this.document = Document.Factory.newInstance();
        this.language = language;
        this.reference = reference;
        this.title = title;
        this.type = type;

        // Check if content has been given
        if (content != null) {

            // Document attachment instance
            Attachment attachment = Attachment.Factory.newInstance();

            // Attachment properties
            attachment.setData(Utils.getByteContentAsXml(content));
            attachment.setMimeType(mimeType);
            attachment.setName(filename);

            // Attachment cursor
            XmlCursor attachmentCursor = attachment.newCursor();

            // Move to attachment data element
            attachmentCursor.toFirstChild();
            attachmentCursor.toNextSibling();
            attachmentCursor.toNextSibling();
            attachmentCursor.toNextToken();

            // Set CDATA bookmark
            attachmentCursor.setBookmark(CDataBookmark.CDATA_BOOKMARK);

            // Dispose cursor
            attachmentCursor.dispose();

            // Append attachment to missive attachment document
            this.document.setContents(attachment);
        }

        // Set date and time of document if provided
        if (this.date != null) {
            this.document.setDate(this.date);
        }

        // Set the other properties of the HURA document
        this.document.setLang(this.language);
        this.document.setReference(this.reference);
        this.document.setTitle(this.title);
        this.document.setType(this.type);
    }

    /**
     * Get the HURA document
     *
     * @return HURA document
     */
    public Document getDocument() {

        // Get the HURA document
        return this.document;
    }
}
