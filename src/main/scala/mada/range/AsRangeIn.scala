
package mada.range


object AsRangeIn {
    def apply[A](base: Range[A], t: Traversal): Range[A] = {
        Assert("compatible traversal", base.traversal conformsTo t)
        if (t conformsTo base.traversal)
            base
        else
            new AsRangeInRange(base, t)
    }
}

class AsRangeInRange[A](val base: Range[A], trv: Traversal)
        extends PointerRange[A](new AsRangeInPointer(base.begin, trv), new AsRangeInPointer(base.end, trv)) {
    override def asRangeIn(t: Traversal) = base.asRangeIn(t)
}

class AsRangeInPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRangeInPointer[A]] {
}
