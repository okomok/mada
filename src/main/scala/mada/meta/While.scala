

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


trait While { this: Meta.type =>

    // For now, Scala doesn't allow metamethod recursion. That's why this is similar to Boost.Preprocessor way.

    // Ideally, argument should be lazily evaluated, but Scala seems to trigger ETI after all.
    type `while` [z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while1[op#apply1[z], p, op], z]
    type while1[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while2[op#apply1[z], p, op], z]
    type while2[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while3[op#apply1[z], p, op], z]
    type while3[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while4[op#apply1[z], p, op], z]
    type while4[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while5[op#apply1[z], p, op], z]
    type while5[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while6[op#apply1[z], p, op], z]
    type while6[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while7[op#apply1[z], p, op], z]
    type while7[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while8[op#apply1[z], p, op], z]
    type while8[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while9[op#apply1[z], p, op], z]
    type while9[z <: Object, p <: Predicate1[z], op <: Transform[z]] = `if`[z, p#apply1[z], while10[op#apply1[z], p, op], z]
    type while10[z <: Object, p <: Predicate1[z], op <: Transform[z]] = Nothing
    // More types will crash compiler...

}
