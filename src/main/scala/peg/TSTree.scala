

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


// See: Plant your data in a ternary search tree
//      at http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


@annotation.visibleForTesting
class TSTree[A, V](implicit __comp: Ordering[A]) extends java.lang.Cloneable {
    private var rootNode: TSTreeNode[A, V] = null

    def clear() {
        rootNode = null
    }

    override def clone: TSTree[A, V] = {
        val that = super.clone.asInstanceOf[TSTree[A, V]]
        if (rootNode ne null) {
            that.rootNode = rootNode.copy(null)
        }
        that
    }

    def containsKey(key: sequence.Vector[A]): Boolean = {
        !get(key).isEmpty
    }

    def iterator: scala.Iterator[(sequence.Vector[A], V)] = {
        if (rootNode eq null) {
            scala.Iterator.empty
        } else {
            val it = new TSTreeNodeIterator(sequence.vector.empty, rootNode)
            it.filter{ case (_, node) => !node.data.isEmpty }.map{ case (key, node) => (key.force, node.data.get) }
            /* 2.8 is unstable.
            for ((key, node) <- it if (!node.data.isEmpty))
                yield (key.force, node.data.get) // force now!
            */
        }
    }

    def get(key: sequence.Vector[A]): Option[V] = {
        get(key, key.start, key.end)
    }

    def isEmpty: Boolean = {
        (rootNode eq null) || rootNode.isGarbage
    }

    def put(key: sequence.Vector[A], value: V): Option[V] = {
        put(key, key.start, key.end, value)
    }

    def remove(key: sequence.Vector[A]): Option[V] = {
        remove(key, key.start, key.end)
    }

    def size: Int = {
        if (rootNode eq null) 0 else rootNode.size
    }

    override def toString: String = {
        val out = prettyprinter.Xml(new java.io.StringWriter, prettyprinter.defaultIndentWidth)
        print(out)
        out.close()
        out._1.toString
    }

    def get(key: sequence.Vector[A], start: Int, end: Int): Option[V] = {
        if ((rootNode eq null) || start == end) {
            return None
        }

        parse(key, start, end) match {
            case Some((value, cur)) if (cur == end) => Some(value)
            case _ => None
        }
    }

    def put(key: sequence.Vector[A], start: Int, end: Int, value: V): Option[V] = {
        if (start == end) {
            throw new UnsupportedOperationException("An empty sequence.Vector can't be a key.")
        }

        if (rootNode eq null) {
            rootNode = new TSTreeNode[A, V](key(start), null)
        }

        val node = copyInto(key, start, end, rootNode)
        val old = node.data
        node.data = Some(value)
        old
    }

    def remove(key: sequence.Vector[A], start: Int, end: Int): Option[V] = {
        if ((rootNode eq null) || start == end) {
            return None
        }

        val node = copyInto(key, start, end, rootNode)
        val old = node.data
        node.data = None
        node.collectGarbage
        old
    }

    def parse(key: sequence.Vector[A], start: Int, end: Int): Option[(V, Int)] = {
        if ((rootNode eq null) || start == end) {
            return None
        }

        val (node, cur) = search(rootNode, key, start, end)
        if ((node eq null) || node.data.isEmpty) {
            None
        } else {
            Some((node.data.get, cur))
        }
    }

    def print(out: prettyprinter.Xml) {
        out.writeStartElement("tstree")
        if (rootNode ne null) {
            rootNode.print(out)
        }
        out.writeEndElement
    }

    private def copyInto(key: sequence.Vector[A], _first: Int, last: Int, _result: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        assert(_first != last)
        assert(_result ne null)
        var first = _first
        var result = _result

        var k = key(first)
        while (true) {
            val way = __comp.compare(k, result.elem)
            if (way < 0) {
                if (result.left eq null) {
                    result.left = new TSTreeNode(k, result)
                }
                result = result.left
            } else if (way > 0) {
                if (result.right eq null) {
                    result.right = new TSTreeNode(k, result)
                }
                result = result.right
            } else {
                first += 1
                if (first == last) {
                    return result
                }

                k = key(first)
                if (result.middle eq null) {
                    result.middle = new TSTreeNode(k, result)
                }
                result = result.middle
            }
        }

        result
    }

