

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


final case class Tuple1[v1 <: Any](private val v1: v1) extends Product1 {
    override  def self = this
    override type self = Tuple1[v1]

    override  def _1: _1 = v1
    override type _1 = v1

    override  def undual: undual = scala.Tuple1(v1.undual)
    override type undual = scala.Tuple1[v1#undual]
}

final case class Tuple2[v1 <: Any, v2 <: Any](private val v1: v1, private val v2: v2) extends Product2 {
    override  def self = this
    override type self = Tuple2[v1, v2]

    override  def _1: _1 = v1
    override type _1 = v1
    override  def _2: _2 = v2
    override type _2 = v2

    override  def undual: undual = scala.Tuple2(v1.undual, v2.undual)
    override type undual = scala.Tuple2[v1#undual, v2#undual]
}
