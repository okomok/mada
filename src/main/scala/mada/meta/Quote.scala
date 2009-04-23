

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Turns metamethod into metafunction.
 */
trait Quotes { this: Meta.type =>

    final class Quote0[f[Void] <: Obj] extends Func0 {
        override type IsBind = False
        override type Apply0[Void] = f[Void]
    }

    final class Quote1[f[_ <: Obj] <: Obj] extends Func1 {
        override type IsBind = False
        override type Apply1[a1 <: Obj] = f[a1]
    }

    final class Quote2[f[_ <: Obj, _ <: Obj] <: Obj] extends Func2 {
        override type IsBind = False
        override type Apply2[a1 <: Obj, a2 <: Obj] = f[a1, a2]
    }

    final class Quote3[f[_ <: Obj, _ <: Obj, _ <: Obj] <: Obj] extends Func3 {
        override type IsBind = False
        override type Apply3[a1 <: Obj, a2 <: Obj, a3 <: Obj] = f[a1, a2, a3]
    }

}
