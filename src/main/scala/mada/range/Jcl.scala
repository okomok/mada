
package mada.range


import java.util.ArrayList


object FromArrayList {
    def apply[A](a: ArrayList[A]): Range[A] = {
        val ia = new IndexAccess[A] {
            override def _set(i: Long, e: A) = a.set(i.toInt, e)
            override def _get(i: Long) = a.get(i.toInt)
            override def _size = a.size
        }
        new IndexAccessRange(ia) {
            override def toArrayList = a
        }
    }

    def apply[A](es: A*): Range[A] = {
        val a = new ArrayList[A](es.length)
        for (e <- es.elements) {
            a.add(e)
        }
        apply(a)
    }
}

object ToArrayList {
    def apply[A](r: Range[A]): ArrayList[A] = {
        var a: ArrayList[A] = newArrayList(r)
        r.foreach({(e: A) => a.add(e)})
        a
    }

    private def newArrayList[A](r: Range[A]) = r.traversal match {
        case RandomAccessTraversal => new ArrayList[A](r.size.toInt)
        case SinglePassTraversal => new ArrayList[A]
    }
}
