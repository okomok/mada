

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada._
import mada.Peg.Compatibles._
import junit.framework.Assert._


class IterableTest {
    def testTrivial: Unit = {
        val p = Peg.from(Iterators.toIterable(Iterator.fromValues('a','b','c')))
        assertTrue("123" >> p >> "XYZ" matches "123abcXYZ")
        assertTrue("123" >> p matches "123abc")
        assertFalse("123" >> p matches "123ab")
        assertFalse("123" >> p >> "XYZ" matches "123aBcXYZ")
        assertTrue("123" >> -p >> "XYZ" matches "123PPPXYZ")
    }

    def testEmpty: Unit = {
        val p = Peg.from(Iterators.toIterable[Char](Iterator.empty))
        assertTrue("123" >> p >> "XYZ" matches "123XYZ")
        assertFalse("123" >> p >> "XYZ" matches "123aXYZ")
    }
}