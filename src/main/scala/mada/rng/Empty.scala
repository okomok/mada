

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Empty extends Empty; trait Empty extends Predefs {
     def empty[A] = EmptyExpr[A]().expr
}


case class EmptyExpr[A]() extends Expr.Alias[Unit, Rng[A]] {
    override protected def _alias = IndexAccessRngExpr(new EmptyIndexAccess[A])
}

class EmptyIndexAccess[A] extends IndexAccess[A] {
    override def _get(i: Long) = throw new java.lang.AssertionError("out of EmptyRng")
    override def _size = 0
    override def toString = "EmptyIndexAccess"
}
