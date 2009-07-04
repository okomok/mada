

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.cpstest


import junit.framework.Assert._


object cps {

    final class ControlContext[+A,-B,+C](val fun: (A => B) => C) {

        final def map[A1](f: A => A1): ControlContext[A1,B,C] = {
            new ControlContext( (k: A1 => B) =>
                fun { (x: A) =>
                    k(f(x))
                }
            )
        }
/*
        final def flatMap[A1, B1, C1 <: B](f: A => ControlContext[A1, B1, C1]): ControlContext[A1, B1, C] = {
            new ControlContext( (k: A1 => B1) =>
                fun { (x: A) =>
                    val res: C1 = f(x).fun(k)
                    res
                }
            )
        }

        final def foreach(f: A => B) = {
            fun(f)
        }
*/
    }

    def shift[A, B, C](fun: (A => B) => C): ControlContext[A, B, C] = new ControlContext(fun)

    def reset[A, C](c: ControlContext[A, A, C]): C = c.fun { (x: A) => x }

}


class CpsTest {
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
        def foo(): ControlContext[Int, Int, Int] = {
            for {
                x <- shift { (k: Int => Int) => k(k(k(7))) }
            } yield {
                x + 1
            }
        }

        def bar(): ControlContext[Int, Int, Int] = {
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

    def testPrintf: Unit = {
        val fromInt = (x: Int) => x.toString
        val fromStr = (x: String) => x

        def format[A, B](toStr: A => String) = shift { (k: String => B) => (x: A) => k(toStr(x)) }
        def sprintf[A](str: ControlContext[String, String, A]) = reset(str)


        ()





    }





}
