

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// push(f) >> ( symbolMap("abc" --> fire >> "abcdef")) >> pop >> ...

class StackedActions[A] {
    val stack = new java.util.ArrayDeque[(Vector[A] => Any, Long)]

    def push(f: Vector[A] => Any): Peg[A] = new PushPeg(f)
    def pop(f: Vector[A] => Any): Peg[A] = new PopPeg(f)
    def fire: Peg[A] = new FirePeg

    class PushPeg(f: Vector[A] => Any) extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            stack.push((f, first))
            first
        }
        override def length = 0
    }

    class PopPeg(f: Vector[A] => Any) extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            stack.pop
            first
        }
        override def length = 0
    }

    class FirePeg extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val (f, i) = stack.pop
            f(v.window(i, first))
            first
        }
        override def length = 0
    }
}
