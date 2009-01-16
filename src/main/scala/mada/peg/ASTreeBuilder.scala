

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import javax.swing.tree.DefaultMutableTreeNode


class ASTreeBuilder(val root: DefaultMutableTreeNode) {
    def this() = this(new DefaultMutableTreeNode)
    def this(x: Any) = this(new DefaultMutableTreeNode(x))

    private val parents = new java.util.ArrayDeque[DefaultMutableTreeNode]
    parents.push(root)

    def toTree: DefaultMutableTreeNode = {
        val n = parents.pop
        if ((n ne root) || !parents.isEmpty) {
            throw new java.lang.IllegalStateException("failed to build tree")
        }
        n
    }

    def leaf[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = leaf(p, Vector.triplify(f))
    def leaf[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = new LeafPeg(p, f)

    class LeafPeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            val cur = self.parse(v, first, last)
            if (cur != FAILURE) {
                parents.peek.add(new DefaultMutableTreeNode(f(v, first, cur), false))
            }
            cur
        }
    }

    def branch[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = branch(p, Vector.triplify(f))
    def branch[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = new BranchPeg(p, f)

    class BranchPeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            val b = new DefaultMutableTreeNode(self)
            parents.push(b)
            val cur = self.parse(v, first, last)
            parents.pop
            if (cur != FAILURE) {
                b.setUserObject(f(v, first, cur))
                parents.peek.add(b)
            }
            cur
        }
    }
}
