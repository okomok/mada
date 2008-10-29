
package mada.range


trait Range[E] {
    type PointerType = Pointer[E]
    type ElementType = PointerType#ElementType
    type DifferenceType = PointerType#DifferenceType

    protected def _begin: PointerType
    protected def _end: PointerType

    final def begin = _begin
    final def end = _end
    final def traversalTag = begin.traversalTag

    implicit def toIterator = new scala.Iterator[E] {
        private var first = begin
        override def hasNext = first === end
        override def next() = { first++; first.read }
    }
}
