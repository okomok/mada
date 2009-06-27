

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] { // physical

    @conversion
    def asIterative: Iterative[A] // logical

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * You shall not override this in a purpose except optimization.
     * (In this regard, JCL hierarchy is broken. E.g. <code>unmodifiableCollection</code> can't forward <code>equals</code>.
     * Probably <code>List/Set</code> shouldn't have been a subclass of <code>Collection</code>.)
     *
     * @see Effective Java 2nd Edition - Item 8
     */
    override def equals(that: Any) = that match {
        case that: Sequence[_] => asIterative.equalsIf(that.asIterative)(function.equal)
        case _ => false
    }

    override def hashCode = {
        var r = 1
        val it = asIterative.begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val it = asIterative.begin
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

// methodization
    sealed class _OfInvariant[A](_this: Iterative[A]) {
        def groupBy[K](f: A => K): scala.collection.Map[K, Vector[A]] = _this._groupBy(_this, f)
        def toSHashSet: scala.collection.Set[A] = _this._toSHashSet(_this)
        def toJIterable: java.lang.Iterable[A] = _this._toJIterable(_this)
        def toVector: Vector[A] = _this._toVector(_this)
        def using[X](x: X)(implicit a: Auto[X]): Using[A, X] = Using(_this, x, a) // final
    }
    implicit def _ofInvariant[A](_this: Sequence[A]): _OfInvariant[A] = new _OfInvariant(_this.asIterative)

    sealed class _OfSequence[A](_this: Iterative[Iterative[A]]) {
        def flatten: Iterative[A] = _this._flatten(_this)
        def unsplit(sep: Iterative[A]): Iterative[A] = _this._unsplit(_this, sep)
    }
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfSequence[A] = new _OfSequence(_this.asIterative.map(_.asIterative))

    sealed class _OfPair[A, B](_this: Iterative[(A, B)]) {
        def unzip: (Iterative[A], Iterative[B]) = _this._unzip(_this)
        def toSHashMap: scala.collection.Map[A, B] = _this._toSHashMap(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.asIterative)

    sealed class _OfChar(_this: Iterative[Char]) {
        def stringize: String = _this._stringize(_this)
        def lexical: Lexical = Lexical(_this)
    }
    implicit def _ofChar(_this: Sequence[Char]): _OfChar = new _OfChar(_this.asIterative)

}


trait SequenceForwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    override def asIterative = delegate.asIterative
    override def equals(that: Any): Boolean = delegate.equals(that)
}
