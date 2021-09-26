package App;

/**
 * @author Steven Hatch
 */

/**
 * InHouse class is a subclass of the Part class that is utilized for InHouse part data
 */
public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * @param machineId the machine id to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * @return the machine id
     */
    public int getMachineId() {
        return machineId;
    }
}