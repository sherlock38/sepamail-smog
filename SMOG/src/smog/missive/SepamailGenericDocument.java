package smog.missive;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import smog.exception.SmogException;
import smog.schema.sem.BICorIBAN;
import smog.schema.sem.MessageBody;
import smog.schema.sem.MessageType;
import smog.schema.sem.MissiveType;
import smog.schema.sem.PriorityCode;
import smog.schema.sem.ReceiverIdentifier;
import smog.utils.Utils;

/**
 * SepamailGenericDocument creates a generic SEPAmail document.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SepamailGenericDocument extends DocumentBase implements DocumentInterface {

    private XmlObject sepamailGenericMessage;

    /**
     * SepamailGenericDocument constructor
     *
     * @param receiverIdentifier The missive recipient
     * @param sender The missive sender
     * @param sendDateTime The date and time at which the missive is being sent
     * @param messageType The type of the message being added to the generic SEPAmail missive document
     * @param messageExpiry The date and time at which the message expires
     * @param sepamailGenericMessage The message content of the SEPAmail missive document
     */
    public SepamailGenericDocument(ReceiverIdentifier receiverIdentifier, BICorIBAN sender, Calendar sendDateTime,
            String messageType, Calendar messageExpiry, XmlObject sepamailGenericMessage) {

        // Initialise the parent class
        super("1206", "1206", Utils.generateMissiveId(), BigInteger.ONE, PriorityCode.NORMAL, MissiveType.NOMINAL,
                receiverIdentifier, sender, sendDateTime, Utils.generateMessageId(), messageExpiry);

        // Initialise class attributes
        this.sepamailGenericMessage = sepamailGenericMessage;

        // Set the message type of the missive document - this is a dummy message type that gets replace by the
        // assignMessageType call below
        this.messageHeader.setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

        // Set the message type
        this.assignMessageType(messageType);

        // Build the simple.request@test document
        this.createDocument();
    }

    /**
     * SepamailGenericDocument constructor
     *
     * @param missiveId The ID of the missive
     * @param missiveOrder The order of the missive
     * @param priorityCode The priority of the missive
     * @param missiveType The type of the missive
     * @param receiverIdentifier The missive recipient
     * @param sender The missive sender
     * @param sendDateTime The date and time at which the missive is being sent
     * @param messageId The ID of the message
     * @param messageType The type of the message being added to the generic SEPAmail missive document
     * @param messageExpiry The date and time at which the message expires
     * @param sepamailGenericMessage The message content of the SEPAmail missive document
     */
    public SepamailGenericDocument(String missiveId, BigInteger missiveOrder, PriorityCode.Enum priorityCode,
            MissiveType.Enum missiveType, ReceiverIdentifier receiverIdentifier, BICorIBAN sender,
            Calendar sendDateTime, String messageId, String messageType, Calendar messageExpiry,
            XmlObject sepamailGenericMessage) {

        // Initialise the parent class
        super("1206", "1206", missiveId, missiveOrder, priorityCode, missiveType, receiverIdentifier, sender,
                sendDateTime, messageId, messageExpiry);

        // Initialise class attributes
        this.sepamailGenericMessage = sepamailGenericMessage;

        // Set the message type of the missive document - this is a dummy message type that gets replace by the
        // assignMessageType call below
        this.messageHeader.setMsgTyp(MessageType.SIMPLE_REQUEST_TEST);

        // Set the message type
        this.assignMessageType(messageType);

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
     * Get the generic message of the SEPAmail missive document
     *
     * @return The generic message of the SEPAmail missive document
     */
    public XmlObject getGenericSepamailMessage() {

        // Generic SEPAmail message
        return this.sepamailGenericMessage;
    }

    /**
     * Get the type of the SEPAmail message of the generic missive document
     *
     * @return Type of message of the generic missive document
     */
    public String getMessageType() {

        // Cursor for message header
        XmlCursor msgHdrCursor = this.messageHeader.newCursor();

        // Position the cursor on the message type element
        msgHdrCursor.toStartDoc();
        msgHdrCursor.toNextToken();

        // Check if we are at the required element
        if (!msgHdrCursor.getName().getLocalPart().equals("MsgTyp")) {

            // Move the cursor to the required element
            while (msgHdrCursor.toNextSibling()) {

                // Check if we are at the required element
                if (msgHdrCursor.getName().getLocalPart().equals("MsgTyp")) {
                    break;
                }
            }
        }

        // Get the message type
        String messageType = msgHdrCursor.getTextValue();

        // Dispose the message header cursor
        msgHdrCursor.dispose();

        return messageType;
    }

    /**
     * Set the message of the generic missive document
     *
     * @param sepamailGenericMessage Message of the generic missive document
     */
    public void setGenericSepamailMessage(XmlObject sepamailGenericMessage) {

        // Assign the message content of the SEPAmail missive
        this.sepamailGenericMessage = sepamailGenericMessage;

        // Build the missive document
        this.createDocument();
    }

    /**
     * Set the type of the SEPAmail missive document message
     *
     * @param messageType SEPAmail missive document message type
     */
    public void setMessageType(String messageType) {

        // Set the message type
        this.assignMessageType(messageType);

        // Build the missive document
        this.createDocument();
    }

    /**
     * Check if the generic missive document is valid
     *
     * @return Whether the missive document is valid
     * @throws SmogException
     */
    @Override
    public boolean validate() throws SmogException {

        // Listener for missive header validation
        ArrayList<XmlError> missiveHeaderValidationMessages = new ArrayList<>();

        // Listener for message header validation
        ArrayList<XmlError> messageHeaderValidationMessages = new ArrayList<>();

        // Validate missive header
        boolean isMissiveHeaderValid =
                this.missiveHeader.validate(new XmlOptions().setErrorListener(missiveHeaderValidationMessages));

        // Validate message header
        boolean isMessageHeaderValid =
                this.messageHeader.validate(new XmlOptions().setErrorListener(messageHeaderValidationMessages));

        // Verify validation for message header - XML Beans validation looks for typed message type and the message
        // header is considered invalid if the specified message type cannot be mapped onto one of the predefied message
        // types. We need to make sure that the validatio did not fail because of an unknown message type and allow for
        // the user defined message type.
        if (!isMessageHeaderValid) {

            // Check for unknown message type error message
            messageHeaderValidationMessages =
                    this.sanitizeMessageHeaderValidationMessages(messageHeaderValidationMessages);

            // Set the valid status of the message header
            isMessageHeaderValid = messageHeaderValidationMessages.isEmpty();
        }

        // Check if missive and message headers are valid
        if (isMissiveHeaderValid && isMessageHeaderValid) {
            return true;
        } else {

            // Validation error message
            String validationMessage = "";

            // Iterator for validation missive header messages
            Iterator<XmlError> missiveHeaderValidationMessagesIterator = missiveHeaderValidationMessages.iterator();

            // Build the complete error message
            while(missiveHeaderValidationMessagesIterator.hasNext()) {

                // Add validation message separtor
                if (!validationMessage.equals("")) {
                    validationMessage += ";";
                }

                // Concatenate validation messages
                validationMessage += missiveHeaderValidationMessagesIterator.next().getMessage();
            }

            // Iterator for validation message header messages
            Iterator<XmlError> messageHeaderValidationMessagesIterator = messageHeaderValidationMessages.iterator();

            // Build the complete error message
            while(messageHeaderValidationMessagesIterator.hasNext()) {

                // Add validation message separtor
                if (!validationMessage.equals("")) {
                    validationMessage += ";";
                }

                // Concatenate validation messages
                validationMessage += messageHeaderValidationMessagesIterator.next().getMessage();
            }

            throw new SmogException(validationMessage);
        }
    }

    /**
     * Build a missive document
     */
    private void createDocument() {

        // New instance of message body wrapper
        this.messageBody = MessageBody.Factory.newInstance();

        // Check if the message has been defined
        if (this.sepamailGenericMessage != null) {

            // Make a copy of the message
            XmlObject messageCopy = this.sepamailGenericMessage.copy();

            // Message body wrapper cursor
            XmlCursor mwCursor = this.messageBody.newCursor();

            // Set the position of the cursor
            mwCursor.toStartDoc();
            mwCursor.toNextToken();

            // SEPAmail message cursor
            XmlCursor messageCursor = this.sepamailGenericMessage.newCursor();
            messageCursor.toStartDoc();
            messageCursor.toNextToken();

            // Add the message to the wrapper
            messageCursor.moveXml(mwCursor);

            // Dispose cursors
            messageCursor.dispose();
            mwCursor.dispose();

            // Assign the value of the SEPAmail message attribute
            this.sepamailGenericMessage = messageCopy;
        }

        // Build the rest of the missive document
        this.assembleDocument();
    }

    /**
     * Set the type of the SEPAmail missive document message
     *
     * @param messageType SEPAmail missive document message type
     */
    private void assignMessageType(String messageType) {

        // Cursor for message header
        XmlCursor msgHdrCursor = this.messageHeader.newCursor();

        // Position the cursor on the message type element
        msgHdrCursor.toStartDoc();
        msgHdrCursor.toNextToken();

        // Check if we are at the required element
        if (!msgHdrCursor.getName().getLocalPart().equals("MsgTyp")) {

            // Move the cursor to the required element
            while (msgHdrCursor.toNextSibling()) {

                // Check if we are at the required element
                if (msgHdrCursor.getName().getLocalPart().equals("MsgTyp")) {
                    break;
                }
            }
        }

        // Set the value of the message type
        msgHdrCursor.setTextValue(messageType);

        // Dispose the cursor
        msgHdrCursor.dispose();
    }

    /**
     * Remove messages concerning unknown message type from the list of validation error messages for a message header
     *
     * @param validationMessages List of validation error messages for a message header
     * @return List of validation error messages for a message header without the error message for unknown message type
     */
    private ArrayList<XmlError> sanitizeMessageHeaderValidationMessages(ArrayList<XmlError> validationMessages) {

        // Unknown message type error message
        XmlError unknownMessageTypeError = null;

        // Scan the list of error messages
        for (int i = 0; i < validationMessages.size(); i++) {

            // Check the error message
            if (validationMessages.get(i).getMessage().matches(".+ '[\\S]+' .+ MessageType .+ http://xsd.sepamail.eu/[\\d]{4}/")) {

                // Unknown message type error message
                unknownMessageTypeError = validationMessages.get(i);

                // Exit the loop
                break;
            }
        }

        // Remove the unknown message type error message from the list of validation messages
        if (unknownMessageTypeError != null) {
            validationMessages.remove(unknownMessageTypeError);
        }

        return validationMessages;
    }
}
