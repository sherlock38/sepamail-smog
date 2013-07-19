package smog.missive.structures;

import java.util.ArrayList;
import smog.schema.p13.RegulatoryAuthority2;
import smog.schema.p13.RegulatoryReporting3;
import smog.schema.p13.RegulatoryReportingType1Code;
import smog.schema.p13.RegulatoryReportingType1Code.Enum;
import smog.schema.p13.StructuredRegulatoryReporting3;

/**
 * Pain013RegulatoryReporting class represents a pain.013.001.01 regulatory reporting message segment.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013RegulatoryReporting {

    // Class attributes
    private RegulatoryAuthority2 authority;
    private ArrayList<Pain013StructuredRegulatoryReporting> details;
    private RegulatoryReporting3 regulatoryReporting;
    private RegulatoryReportingType1Code.Enum reportingTypeCode;

    /**
     * Pain013RegulatoryReporting constructor
     *
     * @param authority Entity requiring the regulatory reporting information
     * @param reportingTypeCode Side of transaction to which the regulatory information applies
     */
    public Pain013RegulatoryReporting(RegulatoryAuthority2 authority, RegulatoryReportingType1Code.Enum
            reportingTypeCode) {

        // Initialise class attributes
        this.authority = authority;
        this.details = new ArrayList<>();
        this.regulatoryReporting = RegulatoryReporting3.Factory.newInstance();
        this.reportingTypeCode = reportingTypeCode;

        // Set the entity requiring the regulatory reporting information
        this.setAuthorityReported();

        // Set the reported reporting type code
        this.setReportingTypeCodeReported();
    }

    /**
     * Add a structured regulatory reporting detail to a regulatory reporting message segment
     *
     * @param structuredRegulatoryReporting Structured regulatory reporting detail
     */
    public void addDetail(Pain013StructuredRegulatoryReporting structuredRegulatoryReporting) {

        // Add structured regulatory reporting object to list
        this.details.add(structuredRegulatoryReporting);

        // Update structured regulatory reporting details in the message segment
        this.setDetailsReported();
    }

    /**
     * Clear the structured regulatory reporting details in the message segment
     */
    public void clearDetails() {

        // Clear the details
        this.details.clear();

        // Update structured regulatory reporting details in the message segment
        this.setDetailsReported();
    }

    /**
     * Get the entity requiring the regulatory reporting information
     *
     * @return Entity requiring the regulatory reporting information
     */
    public RegulatoryAuthority2 getAuthority() {

        // Get the entity requiring the regulatory reporting information
        return this.authority;
    }

    /**
     * Get the structured regulatory reporting details
     *
     * @return Structured regulatory reporting details
     */
    public ArrayList<Pain013StructuredRegulatoryReporting> getDetails() {

        // Get the structured regulatory reporting details
        return this.details;
    }

    /**
     * Get the regulatory reporting message segment
     *
     * @return Regulatory reporting message segment
     */
    public RegulatoryReporting3 getRegulatoryReporting() {

        // Get the regulatory reporting message segment
        return this.regulatoryReporting;
    }

    /**
     * Get the regulatory reporting type code
     *
     * @return Regulatory reporting type code
     */
    public RegulatoryReportingType1Code.Enum getReportingTypeCode() {

        // Get the regulatory reporting type code
        return this.reportingTypeCode;
    }

    /**
     * Remove the structured regulatory reporting detail at the specified index
     *
     * @param index Index of detail that needs to be removed
     * @return Whether the detail was successfully removed
     */
    public boolean removeDetailAtIndex(int index) {

        // Check if index is valid
        if (index > -1 && index < this.details.size()) {

            // Remove the structured regulatory reporting detail
            this.details.remove(index);

            // Update the reported details
            this.setDetailsReported();

            // Structured regulatory reporting detail was successfully remove
            return true;
        }

        // Structured regulatory report could not be removed
        return false;
    }

    /**
     * Set the entity requiring the regulatory reporting information
     *
     * @param authority Entity requiring the regulatory reporting information
     */
    public void setAuthority(RegulatoryAuthority2 authority) {

        // Set the entity requiring the regulatory reporting information
        this.authority = authority;

        // Set the entity requiring the regulatory reporting information
        this.setAuthorityReported();
    }

    /**
     * Set the regulatory reporting type code
     *
     * @param reportingTypeCode Regulatory reporting type code
     */
    public void setReportingTypeCode(Enum reportingTypeCode) {

        // Set the regulatory reporting type code
        this.reportingTypeCode = reportingTypeCode;

        // Set the reported reporting type code
        this.setReportingTypeCodeReported();
    }

    /**
     * Set the entity which requires the regulatory reporting information
     */
    private void setAuthorityReported() {

        // Check that the authority entity has been defined
        if (this.authority != null) {

            // Set the reported authority
            this.regulatoryReporting.setAuthrty(this.authority);

        } else {

            // Remove the reported authority from the message segment
            if (this.regulatoryReporting.isSetAuthrty()) {
                this.regulatoryReporting.unsetAuthrty();
            }
        }
    }

    /**
     * Set the structured regulatory reporting details reported
     */
    private void setDetailsReported() {

        // Remove existing structured regulatory reporting details from the message segment
        for (int i = this.regulatoryReporting.sizeOfDtlsArray() -1; i > -1; i--) {
            this.regulatoryReporting.removeDtls(i);
        }

        // Check that the list of details is not empty
        if (!this.details.isEmpty()) {

            // Array of structured regulatory reporting details
            StructuredRegulatoryReporting3[] srrs = new StructuredRegulatoryReporting3[this.details.size()];

            // Traverse the list of structured regulatory reporting details
            for (int i = 0; i < this.details.size(); i++) {
                srrs[i] = this.details.get(i).getStructuredRegulatoryReporting();
            }

            // Add the array of structured regulatory reporting details to the message segment
            this.regulatoryReporting.setDtlsArray(srrs);
        }
    }

    /**
     * Set the reported side of the transaction to which the regulatory information applies
     */
    private void setReportingTypeCodeReported() {

        // Check that the reporting type code has been defined
        if (this.reportingTypeCode != null) {

            // Set the reporting type code
            this.regulatoryReporting.setDbtCdtRptgInd(this.reportingTypeCode);

        } else {

            // Remove the reported reporting type code from the message segment
            if (this.regulatoryReporting.isSetDbtCdtRptgInd()) {
                this.regulatoryReporting.unsetDbtCdtRptgInd();
            }
        }
    }
}
