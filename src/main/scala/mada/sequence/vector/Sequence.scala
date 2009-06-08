

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[A] extends iterative.Sequence[A] { // physical

    override def asIterative: Iterative[A] = AsIterative(asVector) // logical super

    @conversion
    def asVector: Vector[A] // logical

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Sequence[_] => asVector.equalsIf(that.asVector)(function.equal)
        case _ => super.equals(that)
    }

    @optimize
    override def hashCode: Int = {
        val v = asVector
        var r = 1
        var i = v.start; val j = v.end
        while (i != j) {
            r = 31 * r + v(i).hashCode
            i += 1
        }
        r
    }

    @optimize
    override def toString = asVector.toJList.toString

}


object Sequence {

// logical hierarchy
    implicit def _asIterative[A](from: Sequence[A]): Iterative[A] = from.asIterative

// methodization
    sealed class _OfVector[A](_this: Vector[Vector[A]]) {
        def undivide: Vector[A] = _this._undivide(_this)
    }
    implicit def _ofVector[A](_this: Sequence[Vector[A]]): _OfVector[A] = new _OfVector(_this.asVector)
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfVector[A] = new _OfVector(_this.asVector.map(_.asVector))

    sealed class _OfPair[A, B](_this: Vector[(A, B)]) {
        def unzip: (Vector[A], Vector[B]) = _this._unzip(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.asVector)

    sealed class _OfChar(_this: Vector[Char]) {
        def stringize: String = _this._stringize(_this)
        def lowerCase: Vector[Char] = _this._lowerCase(_this)
        def upperCase: Vector[Char] = _this._upperCase(_this)
        def toJCharSequence: java.lang.CharSequence = _this._toJCharSequence(_this)
    }
    implicit def _ofChar(_this: Sequence[Char]): _OfChar = new _OfChar(_this.asVector)

}


trait SequenceForwarder[A] extends Sequence[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Sequence[A]
    override def asVector = delegate.asVector
}
