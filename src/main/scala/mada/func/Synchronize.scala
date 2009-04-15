

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Converts a function to synchronized one.
 */
trait Synchronize { this: Functions.type =>
    def synchronize1[T1, R](f: Function1[T1, R]): Function1[T1, R] = new Function1[T1, R] {
        override def apply(v1: T1) = synchronized { f(v1) }
    }

    def synchronize2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = new Function2[T1, T2, R] {
        override def apply(v1: T1, v2: T2) = synchronized { f(v1, v2) }
    }

    def synchronize3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = new Function3[T1, T2, T3, R] {
        override def apply(v1: T1, v2: T2, v3: T3) = synchronized { f(v1, v2, v3) }
    }

    def synchronize[T1, R](f: Function1[T1, R]): Function1[T1, R] = synchronize1(f)
    def synchronize[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = synchronize2(f)
    def synchronize[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = synchronize3(f)
}
