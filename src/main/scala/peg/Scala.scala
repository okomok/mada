

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private
case class FromSIterable[A](_1: scala.Iterable[A]) extends Forwarder[A] {
    override protected val delegate = fromSIterableBy(_1)(function.equal)
}

private
case class FromSIterableBy[A](_1: scala.Iterable[A], _2: (A, A) => Boolean) extends Forwarder[A] {
    override protected val delegate = fromSequenceBy(sequence.Iterative.from(_1))(_2)
}


private
case class FromSRegex(_1: scala.util.matching.Regex) extends Forwarder[Char] {
    override protected val delegate = fromRegexPattern(_1.pattern)
}
