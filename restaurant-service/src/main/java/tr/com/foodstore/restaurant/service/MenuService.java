package tr.com.foodstore.restaurant.service;

import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.dto.MenuDto;

public interface MenuService {
    void createMenu(Menu menu) throws BaseException;

    void updateMenu(Menu menu, String oldName) throws BaseException;

    void deleteMenu(Menu menu) throws BaseException;

    Menu getMenuById(Long id) throws BaseException;

    MenuDto transformDtoFromMenu(Menu menu);

    Menu transformMenuFromDto(MenuDto menuDto);
}
