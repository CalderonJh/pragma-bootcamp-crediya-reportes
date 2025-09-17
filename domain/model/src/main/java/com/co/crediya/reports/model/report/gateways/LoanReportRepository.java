package com.co.crediya.reports.model.report.gateways;

import com.co.crediya.reports.model.report.LoanReport;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;

public interface LoanReportRepository {
  Mono<LoanReport> getReport();

  Mono<LoanReport> updateReport(long newLoans, BigDecimal newAmount);
}
