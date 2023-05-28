package com.tiagovaldrich.brledger.infrastructure.config;

import com.github.f4b6a3.tsid.TsidCreator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TsidIdentityGenerator implements IdentifierGenerator {
    @Override
    public Long generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return TsidCreator.getTsid().toLong();
    }
}
