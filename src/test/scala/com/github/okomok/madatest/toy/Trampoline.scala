

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package trampolinetest


import com.github.okomok.mada

import junit.framework.Assert._


// import scala.util.control.TailRec


sealed trait Tree
final case class Node(_1: String, _2: Tree, _3: Tree) extends Tree
final case class Tip(_1: String) extends Tree


class TrampolineTest {

    def sizeNotTailRecursive(t: Tree): Int = t match {
        case Tip(_) => 1
        case Node(_, l, r) => sizeNotTailRecursive(l) + sizeNotTailRecursive(r)
    }

    def mkBigUnbalancedTree(n: Int, t: Tree): Tree = {
        if (n == 0) {
            t
        } else {
            Node("node", Tip("tip"), mkBigUnbalancedTree(n - 1, t))
        }
    }
/*
    val tree1 = Tip("tip")
    val tree2 = mkBigUnbalancedTree(2000, tree1)
    val tree3 = mkBigUnbalancedTree(2000, tree2)
    val tree4 = mkBigUnbalancedTree(2000, tree3)
    val tree5 = mkBigUnbalancedTree(2000, tree4)
    val tree6 = mkBigUnbalancedTree(2000, tree5)

    def testStackOverflow: Unit = {
        sizeNotTailRecursive(tree6)
        ()
    }
*/
}
