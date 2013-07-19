package smog.missive.structures;

import java.util.ArrayList;
import smog.schema.p13.AmountType3Choice;
import smog.schema.p13.BranchAndFinancialInstitutionIdentification5;
import smog.schema.p13.CashAccount16;
import smog.schema.p13.ChargeBearerType1Code;
import smog.schema.p13.Cheque6;
import smog.schema.p13.CreditTransferTransactionInformation14;
import smog.schema.p13.InstructionForCreditorAgent1;
import smog.schema.p13.PartyIdentification43;
import smog.schema.p13.PaymentIdentification1;
import smog.schema.p13.PaymentTypeInformation19;
import smog.schema.p13.Purpose2Choice;
import smog.schema.p13.RegulatoryReporting3;
import smog.schema.p13.RemittanceInformation6;
import smog.schema.p13.RemittanceLocation2;
import smog.schema.p13.TaxInformation3;

/**
 * Pain013CreditTransferTransactionInformation class represents a pain.013.001.01 credit transfer transaction
 * information.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013CreditTransferTransactionInformation {

    // Class attributes
    private AmountType3Choice amount;
    private ChargeBearerType1Code.Enum chargeBearer;
    private Cheque6 chequeInstruction;
    private PartyIdentification43 creditor;
    private CashAccount16 creditorAccount;
    private CreditTransferTransactionInformation14 creditTransferTransactionInformation;
    private BranchAndFinancialInstitutionIdentification5 creditorAgent;
    private ArrayList<Pain013InstructionForCreditorAgent> instructionsForCreditorAgent;
    private BranchAndFinancialInstitutionIdentification5 intermediaryAgent1;
    private BranchAndFinancialInstitutionIdentification5 intermediaryAgent2;
    private BranchAndFinancialInstitutionIdentification5 intermediaryAgent3;
    private PaymentIdentification1 paymentIdenfitication;
    private PaymentTypeInformation19 paymentTypeInformation;
    private Purpose2Choice purpose;
    private ArrayList<Pain013RegulatoryReporting> regulatoryReportings;
    private ArrayList<Pain013RemittanceLocation> relatedRemittanceInformation;
    private RemittanceInformation6 remittanceInformation;
    private TaxInformation3 tax;
    private PartyIdentification43 ultimateCreditor;
    private PartyIdentification43 ultimateDebtor;

    /**
     * Pain013CreditTransferTransactionInformation constructor
     *
     * @param paymentIdenfitication Set of elements used to reference a payment instruction
     * @param paymentTypeInformation Set of elements used to further specify the type of transaction
     * @param amount Amount of money to be moved between the debtor and creditor before deduction of charges
     * @param chargeBearer Party who bears the charges associated with the processing of the payment transaction
     * @param chequeInstruction Set of elements needed to issue a cheque
     * @param ultimateDebtor Ultimate party that owes an amount of money to the creditor
     * @param intermediaryAgent1 Agent between the debtor's agent and the creditor's agent
     * @param intermediaryAgent2 Agent between the debtor's agent and the creditor's agent
     * @param intermediaryAgent3 Agent between the debtor's agent and the creditor's agent
     * @param creditorAgent Financial institution servicing an account for the creditor
     * @param creditor Party to which an amount of money is due
     * @param creditorAccount Unambiguous identification of the account of the creditor
     * @param ultimateCreditor Ultimate party to which an amount of money is due
     * @param purpose Underlying reason for the payment transaction
     * @param tax Set of elements used to provide details on the tax
     * @param remittanceInformation Unique identification to unambiguously identify the remittance information
     */
    public Pain013CreditTransferTransactionInformation(PaymentIdentification1 paymentIdenfitication,
            PaymentTypeInformation19 paymentTypeInformation, AmountType3Choice amount,
            ChargeBearerType1Code.Enum chargeBearer, Cheque6 chequeInstruction, PartyIdentification43 ultimateDebtor,
            BranchAndFinancialInstitutionIdentification5 intermediaryAgent1,
            BranchAndFinancialInstitutionIdentification5 intermediaryAgent2,
            BranchAndFinancialInstitutionIdentification5 intermediaryAgent3,
            BranchAndFinancialInstitutionIdentification5 creditorAgent, PartyIdentification43 creditor,
            CashAccount16 creditorAccount, PartyIdentification43 ultimateCreditor, Purpose2Choice purpose,
            TaxInformation3 tax, RemittanceInformation6 remittanceInformation) {

        // Initialise class attributes
        this.amount = amount;
        this.chargeBearer = chargeBearer;
        this.chequeInstruction = chequeInstruction;
        this.creditTransferTransactionInformation = CreditTransferTransactionInformation14.Factory.newInstance();
        this.creditor = creditor;
        this.creditorAccount = creditorAccount;
        this.creditorAgent = creditorAgent;
        this.instructionsForCreditorAgent = new ArrayList<>();
        this.intermediaryAgent1 = intermediaryAgent1;
        this.intermediaryAgent2 = intermediaryAgent2;
        this.intermediaryAgent3 = intermediaryAgent3;
        this.paymentIdenfitication = paymentIdenfitication;
        this.paymentTypeInformation = paymentTypeInformation;
        this.purpose = purpose;
        this.regulatoryReportings = new ArrayList<>();
        this.relatedRemittanceInformation = new ArrayList<>();
        this.remittanceInformation = remittanceInformation;
        this.tax = tax;
        this.ultimateCreditor = ultimateCreditor;
        this.ultimateDebtor = ultimateDebtor;

        // Set the payment identification of the credit transfer transaction information message segment
        this.setCttiPaymentIdentification();

        // Set the payment type information of the message segment
        this.setCttiPaymentTypeInformation();

        // Set the amount of money to be moved between the debtor and creditor in the message segment
        this.setCttiAmount();

        // Set the charge bearer in the message segment
        this.setCttiChargeBearer();

        // Set the cheque instruction of the message segment
        this.setCttiChequeInstruction();

        // Set the ultimate debtor that owes an amount of money to the creditor in the message segment
        this.setCttiUltimateDebtor();

        // Set the intermediary agents of the message segment
        this.setCttiIntermediaryAgent(1);
        this.setCttiIntermediaryAgent(2);
        this.setCttiIntermediaryAgent(3);

        // Set the creditor agent of the message segment
        this.setCttiCreditorAgent();

        // Set the creditor of the message segment
        this.setCttiCreditor();

        // Set the creditor account
        this.setCttiCreditorAccount();

        // Set the ultimate party to which an amount of money is due in the message segment
        this.setCttiUltimateCreditor();

        // Set the instructions for creditor agent in the message segment
        this.setCttiInstructionForCreditorAgent();

        // Set the purpose of the payment transaction in the message segment
        this.setCttiPurpose();

        // Set the regulatory reporting elements of the message segment
        this.setCttiRegulatoryReporting();

        // Set the tax details of the message segment
        this.setCttiTax();

        // Set the related remittance information of the message segment
        this.setCttiRelatedRemittanceInformation();

        // Set the remittance information of the message segment
        this.setCttiRemittanceInformation();
    }

    /**
     * Add the given instruction for creditor agent to the list of instructions for creditor agent
     *
     * @param instructionForCreditorAgent Instruction for creditor agent
     */
    public void addInstructionForCreditorAgent(Pain013InstructionForCreditorAgent instructionForCreditorAgent) {

        // Add the instruction to the list
        this.instructionsForCreditorAgent.add(instructionForCreditorAgent);

        // Set the instructions for creditor agent in the message segment
        this.setCttiInstructionForCreditorAgent();
    }

    /**
     * Add the given regulatory reporting to the list of regulatory reporting elements
     *
     * @param regulatoryReporting Regulatory reporting
     */
    public void addRegulatoryReporting(Pain013RegulatoryReporting regulatoryReporting) {

        // Add reporting to the list
        this.regulatoryReportings.add(regulatoryReporting);

        // Set the regulatory reporting elements of the message segment
        this.setCttiRegulatoryReporting();
    }

    /**
     * Add the given related remittance information to the list of related remittance information
     *
     * @param remittanceLocation Related remittance information
     */
    public void addRelatedRemittanceInformation(Pain013RemittanceLocation remittanceLocation) {

        // Add related remittance information to the list
        this.relatedRemittanceInformation.add(remittanceLocation);

        // Set the related remittance information of the message segment
        this.setCttiRelatedRemittanceInformation();
    }

    /**
     * Clear the list of instructions for creditor agent
     */
    public void clearInstructionsForCreditorAgent() {

        // Clear the list
        this.instructionsForCreditorAgent.clear();

        // Set the instructions for creditor agent in the message segment
        this.setCttiInstructionForCreditorAgent();
    }

    /**
     * Clear the list of regulatory reporting elements
     */
    public void clearRegulatoryReportings() {

        // Clear the list
        this.regulatoryReportings.clear();

        // Set the regulatory reporting elements of the message segment
        this.setCttiRegulatoryReporting();
    }

    /**
     * Clear the list of related remittance information
     */
    public void clearRelatedRemittanceInformation() {

        // Clear the list
        this.relatedRemittanceInformation.clear();

        // Set the related remittance information of the message segment
        this.setCttiRelatedRemittanceInformation();
    }

    /**
     * Get the amount of money to be moved between the debtor and creditor
     *
     * @return Amount of money to be moved between the debtor and creditor
     */
    public AmountType3Choice getAmount() {

        // Get the amount of money to be moved between the debtor and creditor
        return this.amount;
    }

    /**
     * Get the charge bearer
     *
     * @return Charge bearer
     */
    public ChargeBearerType1Code.Enum getChargeBearer() {

        // Get the charge bearer
        return this.chargeBearer;
    }

    /**
     * Get the cheque instruction
     *
     * @return Cheque instruction
     */
    public Cheque6 getChequeInstruction() {

        // Get the cheque instruction
        return this.chequeInstruction;
    }

    /**
     * Get the creditor
     *
     * @return Creditor
     */
    public PartyIdentification43 getCreditor() {

        // Get the creditor
        return this.creditor;
    }

    /**
     * Get the creditor account
     *
     * @return Creditor account
     */
    public CashAccount16 getCreditorAccount() {

        // Get the creditor account
        return this.creditorAccount;
    }

    /**
     * Get the creditor agent
     *
     * @return Creditor agent
     */
    public BranchAndFinancialInstitutionIdentification5 getCreditorAgent() {

        // Get the creditor agent
        return this.creditorAgent;
    }

    /**
     * Get the creditor transfer transaction information message segment
     *
     * @return Creditor transfer transaction information message segment
     */
    public CreditTransferTransactionInformation14 getCreditTransferTransactionInformation() {

        // Get the creditor transfer transaction information message segment
        return this.creditTransferTransactionInformation;
    }

    /**
     * Get the instruction for creditor agent at the specified index
     *
     * @param index Index of instruction for creditor agent
     * @return Instructions for creditor agent at the specified index
     */
    public Pain013InstructionForCreditorAgent getInstructionsForCreditorAgent(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.instructionsForCreditorAgent.size()) {

            // Instruction for creditor agent at the specified index
            return this.instructionsForCreditorAgent.get(index);
        }

        // Index is not valid
        return null;
    }

    /**
     * Get the list of instructions for creditor agent
     *
     * @return List of instructions for creditor agent
     */
    public ArrayList<Pain013InstructionForCreditorAgent> getInstructionsForCreditorAgent() {

        // Get the list of instructions for creditor agent
        return this.instructionsForCreditorAgent;
    }

    /**
     * Get the first intermediary agent
     *
     * @return First intermediary agent
     */
    public BranchAndFinancialInstitutionIdentification5 getIntermediaryAgent1() {

        // Get the intermediary agent 1
        return this.intermediaryAgent1;
    }

    /**
     * Get the second intermediary agent
     *
     * @return Second intermediary agent
     */
    public BranchAndFinancialInstitutionIdentification5 getIntermediaryAgent2() {

        // Get the intermediary agent 2
        return this.intermediaryAgent2;
    }

    /**
     * Get the third intermediary agent
     *
     * @return Third intermediary agent
     */
    public BranchAndFinancialInstitutionIdentification5 getIntermediaryAgent3() {

        // Get the intermediary agent 3
        return this.intermediaryAgent3;
    }

    /**
     * Get the payment identification of the credit transfer transaction information
     *
     * @return Payment identification of the credit transfer transaction information
     */
    public PaymentIdentification1 getPaymentIdentification() {

        // Get the payment identification
        return this.paymentIdenfitication;
    }

    /**
     * Get the payment type information
     *
     * @return Payment type information
     */
    public PaymentTypeInformation19 getPaymentTypeInformation() {

        // Get the payment type information
        return this.paymentTypeInformation;
    }

    /**
     * Get the purpose of the payment transaction
     *
     * @return Purpose of the payment transaction
     */
    public Purpose2Choice getPurpose() {

        // Get the purpose of the payment transaction
        return this.purpose;
    }

    /**
     * Get the list of regulatory reporting elements
     *
     * @return List of regulatory reporting elements
     */
    public ArrayList<Pain013RegulatoryReporting> getRegulatoryReportings() {

        // Get the list of regulatory reporting elements
        return this.regulatoryReportings;
    }

    /**
     * Get the list of related remittance information
     *
     * @return List of related remittance information
     */
    public ArrayList<Pain013RemittanceLocation> getRelatedRemittanceInformation() {

        // Get the list of related remittance information
        return this.relatedRemittanceInformation;
    }

    /**
     * Get the remittance information
     *
     * @return Remittance information
     */
    public RemittanceInformation6 getRemittanceInformation() {

        // Get the remittance information
        return this.remittanceInformation;
    }

    /**
     * Get the tax details
     *
     * @return Tax details
     */
    public TaxInformation3 getTax() {

        // Get the tax details
        return this.tax;
    }

    /**
     * Get the ultimate party to which an amount of money is due
     *
     * @return Ultimate party to which an amount of money is due
     */
    public PartyIdentification43 getUltimateCreditor() {

        // Get the ultimate party to which an amount of money is due
        return this.ultimateCreditor;
    }

    /**
     * Get the ultimate party that owes an amount of money to the creditor
     *
     * @return Ultimate party that owes an amount of money to the creditor
     */
    public PartyIdentification43 getUltimateDebtor() {

        // Get the ultimate party that owes an amount of money to the creditor
        return this.ultimateDebtor;
    }

    /**
     * Remove the given instruction for creditor agent from the list if it exists
     *
     * @param instructionForCreditorAgent Instruction for creditor agent that needs to be removed
     * @return Whether the instruction for creditor agent was successfully removed
     */
    public boolean removeInstructionsForCreditorAgent(Pain013InstructionForCreditorAgent instructionForCreditorAgent) {

        // Check if the instruction was successfully removed
        if (this.instructionsForCreditorAgent.remove(instructionForCreditorAgent)) {

            // Set the instructions for creditor agent in the message segment
            this.setCttiInstructionForCreditorAgent();

            return true;
        }

        // Instruction for creditor agent could not be removed
        return false;
    }

    /**
     * Remove a regulatory reporting at the given index
     *
     * @param index Index of regulatory reporting
     * @return Whether the regulatory reporting could be removed
     */
    public boolean removeRegulatoryReportingAtIndex(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.regulatoryReportings.size()) {

            // Remove the regulatory reporting
            this.regulatoryReportings.remove(index);

            // Set the regulatory reporting elements of the message segment
            this.setCttiRegulatoryReporting();

            return true;
        }

        // Regulatory reporting could not be removed
        return false;
    }

    /**
     * Remove the related remittance information at the given index
     *
     * @param index Index of related remittance information
     * @return Whether the related remittance information could be removed
     */
    public boolean removeRelatedRemittanceInformationAtIndex(int index) {

        // Check if the index is valid
        if (index > -1 && index < this.relatedRemittanceInformation.size()) {

            // Remove the related remittance information
            this.relatedRemittanceInformation.remove(index);

            // Set the related remittance information of the message segment
            this.setCttiRelatedRemittanceInformation();

            return true;
        }

        // The related remittance information could not be removed
        return false;
    }

    /**
     * Set the amount of money to be moved between the debtor and creditor
     *
     * @param amount Amount of money to be moved between the debtor and creditor
     */
    public void setAmount(AmountType3Choice amount) {

        // Set the amount of money to be moved between the debtor and creditor
        this.amount = amount;

        // Set the amount of money to be moved between the debtor and creditor in the message segment
        this.setCttiAmount();
    }

    /**
     * Set the charge bearer
     *
     * @param chargeBearer Charge bearer
     */
    public void setChargeBearer(ChargeBearerType1Code.Enum chargeBearer) {

        // Set the charge bearer
        this.chargeBearer = chargeBearer;

        // Set the charge bearer in the message segment
        this.setCttiChargeBearer();
    }

    /**
     * Set the creditor
     *
     * @param creditor Creditor
     */
    public void setCreditor(PartyIdentification43 creditor) {

        // Set the creditor
        this.creditor = creditor;

        // Set the creditor of the message segment
        this.setCttiCreditor();
    }

    /**
     * Set the creditor account
     *
     * @param creditorAccount Creditor account
     */
    public void setCreditorAccount(CashAccount16 creditorAccount) {

        // Set the creditor account
        this.creditorAccount = creditorAccount;

        // Set the creditor account of the message segment
        this.setCttiCreditorAccount();
    }

    /**
     * Set the creditor agent
     *
     * @param creditorAgent Creditor agent
     */
    public void setCreditorAgent(BranchAndFinancialInstitutionIdentification5 creditorAgent) {

        // Set the creditor agent
        this.creditorAgent = creditorAgent;

        // Set the creditor agent of the message segment
        this.setCttiCreditorAgent();
    }

    /**
     * Set the first intermediary agent
     *
     * @param intermediaryAgent1 First intermediary agent
     */
    public void setIntermediaryAgent1(BranchAndFinancialInstitutionIdentification5 intermediaryAgent1) {

        // Set the first intermediary agent
        this.intermediaryAgent1 = intermediaryAgent1;

        // Set the first intermediary agent of the message segment
        this.setCttiIntermediaryAgent(1);
    }

    /**
     * Set the second intermediary agent
     *
     * @param intermediaryAgent2 Second intermediary agent
     */
    public void setIntermediaryAgent2(BranchAndFinancialInstitutionIdentification5 intermediaryAgent2) {

        // Set the second intermediary agent
        this.intermediaryAgent2 = intermediaryAgent2;

        // Set the second intermediary agent of the message segment
        this.setCttiIntermediaryAgent(2);
    }

    /**
     * Set the third intermediary agent
     *
     * @param intermediaryAgent3 Third intermediary agent
     */
    public void setIntermediaryAgent3(BranchAndFinancialInstitutionIdentification5 intermediaryAgent3) {

        // Set the third intermediary agent
        this.intermediaryAgent3 = intermediaryAgent3;

        // Set the third intermediary agent of the message segment
        this.setCttiIntermediaryAgent(3);
    }

    /**
     * Set the payment identification of the credit transfer transaction information
     *
     * @param paymentIdentification Payment identification of the credit transfer transaction information
     */
    public void setPaymentIdentification(PaymentIdentification1 paymentIdentification) {

        // Set the payment identification
        this.paymentIdenfitication = paymentIdentification;

        // Set the payment identification of the credit transfer transaction information message segment
        this.setCttiPaymentIdentification();
    }

    /**
     * Set the payment type information
     *
     * @param paymentTypeInformation Payment type information
     */
    public void setPaymentTypeInformation(PaymentTypeInformation19 paymentTypeInformation) {

        // Set the payment type information
        this.paymentTypeInformation = paymentTypeInformation;

        // Set the payment type information of the message segment
        this.setCttiPaymentTypeInformation();
    }

    /**
     * Set the purpose of the payment transaction
     *
     * @param purpose Purpose of the payment transaction
     */
    public void setPurpose(Purpose2Choice purpose) {

        // Set the purpose of the payment transaction
        this.purpose = purpose;

        // Set the purpose of the payment transaction in the message segment
        this.setCttiPurpose();
    }

    /**
     * Set the remittance information
     *
     * @param remittanceInformation Remittance information
     */
    public void setRemittanceInformation(RemittanceInformation6 remittanceInformation) {

        // Set the remittance information
        this.remittanceInformation = remittanceInformation;

        // Set the remittance information of the message segment
        this.setCttiRemittanceInformation();
    }

    /**
     * Set the tax details
     *
     * @param tax Tax details
     */
    public void setTax(TaxInformation3 tax) {

        // Set the tax details
        this.tax = tax;

        // Set the tax details of the message segment
        this.setCttiTax();
    }

    /**
     * Set the ultimate party to which an amount of money is due
     *
     * @param ultimateCreditor Ultimate creditor
     */
    public void setUltimateCreditor(PartyIdentification43 ultimateCreditor) {

        // Set the ultimate creditor
        this.ultimateCreditor = ultimateCreditor;

        // Set the ultimate party to which an amount of money is due in the message segment
        this.setCttiUltimateCreditor();
    }

    /**
     * Set the ultimate party that owes an amount of money to the creditor
     *
     * @param ultimateDebtor Ultimate party that owes an amount of money to the creditor
     */
    public void setUltimateDebtor(PartyIdentification43 ultimateDebtor) {

        // Set the ultimate debtor
        this.ultimateDebtor = ultimateDebtor;

        // Set the ultimate debtor that owes an amount of money to the creditor in the message segment
        this.setCttiUltimateDebtor();
    }

    /**
     * Set the amount of money to be moved between the debtor and creditor in the message segment
     */
    private void setCttiAmount() {

        // Set the amount of money to be moved between the debtor and creditor in the message segment
        this.creditTransferTransactionInformation.setAmt(this.amount);
    }

    /**
     * Set the charge bearer in the message segment
     */
    private void setCttiChargeBearer() {

        // Set the charge bearer in the message segment
        this.creditTransferTransactionInformation.setChrgBr(this.chargeBearer);
    }

    /**
     * Set the cheque instruction of the message segment
     */
    private void setCttiChequeInstruction() {

        // Check if the cheque instruction element has been defined
        if (this.chequeInstruction != null) {

            // Set the cheque instruction of the message segment
            this.creditTransferTransactionInformation.setChqInstr(this.chequeInstruction);

        } else {

            // Remove the cheque instruction from the message segment
            if (this.creditTransferTransactionInformation.isSetChqInstr()) {
                this.creditTransferTransactionInformation.unsetChqInstr();
            }
        }
    }

    /**
     * Set the creditor of the message segment
     */
    private void setCttiCreditor() {

        // Set the creditor of the message segment
        this.creditTransferTransactionInformation.setCdtr(this.creditor);
    }

    /**
     * Set the creditor account of the message segment
     */
    private void setCttiCreditorAccount() {

        // Check if the creditor account has been specified
        if (this.creditorAccount != null) {

            // Add the creditor account to the message segment
            this.creditTransferTransactionInformation.setCdtrAcct(this.creditorAccount);

        } else {

            // Remove the creditor from the message segment
            if (this.creditTransferTransactionInformation.isSetCdtrAcct()) {
                this.creditTransferTransactionInformation.unsetCdtrAcct();
            }
        }
    }

    /**
     * Set the creditor agent of the message segment
     */
    private void setCttiCreditorAgent() {

        // Set the creditor agent of the message segment
        this.creditTransferTransactionInformation.setCdtrAgt(this.creditorAgent);
    }

    /**
     * Set the instructions for creditor agent in the message segment
     */
    private void setCttiInstructionForCreditorAgent() {

        // Remove all instructions for creditor agent from the message segment
        for (int i = this.creditTransferTransactionInformation.sizeOfInstrForCdtrAgtArray() - 1; i > -1; i--) {
            this.creditTransferTransactionInformation.removeInstrForCdtrAgt(i);
        }

        // Check if we have instructions for creditor agent
        if (!this.instructionsForCreditorAgent.isEmpty()) {

            // Array of instructions for creditor agent
            InstructionForCreditorAgent1[] instructions =
                    new InstructionForCreditorAgent1[this.instructionsForCreditorAgent.size()];

            // Build the array of instructions
            for (int i = 0; i < this.instructionsForCreditorAgent.size(); i++) {
                instructions[i] = this.instructionsForCreditorAgent.get(i).getInstructionForCreditorAgent();
            }

            // Add instructions to the message segment
            this.creditTransferTransactionInformation.setInstrForCdtrAgtArray(instructions);
        }
    }

    /**
     * Set the intermediary agent of the message segment
     *
     * @param index Index of the intermediary agent
     */
    private void setCttiIntermediaryAgent(int index) {

        // Check the index of the intermediary agent
        switch (index) {

            // Intermediary agent 1
            case 1: {

                // Check if the intermediary agent has been defined
                if (this.intermediaryAgent1 != null) {

                    // Add the intermediary agent to the message segment
                    this.creditTransferTransactionInformation.setIntrmyAgt1(this.intermediaryAgent1);

                } else {

                    // Remove the intermediary agent from the message segment
                    if (this.creditTransferTransactionInformation.isSetIntrmyAgt1()) {
                        this.creditTransferTransactionInformation.unsetIntrmyAgt1();
                    }
                }
            }
            break;

            // Intermediary agent 2
            case 2: {

                // Check if the intermediary agent has been defined
                if (this.intermediaryAgent2 != null) {

                    // Add the intermediary agent to the message segment
                    this.creditTransferTransactionInformation.setIntrmyAgt2(this.intermediaryAgent2);

                } else {

                    // Remove the intermediary agent from the message segment
                    if (this.creditTransferTransactionInformation.isSetIntrmyAgt2()) {
                        this.creditTransferTransactionInformation.unsetIntrmyAgt2();
                    }
                }
            }
            break;

            // Intermediary agent 3
            case 3: {

                // Check if the intermediary agent has been defined
                if (this.intermediaryAgent3 != null) {

                    // Add the intermediary agent to the message segment
                    this.creditTransferTransactionInformation.setIntrmyAgt3(this.intermediaryAgent3);

                } else {

                    // Remove the intermediary agent from the message segment
                    if (this.creditTransferTransactionInformation.isSetIntrmyAgt3()) {
                        this.creditTransferTransactionInformation.unsetIntrmyAgt3();
                    }
                }
            }
            break;
        }
    }

    /**
     * Set the payment identification of the credit transfer transaction information message segment
     */
    private void setCttiPaymentIdentification() {

        // Set the payment idenfication of the message segment
        this.creditTransferTransactionInformation.setPmtId(this.paymentIdenfitication);
    }

    /**
     * Set the payment type information of the message segment
     */
    private void setCttiPaymentTypeInformation() {

        // Check if the payment type information has been specified
        if (this.paymentTypeInformation != null) {

            // Set the payment type information of the message segment
            this.creditTransferTransactionInformation.setPmtTpInf(this.paymentTypeInformation);

        } else {

            // Remove the payment type information from the message segment
            if (this.creditTransferTransactionInformation.isSetPmtTpInf()) {
                this.creditTransferTransactionInformation.unsetPmtTpInf();
            }
        }
    }

    /**
     * Set the purpose of the payment transaction in the message segment
     */
    private void setCttiPurpose() {

        // Check if the purpose of the payment transaction has been specified
        if (this.purpose != null) {

            // Add the purpose to the message segment
            this.creditTransferTransactionInformation.setPurp(this.purpose);

        } else {

            // Remove the purpose from the message segment
            if (this.creditTransferTransactionInformation.isSetPurp()) {
                this.creditTransferTransactionInformation.unsetPurp();
            }
        }
    }

    /**
     * Set the regulatory reporting elements of the message segment
     */
    private void setCttiRegulatoryReporting() {

        // Remove all regulatory reporting elements from the message segment
        for (int i = this.creditTransferTransactionInformation.sizeOfRgltryRptgArray() - 1; i > -1; i--) {
            this.creditTransferTransactionInformation.removeRgltryRptg(i);
        }

        // Check if the list of regulatory reporting elements is not empty
        if (!this.regulatoryReportings.isEmpty()) {

            // Array of regulatory reporting message segments
            RegulatoryReporting3[] reportings = new RegulatoryReporting3[this.regulatoryReportings.size()];

            // Build the array of reportings
            for (int i = 0; i < this.regulatoryReportings.size(); i++) {
                reportings[i] = this.regulatoryReportings.get(i).getRegulatoryReporting();
            }

            // Add the reportings to the message segment
            this.creditTransferTransactionInformation.setRgltryRptgArray(reportings);
        }
    }

    /**
     * Set the related remittance information of the message segment
     */
    private void setCttiRelatedRemittanceInformation() {

        // Remove all related remittance information from the message segment
        for (int i = this.creditTransferTransactionInformation.sizeOfRltdRmtInfArray() - 1; i > -1; i--) {
            this.creditTransferTransactionInformation.removeRltdRmtInf(i);
        }

        // Check if the list of related remittance information is not empty
        if (!this.relatedRemittanceInformation.isEmpty()) {

            // Array of related remittance information
            RemittanceLocation2[] remittanceLocations =
                    new RemittanceLocation2[this.relatedRemittanceInformation.size()];

            // Build the list of remittance locations
            for (int i = 0; i < this.relatedRemittanceInformation.size(); i++) {
                remittanceLocations[i] = this.relatedRemittanceInformation.get(i).getRemittanceLocation();
            }

            // Add the list of related remittance information to the message segment
            this.creditTransferTransactionInformation.setRltdRmtInfArray(remittanceLocations);
        }
    }

    /**
     * Set the remittance information of the message segment
     */
    private void setCttiRemittanceInformation() {

        // Check if the remittance information has been defined
        if (this.remittanceInformation != null) {

            // Add the remittance information to the message segment
            this.creditTransferTransactionInformation.setRmtInf(this.remittanceInformation);

        } else {

            // Remove remittance information from the message segment
            if (this.creditTransferTransactionInformation.isSetRmtInf()) {
                this.creditTransferTransactionInformation.unsetRmtInf();
            }
        }
    }

    /**
     * Set the tax details of the message segment
     */
    private void setCttiTax() {

        // Check if the tax details have been specified
        if (this.tax != null) {

            // Add tax details to the message segment
            this.creditTransferTransactionInformation.setTax(this.tax);

        } else {

            // Remove tax details from the message segment
            if (this.creditTransferTransactionInformation.isSetTax()) {
                this.creditTransferTransactionInformation.unsetTax();
            }
        }
    }

    /**
     * Set the ultimate party to which an amount of money is due in the message segment
     */
    private void setCttiUltimateCreditor() {

        // Check if the ultimate creditor has been specified
        if (this.ultimateCreditor != null) {

            // Set the ultimate creditor of the message segment
            this.creditTransferTransactionInformation.setUltmtCdtr(this.ultimateCreditor);

        } else {

            // Remove the ultimate creditor from the message segment
            if (this.creditTransferTransactionInformation.isSetUltmtCdtr()) {
                this.creditTransferTransactionInformation.unsetUltmtCdtr();
            }
        }
    }

    /**
     * Set the ultimate debtor that owes an amount of money to the creditor in the message segment
     */
    private void setCttiUltimateDebtor() {

        // Check if the ultimate debtor has been specified
        if (this.ultimateDebtor != null) {

            // Set the ultimate debtor of the message segment
            this.creditTransferTransactionInformation.setUltmtDbtr(this.ultimateDebtor);

        } else {

            // Remove the ultimate debtor from the message segment
            if (this.creditTransferTransactionInformation.isSetUltmtDbtr()) {
                this.creditTransferTransactionInformation.unsetUltmtDbtr();
            }
        }
    }
}
