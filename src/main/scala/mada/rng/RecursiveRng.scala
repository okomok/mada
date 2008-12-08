
package mada.rng


class RecursiveRng[A](val traversal: Traversal) extends Ref[Expr.Of[Rng[A]]](null) with Expr.ConstantOf[Rng[A]] {
    Assert("RecursiveRng must be Forward", traversal <:< ForwardTraversal)
    Assert("RecursiveRng can't be RandomAccess", traversal >:> BidirectionalTraversal)
    def this() = this(ForwardTraversal)
    override def _of = RecursiveImpl(deref, traversal)
}


object RecursiveImpl {
    def apply[A](x: Expr.Of[Rng[A]], t: Traversal): Rng[A] = {
        val z = x.xlazy
        new RecursivePointer(z, false, t) <=< new RecursivePointer(z, true, t)
    }
}

class RecursivePointer[A](rngExpr: Expr.Of[Rng[A]], private val fromEnd: Boolean, override val _traversal: Traversal)
        extends PointerFacade[A, RecursivePointer[A]] {
    def base = { optionBaseInit; optionBase.get }
    override protected def _read = *(base)
    override protected def _write(e: A) = { *(base) = e }

    override protected def _equals(that: RecursivePointer[A]) = {
        if (fromEnd != that.fromEnd) {
            false
        } else if (!optionBase.isEmpty || !that.optionBase.isEmpty) {
            base == that.base
        } else {
            savedDiff == that.savedDiff
        }
    }

    override protected def _increment = {
        if (optionBase.isEmpty) {
            savedDiff += 1
        } else {
            base.pre_++
        }
    }

    override protected def _copy = {
        val that = new RecursivePointer(rngExpr, fromEnd, traversal)
        that.savedDiff = savedDiff
        that.optionBase = optionBaseCopy
        that
    }

    override protected def _decrement = {
        if (optionBase.isEmpty) {
           savedDiff -= 1
        } else {
            base.pre_--
        }
    }

    private var savedDiff = 0L
    private var optionBase: Option[Pointer[A]] = None

    private def optionBaseInit = {
        if (optionBase.isEmpty) {
            optionBase = new Some(if (fromEnd) rngExpr.eval.end else rngExpr.eval.begin)
            optionBase.get.advance(savedDiff)
        }
    }

    private def optionBaseCopy: Option[Pointer[A]] = {
        if (optionBase.isEmpty) {
            None
        } else {
            new Some(optionBase.get.copy)
        }
    }

    override def hashCode = base.hashCode
    override def toString = new StringBuilder().append("RecursivePointer of ").append(base).toString
}
