package com.example.hmspfa.services;

import com.example.hmspfa.entities.Invoice;
import com.example.hmspfa.exceptions.InvoiceNotFoundException;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id) throws InvoiceNotFoundException;
    void deleteInvoice(Long id) throws  InvoiceNotFoundException;
    Invoice updateInvoice(Invoice invoice) throws  InvoiceNotFoundException;

    List<Invoice> getAllInvoices();
}
