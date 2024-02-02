package com.example.springcommerce.services.impl;

import com.example.springcommerce.models.Color;
import com.example.springcommerce.repository.ColorRepository;
import com.example.springcommerce.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
}
