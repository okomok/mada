

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains utility methods operating on type <code>ASTreeBuilder</code>.
 */
private[mada] object XMLPrettyPrinter {
    /**
     * Default indent width: <code>4</code>
     */
    val defaultIndentWidth = 4

    /**
     * @return  <code>new java.io.OutputStreamWriter(java.lang.System.out)</code>.
     */
    def defaultWriter = new java.io.OutputStreamWriter(java.lang.System.out)
}


/**
 * A quick-and-dirty printer for pegs; mainly used for debugging.
 *
 * @param   out where strings are printed
 * @param   indentWidth indent width
 */
private[mada] class XMLPrettyPrinter(val out: java.io.Writer, val indentWidth: Int) extends PrettyPrinter {
    out.write("<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>\n")

    /**
     * @return  <code>this(XMLPrettyPrinter.defaultWriter, XMLPrettyPrinter.defaultIndentWidth)</code>.
     */
    def this() = this(XMLPrettyPrinter.defaultWriter, XMLPrettyPrinter.defaultIndentWidth)

    /**
     * @return  <code>this(o, XMLPrettyPrinter.defaultIndentWidth)</code>.
     */
    def this(o: java.io.Writer) = this(o, XMLPrettyPrinter.defaultIndentWidth)

    /**
     * @return  <code>this(XMLPrettyPrinter.defaultWriter, w)</code>
     */
    def this(w: Int) = this(XMLPrettyPrinter.defaultWriter, w)

    private var indentLevel = 0
    private val indentString = Vectors.single(' ').cycle(indentWidth)
    private def indent = indentString.cycle(indentLevel)
    private val stack = new java.util.ArrayDeque[Any]

    /**
     * Writes start element tag with new line.
     */
    private[mada] def writeStartElement(tag: Any): Unit = {
        stack.push(tag)
        out.write(Vectors.stringize(indent ++ "<" ++ tag.toString ++ ">\n"))
        out.flush
        indentLevel += 1
    }

    /**
     * Writes end element tag with new line.
     */
    private[mada] def writeEndElement: Unit = {
        indentLevel -= 1
        out.write(Vectors.stringize(indent ++ "</" ++ stack.pop.toString ++ ">\n"))
        out.flush
    }

    /**
     * Writes element without new line.
     */
    private[mada] def writeElement(tag: Any, chars: Any): Unit = {
        out.write(Vectors.stringize(indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    /**
     * @return  <code>out.close</code>.
     */
    override def close: Unit = {
        Assert(stack.isEmpty)
        out.close
    }

    override def write[A](p: Peg[A]): Peg[A] = new ElementPeg(p)

    private class ElementPeg[A](override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            writeStartElement(self)

            writeElement("Peg.parsing", v(start, end))
            val cur = self.parse(v, start, end)
            if (cur == Peg.FAILURE) {
                writeElement("Peg.parsed", "Peg.FAILURE")
            } else {
                writeElement("Peg.parsed", v(start, cur))
            }

            writeEndElement
            cur
        }
    }
}
