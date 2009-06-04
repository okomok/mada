

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.prettyprinter


case class Xml(_1: java.io.Writer, _2: Int) extends PrettyPrinter {
    _1.write("<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>\n")

    private var indentLevel = 0
    private val indentString = sequence.vector.single(' ').times(_2)
    private def indent = indentString.times(indentLevel)
    private val stack = new java.util.ArrayDeque[Any]

    /**
     * Writes start element tag with new line.
     */
    def writeStartElement(tag: Any): Unit = {
        stack.push(tag)
        _1.write((indent ++ "<" ++ tag.toString ++ ">\n").stringize)
        _1.flush
        indentLevel += 1
    }

    /**
     * Writes end element tag with new line.
     */
    def writeEndElement: Unit = {
        indentLevel -= 1
        _1.write((indent ++ "</" ++ stack.pop.toString ++ ">\n").stringize)
        _1.flush
    }

    /**
     * Writes element without new line.
     */
    def writeElement(tag: Any, chars: Any): Unit = {
        _1.write((indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n").stringize)
        _1.flush
    }

    /**
     * @return  <code>_1.close</code>.
     */
    override def close: Unit = {
        util.assert(stack.isEmpty)
        _1.close
    }

    override def print[A](p: Peg[A]): Peg[A] = Print(p)

    case class Print[A](_1: Peg[A]) extends Peg[A] {
        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            writeStartElement(_1)

            writeElement("parsing", v(start, end))
            val cur = _1.parse(v, start, end)
            if (cur == FAILURE) {
                writeElement("parsed", "FAILURE")
            } else {
                writeElement("parsed", v(start, cur))
            }

            writeEndElement
            cur
        }
        override def width = _1.width
    }
}
