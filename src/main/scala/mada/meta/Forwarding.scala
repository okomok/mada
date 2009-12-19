

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Function forwardings
 */
sealed trait forwarding0[f <: Function0] extends Function0 {
    override type apply = f#apply
}

sealed trait forwarding1[f <: Function1] extends Function1 {
    override type Arg1 = f#Arg1
    override type apply[v1 <: Arg1] = f#apply[v1]
}

sealed trait forwarding2[f <: Function2] extends Function2 {
    override type Arg1 = f#Arg1
    override type Arg2 = f#Arg2
    override type apply[v1 <: Arg1, v2 <: Arg2] = f#apply[v1, v2]
}

sealed trait forwarding3[f <: Function3] extends Function3 {
    override type Arg1 = f#Arg1
    override type Arg2 = f#Arg2
    override type Arg3 = f#Arg3
    override type apply[v1 <: Arg1, v2 <: Arg2, v3 <: Arg3] = f#apply[v1, v2, v3]
}
