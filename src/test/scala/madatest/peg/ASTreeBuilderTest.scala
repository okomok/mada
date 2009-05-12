

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.{Vector, vector}
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._

import javax.swing.tree.TreeNode


class ASTreeBuilderTest {
    var indent = 0

    def testTrivial: Unit = {
        val expr, term, factor, digit = new Rule[Char]
        val tb = ASTreeBuilder("root")
        printTree(tb.tree) // trivial tree

        expr    ::= tb(term >> ( '+' >> term | '-' >> term ).*){ _ => "expr" }
        term    ::= tb(factor >> ( '*' >> factor | '/' >> factor ).*){ _ => "term" }
        factor  ::= tb((digit+) | '(' >> expr >> ')' | '-' >> factor | '+' >> factor){ _ => "factor" }
        digit   ::= tb.leaf(range('0','9')){ v => v.toString }

        assertTrue(expr matches "(1+2)*(3*(4-5))")
        printTree(tb.tree)
    }

    def printTree(t: TreeNode): Unit = {
        return

        if (t.toString != null)
            println(vector.stringize(vector.from(" ").cycle(indent) ++ t.toString))
        indent += 4
        val enum = t.children
        while (enum.hasMoreElements) {
            val c = enum.nextElement
            printTree(c.asInstanceOf[TreeNode])
        }
        indent -= 4
    }
}
