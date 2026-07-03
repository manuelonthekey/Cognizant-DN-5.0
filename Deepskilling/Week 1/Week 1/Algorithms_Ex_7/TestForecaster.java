public class TestForecaster {
    public static void main(String[] args) {

        // Scenario 1: Constant growth rate forecast
        double presentValue = 100000.0;  // e.g., current revenue in $
        double growthRate = 0.08;        // 8% annual growth
        int years = 5;

        double futureValue = FinancialForecaster.calculateFutureValue(presentValue, growthRate, years);
        System.out.printf("Constant Growth Forecast:%n");
        System.out.printf("Present Value: $%.2f%n", presentValue);
        System.out.printf("Growth Rate: %.1f%% per year%n", growthRate * 100);
        System.out.printf("Years: %d%n", years);
        System.out.printf("Projected Future Value: $%.2f%n", futureValue);

        System.out.println("--------------------------------------------------");

        // Scenario 2: Variable growth rates per year (more realistic)
        double[] variableRates = {0.05, 0.07, 0.03, 0.09, 0.06}; // year-by-year rates
        double futureValueVariable = FinancialForecaster.calculateFutureValueVariableRates(
                presentValue, variableRates, 0);

        System.out.printf("Variable Growth Forecast:%n");
        System.out.printf("Present Value: $%.2f%n", presentValue);
        System.out.print("Yearly Growth Rates: ");
        for (double r : variableRates) {
            System.out.printf("%.1f%% ", r * 100);
        }
        System.out.println();
        System.out.printf("Projected Future Value: $%.2f%n", futureValueVariable);
    }
}
