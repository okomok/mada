

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


object Length {

    type result[l <: List] = l#accept[vt[meta.Zero]]

    sealed trait vt[n <: meta.Nat] extends Visitor {
        override type Result = meta.Nat
        override type visitNil = n
        override type visitCons[h, t <: List] = t#accept[vt[n#increment]]
    }

}
