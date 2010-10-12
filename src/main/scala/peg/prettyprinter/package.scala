

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


package object prettyprinter {


// constants

    @equivalentTo("new java.io.OutputStreamWriter(java.lang.System.out)")
    def defaultWriter = new java.io.OutputStreamWriter(java.lang.System.out)

    @equivalentTo("4")
    val defaultIndentWidth = 4


// xml

    @equivalentTo("xml(defaultWriter, defaultIndentWidth)")
    def xml: PrettyPrinter = xml(defaultWriter, defaultIndentWidth)

    @equivalentTo("xml(out, defaultIndentWidth)")
    def xml(out: java.io.Writer): PrettyPrinter = xml(out, defaultIndentWidth)

    @equivalentTo("xml(defaultWriter, indentWidth)")
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
