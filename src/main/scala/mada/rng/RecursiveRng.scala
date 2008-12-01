
package mada.rng


/*
val x = new RecursiveRng[A]
x := a.rng_concat(x) // x.deref = a.rng_concat( x ).rng_filter(f) // x.deref Expr(..Expr(...(x)))


x := x // x.deref = x
val r = x.eval // RecursiveImpl(x) // does nothing.
r.begin // base touched -> x.eval.begin --> stack overflows.

r.begin // touches base by copy methods.
r.begin // does nothing. See .copy method // but NOT thread-safe.

while eval, RecursivePointer doesn't touch base... how?
if ConcatExpr.eval calls *(p), stack overflows.




??? def fun: Expr[Rng[A]] = a.rng_concat(fun())
*/


class RecursiveRng[A](val traversal: Traversal) extends Ref[Expr[Rng[A]]](null) with Expr[Rng[A]] {
    Assert("RecursiveRng must be Forward", traversal <:< ForwardTraversal)
    Assert("RecursiveRng can't be RandomAccess", traversal >:> BidirectionalTraversal)
    def this() = this(ForwardTraversal)
    override def _eval = RecursiveImpl(deref, traversal)
}


object RecursiveImpl {
    def apply[A](x: Expr[Rng[A]], t: Traversal): Rng[A] = {
        val z = x.lazy_
        new Rng[A] { // avoid PointerRng forcing to copy.
            override def _begin = new RecursivePointer(z.eval.begin, t)
            override def _end = new RecursivePointer(z.eval.end, t)
        }
    }
}

class RecursivePointer[A](_base: => Pointer[A], override val _traversal: Traversal) // by-name
        extends PointerFacade[A, RecursivePointer[A]] {
    lazy val base = _base
    override def _read = *(base)
    override def _write(e: A) { *(base) = e }
    override def _equals(that: RecursivePointer[A]) = base == that.base
    override def _increment { base.pre_++ }
    override def _copy = { val b = base.copy; new RecursivePointer(b, traversal) }
    override def _decrement { base.pre_-- }
    override def hashCode = base.hashCode
    override def toString = new StringBuilder().append("RecursivePointer of ").append(base).toString
}
