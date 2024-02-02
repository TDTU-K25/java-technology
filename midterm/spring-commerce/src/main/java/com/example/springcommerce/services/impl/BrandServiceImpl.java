package com.example.springcommerce.services.impl;

import com.example.springcommerce.models.Brand;
import com.example.springcommerce.repository.BrandRepository;
import com.example.springcommerce.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
