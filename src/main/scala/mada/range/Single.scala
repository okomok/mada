
package mada.range


object SingleConversion extends SingleConversion

trait SingleConversion {
    implicit def madaRangeFromSingle[A](from: A) = FromSingle(from)
    implicit def madaRangeToSingle[A](from: Range[A]): A = from.first
}


object FromSingle {
    def apply[A](b: A) = new SingleRange(b)
}

class SingleRange[A](base: A) extends IndexAccessRange[A] {
    override def _get(i: Long) = { Assert("out of SingleRange", i == 0); base }
    override def _size = 1
}
