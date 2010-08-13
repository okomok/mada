

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
final case class Recursive[f <: Function0](f: f) extends AbstractPeg {
    type self = Recursive[f]

    private lazy val p: p = f.apply.asInstanceOfPeg
    private type p        = f#apply#asInstanceOfPeg

    final override  def parse[xs <: List](xs: xs): parse[xs] = p.parse(xs)
    final override type parse[xs <: List]                    = p#parse[xs]

    final override  def width: width = p.width
    final override type width        = p#width

/*
    final override  def seq[that <: Peg](that: that): seq[that] = p.seq(that)
    final override type seq[that <: Peg]                        = p#seq[that]

    final override  def or[that <: Peg](that: that): or[that] = p.or(that)
    final override type or[that <: Peg]                       = p#or[that]

    final override  def star: star = p.star
    final override type star       = p#star

    final override  def plus: plus = p.plus
    final override type plus       = p#plus

    final override  def opt: opt = p.opt
    final override type opt      = p#opt

    final override  def and: and = p.and
    final override type and      = p#and

    final override  def not: not = p.not
    final override type not      = p#not

    final override  def map[f <: Function1](f: f): map[f] = p.map(f)
    final override type map[f <: Function1]               = p#map[f]

    final override  def matches[xs <: List](xs: xs): matches[xs] = p.matches(xs)
    final override type matches[xs <: List]                      = p#matches[xs]
*/
}
