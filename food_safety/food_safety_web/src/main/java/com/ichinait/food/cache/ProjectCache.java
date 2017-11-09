package com.ichinait.food.cache;

import com.ichinait.food.db.entity.Project;
import com.ichinait.food.service.project.ProjectService;
import com.ichinait.food.util.SpringContextHolder;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 实验项目缓存类
 * Created by ichinait on 2016/3/15.
 */
public class ProjectCache {
    private  static ProjectCache instance;

    private List<Project> cachedProjects;

    /**
     * 更新缓存
     */
    public void update(){
        ProjectService projectService = (ProjectService) SpringContextHolder.getBean("projectService");
        cachedProjects = projectService.queryProjectNoPage();
    }

    public void add(Project p){
        this.cachedProjects.add(p);
    }

    public void remove(Project p){
        this.cachedProjects.remove(p);
    }

    public List<Project> getCachedProjects() {
        if(CollectionUtils.isEmpty(cachedProjects)){
           update();
        }
        return cachedProjects;
    }

    private ProjectCache(){

    }


    public static ProjectCache getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static ProjectCache instance = new ProjectCache();
    }
}
