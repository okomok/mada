

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object DropWhile extends DropWhile; trait DropWhile extends Predefs {
    class MadaRngDropWhile[A](_1: Expr.Of[Rng[A]]) {
        def dropWhile(_2: A => Boolean) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: Expr.Of[Rng[A]]): MadaRngDropWhile[A] = new MadaRngDropWhile[A](_1)
}


case class DropWhileExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Transform[Rng[A]] {
    override protected def _default = DropWhileImpl(_1.eval, _2)
}


object DropWhileImpl {
    import stl.Find._

    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        r./.stl_find(!f(_: A))./ <=< r.end
    }
}
