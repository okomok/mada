

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Expr</code>.
 */
object Expr {


// aliases

    /**
     * Alias of <code>Expr[_, A]</code>
     */
    type Of[A] = Expr[_, A]

    /**
     * Alias of <code>Expr[A, A]</code>
     */
    type Identity[A] = Expr[A, A]

    /**
     * Alias of <code>Expr[Nothing, A]</code>
     */
    type Terminal[A] = Expr[Nothing, A]

    /**
     * Alias of <code>expr.ConstantOf</code>
     */
    type ConstantOf[A] = expr.ConstantOf[A]

    /**
     * Alias of <code>expr.Method</code>
     */
    type Method[Z, A] = expr.Method[Z, A]

    /**
     * Alias of <code>expr.Method[A, A]</code>
     */
    type Transform[A] = expr.Method[A, A]

    /**
     * Alias of <code>expr.Alias</code>
     */
    type Alias[Z, A] = expr.Alias[Z, A]

    /**
     * Alias of <code>expr.Constant</code>
     */
    val Constant = expr.Constant

    /**
     * Alias of <code>expr.Constant</code>
     */
    type Constant[A] = expr.Constant[A]

    /**
     * Alias of <code>expr.Lazy</code>
     */
    val Lazy = expr.Lazy

    /**
     * Alias of <code>expr.Lazy</code>
     */
    type Lazy[A] = expr.Lazy[A]

    /**
     * Alias of <code>expr.Seal</code>
     */
    val Seal = expr.Seal

    /**
     * Alias of <code>expr.Seal</code>
     */
    type Seal[A] = expr.Seal[A]

    /**
     * @return  <code>Constant(from).expr<code>
     */
    def apply[A](from: A) = Constant(from).expr

    /**
     * Alias of <code>expr.Start</code>
     */
    type Start[A] = expr.Start[A]

    /**
     * Alias of <code>Expr</code>
     */
    type Type[Z, A] = Expr[Z, A]


// exceptions

    /**
     * Thrown if Self case missing.
     */
    case object NoSelfCaseError extends Error

    /**
     * Thrown if case unknown.
     */
    case object NoUnknownCaseError extends Error
}


/**
 * Replaces expression evaluations with modern and heavy way.
 */
trait Expr[Z, A] {

    /**
     * Overridden in subclasses.
     */
    protected def _eval[B](x: Expr[A, B]): B

    /**
     * @return  <code>_eval(x)</code>.
     */
    final def eval[B](x: Expr[A, B]): B = _eval(x)

    /**
     * @return  <code>eval(Self)</code>.
     */
    final def eval: A = eval(Self)

    /**
     * Self evaluation case
     */
    case object Self extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoSelfCaseError
    }

    /**
     * Unknown evaluation case
     */
    case object Unknown extends Expr.Identity[A] {
        override protected def _eval[B](x: Expr[A, B]): B = throw Expr.NoUnknownCaseError
    }

    /**
     * @return  <code>x.eval(x.unknown)</code>.
     */
    protected def dontKnow[B](x: Expr[A, B]): B = x.eval(x.Unknown)

    /**
     * @return  <code>this</code>.
     */
    final def expr = this

    /**
     * @return  <code>Expr.Lazy(this).expr</code>.
     */
    final def xlazy = Expr.Lazy(this).expr

    /**
     * @return  <code>Expr.Seal(this).expr</code>.
     */
    final def xseal = Expr.Seal(this).expr

    /**
     * Alias of <code>eval</code>
     */
    final def / = eval

    /**
     * Alias of <code>eval</code>
     */
    final def ?[B](x: Expr[A, B]) = eval(x)

    /**
     * Alias of <code>xseal</code>
     */
    final def ! = xseal

    /**
     * Alias of <code>Expr</code>
     */
    final def companion = Expr
}