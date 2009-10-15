

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta; package nat


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


trait Visitor[R] {
    type visitZero <: R
    type visitSucc[n <: Nat] <: R
}
