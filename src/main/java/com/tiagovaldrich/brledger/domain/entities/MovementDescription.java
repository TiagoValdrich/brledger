package com.tiagovaldrich.brledger.domain.entities;

import com.tiagovaldrich.brledger.common.enums.EntryType;
import com.tiagovaldrich.brledger.common.enums.TransactionType;

public class MovementDescription {
    public static String buildDescription(Movement movement) {
        var prefix = getTransactionTypePrefix(movement.getTransactionType());
        var movementationType = getMovementationType(movement.getTransactionType(), movement.getType());
        var conterpartyName = movement.getCounterpartyBankAccount().getHolderName();

        return prefix + " " + movementationType + " " + conterpartyName;
    }

    private static String getMovementationType(TransactionType transactionType, EntryType entryType) {
        if (transactionType == TransactionType.PIX) {
            return entryType == EntryType.CREDIT ? "Recebido de" : "Enviado de";
        }

        return entryType == EntryType.CREDIT ? "Recebida de" : "Enviada de";
    }

    private static String getTransactionTypePrefix(TransactionType transactionType) {
        switch (transactionType) {
            case PIX: return "Pix";
            case TED: return "Ted";
            case TEV: return "Tev";
            default: return "Movimentação";
        }
    }
}
