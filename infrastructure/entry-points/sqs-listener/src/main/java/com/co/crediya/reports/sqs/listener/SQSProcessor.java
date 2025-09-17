package com.co.crediya.reports.sqs.listener;

import com.co.crediya.reports.sqs.listener.dto.ActiveLoansReportUpdateDTO;
import com.co.crediya.reports.usecase.loansreport.LoansReportUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Function;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {
  private final LoansReportUseCase loansReportUseCase;
  private static final Logger logger = Logger.getLogger(SQSProcessor.class.getName());
  private final ObjectMapper objectMapper;
  private final TransactionalOperator transactionalOperator;

  @Override
  public Mono<Void> apply(Message message) {
    logger.info("Received message at SQSProcessor: " + message.body());
    try {
      ActiveLoansReportUpdateDTO body =
          objectMapper.readValue(message.body(), new TypeReference<>() {});
      return loansReportUseCase.updateReport(body.newLoansCount(), body.totalAmount()).then();
    } catch (JsonProcessingException e) {
      return Mono.error(e);
    }
  }
}
