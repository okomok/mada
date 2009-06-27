

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.auto


case class UsedWith[A](_1: Seq[Auto[_]], _2: A) extends Auto[A] {
    override def get = _2
    override def usedBy[B](f: A => B): B = _1.size match {
        case 1 => using(_1(0)){ _ => f(_2) }
        case 2 => using(_1(0), _1(1)){ (_, _) => f(_2) }
        case 3 => using(_1(0), _1(1), _1(2)){ (_, _, _) => f(_2) }
        case 4 => using(_1(0), _1(1), _1(2), _1(3)){ (_, _, _, _) => f(_2) }
        case 5 => using(_1(0), _1(1), _1(2), _1(3), _1(4)){ (_, _, _, _, _) => f(_2) }
        case _ => throw new IllegalArgumentException("too long auto sequence")
    }
}
