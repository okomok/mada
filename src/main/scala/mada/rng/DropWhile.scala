
package mada.rng


import End._
import FindPointerOf._


object DropWhile extends DropWhile; trait DropWhile extends Predefs {
    class MadaRngDropWhile[A](_1: Expr[Rng[A]]) {
        def rng_dropWhile(_2: A => Boolean) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: Expr[Rng[A]]): MadaRngDropWhile[A] = new MadaRngDropWhile[A](_1)
}


case class DropWhileExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Rng[A]] {
    override def _eval = {
        val z1 = _1.lazy_
        z1.rng_findPointerOf(!_2(_)).eval <=< z1.rng_end.eval
    }
}
