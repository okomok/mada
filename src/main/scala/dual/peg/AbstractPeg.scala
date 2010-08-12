

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


trait AbstractPeg extends Peg {
    final override  def seq[that <: Peg](that: that): seq[that] = Seq.apply(self, that)
    final override type seq[that <: Peg]                        = Seq.apply[self, that]

    final override  def or[that <: Peg](that: that): or[that] = Or.apply(self, that)
    final override type or[that <: Peg]                       = Or.apply[self, that]

    final override  def star: star = Star.apply(self)
    final override type star       = Star.apply[self]

    final override  def plus: plus = Plus.apply(self)
    final override type plus       = Plus.apply[self]

    final override  def opt: opt = Opt.apply(self)
    final override type opt      = Opt.apply[self]

    final override  def and: and = And.apply(self)
    final override type and      = And.apply[self]

    final override  def not: not = Not.apply(self)
    final override type not      = Not.apply[self]
}
