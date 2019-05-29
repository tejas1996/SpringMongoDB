package mongodata.incomeExpense;

import org.springframework.data.annotation.Id;
import org.bson.types.Decimal128;
public class Income {
        @Id
        public String id;

        private String month;
        private String invoice_number;
        private String received_date;
        private String donor_name;
        private Decimal128 amount;
        private String narration;
        private String project;
        private String remarks;
        private String inv_amt_not_recd;


    public Income(String month, String invoice_number, String received_date, String donor_name, String amount, String narration, String project, String remarks, String inv_amt_not_recd) {
        this.month = month;
        this.invoice_number = invoice_number;
        this.received_date = received_date;
        this.donor_name = donor_name;
        this.amount = Decimal128.parse(amount);
        this.narration = narration;
        this.project = project;
        this.remarks = remarks;
        this.inv_amt_not_recd = inv_amt_not_recd;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getReceived_date() {
        return received_date;
    }

    public void setReceived_date(String received_date) {
        this.received_date = received_date;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public Decimal128 getAmount() {
        return amount;
    }

    public void setAmount(Decimal128 amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInv_amt_not_recd() {
        return inv_amt_not_recd;
    }

    public void setInv_amt_not_recd(String inv_amt_not_recd) {
        this.inv_amt_not_recd = inv_amt_not_recd;
    }
}
