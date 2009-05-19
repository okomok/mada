

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
 * @param   out         where strings are printed
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
    private val indentString = vector.single(' ').times(indentWidth)
    private def indent = indentString.times(indentLevel)
    private val stack = new java.util.ArrayDeque[Any]

    /**
     * Writes start element tag with new line.
     */
    def writeStartElement(tag: Any): Unit = {
        stack.push(tag)
        out.write(vector.stringize(indent ++ "<" ++ tag.toString ++ ">\n"))
        out.flush
        indentLevel += 1
    }

    /**
     * Writes end element tag with new line.
     */
    def writeEndElement: Unit = {
        indentLevel -= 1
        out.write(vector.stringize(indent ++ "</" ++ stack.pop.toString ++ ">\n"))
        out.flush
    }

    /**
     * Writes element without new line.
     */
    def writeElement(tag: Any, chars: Any): Unit = {
        out.write(vector.stringize(indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    /**
     * @return  <code>out.close</code>.
     */
    override def close: Unit = {
        Assert(stack.isEmpty)
        out.close
    }

    override def print[A](p: Peg[A]): Peg[A] = new ElementPeg(p)

    private class ElementPeg[A](override val delegate: Peg[A]) extends Forwarder[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            writeStartElement(delegate)

            writeElement("parsing", v(start, end))
            val cur = delegate.parse(v, start, end)
            if (cur == FAILURE) {
                writeElement("parsed", "FAILURE")
            } else {
                writeElement("parsed", v(start, cur))
            }

            writeEndElement
            cur
        }
    }
}
