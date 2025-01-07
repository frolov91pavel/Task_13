package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    Class<?>[] identityBy() default {};
    String fileNamePrefix() default "";
    boolean zip() default false;
    int listLimit() default Integer.MAX_VALUE;
}