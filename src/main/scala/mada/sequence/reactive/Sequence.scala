

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] { // physical

    @conversion
    def asReactive: Reactive[A] // logical

}


object Sequence {

// methodization
    sealed class _OfSequence[A](_this: Reactive[Reactive[A]]) {
        def flatten: Reactive[A] = _this._flatten(_this)
        def unsplit(sep: Reactive[A]): Reactive[A] = _this._unsplit(_this, sep)
    }
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfSequence[A] = new _OfSequence(_this.asReactive.map(_.asReactive))

    sealed class _OfPair[A, B](_this: Reactive[(A, B)]) {
        def unzip: (Reactive[A], Reactive[B]) = _this._unzip(_this)
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.asReactive)

}


trait SequenceForwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    override def asReactive = delegate.asReactive
    override def equals(that: Any): Boolean = delegate.equals(that)
}
