

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Converts metamethod to metafunction.
 */
trait Quotes { this: Meta.type =>

    final class quote0[R <: Object, f <: R] extends Function0 {
        override type isBind = `false`

        override type Result0 = R
        override type apply0 = f
    }

    final class quote1[T1 <: Object, R <: Object, f[_ <: T1] <: R] extends Function1 {
        override type isBind = `false`

        override type Argument11 = T1
        override type Result1 = R
        override type apply1[v1 <: Argument11] = f[v1]
    }

    final class quote2[T1 <: Object, T2 <: Object, R <: Object, f[_ <: T1, _ <: T2] <: R] extends Function2 {
        override type isBind = `false`

        override type Argument21 = T1
        override type Argument22 = T2
        override type Result2 = R
        override type apply2[v1 <: Argument21, v2 <: Argument22] = f[v1, v2]
    }

    final class quote3[T1 <: Object, T2 <: Object, T3 <: Object, R <: Object, f[_ <: T1, _ <: T2, _ <: T3] <: R] extends Function3 {
        override type isBind = `false`

        override type Argument31 = T1
        override type Argument32 = T2
        override type Argument33 = T3
        override type Result3 = R
        override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = f[v1, v2, v3]
    }

}
