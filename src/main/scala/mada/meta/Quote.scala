

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Converts metamethod to metafunction.
 */
trait Quotes { this: Meta.type =>

    final class quote0[R <: Object, f[void] <: R] extends Function0 {
        override type isBind = `false`

        override type Result = R
        override type apply[void] = f[void]
    }

    final class quote1[T1 <: Object, R <: Object, f[_ <: T1] <: R] extends Function1 {
        override type isBind = `false`

        override type Argument1 = T1
        override type Result = R
        override type apply[v1 <: Argument1] = f[v1]
    }

    final class quote2[T1 <: Object, T2 <: Object, R <: Object, f[_ <: T1, _ <: T2] <: R] extends Function2 {
        override type isBind = `false`

        override type Argument1 = T1
        override type Argument2 = T2
        override type Result = R
        override type apply[v1 <: Argument1, v2 <: Argument2] = f[v1, v2]
    }

    final class quote3[T1 <: Object, T2 <: Object, T3 <: Object, R <: Object, f[_ <: T1, _ <: T2, _ <: T3] <: R] extends Function3 {
        override type isBind = `false`

        override type Argument1 = T1
        override type Argument2 = T2
        override type Argument3 = T3
        override type Result = R
        override type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] = f[v1, v2, v3]
    }

}
