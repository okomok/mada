

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Converts metamethod to metafunction.
 */
sealed trait quote0[f <: R, R] extends Function0 {
    override type Result0 = R

    override type apply0 = f
}

sealed trait quote1[f[_ <: T1] <: R, T1, R] extends Function1 {
    override type Argument11 = T1
    override type Result1 = R

    override type apply1[v1 <: Argument11] = f[v1]
}

sealed trait quote2[f[_ <: T1, _ <: T2] <: R, T1, T2, R] extends Function2 {
    override type Argument21 = T1
    override type Argument22 = T2
    override type Result2 = R

    override type apply2[v1 <: Argument21, v2 <: Argument22] = f[v1, v2]
}

sealed trait quote3[f[_ <: T1, _ <: T2, _ <: T3] <: R, T1, T2, T3, R] extends Function3 {
    override type Argument31 = T1
    override type Argument32 = T2
    override type Argument33 = T3
    override type Result3 = R

    override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = f[v1, v2, v3]
}
