package com.example.Robomain.application.vendor.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Robomain.application.shared.response.PaginationResponse;
import com.example.Robomain.application.vendor.dto.VendorDto;
import com.example.Robomain.application.vendor.mapper.VendorDtoMapper;
import com.example.Robomain.application.vendor.query.ListVendorsQuery;
import com.example.Robomain.domain.vendor.repository.IVendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListVendorsQueryHandler {

    private final IVendorRepository vendorRepository;
    private final VendorDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public PaginationResponse<VendorDto> handle(ListVendorsQuery query) {
        var vendors = vendorRepository.search(
                query.getKeyword(), query.getStatus(), query.getEnterpriseId(),
                query.getPage(), query.getSize());
        var total = vendorRepository.count(query.getKeyword(), query.getEnterpriseId());
        var dtos = vendors.stream().map(dtoMapper::toDto).toList();
        return PaginationResponse.of(dtos, total, query.getPage(), query.getSize());
    }
}

