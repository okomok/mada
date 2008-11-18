
package mada.rng


object AsRngBy extends AsRngBy

trait AsRngBy extends Predefs {
    class MadaRngAsRngBy[A](_1: Expr[Rng[A]]) {
        def asRngBy(_2: Expr[Traversal]) = AsRngByExpr(_1, _2).expr
    }
    implicit def toMadaRngAsRngBy[A](_1: Expr[Rng[A]]): MadaRngAsRngBy[A] = new MadaRngAsRngBy[A](_1)
}


case class AsRngByExpr[A](_1: Expr[Rng[A]], _2: Expr[Traversal]) extends Expr[Rng[A]] {
    override def _eval = AsRngByImpl(_1.eval, _2.eval)
}


object AsRngByImpl {
    def apply[A](r: Rng[A], t: Traversal): Rng[A] = {
        Assert("requires compatible traversals", r.traversal <:< t)
        if (t <:< r.traversal)
            r
        else
            new AsRngByPointer(r.begin, t) <=< new AsRngByPointer(r.end, t)
    }
}

class AsRngByPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRngByPointer[A]] {
}
