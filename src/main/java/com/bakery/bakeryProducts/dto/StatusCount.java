package com.bakery.bakeryProducts.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class StatusCount {
    Collection<String> statusName;
    Collection<Long> statusCount;

}
