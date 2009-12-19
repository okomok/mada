

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Converts metamethod to metafunction.
 */
sealed trait quote0[f <: R, R] extends Function0 {
    override type Result = R

    override type apply = f
}

sealed trait quote1[f[_ <: T1] <: R, T1, R] extends Function1 {
    override type Argument1 = T1
    override type Result = R

    override type apply[v1 <: Argument1] = f[v1]
}

sealed trait quote2[f[_ <: T1, _ <: T2] <: R, T1, T2, R] extends Function2 {
    override type Argument1 = T1
    override type Argument2 = T2
    override type Result = R

    override type apply[v1 <: Argument1, v2 <: Argument2] = f[v1, v2]
}

sealed trait quote3[f[_ <: T1, _ <: T2, _ <: T3] <: R, T1, T2, T3, R] extends Function3 {
    override type Argument1 = T1
    override type Argument2 = T2
    override type Argument3 = T3
    override type Result = R

    override type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] = f[v1, v2, v3]
}
