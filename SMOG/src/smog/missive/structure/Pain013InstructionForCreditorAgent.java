package smog.missive.structure;

import java.util.Objects;
import smog.schema.p13.Instruction3Code;
import smog.schema.p13.InstructionForCreditorAgent1;

/**
 * Pain013InstructionForCreditorAgent class represents a pain.013.001.01 instruction for creditor agent.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class Pain013InstructionForCreditorAgent {

    // Class attributes
    private Instruction3Code.Enum instructionCode;
    private InstructionForCreditorAgent1 instructionForCreditorAgent;
    private String instructionInfo;

    /**
     * Pain013InstructionForCreditorAgent default constructor
     */
    public Pain013InstructionForCreditorAgent() {

        // Initialise class attributes
        this.instructionCode = null;
        this.instructionForCreditorAgent = InstructionForCreditorAgent1.Factory.newInstance();
        this.instructionInfo = null;

        // Build the message segment
        this.buildInstructionForCreditorAgent();
    }

    /**
     * Pain013InstructionForCreditorAgent constructor
     *
     * @param instructionCode Instruction code
     * @param instructionInfo Instruction info
     */
    public Pain013InstructionForCreditorAgent(Instruction3Code.Enum instructionCode, String instructionInfo) {

        // Initialise class attributes
        this.instructionCode = instructionCode;
        this.instructionForCreditorAgent = InstructionForCreditorAgent1.Factory.newInstance();
        this.instructionInfo = instructionInfo;

        // Build the message segment
        this.buildInstructionForCreditorAgent();
    }

    /**
     * Get the instruction code associated with the instruction for creditor agent
     *
     * @return Instruction code associated with the instruction for creditor agent
     */
    public Instruction3Code.Enum getInstructionCode() {

        // Get the instruction code
        return this.instructionCode;
    }

    /**
     * Get the instruction code associated with the instruction for creditor agent
     *
     * @param instructionCode Instruction code
     */
    public void setInstructionCode(Instruction3Code.Enum instructionCode) {

        // Set the instruction code
        this.instructionCode = instructionCode;

        // Set the instruction code if it has been defined
        if (this.instructionCode != null) {
            this.instructionForCreditorAgent.setCd(this.instructionCode);
        }
    }

    /**
     * Get the instruction info associated with the instruction for creditor agent
     *
     * @return Instruction info associated with the instruction for creditor agent
     */
    public String getInstructionInfo() {

        // Get the instruction info
        return this.instructionInfo;
    }

    /**
     * Set the instruction info associated with the instruction for creditor agent
     *
     * @param instructionInfo Instruction info
     */
    public void getInstructionInfo(String instructionInfo) {

        // Set the instruction info
        this.instructionInfo = instructionInfo;

        // Set the instruction info if it has been defined
        if (this.instructionInfo != null) {
            this.instructionForCreditorAgent.setInstrInf(instructionInfo);
        }
    }

    /**
     * Get instruction for creditor agent message segment
     *
     * @return Instruction for creditor agent message segment
     */
    public InstructionForCreditorAgent1 getInstructionForCreditorAgent() {

        // Get instruction for creditor agent message segment
        return this.instructionForCreditorAgent;
    }

   /**
     * Compare Pain013InstructionForCreditorAgent objects based on the instruction code and instruction info
     *
     * @param other Object that needs to be compared to the current Pain013InstructionForCreditorAgent instance
     * @return Whether the given object is equal to the current Pain013InstructionForCreditorAgent instance
     */
    @Override
    public boolean equals(Object other) {

        // Comparison result
        boolean result = false;

        // Check if the given object is not null
        if (other != null) {

            // Check if the given object is of the same type
            if (other instanceof Pain013InstructionForCreditorAgent) {

                // Check if we have same reference
                return (this.instructionInfo.equals(((Pain013InstructionForCreditorAgent) other).instructionInfo) &&
                        this.instructionCode == ((Pain013InstructionForCreditorAgent) other).instructionCode);
            }
        }

        return result;
    }

    /**
     * Generate a hash for a Pain013InstructionForCreditorAgent object based on its instruction code and info
     *
     * @return Hash for a Pain013InstructionForCreditorAgent object based on its instruction code and info
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.instructionCode);
        hash = 73 * hash + Objects.hashCode(this.instructionInfo);
        return hash;
    }

    /**
     * Set the properties of the InstructionForCreditorAgent message segment
     */
    private void buildInstructionForCreditorAgent() {

        // Set the instruction code if it has been defined
        if (this.instructionCode != null) {
            this.instructionForCreditorAgent.setCd(this.instructionCode);
        } else {
            if (this.instructionForCreditorAgent.isSetCd()) {
                this.instructionForCreditorAgent.unsetCd();
            }
        }

        // Set the instruction info if it has been defined
        if (this.instructionInfo != null) {
            this.instructionForCreditorAgent.setInstrInf(instructionInfo);
        } else {
            if (this.instructionForCreditorAgent.isSetInstrInf()) {
                this.instructionForCreditorAgent.unsetInstrInf();
            }
        }
    }
}
