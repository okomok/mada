

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package functiontest


import com.github.okomok.mada

import mada.function
import junit.framework.Assert._


class ParameterizeTest extends org.scalatest.junit.JUnit3Suite {

    def foo(w: Int, h: Int, msg: String): Int = w * h + msg.length

    object width extends function.Parameter[Int]
    object height extends function.Parameter[Int] {
       override def argument = 2 // set default value
    }
    case object message extends function.Parameter[String] // `case` makes error-message cute?

    def msg: String = throw new Error // dummy.
    def msg_=(v: String) = message.pass(v)

    val pd = function.parameterize(foo _)(width -> 10, height, message -> "hello") // set default values (not lazy)
    def namedFoo(ps: function.Parameter[_]*) = pd(ps)

    def testTrivial: Unit = {
        assertEquals(10 * 2 + 5, namedFoo())
        assertEquals(3 * 2 + 5, namedFoo(width -> 3))
        assertEquals(10 * 3 + 5, namedFoo(height->3))
        assertEquals(10 * 2 + 4, namedFoo(message pass "hola"))
        assertEquals(10 * 2 + 4, namedFoo(msg="hola"))
        assertEquals(3 * 2 + 2, namedFoo(height, message -> "ya", width -> 3))
    }


    val port = new function.Parameter[Int] { } // must be `val`.
    def bar(msg: String, port: Int): Int = msg.length * port
    def namedBar(ps: function.Parameter[_]*) = function.parameterize2(bar)(message -> "hello", port -> 99)(ps)

    def testShareParam: Unit = {
        assertEquals(5 * 99, namedBar())
        assertEquals(4 * 99, namedBar(message pass "hola"))
        assertEquals(4 * 4, namedBar(port -> 4, message pass "hola"))
    }


    object noDefault extends function.Parameter[Int] // throws if `argument` is called.
    def buz(v: Int): Int = v
    def namedBuz(ps: function.Parameter[_]*) = function.parameterize(buz _)(noDefault)(ps)

    def testLazy: Unit = {
        assertEquals(10, namedBuz(noDefault -> 10))
    }
}
