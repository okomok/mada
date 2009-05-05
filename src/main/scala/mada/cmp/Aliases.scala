

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.cmp


trait Aliases { this: Compare.type =>
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
     * Alias of <code>Compare</code>
     */
    type Type[-A] = Compare[A]
}
