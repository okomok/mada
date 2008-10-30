
package mada.range


class RangeIterator[E](r: Range[E]) extends Iterator[E] {
    private val p = r.begin
    private val q = r.end
    override def hasNext = p != q
    override def next = { p++/; p.read }
}
