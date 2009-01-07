

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object PrettyPrinter {
    def apply: PrettyPrinter = apply(new java.io.OutputStreamWriter(java.lang.System.out))
    def apply(out: java.io.Writer): PrettyPrinter = new PrettyPrinter(out)
}

class PrettyPrinter(out: java.io.Writer) {
    private var indentLevel = 0

    def writeStartElement(tag: Any): Unit = {
        out.write(appendIndents(new StringBuilder).append('<').append(tag).append('>').toString)
        out.write('\n')
        out.flush
        indentLevel += 1
    }

    def writeEndElement(tag: Any): Unit = {
        indentLevel -= 1
        out.write(appendIndents(new StringBuilder).append("</").append(tag).append('>').toString)
        out.write('\n')
        out.flush
    }

    def writeElement(tag: Any, chars: Any): Unit = {
        out.write(
            appendIndents(new StringBuilder).
                append('<').append(tag).append('>').
                    append(chars).
                append("</").append(tag).append('>').
            toString )
        out.write('\n')
        out.flush
    }

    def writeCharacters(tag: Any): Unit = {
        out.write(appendIndents(new StringBuilder()).append(tag).toString)
        out.write('\n')
        out.flush
    }

    def appendIndents(sb: StringBuilder): StringBuilder = {
        var i = 0
        while (i < indentLevel) {
            sb.append("    ")
            i += 1
        }
        sb
    }

    def close: Unit = {
        out.close
    }
}


object PrintTo {
    def apply[A](p: Peg[A], out: PrettyPrinter): Peg[A] = new PrintToPeg[A](p, out)
}

class PrintToPeg[A](override val self: Peg[A], out: PrettyPrinter) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        out.writeStartElement(self)

        out.writeElement("parsing", v.window(first, last))
        val cur = self.parse(v, first, last)
        if (cur == FAILED) {
            out.writeElement("parsed", "FAILED")
        } else {
            out.writeElement("parsed", v.window(first, cur))
        }

        out.writeEndElement(self)
        cur
    }
}
