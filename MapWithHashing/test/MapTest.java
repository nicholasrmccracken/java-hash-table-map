import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

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

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * Test cases for constructors
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

}
