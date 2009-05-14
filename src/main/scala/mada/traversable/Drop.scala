

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Drop[A](val _1: Traversable[A], val _2: Int) extends Traversable[A] { self =>
    override def start = _1 match {
        case _1: Drop[_] => _1._1.drop(_1._2 + _2).start // drop-drop fusion
        case _ => _start
    }

    private def _start = {
        val t = _1.start
        var i = _2
        while (i != 0 && t) {
            t.++
            i -= 1
        }
        t
    }
}
