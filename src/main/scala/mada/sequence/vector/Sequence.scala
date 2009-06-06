

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * The marker trait of sequences to work around problems around erasure.
 */
trait Sequence[A] extends iterative.Sequence[A] { // physical

    @conversion
    def toVector: Vector[A] // logical

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Sequence[_] => toVector.equalsIf(that.toVector)(function.equal)
        case _ => super.equals(that)
    }

    @optimize
    override def hashCode: Int = {
        val v = toVector
        var r = 1
        var i = v.start; val j = v.end
        while (i != j) {
            r = 31 * r + v(i).hashCode
            i += 1
        }
        r
    }

    @optimize
    override def toString = toVector.toJArrayList.toString

}


trait SequenceForwarder[A] extends Sequence[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Sequence[A]
    override def toVector = delegate.toVector
}
