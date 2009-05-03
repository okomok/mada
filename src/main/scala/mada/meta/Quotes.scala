

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Converts metamethod to metafunction.
 */
trait Quotes { this: Meta.type =>

    sealed trait quote0[f <: R, R <: Object] extends Function0 {
        override type isBind = `false`

        override type Result0 = R
        override type apply0 = f
    }

    sealed trait quote1[f[_ <: T1] <: R, T1 <: Object, R <: Object] extends Function1 {
        override type isBind = `false`

        override type Argument11 = T1
        override type Result1 = R
        override type apply1[v1 <: Argument11] = f[v1]
    }

    sealed trait quote2[f[_ <: T1, _ <: T2] <: R, T1 <: Object, T2 <: Object, R <: Object] extends Function2 {
        override type isBind = `false`

        override type Argument21 = T1
        override type Argument22 = T2
        override type Result2 = R
        override type apply2[v1 <: Argument21, v2 <: Argument22] = f[v1, v2]
    }

    sealed trait quote3[f[_ <: T1, _ <: T2, _ <: T3] <: R, T1 <: Object, T2 <: Object, T3 <: Object, R <: Object] extends Function3 {
        override type isBind = `false`

        override type Argument31 = T1
        override type Argument32 = T2
        override type Argument33 = T3
        override type Result3 = R
        override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = f[v1, v2, v3]
    }

}
