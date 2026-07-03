public class FinancialForecaster {

    /**
     * Recursively calculates the future value of an investment/revenue
     * given a present value, an annual growth rate, and number of years.
     *
     * Formula: FV = PV * (1 + r)^n
     *
     * @param presentValue the current/starting value
     * @param growthRate   the annual growth rate as a decimal (e.g., 0.08 for 8%)
     * @param years        number of years to project forward
     * @return the projected future value
     */
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        // Base case: no more years to compound
        if (years == 0) {
            return presentValue;
        }

        // Recursive case: grow the value by one year, then recurse for the rest
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    /**
     * A variant that supports a DIFFERENT growth rate per year
     * (more realistic for financial forecasting, where growth rates fluctuate).
     *
     * @param presentValue starting value
     * @param growthRates  array of growth rates, one per year (e.g., [0.05, 0.07, 0.03])
     * @param index        current index into growthRates (start at 0)
     * @return the projected future value after applying all given rates
     */
    public static double calculateFutureValueVariableRates(double presentValue, double[] growthRates, int index) {
        // Base case: reached the end of the growth rate array
        if (index == growthRates.length) {
            return presentValue;
        }

        // Recursive case: apply this year's rate, move to the next year
        return calculateFutureValueVariableRates(
                presentValue * (1 + growthRates[index]),
                growthRates,
                index + 1
        );
    }
}