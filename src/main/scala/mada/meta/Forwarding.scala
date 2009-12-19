

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Function forwardings
 */
sealed trait forwarding0[f <: Function0] extends Function0 {
    override type apply = f#apply
}

sealed trait forwarding1[f <: Function1[_ <: Any]] extends Function1[Any] {
    override type apply[v1 <: Any] = f#apply[v1]
}

sealed trait forwarding2[f <: Function2[_ <: Any]] extends Function2[Any, Any] {
    override type apply[v1 <: Any, v2 <: Any] = f#apply[v1, v2]
}

sealed trait forwarding3[f <: Function3[_ <: Any]] extends Function3[Any, Any] {
    override type apply[v1 <: Any, v2 <: Any, v3 <: Any] = f#apply[v1, v2, v3]
}
