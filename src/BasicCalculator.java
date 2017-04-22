import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/basic-calculator/#/description
 */
public class BasicCalculator {

    public static void main(String[] args) {

        Map<String, Integer> t = new HashMap<>();
        t.put("1 + 1", 2);
        t.put(" 2-1 + 2 ", 3);
        t.put("(1+(4+5+2)-3)+(6+8)", 23);
        t.put("-3 - 5", -8);
        t.put("4-(5+4)", -5);

        for (Entry<String, Integer> test : t.entrySet()) {
            int result = new BasicCalculator().calculate(test.getKey());
            if (result != test.getValue()) {
                System.out.println("test failed '" + test.getKey() + "' expected " + test.getValue() + " but got " + result);
            } else {
                System.out.println("test succeeded: " + test.getKey() + " = " + result);
            }
        }
    }

    /*

    "1 + 1" = 2
    " 2-1 + 2 " = 3
    "(1+(4+5+2)-3)+(6+8)" = 23

     */
    public int calculate(String s) {
        s = s + ")";
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray())
            list.add(c);

        return bracket(list.iterator());
    }

    public int bracket(Iterator<Character> s) {
        int sum = 0;
        int factor = 1;
        StringBuilder summand = new StringBuilder();

        while (s.hasNext()) {
            char c = s.next();
            switch (c) {
                case ' ':
                    // ignore
                    break;
                case ')':
                    if (summand.length() > 0)
                        sum += factor * Integer.parseInt(summand.toString());
                    return sum;
                case '+':
                    if (summand.length() > 0) {
                        sum += factor * Integer.parseInt(summand.toString());
                        summand = new StringBuilder();
                        factor = 1;
                    }
                    break;
                case '-':
                    if (summand.length() > 0) {
                        sum += factor * Integer.parseInt(summand.toString());
                        summand = new StringBuilder();
                        factor = -1;
                    } else {
                        factor *= -1;
                    }
                    break;
                case '(':
                    sum += factor * bracket(s);
                    summand = new StringBuilder();
                    factor = 1;
                    break;
                default:
                    summand.append(c);
            }
        }
        return sum;
    }
}
