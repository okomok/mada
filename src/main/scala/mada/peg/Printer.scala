

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Printer {
    val defaultIndentWidth = 4
    def defaultWriter = new java.io.OutputStreamWriter(java.lang.System.out)
}

class Printer(out: java.io.Writer, indentWidth: Int) {
    import Vector.compatibles._

    def this() = this(Printer.defaultWriter, Printer.defaultIndentWidth)
    def this(o: java.io.Writer) = this(o, Printer.defaultIndentWidth)
    def this(w: Int) = this(Printer.defaultWriter, w)

    private var indentLevel = 0
    private def indent = Vector.single(' ').cycle(indentWidth).cycle(indentLevel)

    def writeStartElement(tag: Any): Unit = {
        out.write(Vector.toString(indent ++ "<" ++ tag.toString ++ ">\n"))
        out.flush
        indentLevel += 1
    }

    def writeEndElement(tag: Any): Unit = {
        indentLevel -= 1
        out.write(Vector.toString(indent ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    def writeElement(tag: Any, chars: Any): Unit = {
        out.write(Vector.toString(indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    def writeCharacters(chars: Any): Unit = {
        out.write(Vector.toString(indent ++ chars.toString))
        out.flush
    }

    def close: Unit = {
        out.close
    }

    def apply[A](p: Peg[A]): Peg[A] = new PrinterPeg(p)
    def apply[A](name: String, p: Peg[A]): Peg[A] = apply(p.named(name))

    class PrinterPeg[A](override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            writeStartElement(self)

            writeElement("peg:parsing", v.window(first, last))
            val cur = self.parse(v, first, last)
            if (cur == FAILED) {
                writeElement("peg:parsed", "peg:failed")
            } else {
                writeElement("peg:parsed", v.window(first, cur))
            }

            writeEndElement(self)
            cur
        }
    }
}
