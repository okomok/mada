

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import cmp._


/**
 * Contains utility methods operating on strict weak ordering.
 */
object Compare extends Conversions {
    /**
     * Alias of <code>Functions.Predicate2[T, T]</code>
     */
    type Type[-T] = Functions.Predicate2[T, T]

    /**
     * Alias of <code>Function1[T, Ordered[T]]</code>
     */
    type GetOrdered[T] = Function1[T, Ordered[T]]

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Type[A]) = to

    /**
     * Alias of <code>fromGetOrdered</code>
     */
    def apply[A](implicit from: GetOrdered[A]): Type[A] = fromGetOrdered(from)

    /**
     * Alias of <code>comp.Compatibles</code>
     */
    val Compatibles = cmp.Compatibles
}


/**
 * Represents strict weak ordering.
 */
trait Compare[-A] extends Function2[A, A, Boolean] {
    /**
     * Alias of <code>lt</code>
     */
    final def apply(x: A, y: A): Boolean

    /**
     * @return  <code>true</code> iif x precedes y.
     */
    def lt(x: A, y: A): Boolean

    /**
     * Can be overridden for optimization.
     *
     * @return  <code>!apply(x, y) && !apply(y, x)</code>.
     */
    def equiv(x: A, y: A): Boolean = !lt(x, y) && !lt(y, x)
}
