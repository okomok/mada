

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Function forwardings
 */
sealed trait forwarding0[f <: Function0] extends Function0 {
    override type Result = f#Result

    override type apply = f#apply
}

sealed trait forwarding1[f <: Function1] extends Function1 {
    override type Argument1 = f#Argument1
    override type Result = f#Result

    override type apply[v1 <: Argument1] = f#apply[v1]
}

sealed trait forwarding2[f <: Function2] extends Function2 {
    override type Argument1 = f#Argument1
    override type Argument2 = f#Argument2
    override type Result = f#Result

    override type apply[v1 <: Argument1, v2 <: Argument2] = f#apply[v1, v2]
}

sealed trait forwarding3[f <: Function3] extends Function3 {
    override type Argument1 = f#Argument1
    override type Argument2 = f#Argument2
    override type Argument3 = f#Argument3
    override type Result = f#Result

    override type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] = f#apply[v1, v2, v3]
}
