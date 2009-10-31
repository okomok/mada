

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.iterative
import junit.framework.Assert._


class SortedTest {

// merge

    def testMerge: Unit = {
    //    new NotStartable[Int]() merge new NotStartable[Int]()
        val A1 = iterative.Of(1,6,7,10,14,17)
        val A2 = iterative.Of(2,5,8,11,13,18)
        val A3 = iterative.Of(3,4,9,12,15,16)
        val AA = iterative.Of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge A3
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testMergeEmpty: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        val A2 = iterative.Of(2,5,8,11,13,18)
        val A3 = iterative.Of(3,4,9,12,15,16)
        val AA = iterative.Of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge iterative.empty merge A3 merge iterative.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
        val B2 = iterative.emptyOf[Int] merge A1 merge A2 merge A3 merge iterative.empty
//        println(sequence.toString(B2))
        assertEquals(B2, AA)
        assertEquals(B2, AA) // run again.
    }

    def testMergeEmpty0: Unit = {
        val B1 = iterative.emptyOf[Int] merge iterative.empty merge iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }


// union

    def ltNoCase(ch1: Char, ch2: Char): Boolean = {
        import java.lang.Character.toLowerCase
        toLowerCase(ch1) < toLowerCase(ch2)
    }

    def testUnion: Unit = {
    //    new NotStartable[Int]() union new NotStartable[Int]()
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,1,2,3,5,7,8,9,11,13)
        val B1 = A1 union A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testUnionEmpty: Unit = {
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,1,2,3,5,7,8,9,11,13)
        val B1 = iterative.emptyOf[Int] union A1 union A2 union iterative.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testUnionEmpty0: Unit = {
        val B1 = iterative.emptyOf[Int] union iterative.empty union iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testUnionStable: Unit = {
        val A1 = "abBBfH"
        val A2 = "ABbCDFFhh"
        val AA = iterative.from("abBBCDfFHh")
        val B1 = iterative.from(A1).unionBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// intersection

    def testIntersection: Unit = {
    //    new NotStartable[Int]() intersection new NotStartable[Int]()
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,3,5)
        val B1 = A1 intersection A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testIntersectionEmpty: Unit = {
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,3,5)
        val B1 = iterative.emptyOf[Int] intersection A1 intersection A2 intersection iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionEmpty0: Unit = {
        val B1 = iterative.emptyOf[Int] intersection iterative.empty intersection iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionStable: Unit = {
        val A1 = "abbBBfhH"
        val A2 = "ABBCDFFH"
        val AA = iterative.from("abbfh")
        val B1 = iterative.from(A1).intersectionBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// difference

    def testDifference: Unit = {
    //    new NotStartable[Int]() difference new NotStartable[Int]()
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(7,9,11)
        val B1 = A1 difference A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testDifferenceEmpty: Unit = {
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(7,9,11)
        val B1 = A1 difference iterative.empty difference A2 difference iterative.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testDifferenceEmpty0: Unit = {
        val B1 = iterative.emptyOf[Int] difference iterative.empty difference iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = iterative.from("BBgH")
        val B1 = iterative.from(A1).differenceBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// symmetricDifference

    def testSymmetricDifference: Unit = {
    //    new NotStartable[Int]() symmetricDifference new NotStartable[Int]()
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
        val B2 = A1 symmetricDifference A1
        assertTrue( B2.isEmpty )
        assertTrue( B2.isEmpty ) // run again.
    }

    def testSymmetricDifferenceEmpty: Unit = {
        val A1 = iterative.Of(1,3,5,7,9,11)
        val A2 = iterative.Of(1,1,2,3,5,8,13)
        val AA = iterative.Of(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference iterative.empty symmetricDifference A2 symmetricDifference iterative.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testSymmetricDifferenceEmpty0: Unit = {
        val B1 = iterative.emptyOf[Int] symmetricDifference iterative.empty symmetricDifference iterative.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testSymmetricDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = iterative.from("BBCDFgH")
        val B1 = iterative.from(A1).symmetricDifferenceBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }
}
