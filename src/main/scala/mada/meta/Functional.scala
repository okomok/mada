

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


sealed trait bind1st[T1, T2, f[_ <: T1, _ <: T2], c <: T1] {
    type apply[b <: T2] = f[c, b]
}

sealed trait bind2nd[T1, T2, f[_ <: T1, _ <: T2], c <: T2] {
    type apply[a <: T1] = f[a, c]
}


sealed trait unary_negate[T1, f[_ <: T1] <: Boolean] {
    type apply[a <: T1] = f[a]#not
}

sealed trait binary_negate[T1, T2, f[_ <: T1, _ <: T2] <: Boolean] {
    type apply[a <: T1, b <: T2] = f[a, b]#not
}


sealed trait unary_compose[T1, T2, f[_ <: T2], g[_ <: T1] <: T2] {
    type apply[a <: T1] = f[g[a]]
}

sealed trait binary_compose[T1, T2, T3, f[_ <: T2, _ <: T3], g1[_ <: T1] <: T2, g2[_ <: T1] <: T3] {
    type apply[a <: T1] = f[g1[a], g2[a]]
}
