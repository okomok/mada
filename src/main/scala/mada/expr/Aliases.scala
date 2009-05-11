

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.expr


@provider
trait Aliases { this: Expr.type =>
    @aliasOf("Expr")
    type Type[Z, A] = Expr[Z, A]

    @aliasOf("Expr[_, A]")
    type Of[A] = Expr[_, A]

    @aliasOf("Expr[A, A]")
    type Identity[A] = Expr[A, A]

    @aliasOf("Expr[Nothing, A]")
    type Terminal[A] = Expr[Nothing, A]

    @aliasOf("expr.Method[A, A]")
    type Transform[A] = expr.Method[A, A]

    /**
     * @return  <code>Constant(from).expr<code>
     */
    def apply[A](from: A) = Constant(from).expr

    @alias type ConstantOf[A] = expr.ConstantOf[A]
    @alias type Method[Z, A] = expr.Method[Z, A]
    @alias type Alias[Z, A] = expr.Alias[Z, A]
    @alias val Constant = expr.Constant
    @alias type Constant[A] = expr.Constant[A]
    @alias val Lazy = expr.Lazy
    @alias type Lazy[A] = expr.Lazy[A]
    @alias val Seal = expr.Seal
    @alias type Seal[A] = expr.Seal[A]
    @alias type Start[A] = expr.Start[A]
}
