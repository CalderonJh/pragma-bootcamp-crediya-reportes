package com.co.crediya.reports.model.report;

import java.math.BigDecimal;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanReport {
  private String reportId;
  private Long totalLoans;
  private BigDecimal totalAmount;
}
