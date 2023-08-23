package budget.entity;

import java.math.BigDecimal;

public class Budget {

    public Budget(String yearMonth, BigDecimal amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    private String yearMonth;
    private BigDecimal amount;

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
