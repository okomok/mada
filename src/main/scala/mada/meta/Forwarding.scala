

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Method forwardings (who needs?)
 */
sealed trait forwarding0[f] {
    type apply = f
}

sealed trait forwarding1[T1, f[_ <: T1]] {
    type apply[v1 <: T1] = f[v1]
}

sealed trait forwarding2[T1, T2, f[_ <: T1, _ <: T2]] {
    type apply[v1 <: T1, v2 <: T2] = f[v1, v2]
}

sealed trait forwarding3[T1, T2, T3, f[_ <: T1, _ <: T2, _ <: T3]] {
    type apply[v1 <: T1, v2 <: T2, v3 <: T3] = f[v1, v2, v3]
}
