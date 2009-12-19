

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Metafunction always returning <code>a</code>
 */
sealed trait always0[T, a <: T] extends Function0 {
    override type Result = T
    override type apply = a
}

sealed trait always1[T, a <: T] extends Function1 {
    override type Argument1 = Any
    override type Result = T
    override type apply[v1 <: Argument1] = a
}

sealed trait always2[T, a <: T] extends Function2 {
    override type Argument1 = Any
    override type Argument2 = Any
    override type Result = T
    override type apply[v1 <: Argument1, v2 <: Argument2] = a
}

sealed trait always3[T, a <: T] extends Function3 {
    override type Argument1 = Any
    override type Argument2 = Any
    override type Argument3 = Any
    override type Result = T
    override type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] = a
}
