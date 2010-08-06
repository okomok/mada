

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
final class Repeat[z <: Any](z: z) {
     def apply = iterate(z, function.identity)
    type apply = iterate[z, function.identity]
}
