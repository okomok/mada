

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


@specializer
sealed abstract class Typed[xs <: List] extends (sequence.List[Any] => xs)


object Typed {

    implicit val _ofNil = new Typed[Nil] {
        override def apply(xs: sequence.List[Any]) = Nil
    }

    implicit def _ofCons[a, as <: List](implicit _typed: Typed[as]) = new Typed[Cons[a, as]] {
        override def apply(xs: sequence.List[Any]) = Cons(xs.head.asInstanceOf[a], _typed(xs.tail))
    }

}
