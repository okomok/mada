

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


object Sequence {

    sealed class MadaVectorEither[A, B](_1: Vector[Either[A, B]]) {
        def lefts = vector.lefts(_1)
        def rights = vector.rights(_1)
    }
    implicit def madaVectorEither[A, B](_1: Sequence[Either[A, B]]): MadaVectorEither[A, B] = new MadaVectorEither(_1.toVector)

    sealed class _OfVector[A](_this: Vector[Vector[A]]) {
        def undivide: Vector[A] = _this._undivide(_this)
    }
    implicit def _ofVector[A](_this: Sequence[Vector[A]]): _OfVector[A] = new _OfVector(_this.toVector)

    sealed class _OfPair[A, B](_this: Vector[(A, B)]) {
        def unzip: (Vector[A], Vector[B]) = _this._unzip(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.toVector)

    sealed class _OfChar(_this: Vector[Char]) {
        def stringize: String = _this._stringize(_this)
        def lowerCase: Vector[Char] = _this._lowerCase(_this)
        def upperCase: Vector[Char] = _this._upperCase(_this)
        def toJCharSequence: java.lang.CharSequence = _this._toJCharSequence(_this)
    }
    implicit def _ofChar(_this: Sequence[Char]): _OfChar = new _OfChar(_this.toVector)

}


trait SequenceForwarder[A] extends Sequence[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Sequence[A]
    override def toVector = delegate.toVector
}
