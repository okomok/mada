
package mada.range


object Permutation {
    def apply[A](elements: Range[A], indices: Range[Long]): Range[A] = {
        Assert("requires RandomAccessRange", elements models RandomAccessTraversal)
        new PermutationRange(elements, indices)
    }
}

class PermutationRange[A](val elements: Range[A], indices: Range[Long]) extends Range[A] {
    val pe = elements.begin
    override val _begin = new PermutationPointer(indices.begin, pe)
    override val _end = new PermutationPointer(indices.end, pe)
}

class PermutationPointer[A](override val _base: Pointer[Long], val elementsBegin: Pointer[A])
        extends PointerAdapter[Long, A, PermutationPointer[A]] {
    override def _read = *(elementsBegin + *(base))
    override def _clone = new PermutationPointer(base.clone, elementsBegin)
}
