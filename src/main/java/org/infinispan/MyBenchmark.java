package org.infinispan;

import org.jboss.logging.Logger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

@Fork(value=1, jvmArgs = {
      "-Xmx4G",
      "-Xms4G",
      "-server"
//      "-XX:+PrintCompilation",
//      "-XX:+UnlockDiagnosticVMOptions",
//      "-XX:+PrintCompilation2"
})
// Run with: -Dorg.jboss.logging.provider=jboss -Djava.util.logging.manager=org.jboss.logmanager.LogManager
@BenchmarkMode({Mode.Throughput})
public class MyBenchmark {

    @Benchmark
    public void noVariable(LoggerHolder loggerHolder) {
        Logger logger = loggerHolder.getLogger();
        logger.tracef("test %s", "test");
    }

    @Benchmark
    public void withVariable(LoggerHolder loggerHolder) {
        Logger logger = loggerHolder.getLogger();
        if(loggerHolder.isTraceEnabled)
            logger.tracef("test %s", "test");
    }

    @Benchmark
    public void withIsTraceEnabledCheck(LoggerHolder loggerHolder) {
        Logger logger = loggerHolder.getLogger();
        if(logger.isTraceEnabled())
            logger.tracef("test %s", "test");
    }

}
