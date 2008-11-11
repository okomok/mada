
package mada.rng


object SingleConversion extends SingleConversion

trait SingleConversion {
    implicit def madaRngFromSingle[A](from: A) = FromSingle(from)
    implicit def madaRngToSingle[A](from: Rng[A]): A = from.first
}


object FromSingle {
    def apply[A](b: A) = new SingleRng(b)
}

class SingleRng[A](base: A) extends IndexAccessRng[A] {
    override def _get(i: Long) = { Assert("out of SingleRng", i == 0); base }
    override def _size = 1
}
