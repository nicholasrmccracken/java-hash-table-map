import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Nicholas McCracken and Jack Mikesell
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /*
     * Test cases for constructors
     */

    /**
     * Test no argument constructor with no arguments.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> map = this.constructorTest();
        Map<String, String> mapExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for kernel methods
     */

    /**
     * Test add by adding one pair when map is initialized as empty.
     */
    @Test
    public final void testAddEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("red",
                "light");
        /*
         * Call method under test
         */
        map.add("red", "light");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Test add by adding multiple pairs when map is initialized as empty.
     */
    @Test
    public final void testAddEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        map.add("red", "light");
        map.add("blue", "dark");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Test add by adding one pair to a non empty map.
     */
    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        map.add("blue", "dark");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Test add by adding multiple pairs to a non empty map.
     */
    @Test
    public final void testAddNonEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark", "green", "light");
        /*
         * Call method under test
         */
        map.add("blue", "dark");
        map.add("green", "light");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Test remove by removing the only pair which will make a non empty map
     * empty.
     */
    @Test
    public final void testRemoveEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        Map<String, String> mapExpected = this.createFromArgsRef("red",
                "light");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element = map.remove("red");
        Map.Pair<String, String> elementExpected = mapExpected.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(map, mapExpected);
        assertEquals(elementExpected, element);
    }

    /**
     * Test remove by removing multiple pairs which will make a non empty map
     * empty.
     */
    @Test
    public final void testRemoveEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element1 = map.remove("blue");
        Map.Pair<String, String> elementExpected1 = mapExpected.remove("blue");
        Map.Pair<String, String> element2 = map.remove("red");
        Map.Pair<String, String> elementExpected2 = mapExpected.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
        assertEquals(elementExpected1, element1);
        assertEquals(elementExpected2, element2);
    }

    /**
     * Test remove by removing one pair from a map that will remain non empty.
     */
    @Test
    public final void testRemoveNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element = map.remove("red");
        Map.Pair<String, String> elementExpected = mapExpected.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(map, mapExpected);
        assertEquals(elementExpected, element);
    }

    /**
     * Test remove by removing multiple pairs from a map that will remain non
     * empty.
     */
    @Test
    public final void testRemoveNonEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark", "green", "bright");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark", "green", "bright");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element1 = map.remove("blue");
        Map.Pair<String, String> elementExpected1 = mapExpected.remove("blue");
        Map.Pair<String, String> element2 = map.remove("green");
        Map.Pair<String, String> elementExpected2 = mapExpected.remove("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
        assertEquals(elementExpected1, element1);
        assertEquals(elementExpected2, element2);
    }

    /**
     * Test removeAny by removing the only pair from a non empty map thereby
     * making it empty.
     */
    @Test
    public final void testRemoveAnyEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        Map<String, String> mapExpected = this.createFromArgsRef("red",
                "light");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element = map.removeAny();
        Map.Pair<String, String> elementExpected = mapExpected.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
        assertEquals(elementExpected, element);
    }

    /**
     * Test removeAny by removing multiple pairs from a non empty map thereby
     * making it empty.
     */
    @Test
    public final void testRemoveAnyEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        map.removeAny();
        map.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Test removeAny by removing a random pair from a non empty map.
     */
    @Test
    public final void testRemoveAnyNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element = map.removeAny();
        /*
         * Assert that removed element is contained in expected set
         */
        assertTrue(mapExpected.hasKey(element.key()));
        /*
         * Assert that values of variables match expectations
         */
        mapExpected.remove(element.key());
        assertEquals(mapExpected, map);
    }

    /**
     * Test removeAny by removing multiple random pairs from a non empty map.
     */
    @Test
    public final void testRemoveAnyNonEmptyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark", "green", "bright");
        Map<String, String> mapExpected = this.createFromArgsRef("red", "light",
                "blue", "dark", "green", "bright");
        /*
         * Call method under test
         */
        Map.Pair<String, String> element1 = map.removeAny();
        Map.Pair<String, String> element2 = map.removeAny();
        /*
         * Assert that removed element is contained in expected set
         */
        assertTrue(mapExpected.hasKey(element1.key()));
        assertTrue(mapExpected.hasKey(element2.key()));
        /*
         * Assert that values of variables match expectations
         */
        mapExpected.remove(element1.key());
        mapExpected.remove(element2.key());
        assertEquals(mapExpected, map);
    }

    /**
     * Test hasKey by checking the only pair available in a map.
     */
    @Test
    public final void testHasKeyAllOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        /*
         * Call method under test
         */
        Boolean contained = map.hasKey("red");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contained);
    }

    /**
     * Test hasKey by checking each of multiple pairs available in a map.
     */
    @Test
    public final void testHasKeyAllMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Boolean contained1 = map.hasKey("red");
        Boolean contained2 = map.hasKey("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contained1);
        assertTrue(contained2);
    }

    /**
     * Test hasKey by checking one of many pairs available in a map.
     */
    @Test
    public final void testHasKeySomeOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Boolean contained = map.hasKey("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contained);
    }

    /**
     * Test hasKey by checking multiple pairs of many pairs available in a map.
     */
    @Test
    public final void testHasKeySomeMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "bright", "blue", "dark");
        /*
         * Call method under test
         */
        Boolean contained1 = map.hasKey("red");
        Boolean contained2 = map.hasKey("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contained1);
        assertTrue(contained2);
    }

    /**
     * Test hasKey by checking for a key that is not in map of multiple keys.
     */
    @Test
    public final void testHasKeySomeFalse() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        Boolean contained = map.hasKey("yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, contained);
    }

    /**
     * Test hasKey by checking for a key that is not in map of one key.
     */
    @Test
    public final void testHasKeyOneFalse() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        /*
         * Call method under test
         */
        Boolean contained = map.hasKey("yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, contained);
    }

    /**
     * Test value by checking the value of the only pair available in a map.
     */
    @Test
    public final void testValueAllOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        /*
         * Call method under test
         */
        String value = map.value("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("light", value);
    }

    /**
     * Test value by checking the value of all of the pairs available in a map.
     */
    @Test
    public final void testValueAllMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        String value1 = map.value("red");
        String value2 = map.value("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("light", value1);
        assertEquals("dark", value2);
    }

    /**
     * Test value by checking the value of one of many pairs available in a map.
     */
    @Test
    public final void testValueSomeOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "blue", "dark");
        /*
         * Call method under test
         */
        String value = map.value("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("dark", value);
    }

    /**
     * Test value by checking the value of multiple pairs of many available in a
     * map.
     */
    @Test
    public final void testValueSomeMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "bright", "blue", "dark");
        /*
         * Call method under test
         */
        String value1 = map.value("green");
        String value2 = map.value("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("bright", value1);
        assertEquals("dark", value2);
    }

    /**
     * Test size by checking the size of an empty map.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int mapLength = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, mapLength);
    }

    /**
     * Test size by checking the size of a map with a single pair.
     */
    @Test
    public final void testSizeOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, mapLength);
    }

    /**
     * Test size by checking the size of a map with a three pairs.
     */
    @Test
    public final void testSizeMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, mapLength);
    }

    /**
     * Test size by checking the if the size of a map with a three pairs stays
     * the same after a specific pair has been removed and readded to the map.
     */
    @Test
    public final void testSizeAddAndRemoveTarget() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int preMapLength = map.size();
        Pair<String, String> removed = map.remove("red");
        map.add(removed.key(), removed.value());
        int postMapLength = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(preMapLength, postMapLength);
    }

    /**
     * Test size by checking the if the size of a map with a three pairs stays
     * the same after a random pair has been removed and readded to the map.
     */
    @Test
    public final void testSizeAddAndRemoveRandom() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int preMapLength = map.size();
        Pair<String, String> removed = map.removeAny();
        map.add(removed.key(), removed.value());
        int postMapLength = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(preMapLength, postMapLength);
    }

    /**
     * Test size by checking the size of a map and then adding a pair to it, and
     * then checking to see if the size was increased.
     */
    @Test
    public final void testSizeAfterAddingOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.add("yellow", "light");
        int mapLengthAfterAdd = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength + 1, mapLengthAfterAdd);
    }

    /**
     * Test size by checking the size of a map and then adding three pairs to
     * it, and then checking to see if the size was increased.
     */
    @Test
    public final void testSizeAfterAddingMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.add("yellow", "light");
        map.add("orange", "light");
        map.add("purple", "light");
        int mapLengthAfterAdd = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength + 3, mapLengthAfterAdd);
    }

    /**
     * Test size by checking the size of a map and then removing a pair, and
     * then checking to see if the size was decreased.
     */
    @Test
    public final void testSizeAfterRemoveOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.remove("green");
        int mapLengthAfterRemove = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength - 1, mapLengthAfterRemove);
    }

    /**
     * Test size by checking the size of a map and then removing three pairs,
     * and then checking to see if the size was decreased.
     */
    @Test
    public final void testSizeAfterRemoveMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.remove("green");
        map.remove("blue");
        map.remove("red");
        int mapLengthAfterRemove = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength - 3, mapLengthAfterRemove);
    }

    /**
     * Test size by checking the size of a map and then removing any pair, and
     * then checking to see if the size was decreased.
     */
    @Test
    public final void testSizeAfterRemoveAnyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.removeAny();
        int mapLengthAfterRemoveAny = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength - 1, mapLengthAfterRemoveAny);
    }

    /**
     * Test size by checking the size of a map and then removing any pair three
     * times, and then checking to see if the size was decreased.
     */
    @Test
    public final void testSizeAfterRemoveAnyMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("red", "light",
                "green", "light", "blue", "light");
        /*
         * Call method under test
         */
        int mapLength = map.size();
        map.removeAny();
        map.removeAny();
        map.removeAny();
        int mapLengthAfterRemoveAny = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapLength - 3, mapLengthAfterRemoveAny);
    }

}
