

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends iterative.Sequence[A] { // physical

    @conversion
    def asList: List[A] // logical

    override def asIterative: Iterative[A] = AsIterative(asList) // logical super

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Sequence[_] => asList.equalsIf(that.asList)(function.equal)
        case _ => super.equals(that)
    }

    override def hashCode = {
        var r = 1
        var it = asList
        while (!it.isEmpty) {
            r = 31 * r + it.head.hashCode
            it = it.tail
        }
        r
    }

}


object Sequence {

// logical hierarchy
    implicit def _asIterative[A](from: Sequence[A]): Iterative[A] = from.asIterative

// methodization
    sealed class _OfSequence[A](_this: List[List[A]]) {
        def flatten: List[A] = _this.foldRight(NilOf[A])(_ ++ _())
    }
    implicit def _ofSequence[A](_this: Sequence[Sequence[A]]): _OfSequence[A] = new _OfSequence(_this.asList.map(_.asList))

    sealed class _OfBoolean(_this: List[Boolean]) {
        def and: Boolean = _this.foldRight(true)(_ && _())
        def or: Boolean = _this.foldRight(false)(_ || _())
    }
    implicit def _ofBoolean(_this: Sequence[Boolean]): _OfBoolean = new _OfBoolean(_this.asList)

    sealed class _OfPair[A, B](_this: List[(A, B)]) {
        def unzip: (List[A], List[B]) = _this.foldRight((NilOf[A], NilOf[B])){ (ab, abs) => (Cons(ab._1, abs()._1), Cons(ab._2, abs()._2)) }
    }
    implicit def _ofPair[A, B](_this: Sequence[(A, B)]): _OfPair[A, B] = new _OfPair(_this.asList)

}


trait SequenceForwarder[+A] extends Sequence[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Sequence[A]
    override def asList = delegate.asList
}
