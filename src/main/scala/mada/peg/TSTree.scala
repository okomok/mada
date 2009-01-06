

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg


class TSTree[A, V](lt: (A, A) => Boolean) {
    private var rootNode: Node = null

    override def toString: String = {
        new StringBuilder().append("<tstreemap>").append(rootNode).append("</tstreemap>").toString
    }

    def contains(key: Vector[A]): Boolean = {
        get(key) != None
    }

    def get(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        get(key, first, last)
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        parse(key, first, last) match {
            case Some((value, cur)) if (cur == last) => Some(value)
            case _ => None
        }
    }

    def parse(key: Vector[A]): Option[(V, Long)] = {
        val (first, last) = key.toPair
        parse(key, first, last)
    }

    def parse(key: Vector[A], first: Long, last: Long): Option[(V, Long)] = {
        if (rootNode == null || first == last) {
            return None
        }

        val (node, cur) = Node.search(rootNode, key, first, last)
        if (node == null || node.value.isEmpty) {
            None
        } else {
            Some((node.value.get, cur))
        }
    }

    def put(key: Vector[A], value: V): V = {
        val (first, last) = key.toPair
        put(key, first, last, value)
    }

    def put(key: Vector[A], first: Long, last: Long, value: V): V = {
        Assert(first != last)

        if (rootNode == null) {
            rootNode = new Node(key(first))
        }
        Node.copyInto(key, first, last, rootNode).value = Some(value)
        value
    }

    object Node {
        def copyInto(key: Vector[A], _first: Long, last: Long, _result: Node): Node = {
            Assert(_first != last)
            Assert(_result != null)
            var first = _first
            var result = _result

            var k = key(first)
            while (true) {
                if (result > k) {
                    if (result.left == null) {
                        result.left = new Node(k)
                    }
                    result = result.left
                } else if (result < k) {
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
                if (first1 > k2) {
                    first1 = first1.left
                } else if (first1 < k2) {
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

    class Node(elem: A) {
        var value: Option[V] = None
        var left: Node = null
        var middle: Node = null
        var right: Node = null

        def <(e: A): Boolean = lt(elem, e)
        def >(e: A): Boolean = lt(e, elem)

        override def toString = {
            val sb = new StringBuilder()
            sb.append("<node>")
                sb.append("<elem>").append(elem).append("</elem>")
            if (!value.isEmpty) {
                sb.append("<value>").append(value.get).append("</value>")
            }
            if (left != null) {
                sb.append("<left>").append(left).append("</left>")
            }
            if (middle != null) {
                sb.append("<middle>").append(middle).append("</middle>")
            }
            if (right != null) {
                sb.append("<right>").append(right).append("</right>")
            }
            sb.append("</node>").toString
        }
    }
}
