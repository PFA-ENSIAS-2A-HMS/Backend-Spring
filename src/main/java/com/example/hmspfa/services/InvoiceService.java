package com.example.hmspfa.services;

import com.example.hmspfa.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    void deleteInvoice(Long id);
    Invoice updateInvoice(Invoice invoice);

    List<Invoice> getAllInvoices();
}
