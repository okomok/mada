
package mada.range


trait Range[E] {
    protected def _begin: Pointer[E]
    protected def _end: Pointer[E]

    final def begin = _begin
    final def end = _end
    final def traversalTag = begin.traversalTag

    final def ->[To](f: RangeFunction[To]): To = (f.fromRange[E])(this)
    final def ->(f: RangeTransformation): Range[E] = (f.fromRange[E])(this)
    final def ->(f: OutdirectFunction): Range[Pointer[E]] = (f.fromRange[E])(this)

    final def toIterator: Iterator[E] = new RangeIterator(this)
}
