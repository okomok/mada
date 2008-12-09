
package mada.rng


import Foreach._


// foldLeft

object FoldLeft extends FoldLeft; trait FoldLeft extends Predefs {
    class MadaRngFoldLeft[A](_1: Expr.Of[Rng[A]]) {
        def foldLeft[B](_2: B, _3: (B, A) => B) = FoldLeftExpr(_1, _2, _3).expr
//        def /:[B](_2: B)(_3: (B, A) => B) = FoldLeftExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldLeft[A](_1: Expr.Of[Rng[A]]): MadaRngFoldLeft[A] = new MadaRngFoldLeft[A](_1)
}

case class FoldLeftExpr[A, B](override val _1: Expr.Of[Rng[A]], _2: B, _3: (B, A) => B) extends Expr.Method[Rng[A], B] {
    override protected def _default = FoldLeftImpl(_1.eval, _2, _3)
}

object FoldLeftImpl {
    def apply[A, B](r: Rng[A], z: B, f: (B, A) => B): B = {
        var acc = z
        r./.foreach({ (e: A) => acc = f(acc, e) })./
        acc
    }
}


// foldRight

object FoldRight extends FoldRight; trait FoldRight extends Predefs {
    class MadaRngFoldRight[A](_1: Expr.Of[Rng[A]]) {
        def foldRight[B](_2: B, _3: (A, B) => B) = FoldRightExpr(_1, _2, _3).expr
//        def :\[B](_2: B)(_3: (A, B) => B) = FoldRightExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldRight[A](_1: Expr.Of[Rng[A]]): MadaRngFoldRight[A] = new MadaRngFoldRight[A](_1)
}


case class FoldRightExpr[A, B](_1: Expr.Of[Rng[A]], _2: B, _3: (A, B) => B) extends Expr.Alias[Rng[A], B] {
    override protected def _alias = FoldLeftExpr(ReverseExpr(_1), _2, { (b: B, a: A) => _3(a, b) })
}
