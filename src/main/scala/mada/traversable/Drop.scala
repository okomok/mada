

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Drop[A](val that: Traversable[A], val count: Int) extends Traversable[A] { ^ =>
    override def start = that match {
        case that: Drop[_] => that.that.drop(that.count + count).start // drop-drop fusion
        case _ => _start
    }

    private def _start = {
        val t = that.start
        var i = count
        while (i != 0 && t) {
            t.++
            i -= 1
        }
        t
    }
}
