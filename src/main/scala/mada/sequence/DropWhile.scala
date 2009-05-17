

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class DropWhile[A](_1: Sequence[A], _2: A => Boolean) extends Sequence[A] {
    override def begin = {
        val it = _1.begin
        it.advanceWhile(_2)
        it
    }
}
