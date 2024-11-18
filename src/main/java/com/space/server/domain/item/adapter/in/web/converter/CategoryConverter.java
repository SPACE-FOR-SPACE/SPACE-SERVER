package com.space.server.domain.item.adapter.in.web.converter;

import com.space.server.domain.item.domain.value.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
  public Category convert(String category) {
      return Category.valueOf(category.toUpperCase());
  }
}
