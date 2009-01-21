

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// This may be a problem.

case class TripleKey[A](v: Vector[A], first: Int, last: Int) {
    override def equals(that: Any) = that match {
        case that: TripleKey[_] => (v eq that.v) && (first == that.first) && (last == that.last)
        case _ => false
    }
    override def hashCode = {
        v.refHashCode + IntHashCode(first) * 7 + IntHashCode(last) * 41
    }
}
