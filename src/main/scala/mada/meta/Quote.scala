

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Converts metamethod to metafunction.
 */
sealed trait quote0[f] extends Function0 {
    override type apply = f
}

sealed trait quote1[f[_ <: T1], T1] extends Function1 {
    override type apply[v1 <: T1] = f[v1]
}

sealed trait quote2[f[_ <: T1, _ <: T2], T1, T2] extends Function2 {
    override type apply[v1 <: T1, v2 <: T2] = f[v1, v2]
}

sealed trait quote3[f[_ <: T1, _ <: T2, _ <: T3], T1, T2, T3] extends Function3 {
    override type apply[v1 <: T1, v2 <: T2, v3 <: T3] = f[v1, v2, v3]
}
