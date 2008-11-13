
package mada.rng


object ArrayConversion extends ArrayConversion

trait ArrayConversion {
    implicit def madaRngFromArray[A](from: Array[A]) = FromArray(from)
    implicit def madaRngToArray[A](from: Rng[A]) = from.toArray
}


object FromArray {
    def apply[A](a: Array[A]): Rng[A] = new ArrayRng(a)

    def apply[A](es: A*): Rng[A] = {
        val a = new Array[A](es.length)
        var i = 0
        for (e <- es.elements) {
            a(i) = e
            i = i + 1
        }
        apply(a)
    }
}

case class ArrayRng[A](base: Array[A]) extends IndexAccessRng[A] {
    override def _set(i: Long, e: A) { base(i.toInt) = e }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
}


object ToArray {
    def apply[A](r: Rng[A]): Array[A] = r.traversal match {
        case _: ForwardTraversal => inForward(r)
        case _: SinglePassTraversal => inForward(r.copy)
    }

    private def inForward[A](r: Rng[A]): Array[A] = {
        val a = new Array[A](r.distance.toInt)
        var i = 0
        ForeachExpr(Expr(r), {(e: A) => a(i) = e; i = i + 1}).eval
        a
    }
}
