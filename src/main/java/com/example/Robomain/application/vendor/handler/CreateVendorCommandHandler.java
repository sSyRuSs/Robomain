package com.example.Robomain.application.vendor.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.vendor.command.CreateVendorCommand;
import com.example.Robomain.application.vendor.mapper.VendorDtoMapper;
import com.example.Robomain.domain.shared.exception.ConflictException;
import com.example.Robomain.domain.vendor.model.Vendor;
import com.example.Robomain.domain.vendor.repository.IVendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateVendorCommandHandler {

    private final IVendorRepository vendorRepository;
    private final VendorDtoMapper dtoMapper;

    @Transactional
    public UUID handle(CreateVendorCommand cmd) {
        if (vendorRepository.existsByVendorCode(cmd.getVendorCode())) {
            throw new ConflictException("Vendor code already exists: " + cmd.getVendorCode());
        }
        Vendor vendor = Vendor.create(cmd.getVendorCode(), cmd.getVendorName(), cmd.getEnterpriseId());
        vendor.update(cmd.getVendorName(), cmd.getDescription(), cmd.getContactPerson(), cmd.getEmail(),
                cmd.getPhoneNumber(), cmd.getAddress(), cmd.getCity(), cmd.getCountry(),
                cmd.getPaymentTerms(), cmd.getTaxId());
        return vendorRepository.save(vendor).getId();
    }
}
