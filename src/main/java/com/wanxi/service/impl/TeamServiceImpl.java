package com.wanxi.service.impl;

import com.wanxi.entity.TeamModel;
import com.wanxi.mapper.TeamDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.TeamService;
import com.wanxi.tool.Date;
import org.springframework.stereotype.Service;

import java.util.List;


@Service ("TeamService")
public class TeamServiceImpl implements TeamService {
    private final TeamDao dao;

    public TeamServiceImpl(TeamDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultModel findAll(TeamModel teamModel) {
        List<TeamModel> teamModels = dao.findAll(teamModel);
        return ResultModel.getModel(teamModels);
    }

    @Override
    public ResultModel del(TeamModel model) {
        int count = dao.del(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel add(TeamModel model) {
        int count = dao.add(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel findById(TeamModel teamModel) {
        TeamModel model = dao.findById(teamModel);
        return ResultModel.getModel(model);
    }

    @Override
    public ResultModel update(TeamModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel getCount(TeamModel model) {
        int count = dao.getCount(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel enable(TeamModel teamModel) {
        int i = dao.enable (teamModel);
        return ResultModel.getModel (i);
    }

    @Override
    public ResultModel addText(TeamModel teamModel) {
        return ResultModel.getModel (dao.addText (teamModel));
    }

    @Override
    public ResultModel findTeamId(TeamModel teamModel) {
        List<TeamModel> teamModels = dao.findTeamId(teamModel);
        return ResultModel.getModel(teamModels);
    }
}
