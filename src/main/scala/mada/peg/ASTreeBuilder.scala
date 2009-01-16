

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import javax.swing.tree.DefaultMutableTreeNode


class ASTreeBuilder(val root: DefaultMutableTreeNode) {
    def this() = this(new DefaultMutableTreeNode)
    def this(x: Any) = this(new DefaultMutableTreeNode(x))

    private val branches = new java.util.ArrayDeque[DefaultMutableTreeNode]
    branches.push(root)

    def toTree: DefaultMutableTreeNode = {
        val b = branches.pop
        if ((b ne root) || !branches.isEmpty) {
            throw new java.lang.IllegalStateException("failed to build tree")
        }
        b
    }

    def apply[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = node(p, f)
    def apply[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = node(p, f)
    def node[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = node(p, Vector.triplify(f))
    def node[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = new NodePeg(p, f)

    class NodePeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            val b = new DefaultMutableTreeNode(self)
            branches.push(b)
            val cur = self.parse(v, first, last)
            Verify(b eq branches.pop)
            if (cur != FAILURE) {
                b.setUserObject(f(v, first, cur))
                branches.peek.add(b)
            }
            cur
        }
    }

    def leaf[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = leaf(p, Vector.triplify(f))
    def leaf[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = {
        def _add(v: Vector[A], first: Long, last: Long) = {
            branches.peek.add(new DefaultMutableTreeNode(f(v, first, last), false))
        }
        p.action(_add _)
    }
}
