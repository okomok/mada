

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package cpstest


import com.github.okomok.mada

import junit.framework.Assert._


object cps {

    final class Shift[+A, -B, +C](val fun: (A => B) => C) {

        final def map[A1](f: A => A1): Shift[A1, B, C] = {
            new Shift( (k: A1 => B) =>
                fun { (x: A) =>
                    k(f(x))
                }
            )
        }

        final def flatMap[A1, B1](f: A => Shift[A1, B1, B]): Shift[A1, B1, C] = {
            new Shift( (k: A1 => B1) =>
                fun { (x: A) =>
                    f(x).fun(k)
                }
            )
        }
/*
        final def foreach(f: A => B) = {
            fun(f)
        }
*/
    }

    def shift[A, B, C](fun: (A => B) => C): Shift[A, B, C] = new Shift(fun)
    def reset[A, C](c: Shift[A, A, C]): C = c.fun { (x: A) => x }

}


class CpsTezt {
    import cps._

    def testSugar: Unit = {
        val r =
        reset {
            for {
                x <- shift { (k: Int => Int) => k(k(k(7))) }
            } yield {
                x + 1
            }
        }

        assertEquals(10, r)
    }

    def testDesugar: Unit = {
        val r =
        reset {
            shift { (k: Int => Int) => k(k(k(7))) }.
                map(_ + 1)
        }

        assertEquals(10, r)

    }

    def testFig1_1: Unit = {
        shift { (k: Int => Int) =>
            "done here"
        }
    }

    def testFig1_2: Unit = {
        val r =
        reset {
            shift { (k: Int => Int) =>
                k(7)
            }
        }

        assertEquals(7, r)
    }

    def testFig1_3: Unit = {
        val r =
        reset {
            for {
                x <- shift { (k: Int => Int) => k(7) }
            } yield {
                x + 1
            }
        }

        assertEquals(16, r * 2)
    }

    def testFig1_4: Unit = {
        val r =
        reset {
            for {
                x <- shift { (k: Int => Int) =>
                    k(k(k(7)))
                }
            } yield {
                x + 1
            }
        }

        assertEquals(20, r * 2)
    }

    def testFig1_5: Unit = {
        val r =
        reset {
            for {
                x <- shift { (k: Int => Int) =>
                    k(k(k(7))); "done"
                }
            } yield {
                x + 1
            }
        }

        assertEquals("done", r)
    }

    def testFig1_6: Unit = {
        def foo(): Shift[Int, Int, Int] = {
            for {
                x <- shift { (k: Int => Int) => k(k(k(7))) }
            } yield {
                x + 1
            }
        }

        def bar(): Shift[Int, Int, Int] = {
            for {
                x <- foo()
            } yield {
                x * 2
            }
        }

        def baz() = {
            reset(bar())
        }

        assertEquals(70, baz()) // (7)+1)*2 )+1)*2 )+1)*2
    }

    def testSideEffect: Unit = {
        var msg: String = ""
        def _println(s: Any) = { msg = msg + s }

    // sugar-form
        _println(1)
        reset {
            for {
                x <- shift { (k: Unit => Unit) =>
                    k()
                    k()
                    _println(2)
                }
            } yield {
                x // Placed only for consistent syntax.
                _println(3)
            }
        }
        assertEquals("1332", msg)
        msg = ""

    // desugar-form
        _println(1)
        reset {
            shift { (k: Unit => Unit) =>
                k()
                k()
                _println(2)
            }.map {
                x => {
                    x
                    _println(3)
                }
            }
        }
        assertEquals("1332", msg)
        msg = ""

        ()
    }

    def testPrintf: Unit = {
        val fromInt = (x: Int) => x.toString
        val fromStr = (x: String) => x

        def format[A, B](toStr: A => String): Shift[String, B, A => B] = {
            shift { (k: String => B) =>
                (x: A) => k(toStr(x))
            }
        }

        def sprintf[A](str: Shift[String, String, A]) = reset(str)

        val f1 = sprintf {
            shift { (k: String => String) =>
                "Hello World!"
            }
        }
        assertEquals("Hello World!", f1)

        val f2 = sprintf {
            for {
                x <- format[String, String](fromStr)
            } yield {
                "Hello " + x + "!"
            }
        }
        assertEquals("Hello World!", f2("World"))

        val f3 = sprintf {
            for {
                x <- format[String, Int => String](fromStr)
                y <- format[Int, String](fromInt)
            } yield {
                "The value of " + x + " is " + y + "."
            }
        }
        assertEquals("The value of x is 3.", f3("x")(3))
    }

    def testMonad: Unit = {
        import mada.sequence._ // Scala 2.8 collection flatMap signature is complicated.

        type Monadic[+U, C[_]] = {
            def flatMap[V](f: U => C[V]): C[V]
        }

        class Reflective[+A, C[_]](xs: Monadic[A, C]) {
            def reflect[B](): Shift[A, C[B], C[B]] = shift { (k: A => C[B]) =>
                xs.flatMap(k)
            }
        }

        implicit def reflective[A](xs: List[A]) = new Reflective[A, List](xs)

        val r = reset {
            val left = List("x", "y", "z")
            val right = List(4, 5, 6)

            for {
                l <- left.reflect[Any]
                r <- right.reflect[Any]
            } yield {
                List(Tuple2(l, r))
            }
        }

        assertEquals(Tuple2("y", 5), r.nth(4))
    }

    def testYieldReturn: Unit = {

        abstract class YieldReturnIterator[A] extends Iterator[A] {
            private var nextValue: Option[Option[A]] = None
            private var getNext: Unit => Unit = { (_: Unit) =>
                reset {
                    for {
                        x <- body
                    } yield {
                        x // Placed only for consistent syntax.
                        nextValue = Some(None)
                        getNext = null
                    }
                }
            }

            protected def yieldReturn(e: A): Shift[Unit, Unit, Unit] = {
                shift { (k: Unit => Unit) =>
                    nextValue = Some(Some(e))
                    getNext = k
                }
            }

            protected def body: Shift[Unit, Unit, Unit]

            override def hasNext = {
                if (nextValue.isEmpty) {
                    getNext()
                }
                !nextValue.get.isEmpty
            }

            override def next = {
                if (nextValue.isEmpty) {
                    getNext()
                }
                val e = nextValue.get.get
                nextValue = None
                e
            }
        }

        class My extends YieldReturnIterator[String] {
            override def body = {
                for {
                    x1 <- yieldReturn("ab")
                    x2 <- yieldReturn("cdef")
                    x3 <- yieldReturn("g")
                } yield {
                    x1 // Placed only for consistent syntax.
                    x2 // Placed only for consistent syntax.
                    x3 // Placed only for consistent syntax.
                }
            }
        }

        val m = new My
        assertEquals("ab", m.next)
        assertEquals("cdef", m.next)
        assertEquals("g", m.next)
        assertFalse(m.hasNext)
    }

}
