
package mada.range


object AsRangeIn {
    def apply[A](base: Range[A], t: Traversal): Range[A] = {
        Assert("requires compatible traversals", base.traversal conformsTo t)
        if (t conformsTo base.traversal)
            base
        else
            new AsRangeInRange(base, t)
    }
}

class AsRangeInRange[A](val base: Range[A], trv: Traversal) extends Range[A] {
    override val _begin = new AsRangeInPointer(base.begin, trv)
    override val _end = new AsRangeInPointer(base.end, trv)

    override def asRangeIn(t: Traversal) = base.asRangeIn(t)
}

class AsRangeInPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRangeInPointer[A]] {
}
