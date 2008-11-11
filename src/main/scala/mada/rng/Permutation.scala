
package mada.rng


object Permutation {
    def apply[A](elements: Rng[A], indices: Rng[Long]): Rng[A] = {
        Assert("requires RandomAccessRng", elements models RandomAccessTraversal)
        new PermutationRng(elements, indices)
    }
}

class PermutationRng[A](val elements: Rng[A], indices: Rng[Long]) extends Rng[A] {
    val pe = elements.begin
    override val _begin = new PermutationPointer(indices.begin, pe)
    override val _end = new PermutationPointer(indices.end, pe)
}

class PermutationPointer[A](override val _base: Pointer[Long], val elementsBegin: Pointer[A])
        extends PointerAdapter[Long, A, PermutationPointer[A]] {
    override def _read = *(elementsBegin + *(base))
    override def _clone = new PermutationPointer(base.clone, elementsBegin)
}
