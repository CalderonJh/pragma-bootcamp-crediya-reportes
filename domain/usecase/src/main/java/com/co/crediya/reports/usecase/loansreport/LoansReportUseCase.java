package com.co.crediya.reports.usecase.loansreport;

import com.co.crediya.reports.model.report.LoanReport;
import com.co.crediya.reports.model.report.gateways.LoanReportRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoansReportUseCase {
  private final LoanReportRepository loanReportRepository;

  public Mono<LoanReport> getReport() {
    return loanReportRepository.getReport();
  }

  public Mono<LoanReport> updateReport(long newLoans, BigDecimal newAmount) {
    return loanReportRepository.updateReport(newLoans, newAmount);
  }
}
