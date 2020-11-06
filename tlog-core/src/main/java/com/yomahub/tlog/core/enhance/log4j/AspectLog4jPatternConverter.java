package com.yomahub.tlog.core.enhance.log4j;

import com.yomahub.tlog.context.TLogContext;
import com.yomahub.tlog.core.context.AspectLogContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Bryan.Zhang
 */
public class AspectLog4jPatternConverter extends PatternConverter {
    @Override
    protected String convert(LoggingEvent loggingEvent) {
        //只有在MDC没有设置的情况下才加到message里
        if(!TLogContext.hasTLogMDC()){
            String logValue = AspectLogContext.getLogValue();
            if(StringUtils.isNotBlank(logValue)){
                return logValue + " " + loggingEvent.getRenderedMessage();
            }else{
                return loggingEvent.getRenderedMessage();
            }
        }else{
            return loggingEvent.getRenderedMessage();
        }
    }
}
