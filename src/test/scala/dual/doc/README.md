# dualjp

`mada.dual` について説明したページです。




## Metaprogramming in Scala のハジマリ

* [MetaScala]
    * `Yrecursion`オプションの提案
* [Michid's Weblog]
* [Scalaで型レベルプログラミング] @kmizu
* [Apocalisp]




## 用語の定義

これらは俺様用語であり、他所では通じない場合があります。


### metatype

`dual.Any`のsubtypeを_metatype_と定義します。

    package dual
    sealed abstract class Boolean extends dual.Any { ...

これがmetaな世界の型になります。`dual.Any`がわざわざ必要な理由は後述。


### metamethod

_metamethod_ とは、metatypeを引数とする、typeあるいはtype-constructorです。

abstract metamethodのカタチはこうなります:

    type not <: dual.Boolean // 引数なし
    type equal[that <: dual.Boolean] <: dual.Boolean // 引数一個

metamethodをoverrideするにはこうします:

    override type not = ...
    override type equal[that <: dual.Boolean] = ...

`override`は無くても結構です。metamethodは再`override`できないことに注意。

metamethodの呼び出しには、`#`を使います:

    type foo[b <: dual.Boolean, c <: dual.Boolean] = b#equal[c]


引数のないmetamethodはしばしば_metavalue_と呼ばれます。
厳密には、metaな`val`というのはありませんが、メモ化されるため、`lazy val`と同じ意味になります。


### metadependent

metamethodの呼び出しがそれを取り囲むmetamethodの引数に依存している場合、それは_metadependent_である、と言います。

    class foo[b <: dual.Boolean] {
        type bar = b#not
    }

今、`bar`はmetadependentです。

    class wow[b <: dual.Boolean] {
        type buz = dual.`true`#not
    }

`buz`はmetadependentではありません。


### dualmethod

polymorphic-methodとしてもmetamethodとしても、 __似たような感じで__利用できる名前を、_dualmethod_と言います。

     def foo[b <: dual.Boolean, c <: dual.Boolean](b: b, c: c): foo[b, c] = b.equal(c)
    type foo[b <: dual.Boolean, c <: dual.Boolean] = b#equal[c]

今、dualmethodである`foo`が、同じくdualmethodである`equal`を呼び出しています。

引数のないdualmethodはしばしば_dualvalue_と呼ばれます。




## meta-genericsは動かない

metaな世界の`if`を作ることを考えます。まずフツーの世界で考えます。

    def myIf[T](b: scala.Boolean, _then: => T, _else: => T): T

これはpolymorphic-method(いわゆるgenerics)です。
これをmetaな世界に移植します。by-name引数はひとまず無視して、

    type metaIf[T, b <: dual.Boolean, _then <: T, _else <: T] <: T // ???

これは、metaな世界のgenericsであると考えられます。しかし、これはうまく動きません。
自分の経験によると、一般にmetaなgenericsはどんな形式であれ、うまく動かないようです。

仕方ないので、Genericsもby-name引数もない世界(つまり、昔のJava)で`if`を考えます。
フツーの世界:

    trait MyFuntion0 {
        def apply: scala.Any
    }

    def myIf(b: scala.Boolean, _then: MyFunction0, _else: MyFunction0): MyFunction0

by-name引数の代わりに、`MyFunction0`で代用します。戻り値型が`MyFunction0`なのは、`if`をネストする時に便利だからです。
さて、これをmetaな世界に移植すると、

    package dual

    trait Function0 extends dual.Any {
        type apply <: dual.Any
    }

    type `if`[b <: dual.Boolean, _then: dual.Function0, _else: dual.Function0] <: dual.Function0




## dual.Any


### meta-asInstanceOf

Meta-genericsが無いので、いわゆるダウンキャストが必要です。
つまり、metaな`asInstanceOf`を自前で実装しなければなりません。それが`dual.Any`の仕事になります:

    package dual
    
    trait Any {
        type asBoolean <: dual.Boolean
        type asFunction0 <: dual.Function0
        // ...
    }

    sealed abstract class `true` extends dual.Boolean {
        type asBoolean = `true`
        // ...
    }

これでダウンキャストを実装できました。
metaな`isInstanceOf`は(可能ではあるものの)まだ実装されていません。


### meta-this

metaな`this`参照も自前で初期化しないといけません。`self`という名前になっています:

    package dual

    trait Any {
        type self <: Any
        // ...
    }

    sealed abstract class `true` extends dual.Boolean {
        type self = `true`
        // ...
    }




## Primitives

Scalaはmetaな世界のprimitiveを用意してくれてないので、全て自前で実装します。
`dual`の提供するprimitiveなmetatypeは:

* `Boolean`
* `Nat` (自然数型)
* `Unit`
* `FunctionN`
* `TupleN`

`Nat`の実装として、`dual.nat.peano`と`dual.nat.dense`の二つが用意されています。
前者は小さな数字、後者は大きな数字が得意です。




## Duality

`mada.dual`で提供される機能はほとんど全部、dualmethodになっています。
値の計算と共に、型も変化してゆきます:

    def myAssert(a: dual.`true`) = ()

    def testDuality {
        import dual.nat.peano.Literal._
        val i: _3 = _3
        val j: _5#minus[_2] = _5.minus(_2)
        myAssert(i.equal(j)) // compile-time check
    }




## フツーの世界との対話


### undual

`dual.Any.undual`によってdualな世界からフツーの世界へ抜け出すことができます:

    import dual.nat.peano.Literal._

    def testUndual {
        val i: scala.Int = _3.times(_2).undual
        assertEquals(6, i)
    }


### Boxing

フツーの型は、dualな世界で使うために`dual.Box`を使って変換しないといけません:

    def testBox {
        val j = dual.Tuple2(dual.Box(2), dual.Box(3))
        assertEquals(3, j._2.undual)
    }

残念ながらauto-boxingはありません。




## Heterogeneous List

`mada.dual`のデータ構造は全てHeterogeneousです。型はそのまま保存されます。
例として`dual.List`を使ってみます:

    import dual.nat.peano.Literal._

    object add2 extends dual.Function1 {
        override type self = add2.type
        override  def apply[x <: dual.Any](x: x): apply[x] = x.asNat plus _2
        override type apply[x <: dual.Any] = x#asNat#plus[_2]
    }

    def testTrivial {
        val xs = _3 :: _4 :: _5 :: _6 :: dual.Nil
        val u = xs.map(add2) // returns a view
        assertEquals(5 :: 6 :: 7 :: 8 :: Nil, u.undual)
        locally {
            import dual.::
            val v: _5 :: _6 :: _7 :: _8 :: dual.Nil = u.force
        }
    }

`add2`のような`dual.FunctionN`の実装をon-the-flyで作ることは出来ないので、いちいち定義しないといけません。
また、`dual.List`のdualmethodは(たいてい)viewを返すことに注意してください。`force`で__canonical__なカタチに変換できます。

[MetaScala]と違って、`mada.dual`は`implicit`を使っていません。
一般に、`implicit`は__composable__ではありません。小さなmethodから大きなmethodを組み立てるのが困難です。




## 計算モデル

Metaprogramming in Scalaは:

* Pure(副作用なし)なオブジェクト指向です。(そんな言語今までありますか？)
* metamehodへ渡す引数は、渡すまえに評価されます。by-name引数はありませんが、代わりに`dual.Function0`を使えます。
* metaな`eq`はありません。型が同じかどうか判断することは出来ません。
* metadependentでないmetamethodの呼び出しの戻り値には、暗黙に`asXXX`が付加されます。
* メモ化があります。metamethodの呼び出しは、それが同じ式でありさえすれば、一度しか評価されません。

フィボナッチ数の例:

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#decrement]#plus[fibonacci[n#decrement#decrement]]
    }

この`fibonacci`は`O(n)`で動きます。しかし:

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#minus[_1]]#plus[fibonacci[n#decrement#decrement]]
    }

