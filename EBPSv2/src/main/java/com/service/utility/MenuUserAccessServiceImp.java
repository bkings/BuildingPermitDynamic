/*    map = new org.codehaus.jackson.map.ObjectMapper().readValue(jsonData, new org.codehaus.jackson.type.TypeReference<Map<String, String>>() {});
 */
package com.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoMenuUserAccess;
import com.model.utility.MenuUserAccess;

@Service
public class MenuUserAccessServiceImp implements MenuUserAccessService {

    @Autowired
    DaoMenuUserAccess da;

    @Override
    public Object getAll(String userType) {
        String sql = "SELECT id AS menu,menu \"menuName\",'"+userType+"' AS \"userType\",'N' AS status,menu_type as \"menuType\" FROM menu_master WHERE ID NOT IN(SELECT menu FROM menu_user_access WHERE user_type='"+userType+"') UNION SELECT M.id AS menu,M.menu \"menuName\",'"+userType+"' AS \"userType\",A.status AS status,M.menu_type as \"menuType\" FROM menu_master M,menu_user_access A WHERE M.id=A.menu AND user_type='"+userType+"' ORDER BY \"menuName\"";
        return da.getRecord(sql);
    }

    @Override
    public Object save(List<MenuUserAccess> list, String Authorization) {
        model.Message message = new model.Message();
        String msg = "";
        int row;
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        row = 0;
        try {
            MenuUserAccess obj;
            for (int i = 0; i < list.size(); i++) {
                obj = new MenuUserAccess(list.get(i).getMenu(), list.get(i).getUserType(), list.get(i).getStatus());
                row = da.save(obj);
            }
            msg = da.getMsg();
            if (row > 0) {
                return message.respondWithMessage("Success");
            } else if (msg.contains("Duplicate entry")) {
                msg = "This record already exist";
            }
            return message.respondWithError(msg);

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

}
