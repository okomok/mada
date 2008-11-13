
package mada.rng


// Note: "x.AsInstanceOf[N] to x.toN" seems compile-time translation.


object Interval {
    def apply(n: Byte, m: Byte) = ByteInterval(n, m).rng
    def apply(n: Char, m: Char) = CharInterval(n, m).rng
    def apply(n: Short, m: Short) = ShortInterval(n, m).rng
    def apply(n: Int, m: Int) = IntInterval(n, m).rng
    def apply(n: Long, m: Long) = LongInterval(n, m).rng
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

class IntervalPointer[N](var base: Long) extends PointerFacade[N, IntervalPointer[N]] {
    override def _traversal = RandomAccessTraversal
    override def _equals(that: IntervalPointer[N]) = base == that.base
    override def _increment { base = base + 1 }
    override def _clone = new IntervalPointer[N](base)
    override def _hashCode = long2Long(base).hashCode
    override def _decrement { base = base - 1 }
    override def _offset(d: Long) { base = base + d }
    override def _difference(that: IntervalPointer[N]) = base - that.base
}
