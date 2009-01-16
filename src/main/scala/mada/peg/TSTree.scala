

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg


class TSTree[A, V](_lt: (A, A) => Boolean) {
    private var rootNode: TSTreeNode[A, V] = null

    def clear: Unit = {
        rootNode = null
    }

    override def clone: TSTree[A, V] = {
        val that = new TSTree[A, V](_lt)
        if (rootNode ne null) {
            that.rootNode = rootNode.clone(null)
        }
        that
    }

    def containsKey(key: Vector[A]): Boolean = {
        get(key) != None
    }

    def elements: Iterator[(Vector[A], V)] = {
        if (rootNode eq null) {
            Iterator.empty
        } else {
            val it = new TSTreeNodeIterator(Vector.empty, rootNode)
            for ((key, node) <- it if (!node.data.isEmpty))
                yield (key.force, node.data.get) // force now!
        }
    }

    def get(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        get(key, first, last)
    }

    def isEmpty: Boolean = {
        (rootNode eq null) || rootNode.isGarbage
    }

    def put(key: Vector[A], value: V): Option[V] = {
        val (first, last) = key.toPair
        put(key, first, last, value)
    }

    def remove(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        remove(key, first, last)
    }

    def size: Int = {
        if (rootNode eq null) 0 else rootNode.size
    }

    override def toString: String = {
        val out = new Peg.PrettyPrinter(new java.io.StringWriter)
        print(out)
        out.close
        out.out.toString
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        if ((rootNode eq null) || first == last) {
            return None
        }

        parse(key, first, last) match {
            case Some((value, cur)) if (cur == last) => Some(value)
            case _ => None
        }
    }

    def put(key: Vector[A], first: Long, last: Long, value: V): Option[V] = {
        if (first == last) {
            throw new IllegalArgumentException("An empty Vector can't be a key.")
        }

        if (rootNode eq null) {
            rootNode = new TSTreeNode[A, V](key(first), null)
        }

        val node = copyInto(key, first, last, rootNode)
        val old = node.data
        node.data = Some(value)
        old
    }

    def remove(key: Vector[A], first: Long, last: Long): Option[V] = {
        if ((rootNode eq null) || first == last) {
            return None
        }

        val node = copyInto(key, first, last, rootNode)
        val old = node.data
        node.data = None
        node.collectGarbage
        old
    }

    def parse(key: Vector[A], first: Long, last: Long): Option[(V, Long)] = {
        if ((rootNode eq null) || first == last) {
            return None
        }

        val (node, cur) = search(rootNode, key, first, last)
        if ((node eq null) || node.data.isEmpty) {
            None
        } else {
            Some((node.data.get, cur))
        }
    }

    def print(out: Peg.PrettyPrinter): Unit = {
        out.writeStartElement("tstree")
        if (rootNode ne null) {
            rootNode.print(out)
        }
        out.writeEndElement
    }

    private def copyInto(key: Vector[A], _first: Long, last: Long, _result: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        Assert(_first != last)
        Assert(_result ne null)
        var first = _first
        var result = _result

        var k = key(first)
        while (true) {
            if (_lt(k, result.elem)) {
                if (result.left eq null) {
                    result.left = new TSTreeNode(k, result)
                }
                result = result.left
            } else if (_lt(result.elem, k)) {
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

    private def search(_first1: TSTreeNode[A, V], key2: Vector[A], _first2: Long, last2: Long): (TSTreeNode[A, V], Long) = {
        Assert(_first1 ne null)
        Assert(_first2 != last2)
        var first1 = _first1
        var first2 = _first2
        var cur1: TSTreeNode[A, V] = null

        var k2 = key2(first2)
        while (first1 ne null) {
            if (_lt(k2, first1.elem)) {
                first1 = first1.left
            } else if (_lt(first1.elem, k2)) {
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


class TSTreeNode[A, V](val elem: A, val parent: TSTreeNode[A, V]) {
    var data: Option[V] = None
    var left: TSTreeNode[A, V] = null
    var middle: TSTreeNode[A, V] = null
    var right: TSTreeNode[A, V] = null

    def clone(parent: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        val node = new TSTreeNode[A, V](elem, parent)
        def _clone(c: TSTreeNode[A, V]) = if (c eq null) null else c.clone(node)
        node.data = data
        node.left = _clone(left)
        node.middle = _clone(middle)
        node.right = _clone(right)
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

    def collectGarbage: Unit = {
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
            parent.collectGarbage
        }
    }

    def print(out: Peg.PrettyPrinter): Unit = {
        def _print(c: TSTreeNode[A, V], s: String) = {
            if (c ne null) {
                out.writeStartElement(s)
                c.print(out)
                out.writeEndElement
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
        out.writeEndElement
    }

    override def toString = {
        new StringBuilder("TSTreeNode(elem: ").append(elem).append(", data: ").append(data).append(')').toString
    }
}


class TSTreeNodeIterator[A, V](parentKey: Vector[A], node: TSTreeNode[A, V]) extends IteratorProxy[(Vector[A], TSTreeNode[A, V])] {
    private val me = Iterator.single((lowerKey, node))
    override val self = me ++ children(node.left) ++ children(node.middle) ++ children(node.right)

    private def children(c: TSTreeNode[A, V]) = {
        if (c eq null) {
            Iterator.empty
        } else {
            if (c eq node.middle) {
                new TSTreeNodeIterator(lowerKey, c)
            } else {
                new TSTreeNodeIterator(parentKey, c)
            }
        }
    }

    private def lowerKey = parentKey ++ Vector.single(node.elem)
}
