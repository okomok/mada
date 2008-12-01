
package mada.rng


object Predefs extends Predefs; trait Predefs extends Namespace
        with DefaultCompatibles {
    implicit def toMadaRngPointerExpr[A](p: => Pointer[A]): Expr[Pointer[A]] = Expr(p)
    implicit def toMadaRngExpr[A](r: => Rng[A]): Expr[Rng[A]] = Expr(r)
}
