

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg.detail


class TSTree[A, V](lt: (A, A) => Boolean) {
    private var root: Node = null

    override def toString: String = {
        new StringBuilder().append("<tstreemap>").append(root).append("</tstreemap>").toString
    }

    def contains(key: Vector[A]): Boolean = {
        get(key) != None
    }

    def get(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        get(key, first, last)
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        val (n, _, _) = Node.search(root, key, first, last)
        if (n == null) None else n.value
    }

    def parse(key: Vector[A]): Long = {
        val (first, last) = key.toPair
        parse(key, first, last)
    }

    def parse(key: Vector[A], first: Long, last: Long): Long = {
        val (_, n, i) = Node.search(root, key, first, last)
        if (n == null || n.value.isEmpty) Parser.FAILED else i
    }

    def put(key: Vector[A], value: V): V = {
        val (first, last) = key.toPair
        put(key, first, last, value)
    }

    def put(key: Vector[A], first: Long, last: Long, value: V): V = {
        Assert(first != last)

        if (root == null) {
            root = new Node(key(first))
        }
        Node.copyInto(key, first, last, root).value = Some(value)
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

        def search(_first1: Node, key2: Vector[A], _first2: Long, last2: Long): (Node, Node, Long) = {
            Assert(_first1 != null)
            var first1 = _first1
            var cur1: Node = null
            var first2 = _first2

            if (first2 != last2) {
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
                            return (first1, cur1, first2)
                        }

                        k2 = key2(first2)
                        first1 = first1.middle
                    }
                }
            }

            (first1, cur1, first2)
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
