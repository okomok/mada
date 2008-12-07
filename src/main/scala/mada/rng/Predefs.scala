
package mada.rng


object Predefs extends Predefs; trait Predefs extends Namespace
        with Compatibles {
    implicit def madaRng2ExprV2Pointer[A](p: Pointer[A]): Expr.Of[Pointer[A]] = Expr.Constant(p)
    implicit def madaRng2ExprV2Rng[A](r: Rng[A]): Expr.Of[Rng[A]] = Expr.Constant(r)
}
