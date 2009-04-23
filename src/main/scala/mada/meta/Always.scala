

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Metafunction always returning <code>a</code>
 */
final class Always[a <: Obj] extends Meta.VarargFunc {
    override type IsBind = Meta.False

    override type Apply0[Void] = a
    override type Apply1[b1 <: Obj] = a
    override type Apply2[b1 <: Obj, b2 <: Obj] = a
    override type Apply3[b1 <: Obj, b2 <: Obj, b3 <: Obj] = a
}
