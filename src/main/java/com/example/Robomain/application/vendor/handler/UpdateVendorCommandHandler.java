package com.example.Robomain.application.vendor.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.example.Robomain.application.vendor.command.UpdateVendorCommand;
import com.example.Robomain.application.vendor.mapper.VendorDtoMapper;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.vendor.repository.IVendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateVendorCommandHandler {

    private final IVendorRepository vendorRepository;
    private final VendorDtoMapper dtoMapper;

    @Transactional
    public UUID handle(UpdateVendorCommand cmd) {
        var vendor = vendorRepository.findById(cmd.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", cmd.getVendorId()));
        vendor.update(cmd.getVendorName(), cmd.getDescription(), cmd.getContactPerson(), cmd.getEmail(),
                cmd.getPhoneNumber(), cmd.getAddress(), cmd.getCity(), cmd.getCountry(),
                cmd.getPaymentTerms(), cmd.getTaxId());
        if (cmd.getStatus() != null) vendor.changeStatus(cmd.getStatus());
        return vendorRepository.save(vendor).getId();
    }
}
