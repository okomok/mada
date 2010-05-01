

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toytest; package printftest


import com.github.okomok.mada

// See: http://blog.rafaelferreira.net/2009/10/type-safe-printf-in-scala.html
//      http://paste.pocoo.org/show/144292/


import mada.blend._


abstract class FChain[ES <: List] {outer =>
    def ::(constant:String) = new FConstant[ES](constant, this)
    def ::[H](formatter: Formatter[H]) = new FCons[H, ES](formatter, this)
    def format(elements: ES):String
}

case class FConstant[ES <: List](constant:String, tail: FChain[ES]) extends FChain[ES] {
    def format(elements: ES):String = constant + tail.format(elements)
}

object FNil extends FChain[Nil] {outer =>
    def format(elements: Nil):String = ""
}

case class FCons[H, ES <: List](formatter: Formatter[H], tail: FChain[ES]) extends FChain[Cons[H, ES]] {
    def format(elements: Cons[H,ES]):String = formatter.formatElement(elements.head) + tail.format(elements.tail)

}

trait Formatter[T] {
    def formatElement(o:Any):String
}

class IntFormatter extends Formatter[Int] {
    def formatElement(o:Any) = "i="+o
}

class BooleanFormatter extends Formatter[Boolean] {
    def formatElement(o:Any) = "b="+o
}


class PrintfTest {

    import junit.framework.Assert._

    def testTrivial: Unit = {
        val str = (new IntFormatter :: " and " :: new BooleanFormatter :: FNil) format (133 :: false :: Nil)
        assertEquals("i=133 and b=false", str)
    }

}

