

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._
import javax.swing.tree.TreeNode


class ASTreeBuilderTest {
    var indent = 0

    def testTrivial: Unit = {
        val (expr, term, factor, digit) = Rule.make4[Char]
        val tb = ASTreeBuilder("root")

        expr    ::= tb(term >> ( '+' >> term | '-' >> term ).*, _ => "expr")
        term    ::= tb(factor >> ( '*' >> factor | '/' >> factor ).*, _ => "term")
        factor  ::= tb((digit+) | '(' >> expr >> ')' | '-' >> factor | '+' >> factor, _ => "factor")
        digit   ::= tb.leaf(range('0','9'), _.toString)

        assertTrue(expr matches "(1+2)*(3*(4-5))")
        //printTree(tb.toTree)
    }

    def printTree(t: TreeNode) {
        if (t.toString != null)
            println(Vector.toString(" ".cycle(indent) ++ t.toString))
        indent += 4
        val enum = t.children
        while (enum.hasMoreElements) {
            val c = enum.nextElement
            printTree(c.asInstanceOf[TreeNode])
        }
        indent -= 4
    }
}
