package com.co.crediya.reports.dynamodb;

import java.math.BigDecimal;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Setter
@DynamoDbBean
public class LoanReportEntity {
  private String reportId;
  private Long totalLoans;
  private BigDecimal totalAmount;

  @DynamoDbPartitionKey
  @DynamoDbAttribute("idk")
  public String getReportId() {
    return reportId;
  }

  @DynamoDbAttribute("totalLoans")
  public Long getTotalLoans() {
    return totalLoans;
  }

  @DynamoDbAttribute("totalAmount")
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }
}