    private def search(_first1: TSTreeNode[A, V], key2: sequence.Vector[A], _first2: Int, last2: Int): (TSTreeNode[A, V], Int) = {
        assert(_first1 ne null)
        assert(_first2 != last2)
        var first1 = _first1
        var first2 = _first2
        var cur1: TSTreeNode[A, V] = null

        var k2 = key2(first2)
        while (first1 ne null) {
            if (__comp.lt(k2, first1.elem)) {
                first1 = first1.left
            } else if (__comp.lt(first1.elem, k2)) {
                first1 = first1.right
            } else {
                cur1 = first1
                first2 += 1
                if (first2 == last2) {
                    return (cur1, first2)
                }

                k2 = key2(first2)
                first1 = first1.middle
            }
        }

        (cur1, first2)
    }
}


private class TSTreeNode[A, V](val elem: A, val parent: TSTreeNode[A, V]) {
    var data: Option[V] = None
    var left: TSTreeNode[A, V] = null
    var middle: TSTreeNode[A, V] = null
    var right: TSTreeNode[A, V] = null

    def copy(parent: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        val node = new TSTreeNode[A, V](elem, parent)
        def _copy(c: TSTreeNode[A, V]) = if (c eq null) null else c.copy(node)
        node.data = data
        node.left = _copy(left)
        node.middle = _copy(middle)
        node.right = _copy(right)
        node
    }

    def size: Int = {
        def _size(c: TSTreeNode[A, V]) = if (c eq null) 0 else c.size
        (if (data.isEmpty) 0 else 1) + _size(left) + _size(middle) + _size(right)
    }

    def isLeaf: Boolean = {
        (left eq null) && (middle eq null) && (right eq null)
    }

     // This is enough, assuming any leaf's data is not None.
    def isGarbage: Boolean = {
        data.isEmpty && isLeaf
    }

    def collectGarbage() {
        if ((parent ne null) && isGarbage) {
            if (parent.left eq this) {
                parent.left = null
            }
            if (parent.middle eq this) {
                parent.middle = null
            }
            if (parent.right eq this) {
                parent.right = null
            }
            parent.collectGarbage()
        }
    }

    def print(out: prettyprinter.Xml) {
        def _print(c: TSTreeNode[A, V], s: String) = {
            if (c ne null) {
                out.writeStartElement(s)
                c.print(out)
                out.writeEndElement()
            }
        }
        out.writeStartElement("node")
        out.writeElement("elem", elem)
        if (!data.isEmpty) {
            out.writeElement("value", data.get)
        }
        _print(left, "left")
        _print(middle, "middle")
        _print(right, "right")
        out.writeEndElement()
    }

    override def toString = string.concat("TSTreeNode(elem: ", elem, ", data: ", data, ')')
}


private class TSTreeNodeIterator[A, V](parentKey: sequence.Vector[A], node: TSTreeNode[A, V]) extends scala.Iterator[(sequence.Vector[A], TSTreeNode[A, V])] {
    private[this] val me = scala.Iterator.single((lowerKey, node))
    private[this] val delegate = me ++ children(node.left) ++ children(node.middle) ++ children(node.right)

    override def hasNext = delegate.hasNext
    override def next = delegate.next

    private def children(c: TSTreeNode[A, V]) = {
        if (c eq null) {
            scala.Iterator.empty
        } else {
            if (c eq node.middle) {
                new TSTreeNodeIterator(lowerKey, c)
            } else {
                new TSTreeNodeIterator(parentKey, c)
            }
        }
    }

    private def lowerKey = parentKey ++ sequence.vector.single(node.elem)
}
