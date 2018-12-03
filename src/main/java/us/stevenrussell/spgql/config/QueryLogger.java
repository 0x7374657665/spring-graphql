package us.stevenrussell.spgql.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Configuration
@Aspect
class QueryLogger {

    private static final Logger LOGGER = Logger.getLogger(QueryLogger.class.getName());

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @Before("execution(* org.springframework.jdbc.core.JdbcTemplate.*(String,..))")
    public void logJDBCQuery(JoinPoint jp) {
        String methodName = ((MethodSignature) jp.getSignature()).getMethod().getName();
        String query = jp.getArgs().length > 0 ? jp.getArgs()[0].toString() : "''";
        LOGGER.info(
                ANSI_BLUE +
                        String.format(">>> %-20s'%s'", methodName + ":", query) +
                        ANSI_RESET
        );
    }
}
