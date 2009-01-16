

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import javax.swing.tree.{ MutableTreeNode, DefaultMutableTreeNode }


// MutableTreeNode.clone isn't public.

object DefaultMutableTreeNodeCloner extends (DefaultMutableTreeNode => DefaultMutableTreeNode) {
    override def apply(n: DefaultMutableTreeNode) = n.clone.asInstanceOf[DefaultMutableTreeNode]
}

object ASTreeBuilder {
    def apply: ASTreeBuilder[DefaultMutableTreeNode] = {
        new ASTreeBuilder(new DefaultMutableTreeNode, DefaultMutableTreeNodeCloner)
    }
    def apply(userObject: Any): ASTreeBuilder[DefaultMutableTreeNode] = {
        new ASTreeBuilder(new DefaultMutableTreeNode(userObject), DefaultMutableTreeNodeCloner)
    }
}

class ASTreeBuilder[T <: MutableTreeNode](root: T, cloner: T => T) {
    private val branches = new java.util.ArrayDeque[T]
    branches.push(root)

    def toTree: T = {
        val n = branches.pop
        if ((n ne root) || !branches.isEmpty) {
            throw new java.lang.IllegalStateException("failed to build tree")
        }
        n
    }

    def apply[A](p: Peg[A])(f: (Vector[A], Long, Long) => Any): Peg[A] = node(p)(f)
    def node[A](p: Peg[A])(f: (Vector[A], Long, Long) => Any): Peg[A] = new NodePeg(p, f)

    class NodePeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            val n = newNode
            branches.push(n)
            val cur = self.parse(v, first, last)
            Verify(n eq branches.pop)
            if (cur != FAILURE) {
                n.setUserObject(f(v, first, cur))
                addNode(branches.peek, n)
            }
            cur
        }
    }

    def leaf[A](p: Peg[A])(f: (Vector[A], Long, Long) => Any): Peg[A] = {
        def _add(v: Vector[A], first: Long, last: Long) = {
            val n = newNode
            n.setUserObject(f(v, first, last))
            addNode(branches.peek, n)
        }
        p.act(_add _)
    }

    private def newNode: T = cloner(root)
    private def addNode(parent: T, child: T): Unit = parent.insert(child, parent.getChildCount)
}
