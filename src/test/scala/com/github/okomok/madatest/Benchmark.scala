

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

trait Benchmark extends scala.testing.Benchmark {
    def testTime: Unit = {
        println(toString + " benchmark")
        multiplier = grainCount
        for (t <- runBenchmark(runCount)) {
            println("    " + t + "ms")
        }
    }

    def runCount: Int = 5
    def grainCount: Int = 10

    def longCalc: Unit = {
        var i = 0
        while (i < 1000) {
            i += 1
        }
    }
}
