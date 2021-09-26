package App;

/**
 * @author Steven Hatch
 */

/**
 * Outsourced class is a subclass of the Part class that is utilized for Outsourced part data
 */
public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }
}