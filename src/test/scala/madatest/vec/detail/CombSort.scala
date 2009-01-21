

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.detail


object CombSort {
    import mada._

    def apply[A](v: Vector[A], f: (A, A) => Boolean): Unit = {
        val (x, first, last) = v.triple

        var gap = last - first
        if (gap < 1)
            return

        var first2 = last
        var swapped = false
        do {
            var newgap = (gap * 10 + 3) / 13
            if (newgap < 1)
                newgap = 1

            first2 += (newgap - gap) // paren is needed in scala 2.7.1
            gap = newgap
            swapped = false

            var (target1, target2) = (first, first2)
            while (target2 != last) {
                if (f(x(target2), x(target1))) {
                    vec.stl.IterSwap(x, target1, x, target2)
                    swapped = true
                }
                target1 += 1; target2 += 1
            }
        } while ((gap > 1) || swapped)
    }

    def apply[A <% Ordered[A]](v: Vector[A]): Unit = apply(v, (_: A) < (_: A))
}



class CombSortTest {
    import mada._
    import junit.framework.Assert._

    def expected = Vector.arrayVector(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23))
    def example =  Vector.arrayVector(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4))

    def testTrivial: Unit ={
        val v = example
        CombSort(v)
        assertEquals(expected, v)
    }
}