これは`O(n^2)`になります。再帰呼び出し中の式のカタチが異なっており、メモ化されないからです。
[C++ Template Metaprogramming]のAPPENDIX C.3.1を見てください。

ちなみに、どういうわけか

    class wow {
        type f = fibonacci[_10]
        type g = fibonacci[_10]
    }

この`fibonacci[_10]`は、二回とも評価されます。metadependentでないから、と推測されます。




## dualの問題点


### ユーザ定義型

metaな`asInstanceOf`を`dual.Any.asXXX`で実装しましたが、これによって
`mada.dual`の利用者がmetatypeを作ることが不可能になりました。
代わりに`dual.TupleN`や`dual.map`を駆使する必要があります。


### 型安全性

`dual.Any.asXXX`の不正な呼び出しはコンパイルエラーになりません(`Nothing`的なものを返す)。
このため、型安全性が__少し__弱まっています。


### assertion

コンパイル時assertionは実装できないと思われます。
ただし、metadependentでない文脈では`dual.free.assert`が使用できます。
実装は上記の`myAssert`と同じです。




## 実装の注意点


### 型が複雑になると遅くなる

[C++ Template Metaprogramming]のAPPENDIX C.3.7を見てください。
感覚的には`O(n^2)`です。これは深刻な欠陥かもしれません。(nsc/symtab/Types.scalaの`isGround`が膨大な回数呼ばれる)


