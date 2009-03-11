

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Iterables
import mada.Vector
import junit.framework.Assert._


class IterablesTest {
    def testEquals: Unit = {
        assertFalse(Iterables.equal(Iterable.empty, Iterables(1,2,3)))
        assertFalse(Iterables.equal(Iterables(1,2), Iterables(1,2,3)))
        assertTrue(Iterables.equal(Iterables(1,2,3), Iterables(1,2,3)))
        assertTrue(Iterables.equal(Iterable.empty, Iterable.empty))
    }

    def testLength: Unit = {
        assertEquals(3, Iterables.length(Iterables(1,2,3)))
        assertEquals(0, Iterables.length(Iterable.empty))
    }

    def testUnfoldRight: Unit = {
        val it = Iterables.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertTrue(Iterables.equal(it, Iterables(10,9,8,7,6,5,4,3,2,1)))
    }

    def testIterate: Unit = {
        val it = Iterables.take(Iterables.iterate(10){ b => b-1 }, 10)
        assertTrue(Iterables.equal(it, Iterables(10,9,8,7,6,5,4,3,2,1)))
    }

    def testRepeat: Unit = {
        val it = Iterables.take(Iterables.repeat(3), 10)
        assertTrue(Iterables.equal(it, Iterables(3,3,3,3,3,3,3,3,3,3)))
    }

    def testCycle: Unit = {
        val it = Iterables.take(Iterables.cycle(Iterables(1,2,3)), 10)
        assertTrue(Iterables.equal(it, Iterables(1,2,3,1,2,3,1,2,3,1)))
    }

    def testInfix: Unit = {
        import Iterables.Infix._

        assertTrue(Iterables(1,2,3) equal Iterables(1,2,3))
        assertEquals(3, Iterables(1,2,3).length)

        val its = Iterables(Iterables(1,2,3), Iterables(4,5,6), Iterables(7,8,9))
        assertTrue((1 until 10) equal its.flatten)
    }

    def testStringize: Unit = {
        assertEquals(Vector.from("abcde"), Vector.from( Iterables.stringize(Iterables('a','b','c','d','e')) ) )
        assertTrue(Iterables.stringize(Iterable.empty).isEmpty)
    }

  // step
    def testStep0: Unit = {
        // Unlike Vector, 0 is allowed.
        val it = Iterables.step(Iterables(1,2,3,4,5,6), 0)
        assertTrue(Iterables.equal(Iterables.take(it, 5), Iterables(1,1,1,1,1)))
    }

    def testStep1: Unit = {
        val it = Iterables.step(Iterables(1,2,3,4,5,6), 1)
        assertTrue(Iterables.equal(it, Iterables(1,2,3,4,5,6)))
    }

    def testStep2: Unit = {
        val it = Iterables.step(Iterables(1,2,3,4,5,6), 2)
        assertTrue(Iterables.equal(it, Iterables(1,3,5)))
    }

    def testStep3: Unit = {
        val it = Iterables.step(Iterables(1,2,3,4,5,6), 3)
        assertTrue(Iterables.equal(it, Iterables(1,4)))
    }

    def testStepFusion: Unit = {
        val it = Iterables.step(Iterables.step(Iterables(1,2,3,4,5,6,7,8,9,10,11), 3), 2)
        assertTrue(Iterables.equal(it, Iterables(1,7)))
    }

    def testStepFusion2: Unit = {
        val it = Iterables.step(Iterables.step(Iterables(1,2,3,4,5,6,7,8,9,10,11), 3), 2)
        assertTrue(Iterables.equal(it, Iterables.step(Iterables.seal(Iterables.step(Iterables(1,2,3,4,5,6,7,8,9,10,11), 3)), 2)))
    }

    def testStepEmpty: Unit = {
        val it0 = Iterables.step(Iterable.empty, 0)
        assertTrue(it0.isEmpty)
        val it1 = Iterables.step(Iterable.empty, 1)
        assertTrue(it1.isEmpty)
        val it2 = Iterables.step(Iterable.empty, 2)
        assertTrue(it2.isEmpty)
    }

  // filter
    def testFilter1: Unit = {
        val it = Iterables.filter(Iterables(1,2,3,4,5,6))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testFilter2: Unit = {
        val it = Iterables.filter(Iterables(1,2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testFilter3: Unit = {
        val it = Iterables.filter(Iterables(2,3,4,5,6))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testFilter4: Unit = {
        val it = Iterables.filter(Iterables(2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testFilter5: Unit = {
        val it = Iterables.filter(Iterables(2,3,4,5,6,6,6,6))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6,6,6,6)))
    }

    def testFilter6: Unit = {
        val it = Iterables.filter(Iterables(2,3,4,5,6,7,7,7,7))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testFilterEmpty1: Unit = {
        val it = Iterables.filter(Iterables(1,1))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

    def testFilterEmpty2: Unit = {
        val it = Iterables.filter(Iterable.empty)(_ => true)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

    def testFilterEmpty3: Unit = {
        val it = Iterables.filter(Iterable.empty)(_ => false)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

    def testFusion: Unit = {
        import Iterables.Infix._
        val it = Iterables(1,2,3,4,5,6) filter_ (_ % 2 == 0) filter_ (_ % 3 == 0)
        assertTrue(Iterables.equal(it, Iterables(6)))
    }

  // takeWhile
    def testTakeWhile1: Unit = {
        val it = Iterables.takeWhile(Iterables(2,4,6,5,5,6))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testTakeWhile2: Unit = {
        val it = Iterables.takeWhile(Iterables(2,4,6,1,2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2,4,6)))
    }

    def testTakeWhile3: Unit = {
        val it = Iterables.takeWhile(Iterables(2,3,4,5,6,7,7,7,7))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterables(2)))
    }

    def testTakeWhileEmpty1: Unit = {
        val it = Iterables.takeWhile(Iterables(1,1))(_ % 2 == 0)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

    def testTakeWhileEmpty2: Unit = {
        val it = Iterables.takeWhile(Iterable.empty)(_ => true)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

    def testTakeWhileEmpty3: Unit = {
        val it = Iterables.takeWhile(Iterable.empty)(_ => false)
        assertTrue(Iterables.equal(it, Iterable.empty))
    }

  // folder/reducer
    def testFolderLeft: Unit = {
        val it = Iterables.folderLeft(Iterables(4,2,4), 64)(_ / _)
        assertTrue(Iterables.equal(it, Iterables(64,16,8,2)))
    }

    def testReducerLeft: Unit = {
        val it = Iterables.reducerLeft(Iterables(1,2,3,4))(_ + _)
        assertTrue(Iterables.equal(it, Iterables(1,3,6,10)))
    }

    def testFolderLeftEmpty: Unit = {
        val it = Iterables.folderLeft(Vector.empty[Int].toRandomAccessSeq, 64)(_ / _)
        assertTrue(Iterables.equal(it, Iterables(64)))
    }

    def testReducerLeftOne: Unit = {
        val it = Iterables.reducerLeft(Iterables(1))(_ + _)
        assertTrue(Iterables.equal(it, Iterables(1)))
    }

  // string
    def testStringFrom: Unit = {
        assertEquals(Iterables.stringFrom(Iterables(1,2,3,4)), Vector(1,2,3,4).toString)
    }
}
