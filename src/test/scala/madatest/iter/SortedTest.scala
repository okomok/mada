

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import junit.framework.Assert._


class SortedTest {
    import Iterables.Operators._


// merge

    def testMerge: Unit = {
        val A1 = Iterables(1,6,7,10,14,17)
        val A2 = Iterables(2,5,8,11,13,18)
        val A3 = Iterables(3,4,9,12,15,16)
        val AA = Iterables(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge A3
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testMergeEmpty: Unit = {
        val A1 = Iterables(1,6,7,10,14,17)
        val A2 = Iterables(2,5,8,11,13,18)
        val A3 = Iterables(3,4,9,12,15,16)
        val AA = Iterables(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge Iterable.empty merge A3 merge Iterable.empty
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
        val B2 = Iterables.emptyOf[Int] merge A1 merge A2 merge A3 merge Iterable.empty
//        println(Iterables.stringFrom(B2))
        assertTrue( Iterables.equal(B2, AA) )
        assertTrue( Iterables.equal(B2, AA) ) // run again.
    }

    def testMergeEmpty0: Unit = {
        val B1 = Iterables.emptyOf[Int] merge Iterable.empty merge Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }


// union

    def ltNoCase(ch1: Char, ch2: Char): Boolean = {
        import java.lang.Character.toLowerCase
        toLowerCase(ch1) < toLowerCase(ch2)
    }

    def testUnion: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,1,2,3,5,7,8,9,11,13)
        val B1 = A1 union A2
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testUnionEmpty: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,1,2,3,5,7,8,9,11,13)
        val B1 = Iterables.emptyOf[Int] union A1 union A2 union Iterable.empty
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testUnionEmpty0: Unit = {
        val B1 = Iterables.emptyOf[Int] union Iterable.empty union Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testUnionStable: Unit = {
        val A1 = "abBBfH"
        val A2 = "ABbCDFFhh"
        val AA = "abBBCDfFHh"
        val B1 = Iterables.from(A1).unionBy(A2)(ltNoCase)
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }


// intersection

    def testIntersection: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,3,5)
        val B1 = A1 intersection A2
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testIntersectionEmpty: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,3,5)
        val B1 = Iterables.emptyOf[Int] intersection A1 intersection A2 intersection Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionEmpty0: Unit = {
        val B1 = Iterables.emptyOf[Int] intersection Iterable.empty intersection Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionStable: Unit = {
        val A1 = "abbBBfhH"
        val A2 = "ABBCDFFH"
        val AA = "abbfh"
        val B1 = Iterables.from(A1).intersectionBy(A2)(ltNoCase)
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }


// difference

    def testDifference: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(7,9,11)
        val B1 = A1 difference A2
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testDifferenceEmpty: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(7,9,11)
        val B1 = A1 difference Iterable.empty difference A2 difference Iterable.empty
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testDifferenceEmpty0: Unit = {
        val B1 = Iterables.emptyOf[Int] difference Iterable.empty difference Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = "BBgH"
        val B1 = Iterables.from(A1).differenceBy(A2)(ltNoCase)
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }


// symmetricDifference

    def testSymmetricDifference: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference A2
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
        val B2 = A1 symmetricDifference A1
        assertTrue( B2.isEmpty )
        assertTrue( B2.isEmpty ) // run again.
    }

    def testSymmetricDifferenceEmpty: Unit = {
        val A1 = Iterables(1,3,5,7,9,11)
        val A2 = Iterables(1,1,2,3,5,8,13)
        val AA = Iterables(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference Iterable.empty symmetricDifference A2 symmetricDifference Iterable.empty
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }

    def testSymmetricDifferenceEmpty0: Unit = {
        val B1 = Iterables.emptyOf[Int] symmetricDifference Iterable.empty symmetricDifference Iterable.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testSymmetricDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = "BBCDFgH"
        val B1 = Iterables.from(A1).symmetricDifferenceBy(A2)(ltNoCase)
        assertTrue( Iterables.equal(B1, AA) )
        assertTrue( Iterables.equal(B1, AA) ) // run again.
    }
}
