

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.func


import mada.Functions
import junit.framework.Assert._


class ParameterizeTest {
    def foo(w: Int, h: Int, msg: String): Int = w + h + msg.length

    object width extends Functions.Parameter[Int] {
        override val argument = 10
    }

    object height extends Functions.Parameter[Int] {
        override val argument = 2
    }

    object message extends Functions.Parameter[String] {
        override val argument = "hello"
    }

    val pd = Functions.parameterize(foo _)(width, height, message)
    def namedFoo(ps: Functions.Parameter[_]*) = pd(ps)

    def testTrivial: Unit = {
        assertEquals(3 + 2 + 5, namedFoo(width -> 3))
        assertEquals(10 + 3 + 5, namedFoo(height(3)))
        assertEquals(10 + 2 + 4, namedFoo(message pass "hola"))
        assertEquals(3 + 2 + 2, namedFoo(height, width -> 3, message -> "ya"))
    }
}
