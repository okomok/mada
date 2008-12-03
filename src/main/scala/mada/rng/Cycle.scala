
package mada.rng


object Cycle extends Cycle; trait Cycle extends Predefs {
    class MadaRngCycle[A](_1: Expr[Rng[A]]) {
        def rng_cycle(_2: Long, _3: Long) = CycleExpr(_1, _2, _3).expr
        def rng_cycle(_2: Long) = CycleExpr(_1, 0, _2).expr
        def rng_cycle = CycleExpr(_1, 0, java.lang.Long.MAX_VALUE).expr
    }
    implicit def toMadaRngCycle[A](_1: Expr[Rng[A]]): MadaRngCycle[A] = new MadaRngCycle[A](_1)
}


case class CycleExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    override def _eval = CycleImpl(_1.eval, _2, _3)
}


object CycleImpl {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        AssertNotEmpty(r)
        val (p, q) = r.toPair
        new CyclePointer(p.copy, n, p, q) <=< new CyclePointer(p.copyIn(BidirectionalTraversal), m, p, q)
    }
}

class CyclePointer[A](override val _base: Pointer[A], var count: Long, val begin: Pointer[A], val end: Pointer[A])
        extends PointerAdapter[A, A, CyclePointer[A]] {
    override def _write(e: A) { throw new NotWritablePointerError(this) }

    override def _equals(that: CyclePointer[A]) = {
        count == that.count && base == that.base
    }

    override def _increment = {
        if (base.pre_++ == end) {
            baseRef := begin.copy
            count += 1
        }
    }

    override def _copy = new CyclePointer(base.copy, count, begin, end)

    override def _decrement = {
        if (base == begin) {
            baseRef := end.copy
            count -= 1
        }
        base.pre_--
    }

    override def _offset(d: Long) = {
        val (quo, rem) = positiveRemainderDivision((base - begin) + d, end - begin)
        Assert("doh", 0 <= rem)
        Assert("doh", rem < end - begin)
        baseRef := (begin + rem)
        count += quo
    }

    override def _difference(that: CyclePointer[A]) = {
        ((end - begin) * (count - that.count)) + (base - that.base)
    }

    override def toString = new StringBuilder().append("CyclePointer of ").append(base).toString

    private def positiveRemainderDivision(a: Long, b: Long): (Long, Long) = {
        Assert("doh", b >= 0)
        val (quo, rem) = (a / b, a % b)
        if (rem < 0) {
            (quo - 1, rem + b)
        } else {
            (quo, rem)
        }
    }
}
