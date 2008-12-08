
package mada.rng


object AsRngBy extends AsRngBy; trait AsRngBy extends Predefs {
    class MadaRngAsRngBy[A](_1: Expr.Of[Rng[A]]) {
        def asRngBy(_2: Traversal) = AsRngByExpr(_1, _2).expr
    }
    implicit def toMadaRngAsRngBy[A](_1: Expr.Of[Rng[A]]): MadaRngAsRngBy[A] = new MadaRngAsRngBy[A](_1)
}


case class AsRngByExpr[A](override val _1: Expr.Of[Rng[A]], _2: Traversal) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case AsRngByExpr(x1, x2) => {
            Assert("requires compatible Traversals", x2 <:< _2)
            AsRngByExpr(x1, _2).eval // asRngBy-asRngBy fusion
        }
        case _ => AsRngByImpl(_1.eval, _2)
    }
}


object AsRngByImpl {
    def apply[A](r: Rng[A], t: Traversal): Rng[A] = {
        Assert("requires compatible Traversals", r.traversal <:< t)
        if (t <:< r.traversal)
            r
        else
            new AsRngByPointer(r.begin, t) <=< new AsRngByPointer(r.end, t)
    }
}

class AsRngByPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRngByPointer[A]] {
    override protected def _copy = new AsRngByPointer(base.copy, traversal)
    override def toString = new StringBuilder().append("AsRngByPointer of ").append(base).toString
}
