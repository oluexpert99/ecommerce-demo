package com.tinybeans.ecommerce.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
  private final String globalisationMessageCode;
  private final String defaultUserMessage;
  private final Object[] defaultUserMessageArgs;

  public ValidationException(
      String globalisationMessageCode,
      String defaultUserMessage,
      Object... defaultUserMessageArgs) {
    this.globalisationMessageCode = globalisationMessageCode;
    this.defaultUserMessage = defaultUserMessage;
    this.defaultUserMessageArgs = defaultUserMessageArgs;
  }
}
