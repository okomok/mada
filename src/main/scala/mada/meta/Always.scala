

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Returns metamethod to always return <code>a</code>.
 */
sealed trait always0[a] {
    type apply = a
}

sealed trait always1[a] {
    type apply[v1 <: Any] = a
}

sealed trait always2[a] {
    type apply[v1 <: Any, v2 <: Any] = a
}

sealed trait always3[a] {
    type apply[v1 <: Any, v2 <: Any, v3 <: Any] = a
}
