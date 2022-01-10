package com.wanxi.mapper;


import com.wanxi.entity.TeamModel;

import java.util.List;

public interface TeamDao extends BaseDao<TeamModel> {
     int addText(TeamModel teamModel);

    List<TeamModel> findTeamId(TeamModel team);
}
