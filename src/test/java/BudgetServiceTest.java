import budget.BudgetService;
import budget.repo.BudgetRepo;
import budget.repo.FakeBudgetRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetServiceTest {

    BudgetService budgetService;

    @BeforeEach
    public void setup() {
        BudgetRepo budgetRepo = new FakeBudgetRepo();
        budgetService = new BudgetService(budgetRepo);
    }


    @Test
    public void wholeMonth() {
        BigDecimal totalAmount = budgetService.totalAmount(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31));
        assertEquals(new BigDecimal(31000), totalAmount);
    }

    @Test
    public void crossMonth() {
        BigDecimal totalAmount = budgetService.totalAmount(LocalDate.of(2023, 3, 31), LocalDate.of(2023, 4, 2));
        assertEquals(new BigDecimal(1200), totalAmount);
    }

    @Test
    public void partialMonth() {
        BigDecimal totalAmount = budgetService.totalAmount(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 2));
        assertEquals(new BigDecimal(2000), totalAmount);
    }

    @Test
    public void crossYear() {
        BigDecimal totalAmount = budgetService.totalAmount(LocalDate.of(2023, 3, 31), LocalDate.of(2024, 1, 1));
        assertEquals(new BigDecimal(4010), totalAmount);
    }

}
