

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class UsedWith[A](_1: Seq[Auto[_]], _2: A) extends Auto[A] {
    override def foreach(f: A => Unit) = _1.size match {
        case 1 => for (_ <- _1(0)) { f(_2) }
        case 2 => for (_ <- _1(0); _ <- _1(1)) f(_2)
        case 3 => for (_ <- _1(0); _ <- _1(1); _ <- _1(2)) { f(_2) }
        case 4 => for (_ <- _1(0); _ <- _1(1); _ <- _1(2); _ <- _1(3)) { f(_2) }
        case 5 => for (_ <- _1(0); _ <- _1(1); _ <- _1(2); _ <- _1(3); _ <- _1(4)) { f(_2) }
        case _ => throw new IllegalArgumentException("too long auto sequence")
    }
}
