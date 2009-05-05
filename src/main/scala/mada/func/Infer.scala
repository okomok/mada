

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Returns <code>f</code> as is, which helps type inference.
 */
@provider
trait Infer { this: Functions.type =>
    def infer1[T1, R](f: Function1[T1, R]): Function1[T1, R] = f
    def infer2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = f
    def infer3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = f

    def infer[T1, R](f: Function1[T1, R]): Function1[T1, R] = infer1(f)
    def infer[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = infer2(f)
    def infer[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = infer3(f)
}
