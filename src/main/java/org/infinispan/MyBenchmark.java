package org.infinispan;

import org.jboss.logging.Logger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

@Fork(value=1, jvmArgs = {
      "-Xmx4G",
      "-Xms4G",
      "-server",
      "-Dorg.jboss.logging.provider=jboss",
      "-Djava.util.logging.manager=org.jboss.logmanager.LogManager",
      // Try to imitate https://github.com/AdoptOpenJDK/jitwatch/blob/master/makeDemoLogFile.sh
      "-XX:+UnlockDiagnosticVMOptions",
      "-XX:+TraceClassLoading",
      "-XX:+LogCompilation",
      "-XX:+PrintInlining",
      "-XX:+PrintAssembly",
      "-XX:PrintAssemblyOptions=intel"
})
// Run with: -Dorg.jboss.logging.provider=jboss -Djava.util.logging.manager=org.jboss.logmanager.LogManager
@BenchmarkMode({Mode.Throughput})
public class MyBenchmark {
    private static final Logger logger = Logger.getLogger(MyBenchmark.class);
    private static final boolean trace = logger.isTraceEnabled();

    @Benchmark
    public void noVariable() {
        logger.tracef("test %s", "test");
    }

    @Benchmark
    public void withConstant() {
        if(trace)
            logger.tracef("test %s", "test");
    }

    @Benchmark
    public void withIsTraceEnabledCheck() {
        if(logger.isTraceEnabled())
            logger.tracef("test %s", "test");
    }

}
