package student;

public class PayStub implements IPayStub{
    String employeeName;
    double netPay;
    double taxes;
    double ytdEarnings;
    double ytdTaxesPaid;
    public PayStub(String employeeName, double ytdEarnings, double ytdTaxesPaid,double netPay,double taxes) {
        this.employeeName = employeeName;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.netPay = netPay;
        this.taxes = taxes;
    }

    @Override
    public double getPay() {
        return netPay;
    }

    @Override
    public double getTaxesPaid() {
        return taxes;
    }

    @Override
    public String toCSV() {
        return "";
    }
}
