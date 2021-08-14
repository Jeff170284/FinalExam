public class Deluxe {
    private String custno;
    private String custname;
    private double initialdep;
    private int noofyears;
    private String savingstype;

    public Deluxe(String custno, String custname, double initialdep, int noofyears, String savingstype) {
        this.custno = custno;
        this.custname = custname;
        this.initialdep = initialdep;
        this.noofyears = noofyears;
        this.savingstype = savingstype;
    }

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public double getInitialdep() {
        return initialdep;
    }

    public void setInitialdep(double initialdep) {
        this.initialdep = initialdep;
    }

    public int getNoofyears() {
        return noofyears;
    }

    public void setNoofyears(int noofyears) {
        this.noofyears = noofyears;
    }

    public String getSavingstype() {
        return savingstype;
    }

    public void setSavingstype(String savingstype) {
        this.savingstype = savingstype;
    }

    public void GenerateTable()
    {

    }
}
