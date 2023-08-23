package budget.repo;

import budget.entity.Budget;

import java.math.BigDecimal;
import java.util.List;

public class FakeBudgetRepo implements BudgetRepo {
    @Override
    public List<Budget> getAll() {
        Budget budget1 = new Budget("202303", new BigDecimal(31000));
        Budget budget2 = new Budget("202304", new BigDecimal(3000));
        Budget budget3 = new Budget("202401", new BigDecimal(310));
        return List.of(budget1, budget2, budget3);
    }

}
