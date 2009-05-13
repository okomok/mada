

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class DropWhile[A](val that: Traversable[A], val predicate: A => Boolean) extends Traversable[A] { ^ =>
    override def start = {
        val t = that.start
        while (t && predicate(~t)) {
            t.++
        }
        t
    }
}
