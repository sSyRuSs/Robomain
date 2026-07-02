package com.example.Robomain.application.vendor.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.vendor.dto.VendorDto;
import com.example.Robomain.application.vendor.mapper.VendorDtoMapper;
import com.example.Robomain.application.vendor.query.GetVendorByIdQuery;
import com.example.Robomain.domain.shared.exception.ResourceNotFoundException;
import com.example.Robomain.domain.vendor.repository.IVendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetVendorByIdQueryHandler {

    private final IVendorRepository vendorRepository;
    private final VendorDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public VendorDto handle(GetVendorByIdQuery query) {
        return vendorRepository.findById(query.getVendorId())
                .map(dtoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", query.getVendorId()));
    }
}

