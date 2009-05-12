

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Drop[A](that: Traversable[A], count: Int) extends Traversable[A] { ^ =>
    override def start = {
        val t = that.start
        var i = count
        while (i != 0 && !t.isEnd) {
            t.increment
            i -= 1
        }
        t
    }
}
