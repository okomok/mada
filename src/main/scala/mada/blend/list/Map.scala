

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


// originally written by kmizushima.
// (doesn't work yet.)


object Map {

    type result[l <: List, A, B, f[_ <: A] <: B] = Nothing // l#accept_List[vt[f]]

    sealed abstract class vt[f[_]] extends Visitor[List] {
        override type visitNil = Nil
        override type visitCons[h, t <: List] = Cons[f[h], t#accept_List[this.type]]
    }

}
