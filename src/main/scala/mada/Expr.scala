

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Expr</code>.
 */
object Expr extends expr.Aliases {
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

    @returncompanion def companion = Expr

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

    @returnThis
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
}