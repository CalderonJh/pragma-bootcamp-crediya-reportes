package com.co.crediya.reports.dynamodb;

import com.co.crediya.reports.model.report.LoanReport;
import com.co.crediya.reports.model.report.gateways.LoanReportRepository;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class LoanReportDynamoRepository implements LoanReportRepository {
  public static final String PARTITION_VALUE = "LOAN_REPORT";
  private final DynamoDbAsyncTable<LoanReportEntity> table;
  private final ObjectMapper mapper;
  private static final Logger logger = Logger.getLogger(LoanReportDynamoRepository.class.getName());

  public LoanReportDynamoRepository(
      DynamoDbEnhancedAsyncClient client,
      ObjectMapper mapper) {
    this.table = client.table("active_loans", TableSchema.fromBean(LoanReportEntity.class));
    this.mapper = mapper;
  }

  @Override
  public Mono<LoanReport> getReport() {
    return fetchLoanReport().map(ent -> mapper.map(ent, LoanReport.class));
  }

  private Mono<LoanReportEntity> fetchLoanReport() {
    return Mono.fromFuture(() -> table.getItem(r -> r.key(k -> k.partitionValue(PARTITION_VALUE))));
  }

  @Override
  public Mono<LoanReport> updateReport(long newLoans, BigDecimal newAmount) {
    return fetchLoanReport()
        .defaultIfEmpty(new LoanReportEntity())
        .flatMap(
            report -> {
              report.setReportId(PARTITION_VALUE);
              report.setTotalLoans(
                  (report.getTotalLoans() == null ? 0L : report.getTotalLoans()) + newLoans);
              report.setTotalAmount(
                  (report.getTotalAmount() == null ? BigDecimal.ZERO : report.getTotalAmount())
                      .add(newAmount));
              return Mono.fromFuture(() -> table.putItem(report)).thenReturn(report);
            })
        .doOnNext(ent -> logger.info(() -> "Updated active loan report: " + ent))
        .map(ent -> mapper.map(ent, LoanReport.class));
  }
}
