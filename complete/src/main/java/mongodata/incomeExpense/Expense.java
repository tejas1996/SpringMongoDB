package mongodata.incomeExpense;

import org.springframework.data.annotation.Id;
import org.bson.types.Decimal128;
public class Expense {
    @Id
    public String id;

    private String month;
    private String payment_date;
    private String batch_id;
    private String gst;
    private String particulars;
    private String bill_amount;
    private Decimal128 net_amount;
    private String narration;
    private String expense_header;
    private String project;
    private String donor;
    private String remarks;

    public Expense(String month, String payment_date, String batch_id, String gst, String particulars, String bill_amount, String net_amount, String narration, String expense_header, String project, String donor, String remarks) {
        this.month = month;
        this.payment_date = payment_date;
        this.batch_id = batch_id;
        this.gst = gst;
        this.particulars = particulars;
        this.bill_amount = bill_amount;
        this.net_amount = Decimal128.parse(net_amount);
        this.narration = narration;
        this.expense_header = expense_header;
        this.project = project;
        this.donor = donor;
        this.remarks = remarks;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public Decimal128 getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(Decimal128 net_amount) {
        this.net_amount = net_amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getExpense_header() {
        return expense_header;
    }

    public void setExpense_header(String expense_header) {
        this.expense_header = expense_header;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
