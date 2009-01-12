

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
        val t = new TSTree[A, V](_lt)
        if (rootNode != null) {
            t.rootNode = rootNode.clone_(null)
        }
        t
    }

    def containsKey(key: Vector[A]): Boolean = {
        get(key) != None
    }

    def get(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        get(key, first, last)
    }

    def isEmpty: Boolean = {
        rootNode == null || rootNode.isGarbage
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
        if (rootNode == null) 0 else rootNode.size
    }

    override def toString: String = {
        val out = new PrettyPrinter(new java.io.StringWriter)
        print(out)
        out.close
        out.out.toString
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        if (rootNode == null || first == last) {
            return None
        }

        parse(key, first, last) match {
            case Some((value, cur)) if (cur == last) => Some(value)
            case _ => None
        }
    }

    def put(key: Vector[A], first: Long, last: Long, value: V): Option[V] = {
        Assert(first != last)

        if (rootNode == null) {
            rootNode = new TSTreeNode[A, V](key(first), null)
        }

        val node = copyInto(key, first, last, rootNode)
        val old = node.data
        node.data = Some(value)
        old
    }

    def remove(key: Vector[A], first: Long, last: Long): Option[V] = {
        if (rootNode == null || first == last) {
            return None
        }

        val node = copyInto(key, first, last, rootNode)
        val old = node.data
        node.data = None
        node.collectGarbage
        old
    }

    def parse(key: Vector[A], first: Long, last: Long): Option[(V, Long)] = {
        if (rootNode == null || first == last) {
            return None
        }

        val (node, cur) = search(rootNode, key, first, last)
        if (node == null || node.data.isEmpty) {
            None
        } else {
            Some((node.data.get, cur))
        }
    }

    def print(out: PrettyPrinter): Unit = {
        out.writeStartElement("tstree")
        if (rootNode != null) {
            rootNode.print(out)
        }
        out.writeEndElement
    }

    private def copyInto(key: Vector[A], _first: Long, last: Long, _result: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        Assert(_first != last)
        Assert(_result != null)
        var first = _first
        var result = _result

        var k = key(first)
        while (true) {
            if (_lt(k, result.elem)) {
                if (result.left == null) {
                    result.left = new TSTreeNode(k, result)
                }
                result = result.left
            } else if (_lt(result.elem, k)) {
                if (result.right == null) {
                    result.right = new TSTreeNode(k, result)
                }
                result = result.right
            } else {
                first += 1
                if (first == last) {
                    return result
                }

                k = key(first)
                if (result.middle == null) {
                    result.middle = new TSTreeNode(k, result)
                }
                result = result.middle
            }
        }

        result
    }

    private def search(_first1: TSTreeNode[A, V], key2: Vector[A], _first2: Long, last2: Long): (TSTreeNode[A, V], Long) = {
        Assert(_first1 != null)
        Assert(_first2 != last2)
        var first1 = _first1
        var first2 = _first2
        var cur1: TSTreeNode[A, V] = null

        var k2 = key2(first2)
        while (first1 != null) {
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

    def clone_(parent: TSTreeNode[A, V]): TSTreeNode[A, V] = {
        val node = new TSTreeNode[A, V](elem, parent)
        def _clone_(child: TSTreeNode[A, V]) = if (child == null) null else child.clone_(node)
        node.data = data
        node.left = _clone_(left)
        node.middle = _clone_(middle)
        node.right = _clone_(right)
        node

    }

    def size: Int = {
        var c = if (data.isEmpty) 0 else 1
        def _size(child: TSTreeNode[A, V]) = if (child == null) 0 else child.size
        c += _size(left)
        c += _size(middle)
        c += _size(right)
        c
    }

     // This is enough, assuming any leaf's data is not None.
    def isGarbage: Boolean = {
        data.isEmpty && left == null && middle == null && right == null
    }

    def collectGarbage: Unit = {
        if (parent != null && isGarbage) {
            if (parent.left == this) {
                parent.left = null
            }
            if (parent.middle == this) {
                parent.middle = null
            }
            if (parent.right == this) {
                parent.right = null
            }
            parent.collectGarbage
        }
    }

    def print(out: PrettyPrinter): Unit = {
        out.writeStartElement("node")

        out.writeElement("elem", elem)
        if (!data.isEmpty) {
            out.writeElement("value", data.get)
        }
        if (left != null) {
            out.writeStartElement("left")
            left.print(out)
            out.writeEndElement
        }
        if (middle != null) {
            out.writeStartElement("middle")
            middle.print(out)
            out.writeEndElement
        }
        if (right != null) {
            out.writeStartElement("right")
            right.print(out)
            out.writeEndElement
        }

        out.writeEndElement
    }
}
