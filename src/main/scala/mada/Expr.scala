

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Expr {
    type Of[A] = Expr[_, A]

    type Identity[A] = Expr[A, A]

    type Terminal[A] = Expr[Nothing, A]

    type ConstantOf[A] = expr.ConstantOf[A]

    type Method[Z, A] = expr.Method[Z, A]

    type Transform[A] = expr.Method[A, A]

    type Alias[Z, A] = expr.Alias[Z, A]

    val Constant = expr.Constant
    type Constant[A] = expr.Constant[A]

    val Cut = expr.Cut
    type Cut[A] = expr.Cut[A]

    val Lazy = expr.Lazy
    type Lazy[A] = expr.Lazy[A]

    def apply[A](from: A) = Constant(from).expr

    type Start[A] = expr.Start[A]

    case object NoSelfCaseError extends Error
    case object NoUnknownCaseError extends Error
}


trait Expr[Z, A] {
    protected def _eval[B](x: Expr[A, B]): B
    final def eval[B](x: Expr[A, B]): B = _eval(x)
    final def eval: A = eval(Self)

    case object Self extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoSelfCaseError
    }

    case object Unknown extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoUnknownCaseError
    }

    protected def dontKnow[B](x: Expr[A, B]): B = x.eval(x.Unknown)

    final def expr = this

    final def xcut = Expr.Cut(this).expr
    final def xlazy = Expr.Lazy(this).expr

    final def / = eval
    final def ?[B](x: Expr[A, B]) = eval(x)
    final def ! = xcut
}