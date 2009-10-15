

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


object Size {

    type result[l <: List] = l#acceptMetaNat[vt[meta.Zero]]

    sealed abstract class vt[n <: meta.Nat] extends Visitor[meta.Nat] {
        override type visitNil = n
        override type visitCons[h, t <: List] = t#acceptMetaNat[vt[n#increment]]
    }

}
