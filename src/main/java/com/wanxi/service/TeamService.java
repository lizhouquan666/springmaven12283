package com.wanxi.service;


import com.wanxi.entity.TeamModel;
import com.wanxi.result.ResultModel;

public interface TeamService extends BaseService<TeamModel>{


    ResultModel addText(TeamModel teamModel);

    ResultModel findTeamId(TeamModel team);
}
