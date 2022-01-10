package com.wanxi.mapper;


import com.wanxi.entity.NewModel;

import java.util.List;

public interface NewDao extends BaseDao<NewModel> {
     int addText(NewModel newModel);

    List<NewModel> findNewId(NewModel newModel);
}
