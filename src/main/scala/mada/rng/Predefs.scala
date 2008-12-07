
package mada.rng


object Predefs extends Predefs; trait Predefs extends Namespace
        with Compatibles {
    implicit def toMadaRngPointerExpr[A](p: => Pointer[A]): Expr[Pointer[A]] = Expr(p)
    implicit def toMadaRngExpr[A](r: => Rng[A]): Expr[Rng[A]] = Expr(r)

    implicit def madaRng2ExprV2Pointer[A](p: Pointer[A]): ExprV2.Of[Pointer[A]] = ExprV2.Constant(p)
    implicit def madaRng2ExprV2Rng[A](r: Rng[A]): ExprV2.Of[Rng[A]] = ExprV2.Constant(r)
}
