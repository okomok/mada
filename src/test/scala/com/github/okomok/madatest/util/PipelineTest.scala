

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package utiltest


import com.github.okomok.mada

import mada.util
import junit.framework.Assert._


class PipelineTest {

    def foo(x: Int)(s: String): Char = 'a'
    def bar(x: Char): String = "abc"

    def testPipe: Unit = {
        import util.|>
        assertEquals("abc", "hello" |> foo(3) |> bar)
        assertEquals((), "hello" |> util.ignore)
    }

    /*
    sealed class ForwardComposition[A, B](f: A => B) {
        def >>[C](g: B => C)(x: A): C = g(f(x))
    }
    implicit def forwardComposition[A, B](f: A => B): ForwardComposition[A, B] = new ForwardComposition(f)
    */
    def testComposition: Unit = {
//        import util.forwardComposition
//        assertEquals("abc", (foo(3)_ >> bar)("buz"))
        assertEquals("abc", (foo(3)_ andThen bar)("buz"))
    }

}
