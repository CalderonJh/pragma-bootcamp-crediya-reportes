package com.co.crediya.reports.api;

import com.co.crediya.reports.model.report.LoanReport;
import com.co.crediya.reports.usecase.loansreport.LoansReportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
  private final LoansReportUseCase loansReportUseCase;

  @Operation(
      operationId = "getReport",
      summary = "Obtiene reporte de cantidad de créditos y monto total",
      requestBody =
          @RequestBody(content = @Content(schema = @Schema(implementation = LoanReport.class))),
      responses = {@ApiResponse(responseCode = "200", content = @Content())})
  public Mono<ServerResponse> listenGETReport() {
    return loansReportUseCase.getReport().flatMap(report -> ServerResponse.ok().bodyValue(report));
  }
}
