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
            throw new TestFailedException(a + " does not equal " + b);
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