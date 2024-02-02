package com.example.springcommerce.repository;

import com.example.springcommerce.SpringCommerceApplication;
import com.example.springcommerce.models.Color;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = SpringCommerceApplication.class)
public class ColorRepositoryTest {
    @Autowired
    private ColorRepository colorRepository;

    @Test
    public void testFindById() {
        Color color = new Color(1, "Red");
        Color result = colorRepository.findById(color.getId()).get();
        assertEquals(color.getName(), result.getName());
    }

    @Test
    public void testSave() {
        Color color = new Color();
        color.setName("Black");
        colorRepository.save(color);
        Color found = colorRepository.findColorByName(color.getName());
        assertEquals(color.getId(), found.getId());
    }
}
