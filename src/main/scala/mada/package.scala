

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package object mada {

    /**
     * Is mada in debug mode?
     */
    final val isDebug = true

    /**
     * Is mada in debug mode?
     */
    type isDebug = meta.`true`


    def newArray[A](c: Int): Array[A] = new Array[AnyRef](c).asInstanceOf[Array[A]]

}
