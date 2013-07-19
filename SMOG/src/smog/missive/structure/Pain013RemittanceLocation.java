
package smog.missive.structure;

import smog.schema.p13.NameAndAddress10;
import smog.schema.p13.RemittanceLocation2;
import smog.schema.p13.RemittanceLocationMethod2Code;

/**
 * Pain013RemittanceLocation class represents a pain.013.001.01 related remittance information.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013RemittanceLocation {

    // Class attributes
    private String electronicAddress;
    private String id;
    private RemittanceLocationMethod2Code.Enum locationMethod;
    private NameAndAddress10 postalAddress;
    private RemittanceLocation2 remittanceLocation;

    /**
     * Pain013RemittanceLocation constructor
     *
     * @param id Unique identification to unambiguously identify the remittance information
     * @param locationMethod Method used to deliver the remittance advice information
     * @param electronicAddress Electronic address to which an agent is to send the remittance information
     * @param postalAddress Postal address to which an agent is to send the remittance information
     */
    public Pain013RemittanceLocation(String id, RemittanceLocationMethod2Code.Enum locationMethod,
            String electronicAddress, NameAndAddress10 postalAddress) {

        // Initialise class attributes
        this.electronicAddress = electronicAddress;
        this.id = id;
        this.locationMethod = locationMethod;
        this.postalAddress = postalAddress;
        this.remittanceLocation = RemittanceLocation2.Factory.newInstance();

        // Set the remittance identification in the message segment
        this.setRemittanceIdenfitication();

        // Set the electronic address in the message segment
        this.setRemittanceElectronicAddress();

        // Set the remittance location method in the message segment
        this.setRemittanceLocationMethod();

        // Set the remittance postal address in the message segment
        this.setRemittanceLocationPostalAddress();
    }

    /**
     * Get the electronic address associated with the remittance location
     *
     * @return Electronic address associated with the remittance location
     */
    public String getElectronicAddress() {

        // Get the electronic address associated with the remittance location
        return this.electronicAddress;
    }

    /**
     * Get the remittance location identification
     *
     * @return Remittance location identification
     */
    public String getId() {

        // Get the remittance location identification
        return this.id;
    }

    /**
     * Get the location method associated with the remittance location
     *
     * @return Location method associated with the remittance location
     */
    public RemittanceLocationMethod2Code.Enum getLocationMethod() {

        // Get the location method associated with the remittance location
        return this.locationMethod;
    }

    /**
     * Get the remittance location message segment
     *
     * @return Remittance location message segment
     */
    public RemittanceLocation2 getRemittanceLocation() {

        // Get the remittance location message segment
        return this.remittanceLocation;
    }

    /**
     * Get the postal address associated with the remittance location
     *
     * @return Postal address associated with the remittance location
     */
    public NameAndAddress10 getPostalAddress() {

        // Get the postal address associated with the remittance location
        return this.postalAddress;
    }

    /**
     * Set the electronic address associated with the remittance location
     *
     * @param electronicAddress Electronic address associated with the remittance location
     */
    public void setElectronicAddress(String electronicAddress) {

        // Set the electronic address
        this.electronicAddress = electronicAddress;

        // Set the electronic address in the message segment
        this.setRemittanceElectronicAddress();
    }

    /**
     * Set the remittance location identification
     *
     * @param id Remittance location identification
     */
    public void setId(String id) {

        // Set the remittance location identification
        this.id = id;

        // Set the remittance identification in the message segment
        this.setRemittanceIdenfitication();
    }

    /**
     * Set the location method associated with the remittance location
     *
     * @param locationMethod Location method associated with the remittance location
     */
    public void setLocationMethod(RemittanceLocationMethod2Code.Enum locationMethod) {

        // Set the location method
        this.locationMethod = locationMethod;

        // Set the remittance location method in the message segment
        this.setRemittanceLocationMethod();
    }

    /**
     * Set the postal address associated with the remittance location
     *
     * @param postalAddress Postal address associated with the remittance location
     */
    public void setPostalAddress(NameAndAddress10 postalAddress) {

        // Set the postal address
        this.postalAddress = postalAddress;

        // Set the remittance postal address in the message segment
        this.setRemittanceLocationPostalAddress();
    }

    /**
     * Set the electronic address in the message segment
     */
    private void setRemittanceElectronicAddress() {

        // Check if the electronic address has been specified
        if (this.electronicAddress != null) {

            // Set the electronic address
            this.remittanceLocation.setRmtLctnElctrncAdr(this.electronicAddress);

        } else {

            // Remove the electronic address associated with the remittance location
            if (this.remittanceLocation.isSetRmtLctnElctrncAdr()) {
                this.remittanceLocation.unsetRmtLctnElctrncAdr();
            }
        }
    }

    /**
     * Set the remittance identification in the message segment
     */
    private void setRemittanceIdenfitication() {

        // Check if the remittance ID has been specified
        if (this.id != null) {

            // Set the remittance identification
            this.remittanceLocation.setRmtId(this.id);

        } else {

            // Remove the remittance identification
            if (this.remittanceLocation.isSetRmtId()) {
                this.remittanceLocation.unsetRmtId();
            }
        }
    }

    /**
     * Set the remittance location method in the message segment
     */
    private void setRemittanceLocationMethod() {

        // Check if the location method has been specified
        if (this.locationMethod != null) {

            // Set the remittance location method
            this.remittanceLocation.setRmtLctnMtd(this.locationMethod);

        } else {

            // Remove the remittance location method from the message segment
            if (this.remittanceLocation.isSetRmtLctnMtd()) {
                this.remittanceLocation.unsetRmtLctnMtd();
            }
        }
    }

    /**
     * Set the remittance postal address in the message segment
     */
    private void setRemittanceLocationPostalAddress() {

        // Check if the postal address has been specified
        if (this.postalAddress != null) {

            // Set the remittance postal address
            this.remittanceLocation.setRmtLctnPstlAdr(this.postalAddress);

        } else {

            // Remove the postal address from the message segment
            if (this.remittanceLocation.isSetRmtLctnPstlAdr()) {
                this.remittanceLocation.unsetRmtLctnPstlAdr();
            }
        }
    }
}
