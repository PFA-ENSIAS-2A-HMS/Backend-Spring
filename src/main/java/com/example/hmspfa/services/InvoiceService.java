package com.example.hmspfa.services;

import com.example.hmspfa.entities.Invoice;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    void deleteInvoice(Long id);
    Invoice updateInvoice(Invoice invoice);
}
