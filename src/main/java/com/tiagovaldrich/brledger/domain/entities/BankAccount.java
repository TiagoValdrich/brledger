package com.tiagovaldrich.brledger.domain.entities;

import com.tiagovaldrich.brledger.application.dto.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private Long id;
    private String holderName;
    private String bank;
    private String branch;
    private String number;
    private String txId;

    public static BankAccount fromDTO(BankAccountDTO dto) {
        return BankAccount.builder()
                .id(dto.id())
                .holderName(dto.holderName())
                .bank(dto.bank())
                .branch(dto.branch())
                .number(dto.number())
                .txId(dto.txId())
                .build();
    }

    public BankAccountDTO toDTO() {
        return new BankAccountDTO(
                this.getId(),
                this.getHolderName(),
                this.getBank(),
                this.getBank(),
                this.getNumber(),
                this.getTxId()
        );
    }
}
