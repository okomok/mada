

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


/**
 * The marker trait of sequences to work around problems around erasure.
 */
trait Sequence[+A] { // physical

    @conversion
    def toIterative: Iterative[A] // logical

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * You shall not override this in a purpose except optimization.
     *
     * @see Effective Java 2nd Edition - Item 8
     */
    override def equals(that: Any) = that match {
        case that: Sequence[_] => toIterative.equalsIf(that.toIterative)(function.equal)
        case _ => false
    }

    override def hashCode = {
        var r = 1
        val it = toIterative.begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val it = toIterative.begin
        if (it) {
            sb.append(~it)
            it.++
        }
        while (it) {
            sb.append(", ")
            sb.append(~it)
            it.++
        }

        sb.append(']')
        sb.toString
    }

}


object Sequence {

    sealed class _OfInvariant[A](_this: Iterative[A]) {
        def toSHashSet: scala.collection.Set[A] = _this._toSHashSet(_this)
        def toJIterable: java.lang.Iterable[A] = _this._toJIterable(_this)
        def toVector: Vector[A] = _this._toVector(_this)
    }
    implicit def _ofInvariant[A](_this: Sequence[A]): _OfInvariant[A] = new _OfInvariant(_this.toIterative)

    sealed class _OfSequence[A](_this: Iterative[Sequence[A]]) {
        def flatten: Iterative[A] = _this._flatten(_this)
        def unsplit(sep: Iterative[A]): Iterative[A] = _this._unsplit(_this, sep)
    }
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfSequence[A] = new _OfSequence(_this.toIterative)

    sealed class _OfPair[A, B](_this: Iterative[(A, B)]) {
        def unzip: (Iterative[A], Iterative[B]) = _this._unzip(_this)
        def toSHashMap: scala.collection.Map[A, B] = _this._toSHashMap(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.toIterative)

    sealed class _OfChar(_this: Iterative[Char]) {
        def stringize: String = _this._stringize(_this)
        def lexical: Lexical = Lexical(_this)
    }
    implicit def _ofChar(_this: Sequence[Char]): _OfChar = new _OfChar(_this.toIterative)

}


trait SequenceForwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    override def toIterative = delegate.toIterative
    override def equals(that: Any): Boolean = delegate.equals(that)
    override def hashCode: Int = delegate.hashCode
    override def toString: String = delegate.toString
}
