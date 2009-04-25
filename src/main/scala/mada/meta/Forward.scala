

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Function forwarding
 */
trait Forwards { this: Meta.type =>

    final class forward0[f <: Function0] extends Function0 {
        override type Result0 = f#Result0
        override type apply0 = f#apply0
    }

    final class forward1[f <: Function1] extends Function1 {
        override type Argument11 = f#Argument11
        override type Result1 = f#Result1
        override type apply1[v1 <: Argument11] = f#apply1[v1]
    }

    final class forward2[f <: Function2] extends Function2 {
        override type Argument21 = f#Argument21
        override type Argument22 = f#Argument22
        override type Result2 = f#Result2
        override type apply2[v1 <: Argument21, v2 <: Argument22] = f#apply2[v1, v2]
    }

    final class forward3[f <: Function3] extends Function3 {
        override type Argument31 = f#Argument31
        override type Argument32 = f#Argument32
        override type Argument33 = f#Argument33
        override type Result3 = f#Result3
        override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = f#apply3[v1, v2, v3]
    }

}
