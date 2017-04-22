import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// https://codefights.com/interview/wjewD7BPuQDhfa5yx/companies/N3sScnJbzdPDQaHPj
public class VisiblePoints {

    public static void main(String[] args) {
        System.out.println(Math.atan2(1, 4));
        System.out.println(Math.atan2(4, 1));
        System.out.println(Math.atan2(4, 0));

        System.out.println(Math.atan2(0, 4));

    }

    double windowAngle = Math.PI / 2;

    int visiblePoints(int[][] points) {

        List<Point> angles = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            angles.add(new Point(x, y));
        }

        final Point referencePoint = new Point(1, 0);

        Collections.sort(angles, new Comparator<Point>() {
            @Override
            public int compare(Point p0, Point p1) {
                double angle0 = clockWiseAngle(referencePoint, p0);
                double angle1 = clockWiseAngle(referencePoint, p1);
                if (angle0 > angle1) {
                    return 1;
                } else if (angle0 < angle1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int n = angles.size();
        for (int i = 0; i < n; i++) {
            angles.add(angles.get(i));
        }

        LinkedList<Point> view = new LinkedList<>();

        int max = 0;
        for (Point angle : angles) {
            view.addLast(angle);
            while (view.size() > n) {
                view.removeFirst();
            }
            double width = Math.abs(clockWiseAngle(view.peekFirst(), view.peekLast()));
            while (width > windowAngle) {
                view.removeFirst();
                width = Math.abs(clockWiseAngle(view.peekFirst(), view.peekLast()));
            }
            max = Math.max(max, view.size());
        }

        return max;
    }

    class Point {
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x, y;
    }

    double clockWiseAngle(Point p0, Point p1) {
        double dot = p0.x * p1.x + p0.y * p1.y;
        double det = p0.x * p1.y - p0.y * p1.x;
        double angle = Math.atan2(det, dot);
        return angle;
    }

}
