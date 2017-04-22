import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/zuma-game/#/description
 */
public class ZumaGame {

    public static void main(String[] args) {

        String board = "WRRBBW";
        String hand = "RB";
        //   System.out.println(new ZumaGame().findMinStep(board, hand));


        board = "WWRRBBWW";
        hand = "WRBRW";

        board = "WW";
        hand = "WRBRW";


        System.out.println(new ZumaGame().findMinStep(board, hand));

        //  System.out.println(new ZumaGame().remove("WWWRRBBB"));
    }


    public int findMinStep(String board, String hand) {
        return recursive(board, hand, 0, new HashMap<String, Integer>());
    }

    public int recursive(String board, String hand, int k, Map<String, Integer> lookup) {

        System.out.println("board: " + board + " hand: " + hand + " k: " + k + " lookup: " + lookup.size());

        String key = board + "_" + hand;
        if (lookup.containsKey(key))
            return lookup.get(key);

        if (board.length() > 0 && hand.length() <= 0) {
            //      System.out.println("fail!!!!!!!");
            lookup.put(key, -1);
            return -1;
        }

        int best = -1;
        for (int i = 0; i < board.length() + 1; i++) {

            String left = board.substring(0, i);
            String right = board.substring(i);

            //       System.out.println("board: " + board + " i: " + i + " left: " + left + " right: " + right);

            for (int j = 0; j < hand.length(); j++) {
                char c = hand.charAt(j);
                String newBoard = left + c + right;
                newBoard = remove(newBoard);
                int solution;
                if (newBoard.length() == 0) {
                    solution = k + 1;
                } else {
                    solution = recursive(newBoard, hand.replaceFirst(c + "", ""), k + 1, lookup);
                }
                if (solution > -1 && (best == -1 || solution < best)) {
                    best = solution;
                }
            }
        }
        lookup.put(key, best);
        return best;
    }

    public String remove(String s) {
        StringBuilder builder = new StringBuilder();
        String chars = s.charAt(0) + "";
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars.charAt(0) == c) {
                chars += c;
            } else {
                if (chars.length() < 3) {
                    builder.append(chars);
                }
                chars = c + "";
            }
        }
        if (chars.length() < 3)
            builder.append(chars);
        String out = builder.toString();
        //   System.out.println("remove, input: " + s + " out: " + out);
        return out;
    }

}
