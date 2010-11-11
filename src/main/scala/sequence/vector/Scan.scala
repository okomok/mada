

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Scan[A](_1: Vector[A], _2: A, _3: (A, A) => A) extends Forwarder[A] {
    override protected val delegate = _1.asIterative.scanLeft(_2)(_3).toVector
}

private
case class Scan1[A](_1: Vector[A], _2: (A, A) => A) extends Forwarder[A] {
    Precondition.notEmpty(_1, "scan1")
    override protected val delegate = _1.asIterative.scanLeft1(_2).toVector
}
