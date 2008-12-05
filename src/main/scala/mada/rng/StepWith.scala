
package mada.rng


object StepWith extends StepWith; trait StepWith extends Predefs {
    class MadaRngStepWith[A](_1: Expr[Rng[A]]) {
        def stepWith(_2: Rng[A] => Pointer[A]) = StepWithExpr(_1, _2).expr
    }
    implicit def toMadaRngStepWith[A](_1: Expr[Rng[A]]): MadaRngStepWith[A] = new MadaRngStepWith[A](_1)
}


case class StepWithExpr[A](_1: Expr[Rng[A]], _2: Rng[A] => Pointer[A]) extends Expr[Rng[A]] {
    override def _eval = StepWithImpl(_1.eval, _2)
}


object StepWithImpl {
    def apply[A](r: Rng[A], f: Rng[A] => Pointer[A]): Rng[A] = {
        val (p, q) = r.toPair
        new StepWithPointer(p, q, f) <=< new StepWithPointer(q, q, f)
    }
}

class StepWithPointer[A](override val _base: Pointer[A], val end: Pointer[A], function: Rng[A] => Pointer[A])
        extends PointerAdapter[A, A, StepWithPointer[A]] {
    override def _write(e: A) = { throw new NotWritablePointerError(this) }
    override def _traversal = base.traversal upper ForwardTraversal
    override def _increment = { baseRef := function(base <=< end) }
    override def _copy = new StepWithPointer(base.copy, end, function)
    override def toString = new StringBuilder().append("StepWithPointer of ").append(base).toString
}
