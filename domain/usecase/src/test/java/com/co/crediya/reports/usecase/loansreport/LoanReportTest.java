package com.co.crediya.reports.usecase.loansreport;

import static org.mockito.Mockito.*;

import com.co.crediya.reports.model.report.LoanReport;
import com.co.crediya.reports.model.report.gateways.LoanReportRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class LoanReportTest {
  @InjectMocks private LoansReportUseCase useCase;
  @Mock private LoanReportRepository loanReportRepository;
  private final LoanReport report = new LoanReport();

  @Test
  @DisplayName("Must get loan report")
  void mustGetLoanReport() {
    when(loanReportRepository.getReport()).thenReturn(Mono.just(report));
    StepVerifier.create(this.useCase.getReport())
        .assertNext(Assertions::assertNotNull)
        .verifyComplete();
    verify(loanReportRepository, times(1)).getReport();
  }

  @Test
  @DisplayName("Must update loan report")
  void mustUpdateLoanReport() {
    long newLoans = 5L;
    BigDecimal newAmount = BigDecimal.valueOf(5000);
    when(loanReportRepository.updateReport(newLoans, newAmount)).thenReturn(Mono.just(report));
    StepVerifier.create(this.useCase.updateReport(newLoans, newAmount))
        .assertNext(Assertions::assertNotNull)
        .verifyComplete();

    verify(loanReportRepository, times(1)).updateReport(newLoans, newAmount);
  }
}
