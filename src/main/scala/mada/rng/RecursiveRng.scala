
package mada.rng


class RecursiveRng[A](val traversal: Traversal) extends Ref[Expr[Rng[A]]](null) with Expr[Rng[A]] {
    Assert("RecursiveRng must be Forward", traversal <:< ForwardTraversal)
    Assert("RecursiveRng can't be RandomAccess", traversal >:> BidirectionalTraversal)
    def this() = this(ForwardTraversal)
    override def _eval = RecursiveImpl(deref, traversal)
}


object RecursiveImpl {
    def apply[A](x: Expr[Rng[A]], t: Traversal): Rng[A] = {
        val z = x.xlazy
        new RecursivePointer(z, false, t) <=< new RecursivePointer(z, true, t)
    }
}

class RecursivePointer[A](rngExpr: Expr[Rng[A]], private val fromEnd: Boolean, override val _traversal: Traversal)
        extends PointerFacade[A, RecursivePointer[A]] {
    def base = { optionBaseInit; optionBase.get }
    override def _read = *(base)
    override def _write(e: A) = { *(base) = e }

    override def _equals(that: RecursivePointer[A]) = {
        if (fromEnd != that.fromEnd) {
            false
        } else if (!optionBase.isEmpty || !that.optionBase.isEmpty) {
            base == that.base
        } else {
            savedDiff == that.savedDiff
        }
    }

    override def _increment = {
        if (optionBase.isEmpty) {
            savedDiff += 1
        } else {
            base.pre_++
        }
    }

    override def _copy = {
        val that = new RecursivePointer(rngExpr, fromEnd, traversal)
        that.savedDiff = savedDiff
        that.optionBase = optionBaseCopy
        that
    }

    override def _decrement = {
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
