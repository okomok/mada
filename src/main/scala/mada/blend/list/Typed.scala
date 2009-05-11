

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait Typed[l <: List] extends (scala.List[Any] => l)


object Typed {
    implicit val ofNil = new Typed[Nil] {
        override def apply(_l: scala.List[Any]) = Nil
    }

    implicit def ofCons[h, t <: List](implicit _typed: Typed[t]) = new Typed[Cons[h, t]] {
        override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
    }
}
