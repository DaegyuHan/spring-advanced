package org.example.expert.config.annotation;

import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLogRecord {
}

