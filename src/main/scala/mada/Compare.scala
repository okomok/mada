

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on strict weak ordering.
 */
object Compare extends comp.Conversions {
    /**
     * Alias of <code>Functions.Predicate2[T, T]</code>
     */
    type Type[-T] = Functions.Predicate2[T, T]

    /**
     * Alias of <code>T => Ordered[T]</code>
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
    val Compatibles = comp.Compatibles
}
