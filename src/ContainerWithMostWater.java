import java.util.ArrayList;
import java.util.List;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] h = {1, 1};
        Test.assertEquals(1, maxArea(h));
    }

    public static int maxArea(int[] height) {
        List<Pole> poles = new ArrayList<>();
        poles.add(new Pole(0, height[0]));
        int maxArea = 0;
        for (int i = 1; i < height.length; i++) {
            Pole p = new Pole(i, height[i]);
            for (Pole leftPole : poles) {
                maxArea = Math.max(Math.min(leftPole.y, p.y) * (p.x - leftPole.x), maxArea);
            }
            if (height[i] > poles.get(poles.size() - 1).y) {
                poles.add(p);
            }
        }
        return maxArea;
    }
}

class Pole {
    Pole(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x, y;
}
