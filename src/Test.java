/**
 * Helper functions for testing
 */
public class Test {

    static void assertTrue(boolean b) {
        if (!b) {
            throw new TestFailedException();
        }
    }

    static void assertEquals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new TestFailedException(b + " does not equal " + a);
        }
    }

    static void assertEquals(int[] a, int[] b) {
        boolean e = true;
        if (a == null && b == null) {
            e = true;
        } else {
            if (a == null || b == null) {
                e = false;
            }
            if (!e) {
                throw new TestFailedException(b + " does not equal " + a);
            } else {
                assertEquals(a.length, b.length);
                for (int i = 0; i < a.length; i++) {
                    assertEquals(a[i], b[i]);
                }
            }
        }
    }

    static class TestFailedException extends IllegalArgumentException {
        TestFailedException() {
            super();
        }

        TestFailedException(String message) {
            super("Test failed: " + message);
        }
    }
}