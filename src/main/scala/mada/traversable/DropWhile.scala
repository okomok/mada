

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class DropWhile[A](_1: Traversable[A], _2: A => Boolean) extends Traversable[A] {
    override def begin = {
        val t = _1.begin
        t.advanceWhile(_2)
        t
    }
}
