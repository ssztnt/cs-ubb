package Model;
import java.util.Objects;
import Factory.ExpressionFactory;
import Enum.Operatie;

public class ExpressionParser {
    private final String[] args;

    public ExpressionParser(String[] args) {
        this.args = args;
    }

    public ComplexExpression parse() {
        String op = args[1];
        NumarComplex[] argss = new NumarComplex[(args.length + 1) / 2];
        for (int i = 0; i < args.length; i++) { // pe pozitii pare sunt nr complexe, impare operanzii
            if (i % 2 == 0) { // nr complex
                int negativ = 0;
                String[] el = args[i].split("[+]", 2);
                if (el.length != 2) {
                    el = args[i].split("-", 2);
                    negativ = 1;
                }
                double re = Double.parseDouble(el[0]);
                String[] IM = el[1].split("[*]", 2);
                double im = Double.parseDouble((IM[0]));
                if (negativ == 1) {
                    im = -im;
                }
                NumarComplex nr = new NumarComplex(re, im);
                argss[i / 2] = nr;
            } else {
                op = args[i];
                if (!Objects.equals(op, "+") && !Objects.equals(op, "-") && !Objects.equals(op, "*") && !Objects.equals(op, "/")) {
                    return null;
                }
            }
        }

        return switch (op) {
            case "+" -> ExpressionFactory.getInstance().createExpression(Operatie.ADDITION, argss);
            case "-" -> ExpressionFactory.getInstance().createExpression(Operatie.SUBSTRACT, argss);
            case "*" -> ExpressionFactory.getInstance().createExpression(Operatie.MULTIPLY, argss);
            case "/" -> ExpressionFactory.getInstance().createExpression(Operatie.DIVIDE, argss);
            default -> null;
        };
    }
}
