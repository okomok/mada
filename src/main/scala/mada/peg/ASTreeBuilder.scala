

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

    def tree: T = {
        val n = branches.peek
        if ((n ne root) || (branches.size != 1)) {
            throw new java.lang.IllegalStateException("failed to build tree")
        }
        n
    }

    def apply[A](p: Peg[A])(f: Vector.Func3[A, Any]): Peg[A] = node(p)(f)
    def node[A](p: Peg[A])(f: Vector.Func3[A, Any]): Peg[A] = new NodePeg(p, f)

    private class NodePeg[A](override val self: Peg[A], f: Vector.Func3[A, Any]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            val n = newNode
            branches.push(n)
            val cur = self.parse(v, start, end)
            Assert.verify(n eq branches.pop)
            if (cur != Peg.FAILURE) {
                n.setUserObject(f(v, start, cur))
                addNode(branches.peek, n)
            }
            cur
        }
    }

    def leaf[A](p: Peg[A])(f: Vector.Func3[A, Any]): Peg[A] = {
        def _add(v: Vector[A], start: Int, end: Int) = {
            val n = newNode
            n.setUserObject(f(v, start, end))
            addNode(branches.peek, n)
        }
        p.act(_add _)
    }

    private def newNode: T = cloner(root)
    private def addNode(parent: T, child: T): Unit = parent.insert(child, parent.getChildCount)
}
