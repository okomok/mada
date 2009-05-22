

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class FromSIterable[A](_1: scala.Iterable[A]) extends Forwarder[A] {
    override protected val delegate = fromSIterableBy(_1)(function.equal)
}

case class FromSIterableBy[A](_1: scala.Iterable[A], _2: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        val it = _1.elements
        var cur = start

        while (it.hasNext && cur != end) {
            if (!_2(it.next, v(cur))) {
                return FAILURE
            }
            cur += 1
        }

        if (cur == end && it.hasNext) FAILURE else cur
    }

    override def width = sequence.fromSIterable(_1).size
}


case class FromSRegex(_1: scala.util.matching.Regex) extends Forwarder[Char] {
    override protected val delegate = fromRegexPattern(_1.pattern)
}
