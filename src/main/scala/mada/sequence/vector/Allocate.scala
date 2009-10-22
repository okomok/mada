

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


import scala.collection.mutable.GenericArray


case class Allocate[A](_1: Int) extends Forwarder[A] {
    override protected val delegate = fromSVector(new GenericArray[A](_1))
}
