
package mada.rng


import End._
import FindPointerOf._


object DropWhile extends DropWhile; trait DropWhile extends Predefs {
    class MadaRngDropWhile[A](_1: ExprV2.Of[Rng[A]]) {
        def dropWhile(_2: A => Boolean) = DropWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngDropWhile[A](_1: ExprV2.Of[Rng[A]]): MadaRngDropWhile[A] = new MadaRngDropWhile[A](_1)
}


case class DropWhileExpr[A](override val _1: ExprV2.Of[Rng[A]], _2: A => Boolean) extends ExprV2.Transform[Rng[A]] {
    override def _default = {
        val z1 = _1.xlazy
        z1.findPointerOf(!_2(_)).eval <=< z1.end.eval
    }
}
