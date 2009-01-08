

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg


class TSTree[A, V](_lt: (A, A) => Boolean) {
    private var impl: TSTreeImpl[A, V] = null
    private var emptyKeyValue: Option[V] = None

    def get(key: Vector[A]): Option[V] = {
        if (key.isEmpty) {
            emptyKeyValue
        } else if (impl == null) {
            None
        } else {
            val (first, last) = key.toPair
            impl.get(key, first, last)
        }
    }

    def put(key: Vector[A], value: V): V = {
        if (key.isEmpty) {
            emptyKeyValue = Some(value)
        } else if (impl == null) {
            impl = new TSTreeImpl(key, value, _lt)
        } else {
            val (first, last) = key.toPair
            impl.put(key, first, last, value)
        }
        value
    }

    def parse(key: Vector[A], first: Long, last: Long): Option[(V, Long)] = {
        if (first == last) {
            parseEmptyKey(first)
        } else if (impl == null) {
            None
        } else { // longest-match
            val x = impl.parse(key, first, last)
            if (x.isEmpty) {
                parseEmptyKey(first)
            } else {
                x
            }
        }
    }

    def containsKey(key: Vector[A]): Boolean = {
        get(key) != None
    }

    def print(out: PrettyPrinter): Unit = {
        out.writeStartElement("tstree")
        if (!emptyKeyValue.isEmpty) {
            out.writeElement("emptykeyvalue", emptyKeyValue.get)
        }
        impl.rootNode.print(out)
        out.writeEndElement
    }

    override def toString: String = {
        val out = new PrettyPrinter(new java.io.StringWriter)
        print(out)
        out.close
        out.out.toString
    }

    private def parseEmptyKey(first: Long): Option[(V, Long)] = {
        if (emptyKeyValue.isEmpty) {
            None
        } else {
            Some((emptyKeyValue.get, first))
        }
    }
}


class TSTreeImpl[A, V](_key: Vector[A], _value: V, _lt: (A, A) => Boolean) {
    Assert(!_key.isEmpty)

    val rootNode: Node = {
        val (first, last) = _key.toPair
        val node = new Node(_key(first))
        Node.copyInto(_key, first, last, node).value = Some(_value)
        node
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        parse(key, first, last) match {
            case Some((value, cur)) if (cur == last) => Some(value)
            case _ => None
        }
    }

    def put(key: Vector[A], first: Long, last: Long, value: V): Unit = {
        Assert(first != last)
        Node.copyInto(key, first, last, rootNode).value = Some(value)
    }

    def parse(key: Vector[A], first: Long, last: Long): Option[(V, Long)] = {
        Assert(first != last)

        val (node, cur) = Node.search(rootNode, key, first, last)
        if (node == null || node.value.isEmpty) {
            None
        } else {
            Some((node.value.get, cur))
        }
    }

    object Node {
        def copyInto(key: Vector[A], _first: Long, last: Long, _result: Node): Node = {
            Assert(_first != last)
            Assert(_result != null)
            var first = _first
            var result = _result

            var k = key(first)
            while (true) {
                if (_lt(k, result.elem)) {
                    if (result.left == null) {
                        result.left = new Node(k)
                    }
                    result = result.left
                } else if (_lt(result.elem, k)) {
                    if (result.right == null) {
                        result.right = new Node(k)
                    }
                    result = result.right
                } else {
                    first += 1
                    if (first == last) {
                        return result
                    }

                    k = key(first)
                    if (result.middle == null) {
                        result.middle = new Node(k)
                    }
                    result = result.middle
                }
            }

            result
        }

        def search(_first1: Node, key2: Vector[A], _first2: Long, last2: Long): (Node, Long) = {
            Assert(_first1 != null)
            Assert(_first2 != last2)
            var first1 = _first1
            var first2 = _first2
            var cur1: Node = null

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

    class Node(val elem: A) {
        var value: Option[V] = None
        var left: Node = null
        var middle: Node = null
        var right: Node = null

        def print(out: PrettyPrinter): Unit = {
            out.writeStartElement("node")

            out.writeElement("elem", elem)
            if (!value.isEmpty) {
                out.writeElement("value", value.get)
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
}
