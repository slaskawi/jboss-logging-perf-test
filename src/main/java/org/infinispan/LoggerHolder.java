package org.infinispan;

import org.jboss.logging.Logger;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LoggerHolder {

   private Logger logger = Logger.getLogger(this.getClass());
   public boolean isTraceEnabled;

   @Setup
   public void init() {
      logger = Logger.getLogger(this.getClass());
      isTraceEnabled = logger.isTraceEnabled();
   }

   public Logger getLogger() {
      return logger;
   }

}
