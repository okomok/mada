

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunctions to return the n-th argument.
 */
trait Args { this: Meta.type =>

    final class arg1 extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = NA
        override type apply1[a1 <: Object] = a1
        override type apply2[a1 <: Object, a2 <: Object] = a1
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = a1
    }

    final class arg2 extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = NA
        override type apply1[a1 <: Object] = NA
        override type apply2[a1 <: Object, a2 <: Object] = a2
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = a2
    }

    final class arg3 extends FunctionV {
        override type isBind = `true`

        override type apply0[Void] = NA
        override type apply1[a1 <: Object] = NA
        override type apply2[a1 <: Object, a2 <: Object] = NA
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = a3
    }

}
