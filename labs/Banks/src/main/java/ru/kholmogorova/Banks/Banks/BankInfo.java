package ru.kholmogorova.Banks.Banks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BankInfo {
    float commission;
    BigDecimal interest;
}
