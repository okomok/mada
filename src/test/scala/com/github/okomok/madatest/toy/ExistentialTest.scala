

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy


import junit.framework.Assert._


import java.lang.Integer
import java.lang.Character


// See: http://www.haskell.org/haskellwiki/Existential_types
class ExistentialTezt {

    class Show[T <: AnyRef](val me: T)

    type Obj = Show[T] forSome { type T }
    type Obj2 = Show[_] // also ok.

    def xs: Array[Obj2] = Array(new Show(new Integer(1)), new Show("foo"), new Show(new Character('c')))

    def xsToString: String = {
        val sb = new StringBuilder
        for (x <- xs)
            sb.append(x.me.toString)
        sb.toString
    }

    def testTrivial {
        assertEquals("1fooc", xsToString)
    }

    trait Expr[A]
    class Val[A](a: A) extends Expr[A]
    class ApplyImpl[A, B](x: Expr[B => A], y: Expr[B]) extends Expr[A]
    type Apply[A] = ApplyImpl[A, _]
}
