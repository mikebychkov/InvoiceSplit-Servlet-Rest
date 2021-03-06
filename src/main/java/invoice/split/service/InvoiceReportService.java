package invoice.split.service;

import invoice.split.model.Debt;
import invoice.split.model.InvoiceDetails;
import invoice.split.model.InvoiceReport;

import java.util.ArrayList;
import java.util.List;

public class InvoiceReportService {

    private final InvoiceDetailsService detailsService = new InvoiceDetailsService();

    private class Info {

        private String usr;
        private Double sum;

        public Info(String usr, Double sum) {
            this.usr = usr;
            this.sum = sum;
        }

        public String getUsr() {
            return usr;
        }

        public void setUsr(String usr) {
            this.usr = usr;
        }

        public Double getSum() {
            return sum;
        }

        public void setSum(Double sum) {
            this.sum = sum;
        }
    }

    public List<InvoiceReport> get(String invoice) {

        InvoiceReport report = new InvoiceReport(invoice);

        List<InvoiceDetails> details = detailsService.getGrouped(invoice);

        double total = details.stream().map(d -> d.getAmount()).reduce((a, b) -> a + b).get();

        long userCount = details.stream().map(d -> d.getUsr()).distinct().count();

        double splitSum = total / userCount;

        List<Info> toPay = new ArrayList<>();
        List<Info> toReceive = new ArrayList<>();

        for (InvoiceDetails d : details) {
            double sum = d.getAmount() - splitSum;
            if (sum > 0) {
                toReceive.add(new Info(d.getUsr(), sum));
            } else if (sum < 0) {
                toPay.add(new Info(d.getUsr(), -sum));
            }
        }

        putDebtsInReport(report, toPay, toReceive);

        return List.of(report);
    }

    private void putDebtsInReport(InvoiceReport report, List<Info> toPay, List<Info> toReceive) {

        for (Info ip : toPay) {
            double pSum = ip.getSum();
            if (pSum == 0) {
                continue;
            }
            for (Info ir : toReceive) {
                if (pSum == 0) {
                    break;
                }
                double rSum = ir.getSum();
                if (rSum == 0) {
                    continue;
                }
                rSum = Math.min(rSum, pSum);
                ir.setSum(ir.getSum() - rSum);
                pSum -= rSum;
                //
                report.addDebt(new Debt(ip.getUsr(), ir.getUsr(), rSum));
            }
            ip.setSum(pSum);
        }
    }
}
