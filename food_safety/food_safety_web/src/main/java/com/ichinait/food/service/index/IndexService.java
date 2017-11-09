package com.ichinait.food.service.index;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.*;
import com.ichinait.food.db.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ichinait on 2016/4/27.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class IndexService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private SampleMapper sampleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AlgorithmMapper algorithmMapper;
    @Resource
    private InstrumentMapper instrumentMapper;
    @Resource
    private StandardsMapper standardsMapper;


    public int countProject(){
        ProjectExample example = new ProjectExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return projectMapper.countByExample(example);
    }

    public int countSample(){
        SampleExample example = new SampleExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return sampleMapper.countByExample(example);
    }

    public int countUser(){
        UserExample example = new UserExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return userMapper.countByExample(example);
    }
    public int countAlgorithm(){
        AlgorithmExample example = new AlgorithmExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return algorithmMapper.countByExample(example);
    }
    public int countInstrument(){
        InstrumentExample example = new InstrumentExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return instrumentMapper.countByExample(example);
    }

    public int countStandards(){
        StandardsExample example = new StandardsExample();
        example.createCriteria().andStatusNotEqualTo(Constant.DATA_INVALID_STATE);
        return standardsMapper.countByExample(example);
    }


}
