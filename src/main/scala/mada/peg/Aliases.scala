

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait Aliases { this: Peg.type =>
    /**
     * Alias of <code>Peg</code>
     */
    type Type[A] = Peg[A]

    /**
     * Alias of <code>PegProxy</code>
     */
    type Forwarder[A] = peg.PegProxy[A]

    /**
     * Alias of <code>Vector.Func[A, Unit]</code>
     */
    type Action[A] = Vector.Func[A, Unit]

    /**
     * Alias of <code>Vector.Func3[A, Unit]</code>
     */
    type Action3[A] = Vector.Func3[A, Unit]

    @alias type ZeroWidth[A] = peg.ZeroWidth[A]
    @alias type Quantified[A] = peg.Quantified[A]
    @alias type PegProxy[A] = peg.PegProxy[A]
    @alias type Rule[A] = peg.Rule[A]
    @alias val ASTreeBuilder = peg.ASTreeBuilder
    @alias type ASTreeBuilder[T <: javax.swing.tree.DefaultMutableTreeNode] = peg.ASTreeBuilder[T]
    @alias val SymbolSet = peg.SymbolSet
    @alias val SymbolMap = peg.SymbolMap
    @alias type SymbolSet[A] = peg.SymbolSet[A]
    @alias type SymbolMap[A] = peg.SymbolMap[A]
    @alias type ByNeedActions[A] = peg.ByNeedActions[A]
    @alias type RegionActions[A] = peg.RegionActions[A]
    @alias type StackActions[A, B] = peg.StackActions[A, B]
    @alias type CapturingGroups[K, A] = peg.CapturingGroups[K, A]
    @alias type Memoizer[A] = peg.Memoizer[A]
    @alias val PrettyPrinter = peg.PrettyPrinter
    @alias type PrettyPrinter = peg.PrettyPrinter
}
