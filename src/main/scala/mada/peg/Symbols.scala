

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg


class Symbols[A](is: Iterator[A]*)(implicit c: A => Ordered[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        set.parse(v, first, last)
    }

    private val set = {
        val s = new TSTreeMap[A, Dummy](vec.stl.Less(c))
        s
    }

    object Dummy extends Dummy; trait Dummy
}


// Can't contain an empty Vector key.
class TSTreeMap[A, V](lt: (A, A) => Boolean) {
    private var root: Node = null

    def get(key: Vector[A]): Option[V] = {
        val (first, last) = key.toPair
        get(key, first, last)
    }

    def get(key: Vector[A], first: Long, last: Long): Option[V] = {
        // `first == last` is ok; it just returns `None`.

        val node = Node.findNode(root, key, first, last)
        if (node == null) {
            None
        } else {
            node.value
        }
    }

    def put(key: Iterator[A], value: V): V = {
        Assert(key.hasNext)

        if (root == null) {
            root = new Node(key.next)
        }

        Node.copyInto(key, root).value = Some(value)
        value
    }

    def parse(key_ : Vector[A], first: Long, last: Long): Long = {
        // `first == last` is ok; it just returns `FAILED`.

        Node.parse(root, key_, first, last)
    }

    object Node {
        def copyInto(first: Iterator[A], _result: Node): Node = {
            Assert(_result != null)

            var result = _result

            while (first.hasNext) {
                val k = first.next
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
                } else if (first.hasNext) {
                    if (result.middle == null) {
                        result.middle = new Node(first.next)
                    }
                    result = result.middle
                }
            }

            result
        }

        def findNode(_first1: Node, key2: Vector[A], _first2: Long, last2: Long): Node = {
            Assert(_first1 != null)

            var first1 = _first1
            var first2 = _first2

            while (first1 != null && first2 != last2) {
                val k2 = key2(first2)
                if (first1 > k2) {
                    first1 = first1.left
                } else if (first1 < k2) {
                    first1 = first1.right
                } else {
                    first1 = first1.middle; first2 += 1
                }
            }

            // If you loved heap, this could return `(first1, first2)`.
            first1
        }

        def parse(_first1: Node, key2: Vector[A], _first2: Long, last2: Long): Long = {
            Assert(_first1 != null)

            var first1 = _first1
            var first2 = _first2

            while (first1 != null && first2 != last2) {
                val k2 = key2(first2)
                if (first1 > k2) {
                    first1 = first1.left
                } else if (first1 < k2) {
                    first1 = first1.right
                } else {
                    first1 = first1.middle; first2 += 1
                }
            }

            if (first1 == null || !first1.value.isEmpty) {
                first2
            } else {
                Parser.FAILED
            }
        }
    }

    class Node(elem: A) {
        var value: Option[V] = None
        var left: Node = null
        var middle: Node = null
        var right: Node = null

        def <(e: A): Boolean = lt(elem, e)
        def >(e: A): Boolean = lt(e, elem)
    }
}
