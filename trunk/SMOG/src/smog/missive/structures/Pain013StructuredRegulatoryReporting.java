package smog.missive.structures;

import java.util.ArrayList;
import java.util.Calendar;
import smog.schema.p13.ActiveOrHistoricCurrencyAndAmount;
import smog.schema.p13.StructuredRegulatoryReporting3;

/**
 * Pain013StructuredRegulatoryReporting class represents a pain.013.001.01 structured regulatory reporting message
 * segment.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013StructuredRegulatoryReporting {

    // Class attributes
    private ActiveOrHistoricCurrencyAndAmount activeOrHistoricCurrencyAndAmount;
    private String code;
    private String countryCode;
    private Calendar date;
    private ArrayList<String> infos;
    private StructuredRegulatoryReporting3 structuredRegulatoryReporting;
    private String type;

    /**
     * Pain013StructuredRegulatoryReporting constructor
     *
     * @param activeOrHistoricCurrencyAndAmount Amount of money to be reported for the regulatory details
     * @param code Regulatory reporting code
     * @param countryCode Country related to the specified type of regulatory reporting
     * @param date Date related to the specified type of regulatory reporting
     * @param type Type of the information supplied in the regulatory reporting
     */
    public Pain013StructuredRegulatoryReporting(ActiveOrHistoricCurrencyAndAmount activeOrHistoricCurrencyAndAmount,
            String code, String countryCode, Calendar date, String type) {

        // Assign class attributes
        this.activeOrHistoricCurrencyAndAmount = activeOrHistoricCurrencyAndAmount;
        this.code = code;
        this.countryCode = countryCode;
        this.date = date;
        this.infos = new ArrayList<>();
        this.structuredRegulatoryReporting = StructuredRegulatoryReporting3.Factory.newInstance();
        this.type = type;

        // Set the amount to be reported
        this.setAmountReported();

        // Set the regulatory reporting code
        this.setCodeReported();

        // Set the reported country code
        this.setCountryCodeReported();

        // Set the reported date
        this.setDateReported();

        // Set the reported type
        this.setTypeReported();
    }

    /**
     * Add an additional information to the structured regulatory reporting
     *
     * @param additionalInformation Additional information
     */
    public void addAdditionalInformation(String additionalInformation) {

        // Verify that the additional information does not already exist
        if (!this.infos.contains(additionalInformation)) {

            // Add additional information to list of additional information
            this.infos.add(additionalInformation);

            // Update the additional information in the message segment
            this.setReportedInfo();
        }
    }

    /**
     * Remove all additional information from the structured regulatory reporting
     */
    public void clearAdditionalInformation() {

        // Clear the list of additinal information
        this.infos.clear();

        // Update the additional information in the message segment
        this.setReportedInfo();
    }

    /**
     * Get the amount associated with the current structured regulatory reporting
     *
     * @return The amount associated with the current structured regulatory reporting
     */
    public ActiveOrHistoricCurrencyAndAmount getActiveOrHistoricCurrencyAndAmount() {

        // Get the amount associated with the current structured regulatory reporting
        return this.activeOrHistoricCurrencyAndAmount;
    }

    /**
     * Get the list of additional information for the structured regulatory reporting detail
     *
     * @return List of additional information for the structured regulatory reporting detail
     */
    public ArrayList<String> getAdditionalInfos() {

        // Get the list of additional information for the structured regulatory reporting detail
        return this.infos;
    }

    /**
     * Get the additional information at the specified index
     *
     * @param index Index of the additional information
     * @return Additional information at the specified index
     */
    public String getAdditionalInfoAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.infos.size()) {

            // Get additional information at specified index
            return this.infos.get(index);
        }

        return null;
    }

    /**
     * Get the structured regulatory reporting code
     *
     * @return Structured regulatory reporting code
     */
    public String getCode() {

        // Get the structured regulatory reporting code
        return this.code;
    }

    /**
     * Get the country code associated to the current structure regulatory reporting
     *
     * @return Country code associated to the current structure regulatory reporting
     */
    public String getCountryCode() {

        // Get the country code associated to the current structure regulatory reporting
        return this.countryCode;
    }

    /**
     * Get the date associated with the structured regulatory reporting
     *
     * @return Date associated with the structured regulatory reporting
     */
    public Calendar getDate() {

        // Get the date associated with the structured regulatory reporting
        return this.date;
    }

    /**
     * Get the structured regulatory reporting message segment
     *
     * @return The structured regulatory reporting message segment
     */
    public StructuredRegulatoryReporting3 getStructuredRegulatoryReporting() {

        // Get the structured regulatory reporting message segment
        return this.structuredRegulatoryReporting;
    }

    /**
     * Get the structure regulatory reporting type
     *
     * @return Structure regulatory reporting type
     */
    public String getType() {

        // Get the structure regulatory reporting type
        return this.type;
    }

    /**
     * Remove the specified additional information from the structured regulatory reporting
     *
     * @param additionalInformation Additional information that needs to be removed
     * @return Whether the specified additional information could be removed
     */
    public boolean removeAdditionalInformation(String additionalInformation) {

        // Remove the specified additional information from the list
        if (this.infos.remove(additionalInformation)) {

            // Update the additional information in the message segment
            this.setReportedInfo();

            // Additional information was successfully remove
            return true;
        }

        // Additional information could not be remove
        return false;
    }

    /**
     * Set the amount associated with the current structured regulatory reporting
     *
     * @param activeOrHistoricCurrencyAndAmount The amount associated with the current structured regulatory reporting
     */
    public void setActiveOrHistoricCurrencyAndAmount(ActiveOrHistoricCurrencyAndAmount
            activeOrHistoricCurrencyAndAmount) {

        // Set the amount associated with the current structured regulatory reporting
        this.activeOrHistoricCurrencyAndAmount = activeOrHistoricCurrencyAndAmount;

        // Set the amount to be reported
        this.setAmountReported();
    }

    /**
     * Set the structured regulatory reporting code
     *
     * @param code Structured regulatory reporting code
     */
    public void setCode(String code) {

        // Set the structured regulatory reporting code
        this.code = code;

        // Set the regulatory reporting code
        this.setCodeReported();
    }

    /**
     * Set the country code associated to the current structure regulatory reporting
     *
     * @param countryCode Country code associated to the current structure regulatory reporting
     */
    public void setCountryCode(String countryCode) {

        // Set the country code associated to the current structure regulatory reporting
        this.countryCode = countryCode;

        // Set the reported country code
        this.setCountryCodeReported();
    }

    /**
     * Set the date associated with the structured regulatory reporting
     *
     * @param date Date associated with the structured regulatory reporting
     */
    public void setDate(Calendar date) {

        // Set the date associated with the structured regulatory reporting
        this.date = date;

        // Set the reported date
        this.setDateReported();
    }

    /**
     * Set the structured regulatory reporting type
     *
     * @param type Structured regulatory reporting type
     */
    public void setType(String type) {

        // Set the structured regulatory reporting type
        this.type = type;

        // Set the reported type
        this.setTypeReported();
    }

    /**
     * Set the amount of money to be reported
     */
    private void setAmountReported() {

        // Check if the amount to be reported has been defined
        if (this.activeOrHistoricCurrencyAndAmount != null) {

            // Set the amount to be reported
            this.structuredRegulatoryReporting.setAmt(this.activeOrHistoricCurrencyAndAmount);

        } else {

            // Remove the amount reported from the message segment
            if (this.structuredRegulatoryReporting.isSetAmt()) {
                this.structuredRegulatoryReporting.unsetAmt();
            }
        }
    }

    /**
     * Set the regulatory reporting code
     */
    private void setCodeReported() {

        // Check if the regulatory reporting code has been defined
        if (this.code != null) {

            // Set the regulatory reporting code
            this.structuredRegulatoryReporting.setCd(this.code);

        } else {

            // Remove the reported regulatory code from the message segment
            if (this.structuredRegulatoryReporting.isSetCd()) {
                this.structuredRegulatoryReporting.unsetCd();
            }
        }
    }

    /**
     * Set the reported country code
     */
    private void setCountryCodeReported() {

        // Check if the country code has been defined
        if (this.countryCode != null) {

            // Set the reported country code
            this.structuredRegulatoryReporting.setCtry(this.countryCode);

        } else {

            // Remove the reported country code from the message segment
            if (this.structuredRegulatoryReporting.isSetCtry()) {
                this.structuredRegulatoryReporting.unsetCtry();
            }
        }
    }

    /**
     * Set the reported date
     */
    private void setDateReported() {

        // Check if the date has been defined
        if (this.date != null) {

            // Set the reported date
            this.structuredRegulatoryReporting.setDt(this.date);

        } else {

            // Remove the reported date from the message segment
            if (this.structuredRegulatoryReporting.isSetDt()) {
                this.structuredRegulatoryReporting.unsetDt();
            }
        }
    }

    /**
     * Set reported additional information that cater for specific domestic regulatory requirements
     */
    private void setReportedInfo() {

        // Remove all reported additional information from the message segment
        for (int i = this.structuredRegulatoryReporting.sizeOfInfArray() - 1; i > -1; i--) {
            this.structuredRegulatoryReporting.removeInf(i);
        }

        // Check if there are additional information
        if (!this.infos.isEmpty()) {

            // Add additional information to the message segment
            for (String information : this.infos) {
                this.structuredRegulatoryReporting.addInf(information);
            }
        }
    }

    /**
     * Set the reported regulatory type
     */
    private void setTypeReported() {

        // Check if the type has been defined
        if (this.type != null) {

            // Set the reported type
            this.structuredRegulatoryReporting.setTp(this.type);

        } else {

            // Remove the reported type from the message segment
            if (this.structuredRegulatoryReporting.isSetTp()) {
                this.structuredRegulatoryReporting.unsetTp();
            }
        }
    }
}
