

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


// val k = r.force
// k.sort....
// r.outplace.sort.reverse...
// r.sort.
// r.reverse.mutate

/*

object Outplace extends Outplace; trait Outplace extends Predefs {
    class MadaRngOutplace[A](_1: Expr.Of[Rng[A]]) {
        def outplace(_2: Rng[A] => Any) = OutplaceExpr(_1, _2).expr
    }
    implicit def toMadaRngOutplace[A](_1: Expr.Of[Rng[A]]): MadaRngOutplace[A] = new MadaRngOutplace[A](_1)
}


case class OutplaceExpr[A](_1: Expr.Of[Rng[A]], _2: Rng[A] => Any) extends Expr.Transform[Rng[A]] {
    override protected def _default = OutplaceImpl(_1.eval, _2)
}


object OutplaceImpl {
    def apply[A](r: Rng[A], f: Rng[A] => Any): Rng[A] = {
        f(r./.force./)
        r
    }
}

*/
