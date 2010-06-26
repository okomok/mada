

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package minihlisttest

import com.github.okomok.mada

object Mini {

    sealed abstract class HList[A] {
        //type Element = A

        type self <: HList[A]
        def self: self

        type head <: A
        def head: head

        type tail <: HList[A]
        def tail: tail

        final type addFirst[e <: A] = HCons[A, e, self]
        final def ::[e <: A](_e: e): addFirst[e] = new HCons[A, e, self](_e, self)
    }

    sealed abstract class HNil[A] extends HList[A] {
        override type self = HNil[A]
        override def self = this

        type head = Nothing
        def head = throw new Error

        type tail = Nothing
        def tail = throw new Error
    }

    final case class HCons[A, h <: A, t <: HList[A]](override val head: h, override val tail: t) extends HList[A] {
        override type self = HCons[A, h, t]
        override def self = this

        override type head = h
        override type tail = t
    }

    class OperatorOf[A] {
        type HNil = Mini.HNil[A]
        type ::[h <: A, t <: HList[A]] =
            //HCons[A, h, t#self] // ok
            HCons[A, h, t] // ok
            //t#addFirst[h] // no
        val HNil: Mini.HNil[A] = new Mini.HNil[A]{}
    }

    //type ::[h <: t#Element, t <: HList[_]] = t#addFirst[h] // doesn't work.
}


class MiniHListTest extends junit.framework.TestCase {

    import junit.framework.Assert._
//    import Mini._
    import mada.meta

    def testTrivial1 {
        val ops = new Mini.OperatorOf[AnyVal]

        import ops._
        type nil = HNil
        val nil: nil = HNil
        type aList = Int :: Char :: HNil
        val aList: aList = 3 :: 'a' :: HNil
    }

}

