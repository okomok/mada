

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


import sbt._


class Mada(info: ProjectInfo) extends DefaultProject(info) {
    val junit = "junit" % "junit" % "3.8.2" % "test"
    val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"
    override def compileOptions = Seq(Deprecation, Unchecked/*, ExplainTypes*/) ++ compileOptions("-Yrecursion", "50") ++ super.compileOptions
}