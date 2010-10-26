

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package privatethistest


class Rational (numArg: Int, denArg: Int) {
  def gcd(l: Int, r: Int): Int = if (r == 0) l else gcd(r, l % r)
  private[this] val n = gcd(numArg.abs, denArg.abs) // doesn't removed yet.
  val num = numArg / n
  val den = denArg / n
}

