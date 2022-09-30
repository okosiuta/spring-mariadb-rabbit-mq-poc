package com.okosiuta.rabitmqpoc.service;

import com.okosiuta.rabitmqpoc.data.model.Certificate;

public interface CertificateService {

    Certificate create();

    void delete(long id);
}
