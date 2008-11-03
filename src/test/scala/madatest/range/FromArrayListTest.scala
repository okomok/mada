
package madatest.range


import mada.range._


class FromArrayListTest extends TestCase {
    def applyTest = {
        assertTrue(FromString("abcdefg") == FromArrayList('a','b','c','d','e','f','g'))
        assertTrue(FromString("abcdefg") != FromArrayList('a','X','c','d','e','f','g'))
    }
}
