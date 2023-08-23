package budget;

import budget.entity.Budget;
import budget.repo.BudgetRepo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BudgetService {

    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public BigDecimal totalAmount(LocalDate startDate, LocalDate endDate) {
        BigDecimal totalAmount = new BigDecimal(0);

        List<Budget> all = budgetRepo.getAll();
        Map<String, BigDecimal> amountMap = all.stream().collect(Collectors.toMap(Budget::getYearMonth, Budget::getAmount));

        long days;
        LocalDate tempStartDate = startDate;
        LocalDate tempEndDate = YearMonth.from(tempStartDate).atEndOfMonth();
        while (!tempStartDate.isAfter(endDate)) {
            if (tempEndDate.isAfter(endDate)) {
                tempEndDate = endDate;
            }
            Duration between = Duration.between(tempStartDate.atStartOfDay(), tempEndDate.atStartOfDay());
            days = between.toDays() + 1;
            YearMonth yearMonth = YearMonth.of(tempStartDate.getYear(),
                    tempStartDate.getMonthValue());
            BigDecimal singleDayBudget = amountMap.get(yearMonth.toString().replace("-", ""));
            singleDayBudget = singleDayBudget == null ? new BigDecimal(0) : singleDayBudget.divide(new BigDecimal(yearMonth.lengthOfMonth()));
            BigDecimal multiplyResult = singleDayBudget.multiply(new BigDecimal(days));
            totalAmount = totalAmount.add(multiplyResult);

            tempStartDate = tempStartDate.plusMonths(1);
            tempStartDate = LocalDate.of(tempStartDate.getYear(), tempStartDate.getMonth(), 1);
            tempEndDate = YearMonth.from(tempStartDate).atEndOfMonth();
        }
        return totalAmount;
    }
}
