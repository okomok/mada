

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package null_


import junit.framework.Assert._


// See: http://www.haskell.org/haskellwiki/Existential_types
class NullTest {
    def testMatch: Unit = {
        assertFalse(null == 10)
        assertFalse(null.isInstanceOf[String])
        assertFalse(null.asInstanceOf[String].isInstanceOf[String])

        null.asInstanceOf[String] match {
            case s: String => fail("doh")
            case null => // println("wow")
        }
        // BTW, Any.== passes null to Any.equals.
        ()
    }

    def testAsInstanceOf: Unit = {
        null.asInstanceOf[String] // ok cuz String <: AnyRef.
        // AssertThrows(null.asInstanceOf[Int]) // should throw due to SLS-6.3?
        // assertTrue(10, null.asInstanceOf[Int] + 10) // ok but probably unspecified.
    }
}
