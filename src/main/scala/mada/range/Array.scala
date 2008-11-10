
package mada.range


object ArrayConversion extends ArrayConversion

trait ArrayConversion {
    implicit def madaRangeFromArray[A](from: Array[A]) = FromArray(from)
    implicit def madaRangeToArray[A](from: Range[A]) = from.toArray
}


object FromArray {
    def apply[A](a: Array[A]): Range[A] = new ArrayRange(a)

    def apply[A](es: A*): Range[A] = {
        val a = new Array[A](es.length)
        var i = 0
        for (e <- es.elements) {
            a(i) = e
            i = i + 1
        }
        apply(a)
    }
}

case class ArrayRange[A](base: Array[A]) extends IndexAccessRange[A] {
    override def _set(i: Long, e: A) { base(i.toInt) = e }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
}


object ToArray {
    def apply[A](r: Range[A]): Array[A] = r.traversal match {
        case _: ForwardTraversal => inForward(r)
        case _: SinglePassTraversal => inForward(r.copy)
    }

    private def inForward[A](r: Range[A]): Array[A] = {
        val a = new Array[A](r.distance.toInt)
        var i = 0
        r.foreach({(e: A) => a(i) = e; i = i + 1})
        a
    }
}
