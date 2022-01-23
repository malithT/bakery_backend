package com.bakery.bakeryProducts.dto;

import lombok.Data;

@Data
public class CancellationSummery {
    Integer totalCancellations;
    String month;
    String year;
}
