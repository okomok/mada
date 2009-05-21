

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import compare._


/**
 * Represents strict weak ordering.
 */
trait Compare[-A] extends Func[A] {

    /**
     * @return  <code>true</code> iif x precedes y.
     */
    def apply(x: A, y: A): Boolean

    /**
     * @return  <code>if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0</code>.
     */
    def threeWay(x: A, y: A): Int = if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0

    @compatibleConversion
    def toSome: ToSome[A] = new ToSome(this)

    @compatibleConversion
    def _toGetOrdered[B](_this: Compare[B]): GetOrdered[B] = ToGetOrdered(_this)

    @compatibleConversion
    def _toOrdering[B](_this: Compare[B]): Ordering[B] = ToOrdering(_this)

    @compatibleConversion
    def _toJComparator[B](_this: Compare[B]): java.util.Comparator[B] = ToJComparator(_this)

    @returnThis
    final def asCompare: Compare[A] = this
}


object Compare {

// methodization

    sealed class OfInvariant[A](_this: Compare[A]) {
        def toGetOrdered: GetOrdered[A] = _this._toGetOrdered(_this)
        def toOrdering: Ordering[A] = _this._toOrdering(_this)
        def toJComparator: java.util.Comparator[A] = _this._toJComparator(_this)
    }
    implicit def ofInvariant[A](_this: Compare[A]): OfInvariant[A] = new OfInvariant(_this)

// compatibles

    implicit def madaCompareFromFunc[A](from: Func[A]): Compare[A] = fromFunc(from)
    implicit def madaCompareFromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = fromGetOrdered(from)
    implicit def madaCompareFromOrdering[A](from: Ordering[A]): Compare[A] = fromOrdering(from)
    implicit def madaCompareFromJComparator[A](from: java.util.Comparator[A]): Compare[A] = fromJComparator(from)

}
