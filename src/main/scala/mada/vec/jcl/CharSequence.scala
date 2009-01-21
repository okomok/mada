

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.jcl


import java.lang.CharSequence


object CharSequenceVector {
    def apply[A](u: CharSequence): Vector[Char] = new CharSequenceVector(u)
}

class CharSequenceVector(val seq: CharSequence) extends Vector[Char] {
    override def size = seq.length
    override def apply(i: Int) = seq.charAt(i)
}


object VectorCharSequence {
    def apply(v: Vector[Char]): CharSequence = new VectorCharSequence(v)
}

class VectorCharSequence(v: Vector[Char]) extends CharSequence {
    override def charAt(index: Int) = v(index)
    override def length = v.size
    override def subSequence(start: Int, end: Int) = new VectorCharSequence(v.window(start, end))
    override def toString = Vector.stringize(v)
}
