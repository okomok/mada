

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object PrettyPrinter {
    val defaultIndentWidth: Int = 4
    def apply: PrettyPrinter = apply(defaultIndentWidth)
    def apply(indentWidth: Int): PrettyPrinter = apply(new java.io.OutputStreamWriter(java.lang.System.out), indentWidth)
    def apply(out: java.io.Writer): PrettyPrinter = new PrettyPrinter(out, defaultIndentWidth)
    def apply(out: java.io.Writer, indentWidth: Int): PrettyPrinter = new PrettyPrinter(out, indentWidth)
}

class PrettyPrinter(out: java.io.Writer, indentWidth: Int) {
    import Vector.compatibles._

    private var indentLevel = 0

    def close: Unit = {
        out.close
    }

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

    private def indent: Vector[Char] = {
        Vector.single(' ').cycle(indentWidth).cycle(indentLevel)
    }
}


object Printed {
    def apply[A](p: Peg[A], out: PrettyPrinter): Peg[A] = new PrintedPeg[A](p, out)
    def apply[A](p: Peg[A], out: PrettyPrinter, name: String): Peg[A] = apply(p.named(name), out)
}

class PrintedPeg[A](override val self: Peg[A], out: PrettyPrinter) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        out.writeStartElement(self)

        out.writeElement("peg:parsing", v.window(first, last))
        val cur = self.parse(v, first, last)
        if (cur == FAILED) {
            out.writeElement("peg:parsed", "peg:failed")
        } else {
            out.writeElement("peg:parsed", v.window(first, cur))
        }

        out.writeEndElement(self)
        cur
    }
}
