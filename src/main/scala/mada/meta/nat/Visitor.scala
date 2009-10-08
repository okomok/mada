

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta.nat


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


trait Visitor {
    type Result
    type visitZero <: Result
    type visitSucc[n <: Nat] <: Result
}

sealed trait equalsVisitor[x <: Nat] extends Visitor {
    type Result = Boolean
    type visitZero = x#isZero
    type visitSucc[y <: Nat] = y#accept[equalsVisitor[x#decrement]]
}

sealed trait subtractVisitor[x <: Nat] extends Visitor {
    type Result = Nat
    type visitZero = x
    type visitSucc[y <: Nat] = y#accept[subtractVisitor[x#decrement]]
}
