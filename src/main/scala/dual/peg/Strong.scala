

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


abstract class Strong[p <: Peg](p: p) extends AbstractPeg {
    override  def parse[xs <: List](xs: xs): parse[xs] = p.parse(xs)
    override type parse[xs <: List]                    = p#parse[xs]
    override  def width: width = p.width
    override type width        = p#width
}
