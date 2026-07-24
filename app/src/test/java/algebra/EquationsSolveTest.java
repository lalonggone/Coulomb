package algebra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Vector;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Equations#solve()} focused on how solved values are rounded.
 *
 * Each Equation here encodes "1 * x = constant", so the solution is just the
 * constant. Using 1/3 makes the rounding behaviour observable: rounded to six
 * decimal places, the nearest value is 0.333333.
 */
public class EquationsSolveTest {

    private static Equations singleVariable(String name, double constant) {
        Vector<Variable> vars = new Vector<>();
        vars.add(new Variable(name, 1));
        return new Equations(new Equation(vars, constant));
    }

    @Test
    void solvedValuesAreRoundedToNearest() throws Exception {
        double x = singleVariable("x", 1.0 / 3.0).solve().get("x");
        // Nearest value at 6 decimal places is 0.333333.
        // The old Math.ceil rounding produced 0.333334.
        assertEquals(0.333333, x, 1e-9,
                "solve() should round to nearest, not always up");
    }

    @Test
    void solvedValuesAreSignSymmetric() throws Exception {
        double positive = singleVariable("x", 1.0 / 3.0).solve().get("x");
        double negative = singleVariable("x", -1.0 / 3.0).solve().get("x");
        // Negating the input should only flip the sign, never change the magnitude.
        assertEquals(Math.abs(positive), Math.abs(negative), 1e-12,
                "a value and its negation should have equal magnitude after rounding");
    }
}