### class内classを避ける

    class Outer[m <: dual.Nat](m: m) {
        type apply[n <: dual.Nat] = ...

        case class Inner[n <: dual.Nat](n: n) extends dual.Function0 {
            type self = Inner[n]
            override type apply = ...
            // ...
        }
    }

このようなカタチをしたクラスは、コンパイルがとても遅くなる場合があります。(nsc/symtab/Names.scalaの`equals`が遅いと思われる)
面倒になりますが、object内classなら問題ないです。


### case classは遅くない

caseを付けてもコンパイル時間に影響しないようです。おかげでdualmethodを簡単に定義できます。


### metatypeに実装を書かない

    trait MyMetatype extends dual.Any {
        type foo <: dual.Nat
        type bar = foo#plus[foo] // !!?
    }

複雑な状況に限りますが、`bar`の呼び出しによって、コンパイラがクラッシュすることがあります。
回避策は、実装専用の中間クラスを作ることです。(Javaと同じように)




## Links

* [Browse Source]
* [Browse Test Source]
* [The Scala Programming Language]


Shunsuke Sogame <<okomok@gmail.com>>




[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/dual/ "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/dual/ "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala "MetaScala"
[Michid's Weblog]: http://michid.wordpress.com/ "Michid's Weblog"
[Apocalisp]: http://apocalisp.wordpress.com/ "Apocalisp"
[Boost.Fusion]: http://www.boost.org/doc/libs/release/libs/fusion/ "Boost.Fusion"
[scala.react]: http://lamp.epfl.ch/~imaier/ "scala.react"
[Reactive Extensions]: http://msdn.microsoft.com/en-us/devlabs/ee794896.aspx "Reactive Extensions"
[scala.Responder]: http://scala.sygneca.com/libs/responder "scala.Responder"
[scala.collection.Traversable]: http://www.scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/scala/collection/Traversable.html "scala.collection.Traversable"
[scala-arm]: http://github.com/jsuereth/scala-arm "scala-arm"
[ARM in Java]: http://www.infoq.com/news/2010/08/arm-blocks "Automatic Resource Management in Java"
[Scalaで型レベルプログラミング]: http://d.hatena.ne.jp/kmizushima/20090418/1240072077 "Scalaで型レベルプログラミング"
[C++ Template Metaprogramming]:http://www.amazon.com/Template-Metaprogramming-Concepts-Techniques-Beyond/dp/0321227255 "C++ Template Metaprogramming"
