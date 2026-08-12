package com.example.Resturant.Client;
package com.example.Resturant.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@FeignClient(name = "MenuitemsClient", url = "http://localhost:9292")
public interface MenuitemsClient {

    @GetMapping("/GetAllFooditems")
    public List<MenuitemsRespose> getAllFooditems();

    @GetMapping("/GetFooditemsById/{ID}")
    public MenuitemsFullRespose getMenuitemById(@PathVariable Long ID);

    @GetMapping("/GetMenuItemsByResturant/{resturantId}")
    public List<Menuitems> getMenuItemsByResturant(@PathVariable Long resturantId);

}
