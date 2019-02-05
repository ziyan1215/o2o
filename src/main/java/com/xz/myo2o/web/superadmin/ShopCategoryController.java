package com.xz.myo2o.web.superadmin;

import com.xz.myo2o.entity.ConstantForSuperAdmin;
import com.xz.myo2o.entity.ShopCategory;
import com.xz.myo2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class ShopCategoryController {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/listshopcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> listShopCategorys() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> list = new ArrayList<ShopCategory>();
        try {
            list = shopCategoryService.getShopCategoryList(null);
            modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
            modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }
}
