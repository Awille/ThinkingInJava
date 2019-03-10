package io;

public class Test1 {
    private static int b = 1;
    private static int testFinally() {
        int a = 1;
        try {
            long b = Long.parseLong("xxx");
            a = 2;
            return a;
        } catch (NumberFormatException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
        }
    }

    private static int testFinally2() {
        try {
            long c = Long.parseLong("xxx");
            b = 2;
            return b;
        } catch (NumberFormatException e) {
            b = 3;
            return b;
        } finally {
            b = 4;
        }
    }

    public static void main(String[] args) {
        System.out.println(testFinally());
        System.out.println(testFinally2());
        System.out.println(b);
    }
}
