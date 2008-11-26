
//
// Copyright (c) David Abrahams 2001.
// Copyright (c) Jeremy Siek 2001-2003.
// Copyright (c) Thomas Witt 2002.
//
// Use, modification and distribution is subject to the
// Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
//


package madatest.rng


import mada.rng._;
import junit.framework.Assert._


object ReadablePointerTest {
    def apply[T](i1: Pointer[T], v: T) {
        assertEquals(*(i1), v)
    }
}


object WritablePointerTest {
    def apply[T](i1: Pointer[T], v: T) {
        *(i1) = v
    }
}


object ForwardReadablePointerTest {
    def apply[T](iv: (Pointer[T], T), jv: (Pointer[T], T)) {
        val i2 = iv._1.copy
        val i3 = iv._1.copy
        assertEquals(i2, i3)
        AssertNotEquals(iv._1, jv._1)
        AssertNotEquals(i2, jv._1)
        ReadablePointerTest(iv._1, iv._2)
        ReadablePointerTest(i2, iv._2)
        ReadablePointerTest(i3, iv._2)

        assertEquals(iv._1, i2++)
        AssertNotEquals(iv._1, ++(i3))

        ReadablePointerTest(i2, jv._2)
        ReadablePointerTest(i3, jv._2)
        ReadablePointerTest(iv._1, iv._2)
    }
}


object BidirectionalReadablePointerTest {
    // Preconditions: *(i) == v1, *(++(i)) == v2
    def apply[A](i: Pointer[A], v1: A, v2: A) {
        val j = i.copy
        ++(j)
        ForwardReadablePointerTest((i, v1), (j, v2))
        ++(i)

        val i1 = i.copy; val i2 = i.copy

        assertEquals(i, i1--)
        AssertNotEquals(i, --(i2))

        ReadablePointerTest(i, v2)
        ReadablePointerTest(i1, v1)
        ReadablePointerTest(i2, v1)

        --(i)
        assertEquals(i, i1)
        assertEquals(i, i2)
        ++(i1)
        ++(i2)

        ReadablePointerTest(i, v1)
        ReadablePointerTest(i1, v2)
        ReadablePointerTest(i2, v2)
    }
}


object RandomAccessReadablePointerTest {
    def apply[A](i: Pointer[A], n: Int, vals: Array[A]) {
        BidirectionalReadablePointerTest(i, vals(0), vals(1))
        val j = i.copy

        var c = 0
        while (c < n-1) {
            assertEquals(i, j + c)
            assertEquals(*(i), vals(c))
            val x = j(c)
            assertEquals(*(i), x)
            assertEquals(*(i), *(j + c))
            ++(i)
            assertTrue(i > j)
            assertTrue(i >= j)
            assertTrue(j <= i)
            assertTrue(j < i)
            c = c+1
        }

        val k = j + n - 1

        c = 0
        while (c < n-1) {
            assertEquals(i, k - c)
            assertEquals(*(i), vals(n - 1 - c))
            val x = j(n - 1 - c)
            assertEquals(*(i), x)
            val q = k - c
            assertEquals(*(i), *(q))
            assertTrue(i > j)
            assertTrue(i >= j)
            assertTrue(j <= i)
            assertTrue(j < i)
            --(i)
            c = c+1
        }
    }
}
