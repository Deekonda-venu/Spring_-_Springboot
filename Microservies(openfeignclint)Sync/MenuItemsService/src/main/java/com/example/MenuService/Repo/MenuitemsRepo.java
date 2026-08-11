package com.example.MenuService.Repo;

import com.example.MenuService.Model.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MenuitemsRepo extends JpaRepository<MenuItems, Long>{


}
