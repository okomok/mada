

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import cmp._


/**
 * Contains utility methods operating on <code>Compare</code>.
 */
object Compare extends Conversions with Compatibles {
    /**
     * Alias of <code>Function2[T, T, Boolean]</code>
     */
    type Func[-T] = Function2[T, T, Boolean]

    /**
     * Alias of <code>Function1[T, Ordered[T]]</code>
     */
    type GetOrdered[T] = Function1[T, Ordered[T]]

    /**
     * Alias of <code>java.util.Comparator[T]</code>
     */
    type Comparator[T] = java.util.Comparator[T]

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Compare[A]) = to

    /**
     * Alias of <code>fromFunc</code>
     */
    def by[A](p: Func[A]): Compare[A] = fromFunc(p)

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: cmp.Compatibles = this

    /**
     * Alias of <code>Compare</code>
     */
    type Type[-A] = Compare[A]
}


/**
 * Represents strict weak ordering.
 */
trait Compare[-A] extends Compare.Func[A] {
    /**
     * @return  <code>true</code> iif x precedes y.
     */
    def apply(x: A, y: A): Boolean

    /**
     * @return  <code>if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0</code>.
     */
    def threeWay(x: A, y: A): Int = if (apply(x, y)) -1 else if (apply(y, x)) 1 else 0

    /**
     * Alias of <code>Compare</code>
     */
    final def companion = Compare
}
