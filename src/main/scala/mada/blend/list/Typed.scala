

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Typed[l <: List] extends (sequence.List[Any] => l)


object Typed {
    implicit val ofNil = new Typed[Nil] {
        override def apply(_l: sequence.List[Any]) = Nil
    }

    implicit def ofCons[h, t <: List](implicit _typed: Typed[t]) = new Typed[Cons[h, t]] {
        override def apply(_l: sequence.List[Any]) = Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
    }
}
