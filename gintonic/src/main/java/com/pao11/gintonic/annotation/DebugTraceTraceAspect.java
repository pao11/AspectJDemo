
package com.pao11.gintonic.annotation;

import android.util.Log;

import com.pao11.gintonic.internal.DebugLog;
import com.pao11.gintonic.internal.StopWatch;
import com.pao11.gintonic.internal.MethodMsg;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 跟踪被DebugTrace注解标记的方法和构造函数
 */
@Aspect
public class DebugTraceTraceAspect {

  private static final String POINTCUT_METHOD =
      "execution(@DebugTrace * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@DebugTrace *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithDebugTrace() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedDebugTrace() {}

  @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    String msg = buildLogMessage(methodName, Double.valueOf(stopWatch.getTotalTime(1)).longValue());
    DebugLog.log(new MethodMsg(className, buildLogMessage(methodName, Double.valueOf(stopWatch.getTotalTimeMillis()).longValue()), Double.valueOf(stopWatch.getTotalTimeMicros()).longValue()));
//    System.out.println("====================around-debug");
    Log.e(className, msg);

    return result;
  }

  private static String buildLogMessage(String methodName, long methodDuration) {
    StringBuilder message = new StringBuilder();
    message.append("Gintonic --> ");
    message.append(methodName);
    message.append(" --> ");
    message.append("[");
    message.append(methodDuration);
    message.append("ms");
    message.append("]");

    return message.toString();
  }
}
