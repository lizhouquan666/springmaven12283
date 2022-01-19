package com.wanxi.service;


import com.wanxi.entity.TeamModel;
import com.wanxi.result.ResultModel;
import com.wanxi.tool.CommonResult;

public interface TeamService extends BaseService<TeamModel>{


    CommonResult addText(TeamModel teamModel);

    CommonResult findTeamId(TeamModel team);
}
