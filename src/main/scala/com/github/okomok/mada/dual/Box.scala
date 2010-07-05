

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


final case class Box[A](e: A) extends Any {
     def unbox = e
    type unbox = A

    override  def undual = unbox
    override type undual = A

    override def equals(that: scala.Any) = that match {
        case that: Box[_] => undual == that.undual
        case _ => false
    }
}
