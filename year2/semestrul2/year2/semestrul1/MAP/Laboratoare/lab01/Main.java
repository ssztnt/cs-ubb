
import Model.ComplexExpression;
import Model.ExpressionParser;
import Model.NumarComplex;

public class Main {

    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(args);
        ComplexExpression expr = parser.parse();

        if (expr != null) {
            NumarComplex result = expr.execute();
            System.out.println("Rezultatul expresiei este: " + result);
        } else {
            System.err.println("Failed to parse the expression.");
        }
    }
}