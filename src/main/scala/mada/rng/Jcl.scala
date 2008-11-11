
package mada.rng


import java.util.ArrayList


object JclConversion extends JclConversion

trait JclConversion {
    implicit def madaRngFromArrayList[A](from: java.util.ArrayList[A]) = FromArrayList(from)
    implicit def madaRngToArrayList[A](from: Rng[A]) = from.toArrayList
}


object FromArrayList {
    def apply[A](a: ArrayList[A]): Rng[A] = new ArrayListRng(a)

    def apply[A](es: A*): Rng[A] = {
        val a = new ArrayList[A](es.length)
        for (e <- es.elements) {
            a.add(e)
        }
        apply(a)
    }
}

class ArrayListRng[A](a: ArrayList[A]) extends IndexAccessRng[A] {
    override def _set(i: Long, e: A) { a.set(i.toInt, e) }
    override def _get(i: Long) = a.get(i.toInt)
    override def _size = a.size
}


object ToArrayList {
    def apply[A](r: Rng[A]): ArrayList[A] = {
        var a: ArrayList[A] = newArrayList(r)
        r.foreach({(e: A) => a.add(e)})
        a
    }

    private def newArrayList[A](r: Rng[A]) = r.traversal match {
        case _: RandomAccessTraversal => new ArrayList[A](r.size.toInt)
        case _: SinglePassTraversal => new ArrayList[A]
    }
}
