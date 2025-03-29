package com.concell.system.mapeadores.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistroResponse {
  private String message;
  private String token;
}
