package edu.matc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This interface's purpose is to utilize logging
 */
public interface UseLogger {

    /**
     * This method's purpose is to provide logging functionality to all necessary Servlets
     * @return log - the log
     */
    default Logger log() {

        return LogManager.getLogger(this.getClass());
    }
}
