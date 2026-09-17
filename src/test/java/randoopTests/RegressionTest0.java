package randoopTests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        ar.edu.unrc.game2048.Board board0 = null;
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Board board1 = new ar.edu.unrc.game2048.Board(board0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"size\" because \"other\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        int int0 = ar.edu.unrc.game2048.Board.DEFAULT_SIZE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 4 + "'", int0 == 4);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        ar.edu.unrc.game2048.Board.Direction direction0 = ar.edu.unrc.game2048.Board.Direction.UP;
        org.junit.Assert.assertTrue("'" + direction0 + "' != '" + ar.edu.unrc.game2048.Board.Direction.UP + "'", direction0.equals(ar.edu.unrc.game2048.Board.Direction.UP));
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        ar.edu.unrc.game2048.Board.Direction direction0 = ar.edu.unrc.game2048.Board.Direction.RIGHT;
        org.junit.Assert.assertTrue("'" + direction0 + "' != '" + ar.edu.unrc.game2048.Board.Direction.RIGHT + "'", direction0.equals(ar.edu.unrc.game2048.Board.Direction.RIGHT));
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        int int0 = ar.edu.unrc.game2048.Board.WINNING_VALUE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 2048 + "'", int0 == 2048);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board(board9);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        int int10 = board9.getSize();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 4 + "'", int10 == 4);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        java.lang.Class<?> wildcardClass9 = deterministicRandom8.getClass();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(wildcardClass9);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        boolean boolean11 = board9.hasEmptyCells();
        boolean boolean12 = board9.moveRight();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position(2048, 0);
        int int3 = position2.row;
        int int4 = position2.row;
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 2048 + "'", int3 == 2048);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 2048 + "'", int4 == 2048);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position((int) ' ', (int) (byte) 100);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.String str1 = cell0.toString();
        ar.edu.unrc.game2048.Cell cell2 = ar.edu.unrc.game2048.Cell.EMPTY;
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell3 = cell0.mergeWith(cell2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cannot merge cells: . and .");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "." + "'", str1, ".");
        org.junit.Assert.assertNotNull(cell2);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        ar.edu.unrc.game2048.Board.Direction direction0 = ar.edu.unrc.game2048.Board.Direction.LEFT;
        org.junit.Assert.assertTrue("'" + direction0 + "' != '" + ar.edu.unrc.game2048.Board.Direction.LEFT + "'", direction0.equals(ar.edu.unrc.game2048.Board.Direction.LEFT));
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveRight();
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell14 = board10.getCell((int) ' ', 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Position (32, 1) is out of bounds for board size 1");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        ar.edu.unrc.game2048.strategy.MoveStrategy moveStrategy11 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean12 = board9.move(moveStrategy11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"ar.edu.unrc.game2048.strategy.MoveStrategy.extractLine(ar.edu.unrc.game2048.Board, int)\" because \"moveStrategy\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        java.lang.String str11 = board9.toString();
        boolean boolean12 = board9.moveRight();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str11, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        int int1 = cell0.getValue();
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        int int11 = board10.getScore();
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell14 = board10.getCell(0, 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Position (0, 10) is out of bounds for board size 1");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        boolean boolean12 = board9.equals((java.lang.Object) (short) 1);
        int int13 = board9.getScore();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        boolean boolean11 = board9.isFull();
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell14 = board9.getCell((int) '#', 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Position (35, 0) is out of bounds for board size 4");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position(2048, 0);
        java.lang.String str3 = position2.toString();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "(2048, 0)" + "'", str3, "(2048, 0)");
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        ar.edu.unrc.game2048.Board.Direction direction0 = ar.edu.unrc.game2048.Board.Direction.DOWN;
        java.lang.Class<?> wildcardClass1 = direction0.getClass();
        org.junit.Assert.assertTrue("'" + direction0 + "' != '" + ar.edu.unrc.game2048.Board.Direction.DOWN + "'", direction0.equals(ar.edu.unrc.game2048.Board.Direction.DOWN));
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        java.util.Set<ar.edu.unrc.game2048.Board.Position> positionSet11 = board9.getEmptyPositions();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(positionSet11);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveUp();
        boolean boolean12 = board10.moveLeft();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        int int10 = deterministicRandom9.nextRandom();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 100 + "'", int10 == 100);
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        boolean boolean2 = cell0.equals((java.lang.Object) 100);
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        boolean boolean11 = board9.isFull();
        boolean boolean12 = board9.moveUp();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position(4, (int) ' ');
        int int3 = position2.row;
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 4 + "'", int3 == 4);
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.Integer[] intArray7 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList8 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList8, intArray7);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom10 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList8);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        int int12 = deterministicRandom10.nextRandom();
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        boolean boolean14 = cell0.equals((java.lang.Object) deterministicRandom10);
        ar.edu.unrc.game2048.Cell cell15 = ar.edu.unrc.game2048.Cell.EMPTY;
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell16 = cell0.mergeWith(cell15);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cannot merge cells: . and .");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertNotNull(intArray7);
        org.junit.Assert.assertArrayEquals(intArray7, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(cell15);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveUp();
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell14 = board10.getCell((int) (byte) 10, (int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Position (10, 1) is out of bounds for board size 1");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position(2048, 0);
        boolean boolean4 = position2.equals((java.lang.Object) "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        int int5 = position2.col;
        java.lang.Integer[] intArray11 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList12 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList12, intArray11);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom14 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList12);
        ar.edu.unrc.game2048.Board board15 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom14);
        boolean boolean16 = board15.moveRight();
        boolean boolean17 = position2.equals((java.lang.Object) board15);
        int int18 = position2.col;
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertNotNull(intArray11);
        org.junit.Assert.assertArrayEquals(intArray11, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 0 + "'", int18 == 0);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell1 = new ar.edu.unrc.game2048.Cell((int) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cell value cannot be negative: 100");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.String str1 = cell0.toString();
        ar.edu.unrc.game2048.Cell cell2 = ar.edu.unrc.game2048.Cell.EMPTY;
        boolean boolean3 = cell0.canMergeWith(cell2);
        boolean boolean5 = cell0.equals((java.lang.Object) true);
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "." + "'", str1, ".");
        org.junit.Assert.assertNotNull(cell2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveUp();
        int int12 = board10.getScore();
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell15 = board10.getCell((int) (byte) 0, 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Position (0, 1) is out of bounds for board size 1");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (short) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveDown();
        java.lang.String str12 = board10.toString();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Score: 0\n+-----+\n|    2|\n+-----+\n" + "'", str12, "Score: 0\n+-----+\n|    2|\n+-----+\n");
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        boolean boolean11 = board9.hasEmptyCells();
        boolean boolean12 = board9.moveUp();
        int int13 = board9.getSize();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 4 + "'", int13 == 4);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        int int11 = deterministicRandom9.nextRandom();
        int int12 = deterministicRandom9.nextRandom();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 10 + "'", int12 == 10);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        java.lang.String str11 = board9.toString();
        java.lang.String str12 = board9.toString();
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board(board9);
        boolean boolean14 = board13.moveLeft();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str11, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str12, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        java.lang.String str11 = board9.toString();
        java.lang.String str12 = board9.toString();
        boolean boolean13 = board9.moveUp();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str11, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str12, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        java.lang.Integer[] intArray7 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList8 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList8, intArray7);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom10 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList8);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        ar.edu.unrc.game2048.Board board12 = new ar.edu.unrc.game2048.Board((int) 'a', (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        int int13 = board12.getSize();
        boolean boolean14 = board12.moveDown();
        org.junit.Assert.assertNotNull(intArray7);
        org.junit.Assert.assertArrayEquals(intArray7, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 97 + "'", int13 == 97);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        java.lang.Integer[] intArray7 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList8 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList8, intArray7);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom10 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList8);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Board board12 = new ar.edu.unrc.game2048.Board(0, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Board size must be positive: 0");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray7);
        org.junit.Assert.assertArrayEquals(intArray7, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.Integer[] intArray7 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList8 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList8, intArray7);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom10 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList8);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        int int12 = deterministicRandom10.nextRandom();
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        boolean boolean14 = cell0.equals((java.lang.Object) deterministicRandom10);
        boolean boolean15 = cell0.isEmpty();
        java.lang.String str16 = cell0.toString();
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertNotNull(intArray7);
        org.junit.Assert.assertArrayEquals(intArray7, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "." + "'", str16, ".");
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        java.lang.Integer[] intArray7 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList8 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList8, intArray7);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom10 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList8);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        ar.edu.unrc.game2048.Board board12 = new ar.edu.unrc.game2048.Board((int) 'a', (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom10);
        int int13 = board12.getSize();
        boolean boolean14 = board12.moveLeft();
        org.junit.Assert.assertNotNull(intArray7);
        org.junit.Assert.assertArrayEquals(intArray7, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 97 + "'", int13 == 97);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position((int) (byte) 0, 10);
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test47");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (short) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        int int11 = deterministicRandom9.nextRandom();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test48");
        ar.edu.unrc.game2048.Cell cell1 = new ar.edu.unrc.game2048.Cell(2048);
        ar.edu.unrc.game2048.Cell cell2 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.String str3 = cell2.toString();
        ar.edu.unrc.game2048.Cell cell4 = ar.edu.unrc.game2048.Cell.EMPTY;
        boolean boolean5 = cell2.canMergeWith(cell4);
        ar.edu.unrc.game2048.Cell cell7 = new ar.edu.unrc.game2048.Cell((int) (byte) 1);
        boolean boolean8 = cell4.canMergeWith(cell7);
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Cell cell9 = cell1.mergeWith(cell7);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Cannot merge cells: 2048 and 1");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(cell2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "." + "'", str3, ".");
        org.junit.Assert.assertNotNull(cell4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test49");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.hasEmptyCells();
        boolean boolean12 = board9.equals((java.lang.Object) (short) 1);
        boolean boolean13 = board9.moveRight();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test50");
        java.lang.Integer[] intArray8 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList9 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList9, intArray8);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom11 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList9);
        ar.edu.unrc.game2048.Board board12 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board((int) 'a', (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
        ar.edu.unrc.game2048.Board board14 = new ar.edu.unrc.game2048.Board((int) (byte) 10, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
        org.junit.Assert.assertNotNull(intArray8);
        org.junit.Assert.assertArrayEquals(intArray8, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test51");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (short) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        ar.edu.unrc.game2048.strategy.MoveStrategy moveStrategy12 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean13 = board11.move(moveStrategy12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"ar.edu.unrc.game2048.strategy.MoveStrategy.extractLine(ar.edu.unrc.game2048.Board, int)\" because \"moveStrategy\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test52");
        java.lang.Integer[] intArray8 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList9 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList9, intArray8);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom11 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList9);
        ar.edu.unrc.game2048.Board board12 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board((int) 'a', (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
        // The following exception was thrown during execution in test generation
        try {
            ar.edu.unrc.game2048.Board board14 = new ar.edu.unrc.game2048.Board((int) (byte) -1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom11);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Board size must be positive: -1");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray8);
        org.junit.Assert.assertArrayEquals(intArray8, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test53");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        java.lang.String str11 = board9.toString();
        int int12 = board9.getSize();
        boolean boolean13 = board9.hasEmptyCells();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str11, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 4 + "'", int12 == 4);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test54");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveRight();
        boolean boolean12 = board10.moveUp();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test55");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((int) (byte) 1, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean11 = board10.moveRight();
        boolean boolean12 = board10.moveRight();
        java.lang.String str13 = board10.toString();
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "Score: 0\n+-----+\n|    2|\n+-----+\n" + "'", str13, "Score: 0\n+-----+\n|    2|\n+-----+\n");
    }

    @Test
    public void test56() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test56");
        java.lang.Integer[] intArray5 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList6 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList6, intArray5);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom8 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList6);
        ar.edu.unrc.game2048.Board board9 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom8);
        boolean boolean10 = board9.moveRight();
        java.lang.String str11 = board9.toString();
        java.lang.String str12 = board9.toString();
        ar.edu.unrc.game2048.Board board13 = new ar.edu.unrc.game2048.Board(board9);
        boolean boolean14 = board13.isFull();
        org.junit.Assert.assertNotNull(intArray5);
        org.junit.Assert.assertArrayEquals(intArray5, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str11, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n" + "'", str12, "Score: 0\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |    2|\n+-----+-----+-----+-----+\n|     |    2|     |    2|\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test57() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test57");
        ar.edu.unrc.game2048.Cell cell0 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.String str1 = cell0.toString();
        ar.edu.unrc.game2048.Cell cell2 = ar.edu.unrc.game2048.Cell.EMPTY;
        boolean boolean3 = cell0.canMergeWith(cell2);
        ar.edu.unrc.game2048.Cell cell4 = ar.edu.unrc.game2048.Cell.EMPTY;
        java.lang.String str5 = cell4.toString();
        ar.edu.unrc.game2048.Cell cell6 = ar.edu.unrc.game2048.Cell.EMPTY;
        boolean boolean7 = cell4.canMergeWith(cell6);
        ar.edu.unrc.game2048.Cell cell9 = new ar.edu.unrc.game2048.Cell((int) (byte) 1);
        boolean boolean10 = cell6.canMergeWith(cell9);
        boolean boolean12 = cell6.equals((java.lang.Object) (short) 1);
        boolean boolean13 = cell2.canMergeWith(cell6);
        org.junit.Assert.assertNotNull(cell0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "." + "'", str1, ".");
        org.junit.Assert.assertNotNull(cell2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(cell4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "." + "'", str5, ".");
        org.junit.Assert.assertNotNull(cell6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test58() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test58");
        ar.edu.unrc.game2048.Board.Position position2 = new ar.edu.unrc.game2048.Board.Position(4, (int) ' ');
        int int3 = position2.col;
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 32 + "'", int3 == 32);
    }

    @Test
    public void test59() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test59");
        ar.edu.unrc.game2048.Cell cell1 = new ar.edu.unrc.game2048.Cell((int) (short) 1);
    }

    @Test
    public void test60() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test60");
        java.lang.Integer[] intArray6 = new java.lang.Integer[] { 100, 1, 1, 10, 1 };
        java.util.ArrayList<java.lang.Integer> intList7 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList7, intArray6);
        ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom deterministicRandom9 = new ar.edu.unrc.game2048.RandomNumGeneration.DeterministicRandom((java.util.List<java.lang.Integer>) intList7);
        ar.edu.unrc.game2048.Board board10 = new ar.edu.unrc.game2048.Board((ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        ar.edu.unrc.game2048.Board board11 = new ar.edu.unrc.game2048.Board(2048, (ar.edu.unrc.game2048.RandomNumGeneration.IRandom) deterministicRandom9);
        boolean boolean13 = board11.equals((java.lang.Object) 10);
        org.junit.Assert.assertNotNull(intArray6);
        org.junit.Assert.assertArrayEquals(intArray6, new java.lang.Integer[] { 100, 1, 1, 10, 1 });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }
}

