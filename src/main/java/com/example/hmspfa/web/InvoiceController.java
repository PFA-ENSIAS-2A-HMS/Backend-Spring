package com.example.hmspfa.web;

import com.example.hmspfa.entities.Invoice;
import com.example.hmspfa.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invoices")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") Long id, @RequestBody Invoice invoice) {
        Invoice existingInvoice = invoiceService.getInvoiceById(id);
        if (existingInvoice != null) {
            invoice.setId(id);
            Invoice updatedInvoice = invoiceService.updateInvoice(invoice);
            return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
