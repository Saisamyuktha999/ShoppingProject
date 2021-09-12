package com.shopping.service;

import com.shopping.bean.InventoryItem;
import com.shopping.bean.InventoryItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<InventoryItem> getAllInventoryItems() {
        return Objects.requireNonNull(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8082/inventories/", InventoryItemList.class)).getInventoryItems());
    }

    public InventoryItem getInventoryItemByCode(String code) {
        return restTemplate.getForObject("http://localhost:8082/inventories/code/" + code, InventoryItem.class);
    }

    public InventoryItem getInventoryItemById(int id) {
        return restTemplate.getForObject("http://localhost:8082/inventories/" + id, InventoryItem.class);
    }

    public void updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity) {
        InventoryItem inventoryItem = new InventoryItem(productCode, availableQuantity);
        restTemplate.put("http://localhost:8082/inventories/", inventoryItem);
    }
}
