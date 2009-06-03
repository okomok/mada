

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector.jcl


import java.lang.CharSequence


private[mada] object CharSequenceToVector {
    def apply[A](from: CharSequence): Vector[Char] = from match {
        case from: VectorCharSequence => from.v // conversion fusion
        case _ => new CharSequenceVector(from)
    }
}

private[mada] class CharSequenceVector(val from: CharSequence) extends Vector[Char] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from.charAt(i)
}


private[mada] object CharSequenceFromVector {
    def apply(v: Vector[Char]): CharSequence = v match {
        case from: CharSequenceVector => from.from // conversion fusion
        case _ => new VectorCharSequence(v)
    }
}

private[mada] class VectorCharSequence(val v: Vector[Char]) extends CharSequence {
    override def charAt(index: Int) = v.nth(index)
    override def length = v.nth.size
    override def subSequence(start: Int, end: Int) = new VectorCharSequence(v.nth(start, end))
    override def toString = vector.stringize(v.nth)
}
