package com.co.crediya.reports.sqs.listener.dto;

import java.math.BigDecimal;

public record ActiveLoansReportUpdateDTO(long newLoansCount, BigDecimal totalAmount) {}
