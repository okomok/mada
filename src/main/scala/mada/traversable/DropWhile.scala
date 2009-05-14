

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class DropWhile[A](_1: Traversable[A], _2: A => Boolean) extends Traversable[A] {
    override def start = {
        val t = _1.start
        while (t && _2(~t)) {
            t.++
        }
        t
    }
}
