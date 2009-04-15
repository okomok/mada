

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Negates a predicate.
 * (This trait is for exposition only: use <code>Functions</code> methods instead.)
 */
trait Not {
    import Functions.{ Predicate1, Predicate2, Predicate3 }

    def not1[T1](f: Predicate1[T1]): Predicate1[T1] = { v1 => !f(v1) }
    def not2[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = { (v1, v2) => !f(v1, v2) }
    def not3[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = { (v1, v2, v3) => !f(v1, v2, v3) }

    def not[T1](f: Predicate1[T1]): Predicate1[T1] = not1(f)
    def not[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = not2(f)
    def not[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = not3(f)
}