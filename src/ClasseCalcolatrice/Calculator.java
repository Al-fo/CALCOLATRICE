package ClasseCalcolatrice;

public abstract class Calculator {
    public static double evaluate(String expression){
        expression = expression.replace(" ", "");
        while(expression.contains("*") || expression.contains("/")){
            int next = 0;
            for(int i = next; i < expression.length(); i++){
                switch(expression.charAt(i)){
                    case '+': case '-':
                        next = i;
                        break;
                    case '*':{
                        String sub = null;
                        for(int j = i + 1; j < expression.length(); j++){
                            if(expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*' || expression.charAt(j) == '/'){
                                sub = expression.substring(next + 1, j);
                                break;
                            }
                        }
                        if(sub == null) sub = next == 0 ? expression.substring(next, expression.length()) : 
                                        expression.substring(next + 1, expression.length());

                        double fact1 = next != 0 ? Double.parseDouble(sub.substring(0, i - next - 1)) : 
                                        Double.parseDouble(sub.substring(0, i));
                        double fact2 = next != 0 ? Double.parseDouble(sub.substring(i - next, sub.length())) : 
                                        Double.parseDouble(sub.substring(i + 1, sub.length()));
                        String product = hasDecimals((Double)(fact1 * fact2)) ? Double.toString(fact1 * fact2) : 
                                        Integer.toString(((Double)(fact1 * fact2)).intValue());
                        expression = expression.replace(sub, product);
                        break;
                    }
                    case '/':{
                        String sub = null;
                        for(int j = i + 1; j < expression.length(); j++){
                            if(expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*' || expression.charAt(j) == '/'){
                                sub = expression.substring(next + 1, j);
                                break;
                            }
                        }
                        if(sub == null) sub = next == 0 ? expression.substring(next, expression.length()) : 
                                        expression.substring(next + 1, expression.length());

                        double fact1 = next != 0 ? Double.parseDouble(sub.substring(0, i - next - 1)) : 
                                        Double.parseDouble(sub.substring(0, i));
                        double fact2 = next != 0 ? Double.parseDouble(sub.substring(i - next, sub.length())) : 
                                        Double.parseDouble(sub.substring(i + 1, sub.length()));
                        String division = hasDecimals((Double)(fact1 / fact2)) ? Double.toString(fact1 / fact2) : 
                                        Integer.toString(((Double)(fact1 / fact2)).intValue());
                        expression = expression.replace(sub, division);
                        break;
                    }
                }
            }
        }
        while(expression.contains("+") || expression.contains("-")){
            String fact1 = "";
            operation:{
                for(int i = 0; i < expression.length(); i++){
                    switch(expression.charAt(i)){
                        case '+':{
                            String fact2 = "";
                            for(int j = i + 1; j < expression.length(); j++){
                                if(expression.charAt(j) == '+' || expression.charAt(j) == '-') break;
                                fact2 += expression.charAt(j);
                            }
                            String sub = fact1 + "+" + fact2;
                            String addition = hasDecimals((Double.parseDouble(fact1) + Double.parseDouble(fact2))) ? Double.toString(Double.parseDouble(fact1) + Double.parseDouble(fact2)) : 
                                                Integer.toString(((Double)(Double.parseDouble(fact1) + Double.parseDouble(fact2))).intValue());
                            expression = expression.replace(sub, addition);
                            break operation;
                        }
                        case '-':{
                            String fact2 = "";
                            for(int j = i+1; j < expression.length(); j++){
                                if(expression.charAt(j) == '+' || expression.charAt(j) == '-') break;
                                fact2 += expression.charAt(j);
                            }
                            String sub = fact1 + "-" + fact2;
                            String substration = hasDecimals((Double.parseDouble(fact1) - Double.parseDouble(fact2))) ? Double.toString(Double.parseDouble(fact1) - Double.parseDouble(fact2)) : 
                                                Integer.toString(((Double)(Double.parseDouble(fact1) - Double.parseDouble(fact2))).intValue());
                            expression = expression.replace(sub, substration);
                            break operation;
                        }
                        default: fact1 += expression.charAt(i);
                    }
                }
            }
        }
        return Double.parseDouble(expression);
    }

    private static boolean hasDecimals(Double n){
        return n.intValue() != n;
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2/2"));
    }
}
