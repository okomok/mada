

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import Vector.Compatibles._


/**
 * Contains utility methods operating on type <code>PrettyPrinter</code>.
 */
object PrettyPrinter {
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
class PrettyPrinter(val out: java.io.Writer, val indentWidth: Int) {
    /**
     * @return  <code>this(PrettyPrinter.defaultWriter, PrettyPrinter.defaultIndentWidth)</code>.
     */
    def this() = this(PrettyPrinter.defaultWriter, PrettyPrinter.defaultIndentWidth)

    /**
     * @return  <code>this(o, PrettyPrinter.defaultIndentWidth)</code>.
     */
    def this(o: java.io.Writer) = this(o, PrettyPrinter.defaultIndentWidth)

    /**
     * @return  <code>this(PrettyPrinter.defaultWriter, w)</code>
     */
    def this(w: Int) = this(PrettyPrinter.defaultWriter, w)

    private var indentLevel = 0
    private val indentString = Vector.single(' ').cycle(indentWidth)
    private def indent = indentString.cycle(indentLevel)
    private val stack = new java.util.ArrayDeque[Any]

    /**
     * Writes start element tag with new line.
     */
    def writeStartElement(tag: Any): Unit = {
        stack.push(tag)
        out.write(Vector.stringize(indent ++ "<" ++ tag.toString ++ ">\n"))
        out.flush
        indentLevel += 1
    }

    /**
     * Writes end element tag with new line.
     */
    def writeEndElement: Unit = {
        indentLevel -= 1
        out.write(Vector.stringize(indent ++ "</" ++ stack.pop.toString ++ ">\n"))
        out.flush
    }

    /**
     * Writes element without new line.
     */
    def writeElement(tag: Any, chars: Any): Unit = {
        out.write(Vector.stringize(indent ++ "<" ++ tag.toString ++ ">" ++ chars.toString ++ "</" ++ tag.toString ++ ">\n"))
        out.flush
    }

    /**
     * Closes <code>out</code>.
     */
    def close: Unit = {
        Assert(stack.isEmpty)
        out.close
    }

    /**
     * Alias of <code>writer</code>
     */
    def apply[A](p: Peg[A]): Peg[A] = writer(p)

    /**
     * Alias of <code>writer</code>
     */
    def apply[A](name: String, p: Peg[A]): Peg[A] = writer(name, p)

    /**
     * Outputs peg.
     */
    def writer[A](p: Peg[A]): Peg[A] = new WriterPeg(p)

    /**
     * Outputs peg with specified tag name.
     */
    def writer[A](name: String, p: Peg[A]): Peg[A] = writer(p.named(name))

    private class WriterPeg[A](override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            writeStartElement(self)

            writeElement("peg:parsing", v(start, end))
            val cur = self.parse(v, start, end)
            if (cur == Peg.FAILURE) {
                writeElement("peg:parsed", "peg:failed")
            } else {
                writeElement("peg:parsed", v(start, cur))
            }

            writeEndElement
            cur
        }
    }
}
