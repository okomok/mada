
package mada.range


// Note: "x.AsInstanceOf[N] to x.toN" is compile-time translation.


object Interval {
    def apply(n: Byte, m: Byte) = ByteInterval(n, m)
    def apply(n: Char, m: Char) = CharInterval(n, m)
    def apply(n: Short, m: Short) = ShortInterval(n, m)
    def apply(n: Int, m: Int) = IntInterval(n, m)
    def apply(n: Long, m: Long) = LongInterval(n, m)
}


// Byte

object ByteInterval {
    def apply(n: Byte, m: Byte) = new ByteIntervalPointer(n) <=< new ByteIntervalPointer(m)
}

class ByteIntervalPointer(n: Byte) extends IntervalPointer[Byte](n) {
    override def _read = base.toByte
}


// Char

object CharInterval {
    def apply(n: Char, m: Char) = new CharIntervalPointer(n) <=< new CharIntervalPointer(m)
}

class CharIntervalPointer(n: Char) extends IntervalPointer[Char](n) {
    override def _read = base.toChar
}


// Short

object ShortInterval {
    def apply(n: Short, m: Short) = new ShortIntervalPointer(n) <=< new ShortIntervalPointer(m)
}

class ShortIntervalPointer(n: Short) extends IntervalPointer[Short](n) {
    override def _read = base.toShort
}


// Int

object IntInterval {
    def apply(n: Int, m: Int) = new IntIntervalPointer(n) <=< new IntIntervalPointer(m)
}

class IntIntervalPointer(n: Int) extends IntervalPointer[Int](n) {
    override def _read = base.toInt
}


// Long

object LongInterval {
    def apply(n: Long, m: Long) = new LongIntervalPointer(n) <=< new LongIntervalPointer(m)
}

class LongIntervalPointer(n: Long) extends IntervalPointer[Long](n) {
    override def _read = base
}


// IntervalPointer

class IntervalPointer[N](private var _base: Long) extends PointerFacade[N, IntervalPointer[N]] {
    final def base = _base
    override def _traversal = RandomAccessTraversal
    override def _equals(that: IntervalPointer[N]) = _base == that._base
    override def _increment = { _base = _base + 1 }
    override def _clone = new IntervalPointer[N](_base)
    override def _hashCode = new java.lang.Long(_base).hashCode
    override def _decrement = { _base = _base - 1 }
    override def _offset(d: Long) = { _base = _base + d }
    override def _difference(that: IntervalPointer[N]) = _base - that._base
}
