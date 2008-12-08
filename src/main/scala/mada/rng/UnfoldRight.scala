
package mada.rng


object UnfoldRight extends UnfoldRight; trait UnfoldRight extends Predefs {
    final def unfoldRight[From, To](_1: From, _2: From => Option[(To, From)]) = UnfoldRightExpr(_1, _2).expr
}


case class UnfoldRightExpr[From, To](_1: From, _2: From => Option[(To, From)]) extends Expr.ConstantFrom[Rng[To]] {
    override protected def _from = UnfoldRightImpl(_1, _2)
}


object UnfoldRightImpl {
    def apply[From, To](z: From, f: From => Option[(To, From)]): Rng[To] = {
        new UnfoldRightPointer(f(z), f) <=< new UnfoldRightPointer(None, f)
    }
}

// ForwardTraversal would need state is copyable.
class UnfoldRightPointer[From, To](var state: Option[(To, From)], val function: From => Option[(To, From)])
        extends PointerFacade[To, UnfoldRightPointer[From, To]] {
    override protected def _read = state.get._1
    override protected def _traversal = SinglePassTraversal
    override protected def _equals(that: UnfoldRightPointer[From, To]) = state.isEmpty == that.state.isEmpty
    override protected def _increment = { state = function(state.get._2) }
}
