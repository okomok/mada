

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


package object prettyprinter {


// constants

    /**
     * @return  <code>new java.io.OutputStreamWriter(java.lang.System.out)</code>
     */
    def defaultWriter = new java.io.OutputStreamWriter(java.lang.System.out)

    /**
     * @return  <code>4</code>.
     */
    val defaultIndentWidth = 4


// xml

    /**
     * @return  <code>xml(defaultWriter, defaultIndentWidth)</code>
     */
    def xml: PrettyPrinter = xml(defaultWriter, defaultIndentWidth)

    /**
     * @return  <code>xml(out, defaultIndentWidth)</code>
     */
    def xml(out: java.io.Writer): PrettyPrinter = xml(out, defaultIndentWidth)

    /**
     * @return  <code>xml(defaultWriter, indentWidth)</code>
     */
    def xml(indentWidth: Int): PrettyPrinter = xml(defaultWriter, indentWidth)

    /**
     * Creates a <code>PrettyPrinter</code> which outputs XML.
     * You must call <code>close</code> later.
     *
     * @param   out         where xml printed
     * @param   indentWidth indent width
     */
    def xml(out: java.io.Writer, indentWidth: Int): PrettyPrinter = Xml(out, indentWidth)


// trash

    /**
     * Returns a <code>PrettyPrinter</code> which outputs nothing.
     */
    val trash: PrettyPrinter = Trash()

}
