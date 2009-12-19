

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
    override type Arg1 = Any
    override type apply[v1 <: Arg1] = a
}

sealed trait always2[a] extends Function2 {
    override type Arg1 = Any
    override type Arg2 = Any
    override type apply[v1 <: Arg1, v2 <: Arg2] = a
}

sealed trait always3[a] extends Function3 {
    override type Arg1 = Any
    override type Arg2 = Any
    override type Arg3 = Any
    override type apply[v1 <: Arg1, v2 <: Arg2, v3 <: Arg3] = a
}
