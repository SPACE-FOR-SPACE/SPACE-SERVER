package com.space.server.domin.item.presentation.converter;

import com.space.server.domin.item.domain.value.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
  public Category convert(String category) {
      return Category.valueOf(category.toUpperCase());
  }
}
