

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Metafunction always returning <code>a</code>
 */
sealed trait always0[a] extends Function0 {
    override type apply = a
}

sealed trait always1[a] extends Function1 {
    override type apply[v1 <: Any] = a
}

sealed trait always2[a] extends Function2 {
    override type apply[v1 <: Any, v2 <: Any] = a
}

sealed trait always3[a] extends Function3 {
    override type apply[v1 <: Any, v2 <: Any, v3 <: Any] = a
}
