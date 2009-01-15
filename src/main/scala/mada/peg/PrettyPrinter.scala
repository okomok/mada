

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import Vector.Compatibles._


object PrettyPrinter {
    val defaultIndentWidth = 4
    def defaultWriter = new java.io.OutputStreamWriter(java.lang.System.out)
}

class PrettyPrinter(val out: java.io.Writer, val indentWidth: Int) {
    def this() = this(PrettyPrinter.defaultWriter, PrettyPrinter.defaultIndentWidth)
    def this(o: java.io.Writer) = this(o, PrettyPrinter.defaultIndentWidth)
    def this(w: Int) = this(PrettyPrinter.defaultWriter, w)

    private var indentLevel = 0
    private val indentString = Vector.single(' ').cycle(indentWidth)
    private def indent = indentString.cycle(indentLevel)
    private val stack = new java.util.LinkedList[Any]

    def writeStartElement(tag: Any): Unit = {
        stack.push(tag)
        out.write(Vector.toString(indent ++ "<" ++ tag.toString ++ ">\n"))
        out.flush
        indentLevel += 1
    }

    def writeEndElement: Unit = {
        indentLevel -= 1
        out.write(Vector.toString(indent ++ "</" ++ stack.pop.toString ++ ">\n"))
        out.flush
    }

    def writeElement(tag: Any, chars: Any): Unit = {
        out.write(Vector.toString(indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    def close: Unit = {
        Assert(stack.isEmpty)
        out.close
    }

    def apply[A](p: Peg[A]): Peg[A] = writer(p)
    def apply[A](name: String, p: Peg[A]): Peg[A] = writer(name, p)
    def writer[A](p: Peg[A]): Peg[A] = new WriterPeg(p)
    def writer[A](name: String, p: Peg[A]): Peg[A] = writer(p.named(name))

    class WriterPeg[A](override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            writeStartElement(self)

            writeElement("peg:parsing", v.window(first, last))
            val cur = self.parse(v, first, last)
            if (cur == FAILURE) {
                writeElement("peg:parsed", "peg:failed")
            } else {
                writeElement("peg:parsed", v.window(first, cur))
            }

            writeEndElement
            cur
        }
    }
}
