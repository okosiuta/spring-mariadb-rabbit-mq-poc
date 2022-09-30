package com.okosiuta.rabitmqpoc.service.impl;

import com.okosiuta.rabitmqpoc.data.ResourceUsageEvent;
import com.okosiuta.rabitmqpoc.data.model.Certificate;
import com.okosiuta.rabitmqpoc.queue.sender.EventSender;
import com.okosiuta.rabitmqpoc.repository.CertificateRepository;
import com.okosiuta.rabitmqpoc.service.CertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.okosiuta.rabitmqpoc.constant.GenericConstants.RESOURCE_USAGE_QUEUE_NAME;
import static com.okosiuta.rabitmqpoc.data.Action.CREATE;
import static com.okosiuta.rabitmqpoc.data.Action.DELETE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final EventSender eventSender;

    @Override
    @Transactional
    public Certificate create() {
        var certificate = new Certificate();
        var newCertificate = certificateRepository.save(certificate);
        var event = new ResourceUsageEvent<>(CREATE, newCertificate, certificate.getResourceType());

        eventSender.sendEvent(RESOURCE_USAGE_QUEUE_NAME, event);

        return newCertificate;
    }

    @Override
    @Transactional
    public void delete(long id) {
        certificateRepository.findById(id)
            .ifPresent(c -> {
                certificateRepository.deleteById(c.getId());

                var event = new ResourceUsageEvent<>(DELETE, c, c.getResourceType());

                eventSender.sendEvent(RESOURCE_USAGE_QUEUE_NAME, event);
            });
    }
}
