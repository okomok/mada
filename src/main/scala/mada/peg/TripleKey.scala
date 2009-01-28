

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// This may be a problem.

private[mada] case class TripleKey[A](v: Vector[A], start: Int, end: Int) {
    override def equals(that: Any) = that match {
        case that: TripleKey[_] => (v eq that.v) && (start == that.start) && (end == that.end)
        case _ => false
    }
    override def hashCode = {
        HashCode.ofRef(v) + HashCode.ofInt(start) * 7 + HashCode.ofInt(end) * 41
    }
}
