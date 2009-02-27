

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Iterators
import mada.Vector
import junit.framework.Assert._


class IteratorsTest {
    def testEquals: Unit = {
        assertFalse(Iterators.equal(Iterator.empty, Iterator.fromValues(1,2,3)))
        assertFalse(Iterators.equal(Iterator.fromValues(1,2), Iterator.fromValues(1,2,3)))
        assertTrue(Iterators.equal(Iterator.fromValues(1,2,3), Iterator.fromValues(1,2,3)))
        assertTrue(Iterators.equal(Iterator.empty, Iterator.empty))
    }

    def testLength: Unit = {
        assertEquals(3, Iterators.length(Iterator.fromValues(1,2,3)))
        assertEquals(0, Iterators.length(Iterator.empty))
    }

    def testUnfoldRight: Unit = {
        val it = Iterators.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertTrue(Iterators.equal(it, Iterator.fromValues(10,9,8,7,6,5,4,3,2,1)))
    }

    def testIterate: Unit = {
        val it = Iterators.iterate(10){ b => b-1 }.take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(10,9,8,7,6,5,4,3,2,1)))
    }

    def testRepeat: Unit = {
        val it = Iterators.repeat(3).take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(3,3,3,3,3,3,3,3,3,3)))
    }

    def testCycle: Unit = {
        val it = Iterators.cycle(Iterators.toIterable(Iterator.fromValues(1,2,3))).elements.take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,2,3,1,2,3,1,2,3,1)))
    }

    def testInfix: Unit = {
        import Iterators.Infix._

        assertTrue(Iterator.fromValues(1,2,3) equal Iterator.fromValues(1,2,3))
        assertEquals(3, Iterator.fromValues(1,2,3).length)

//        val it = Iterator.fromValues(1,2,3).cycle.take(10)
//        assertTrue(Iterators.equal(it, Iterator.fromValues(1,2,3,1,2,3,1,2,3,1)))
    }

    def testStringize: Unit = {
        assertEquals(Vector.from("abcde"), Vector.from( Iterators.stringize(Iterator.fromValues('a','b','c','d','e')) ) )
        assertTrue(Iterators.stringize(Iterator.empty).isEmpty)
    }

  // step
    def testStep0: Unit = {
        // Unlike Vector, 0 is allowed.
        val it = Iterators.step(Iterator.fromValues(1,2,3,4,5,6), 0)
        assertTrue(Iterators.equal(it.take(5), Iterator.fromValues(1,1,1,1,1)))
    }

    def testStep1: Unit = {
        val it = Iterators.step(Iterator.fromValues(1,2,3,4,5,6), 1)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,2,3,4,5,6)))
    }

    def testStep2: Unit = {
        val it = Iterators.step(Iterator.fromValues(1,2,3,4,5,6), 2)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,3,5)))
    }

    def testStep3: Unit = {
        val it = Iterators.step(Iterator.fromValues(1,2,3,4,5,6), 3)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,4)))
    }

    def testStepFusion: Unit = {
        val it = Iterators.step(Iterators.step(Iterator.fromValues(1,2,3,4,5,6,7,8,9,10,11), 3), 2)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,7)))
    }

    def testStepFusion2: Unit = {
        val it = Iterators.step(Iterators.step(Iterator.fromValues(1,2,3,4,5,6,7,8,9,10,11), 3), 2)
        assertTrue(Iterators.equal(it, Iterators.step(Iterators.seal(Iterators.step(Iterator.fromValues(1,2,3,4,5,6,7,8,9,10,11), 3)), 2)))
    }

    def testStepEmpty: Unit = {
        val it0 = Iterators.step(Iterator.empty, 0)
        assertTrue(Iterators.isEmpty(it0))
        val it1 = Iterators.step(Iterator.empty, 1)
        assertTrue(Iterators.isEmpty(it1))
        val it2 = Iterators.step(Iterator.empty, 2)
        assertTrue(Iterators.isEmpty(it2))
    }

  // filter
    def testFilter1: Unit = {
        val it = Iterators.filter(Iterator.fromValues(1,2,3,4,5,6))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testFilter2: Unit = {
        val it = Iterators.filter(Iterator.fromValues(1,2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testFilter3: Unit = {
        val it = Iterators.filter(Iterator.fromValues(2,3,4,5,6))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testFilter4: Unit = {
        val it = Iterators.filter(Iterator.fromValues(2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testFilter5: Unit = {
        val it = Iterators.filter(Iterator.fromValues(2,3,4,5,6,6,6,6))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6,6,6,6)))
    }

    def testFilter6: Unit = {
        val it = Iterators.filter(Iterator.fromValues(2,3,4,5,6,7,7,7,7))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testFilterEmpty1: Unit = {
        val it = Iterators.filter(Iterator.fromValues(1,1))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

    def testFilterEmpty2: Unit = {
        val it = Iterators.filter(Iterator.empty)(_ => true)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

    def testFilterEmpty3: Unit = {
        val it = Iterators.filter(Iterator.empty)(_ => false)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

    def testFusion: Unit = {
        import Iterators.Infix._
        val it = Iterator.fromValues(1,2,3,4,5,6) filter_ (_ % 2 == 0) filter_ (_ % 3 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(6)))
    }

  // takeWhile
    def testTakeWhile1: Unit = {
        val it = Iterators.takeWhile(Iterator.fromValues(2,4,6,5,5,6))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testTakeWhile2: Unit = {
        val it = Iterators.takeWhile(Iterator.fromValues(2,4,6,1,2,3,4,5,6,7))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2,4,6)))
    }

    def testTakeWhile3: Unit = {
        val it = Iterators.takeWhile(Iterator.fromValues(2,3,4,5,6,7,7,7,7))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.fromValues(2)))
    }

    def testTakeWhileEmpty1: Unit = {
        val it = Iterators.takeWhile(Iterator.fromValues(1,1))(_ % 2 == 0)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

    def testTakeWhileEmpty2: Unit = {
        val it = Iterators.takeWhile(Iterator.empty)(_ => true)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

    def testTakeWhileEmpty3: Unit = {
        val it = Iterators.takeWhile(Iterator.empty)(_ => false)
        assertTrue(Iterators.equal(it, Iterator.empty))
    }

  // folder/reducer
    def testFolderLeft: Unit = {
        val it = Iterators.folderLeft(Iterator.fromValues(4,2,4), 64)(_ / _)
        assertTrue(Iterators.equal(it, Iterator.fromValues(64,16,8,2)))
    }

    def testReducerLeft: Unit = {
        val it = Iterators.reducerLeft(Iterator.fromValues(1,2,3,4))(_ + _)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,3,6,10)))
    }

    def testFolderLeftEmpty: Unit = {
        val it = Iterators.folderLeft(Iterators.emptyOf[Int], 64)(_ / _)
        assertTrue(Iterators.equal(it, Iterator.fromValues(64)))
    }

    def testReducerLeftOne: Unit = {
        val it = Iterators.reducerLeft(Iterator.fromValues(1))(_ + _)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1)))
    }
}
