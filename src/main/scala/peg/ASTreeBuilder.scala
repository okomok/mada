

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


import javax.swing.tree.{MutableTreeNode, DefaultMutableTreeNode}


/**
 * Contains utility methods operating on type <code>ASTreeBuilder</code>.
 */
object ASTreeBuilder {
    /**
     * Creates a default root tree.
     */
    def apply: ASTreeBuilder[DefaultMutableTreeNode] = {
        new ASTreeBuilder(new DefaultMutableTreeNode, defaultCloner)
    }

    /**
     * Creates a default root tree with specified <code>userObject</code>.
     */
    def apply(userObject: Any): ASTreeBuilder[DefaultMutableTreeNode] = {
        new ASTreeBuilder(new DefaultMutableTreeNode(userObject), defaultCloner)
    }

    /**
     * A <code>cloner</code> of <code>DefaultMutableTreeNode</code>
     */
    val defaultCloner: Function1[DefaultMutableTreeNode, DefaultMutableTreeNode] = new Function1[DefaultMutableTreeNode, DefaultMutableTreeNode] {
        override def apply(n: DefaultMutableTreeNode) = n.clone.asInstanceOf[DefaultMutableTreeNode]
    }
}


/**
 * Builds <code>MutableTreeNode</code> while parsing.
 * <code>cloner</code> is needed because <code>MutableTreeNode.clone</code> isn't public for some reason.
 *
 * @param   root    the root node of this tree
 * @param   cloner  a function returns shallow-copy of <code>T</code> object
 */
class ASTreeBuilder[T <: MutableTreeNode](root: T, cloner: T => T) {
    private[this] val branches = new java.util.ArrayDeque[T]
    branches.push(root)

    /**
     * Returns the result tree. Ensures the tree is built successfully.
     *
     * @throws  IllegalStateException if tree is broken.
     */
    def tree: T = {
        val n = branches.peek
        if ((n ne root) || (branches.size != 1)) {
            throw new java.lang.IllegalStateException("failed to build tree")
        }
        n
    }

    @annotation.aliasOf("node")
    final def apply[A](p: Peg[A])(f: Action[A]): Peg[A] = node(p)(f)

    /**
     * Creates a Peg which appends a tree node using <code>f</code>.
     */
    def node[A](p: Peg[A])(f: Action[A]): Peg[A] = Node(p, f)

    case class Node[A](_1: Peg[A], _2: Action[A]) extends Peg[A] {
        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            val n = newNode
            branches.push(n)
            val cur = _1.parse(v, start, end)
            require(n eq branches.pop)
            if (cur != FAILURE) {
                n.setUserObject(_2(v(start, cur)))
                addNode(branches.peek, n)
            }
            cur
        }
        override def width = _1.width
    }

    /**
     * Creates a Peg which appends a leaf node using <code>f</code>.
     */
    def leaf[A](p: Peg[A])(f: Action[A]): Peg[A] = {
        def _add(v: sequence.Vector[A]) = {
            val n = newNode
            n.setUserObject(f(v))
            addNode(branches.peek, n)
        }
        p.act(_add _)
    }

    private def newNode: T = cloner(root)
    private def addNode(parent: T, child: T): Unit = parent.insert(child, parent.getChildCount)
}
