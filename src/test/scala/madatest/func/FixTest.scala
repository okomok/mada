

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.func


import mada.Functions
import junit.framework.Assert._

// See: That about wraps it up --- Using FIX to handle errors without exceptions, and other programming tricks (1997)
//  at http://citeseer.ist.psu.edu/51062.html

class FixTest {
    class R(val i: Int)

    def testTrivial: Unit = {
        val inc: (Int => R) => Int => R
            = (f: Int => R) => (x: Int) =>
                new R(x + 1)

        val wrap: ((Int => R) => (Int => R)) => (String => Int => R) => String => Int => R
            = (f: (Int => R) => (Int => R)) => (f_ : String => Int => R) => (extra: String) => (p: Int) =>
                f(f_(extra))(p)

        val wrap_inc: (String => Int => R) => String => Int => R
            = wrap(inc)

        val fix_wrap_inc: String => Int => R
            = Functions.fix(wrap_inc)

        assertEquals(11, fix_wrap_inc("hello")(10).i)
    }
}
