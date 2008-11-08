
package mada.range


// Note: These are considered yet another boxing.


// Bytes

object Bytes extends ((Byte, Byte) => Range[Byte]) {
    def apply(n: Byte, m: Byte) = new BytesPointer(n) <=< new BytesPointer(m)
}

class BytesPointer(private var _base: Byte) extends PointerFacade[Byte, BytesPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: BytesPointer) = _base == that._base
    override def _increment = { _base = (_base + 1).toByte }
    override def _clone = new BytesPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = { _base = (_base - 1).toByte }
    override def _offset(d: Long) = { _base = (_base + d).toByte }
    override def _difference(that: BytesPointer) = _base - that._base
}


// Shorts

object Shorts extends ((Short, Short) => Range[Short]) {
    def apply(n: Short, m: Short) = new ShortsPointer(n) <=< new ShortsPointer(m)
}

class ShortsPointer(private var _base: Short) extends PointerFacade[Short, ShortsPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: ShortsPointer) = _base == that._base
    override def _increment = { _base = (_base + 1).toShort }
    override def _clone = new ShortsPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = { _base = (_base - 1).toShort }
    override def _offset(d: Long) = { _base = (_base + d).toShort }
    override def _difference(that: ShortsPointer) = _base - that._base
}


// Chars

object Chars extends ((Char, Char) => Range[Char]) {
    def apply(n: Char, m: Char) = new CharsPointer(n) <=< new CharsPointer(m)
}

class CharsPointer(private var _base: Char) extends PointerFacade[Char, CharsPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: CharsPointer) = _base == that._base
    override def _increment = { _base = (_base + 1).toChar }
    override def _clone = new CharsPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = { _base = (_base - 1).toChar }
    override def _offset(d: Long) = { _base = (_base + d).toChar }
    override def _difference(that: CharsPointer) = _base - that._base
}


// Ints

object Ints extends ((Int, Int) => Range[Int]) {
    def apply(n: Int, m: Int) = new IntsPointer(n) <=< new IntsPointer(m)
}

class IntsPointer(private var _base: Int) extends PointerFacade[Int, IntsPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: IntsPointer) = _base == that._base
    override def _increment = { _base = _base + 1 }
    override def _clone = new IntsPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = { _base = _base - 1 }
    override def _offset(d: Long) = { _base = (_base + d).toInt }
    override def _difference(that: IntsPointer) = _base - that._base
}


// Longs

object Longs extends ((Long, Long) => Range[Long]) {
    def apply(n: Long, m: Long) = new LongsPointer(n) <=< new LongsPointer(m)
}

class LongsPointer(private var _base: Long) extends PointerFacade[Long, LongsPointer] {
    final def base = _base
    override def _read = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: LongsPointer) = _base == that._base
    override def _increment = { _base = _base + 1 }
    override def _clone = new LongsPointer(_base)
    override def _hashCode = _base.toInt // BUGBUG
    override def _decrement = { _base = _base - 1 }
    override def _offset(d: Long) = { _base = _base + d }
    override def _difference(that: LongsPointer) = _base - that._base
}
