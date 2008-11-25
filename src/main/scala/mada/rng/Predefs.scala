
package mada.rng


object Predefs extends Predefs

trait Predefs extends Traits
        with DefaultCompatibles {
    implicit def toMadaRngPointerExpr[A](p: Pointer[A]): Expr[Pointer[A]] = p.toExpr
    implicit def toMadaRngExpr[A](r: Rng[A]): Expr[Rng[A]] = r.toExpr
}
