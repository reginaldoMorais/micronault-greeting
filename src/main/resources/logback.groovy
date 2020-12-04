import static ch.qos.logback.classic.Level.*

def LOG_PATH = System.getProperty("log.dir") ?: "log"

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%cyan(%d{HH:mm:ss.SSS}) %gray([%thread]) %highlight(%-5level) %magenta(%logger{36}) - %msg%n"
    }
}

appender("FILE", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%cyan(%d{HH:mm:ss.SSS}) %gray([%thread]) %highlight(%-5level) %magenta(%logger{36}) - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_PATH}/greeting.%d{yyyy-MM-dd}.log"
        maxHistory = 10
    }
}

def appenderList = ['STDOUT', 'FILE']

logger('org.springframework', INFO, appenderList, false)
logger('org.springframework.data.mongodb', WARN, appenderList, false)
logger('org.springframework.security', WARN, appenderList, false)
logger('org.springframework.jdbc', WARN, appenderList, false)
logger('org.springframework.transaction.interceptor', TRACE, appenderList, false)
logger('com.b2wdigital', INFO, appenderList, false)
logger('org.hibernate', WARN, appenderList, false)
logger('org.apache.http', WARN, appenderList, false)

root(INFO, appenderList)
