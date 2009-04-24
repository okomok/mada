

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Function forwarding
 */
trait Forwards { this: Meta.type =>

    final class forward0[f <: Function0] extends Function0 {
        override type Result = f#Result
        override type apply[void] = f#apply[void]
    }

    final class forward1[f <: Function1] extends Function1 {
        override type Argument1 = f#Argument1
        override type Result = f#Result
        override type apply[v1 <: Argument1] = f#apply[v1]
    }

    final class forward2[f <: Function2] extends Function2 {
        override type Argument1 = f#Argument1
        override type Argument2 = f#Argument2
        override type Result = f#Result
        override type apply[v1 <: Argument1, v2 <: Argument2] = f#apply[v1, v2]
    }

    final class forward3[f <: Function3] extends Function3 {
        override type Argument1 = f#Argument1
        override type Argument2 = f#Argument2
        override type Argument3 = f#Argument3
        override type Result = f#Result
        override type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] = f#apply[v1, v2, v3]
    }

}
