

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunctions to return the n-th argument.
 */
trait Args { this: Meta.type =>

    final class Arg1 extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = NA
        override type Apply1[a1 <: Obj] = a1
        override type Apply2[a1 <: Obj, a2 <: Obj] = a1
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = a1
    }

    final class Arg2 extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = NA
        override type Apply1[a1 <: Obj] = NA
        override type Apply2[a1 <: Obj, a2 <: Obj] = a2
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = a2
    }

    final class Arg3 extends VarargFunc {
        override type IsBind = True

        override type Apply0[Void] = NA
        override type Apply1[a1 <: Obj] = NA
        override type Apply2[a1 <: Obj, a2 <: Obj] = NA
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = a3
    }

}
