
package mada.rng


object AsRngIn {
    def apply[A](base: Rng[A], t: Traversal): Rng[A] = {
        Assert("requires compatible traversals", base.traversal conformsTo t)
        if (t conformsTo base.traversal)
            base
        else
            new AsRngInRng(base, t)
    }
}

class AsRngInRng[A](val base: Rng[A], trv: Traversal) extends Rng[A] {
    override val _begin = new AsRngInPointer(base.begin, trv)
    override val _end = new AsRngInPointer(base.end, trv)

    override def asRngIn(t: Traversal) = base.asRngIn(t)
}

class AsRngInPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRngInPointer[A]] {
}
