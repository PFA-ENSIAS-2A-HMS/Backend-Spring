package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Invoice;
import com.example.hmspfa.services.InvoiceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return null;
    }
}
