

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


final case class Box[A](private val e: A) extends Any {
    override  def self = this
    override type self = Box[A]

    override  def asInstanceOfBox = self
    override type asInstanceOfBox = self

     def unbox = e
    type unbox = A

    override  def undual = unbox
    override type undual = unbox
}
