package com.onepointpropertybackend.projects.repository;

import java.util.List;

import com.onepointpropertybackend.projects.model.Inventory;
import com.onepointpropertybackend.projects.model.ProjectDisplay;
import com.onepointpropertybackend.projects.model.ProjectInventoryDisplay;
import com.onepointpropertybackend.projects.repository.mapper.ProjectInventoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.onepointpropertybackend.projects.model.Project;
import com.onepointpropertybackend.projects.repository.mapper.ProjectRowMapper;

@Repository(value = "projectRepo")
public class ProjectRepositoryImpl implements ProjectRepository {

    static String fields = "pd.id, pd.name , pd.address, pd.startdate, pt.typecode, pd.description, pd.status";
    static String tableName = "projectdetail pd,projecttype pt";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ProjectDisplay> findAllProject() throws Exception {
        String sql = "select " + fields + " from " + tableName+" where  pd.typeid=pt.id";
        return jdbcTemplate.query(sql, new ProjectRowMapper());
    }

    @Override
    public List<ProjectDisplay> findProjectById(int id) throws Exception {
        String sql = "select " + fields + " from " + tableName + " where pd.id=" + id + " and pd.typeid=pt.id";
        return jdbcTemplate.query(sql, new ProjectRowMapper());
    }

    @Override
    public List<ProjectInventoryDisplay> findProjectInventoryById(int id) throws Exception {
        String sql = "select pi.id,pi.totalunits,pt.typecode,pi.carpetarea,pi.shortdescription,pi.longdescription" +
                " from projectinventory pi,propertytype pt " +
                "where pi.type=pt.id and pi.projectid=" + id;
        return jdbcTemplate.query(sql, new ProjectInventoryRowMapper());
    }

    @Override
    public int addProject(Project project) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        project.setStatus(true);
        String sql="INSERT INTO " +
                "`projectdetail` ( `name`, `address` , `startdate`, `typeid` , `description` ,`status`) " +
                "VALUES (:name,:address,:startDate,:typeId,:description,:status)";
        jdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(project), holder);
        return holder.getKey().intValue();
    }

    @Override
    public int addInventory(Inventory inventory) throws Exception {
        KeyHolder holder =new GeneratedKeyHolder();
        String sql="INSERT INTO " +
                "`projectinventory`(`projectid`, `totalunits`, `type`, `carpetarea`, `shortdescription`, `longdescription`)" +
                " VALUES (:projectId,:totalUnits,:typeId,:carpetArea,:shortDescription,:longDescription)";
        jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(inventory),holder);
        return holder.getKey().intValue();
    }

    @Override
    public boolean updateInventory(int id,Inventory inventory) throws Exception{
        String sql="UPDATE `projectinventory` " +
                "`totalunits`=:totalUnits,`type`=:typeId,`carpetarea`=:carpetArea,`shortdescription`=:shortDescription,`longdescription`=:longDescription" +
                " WHERE id="+id;
        return jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(inventory)) > 0;
    }

    @Override
    public boolean updateProject(int id, Project project) throws Exception {
        String sql = "UPDATE `projectdetail` set "
                + "`name` = :name, `address`= :address, `typeid`=:typeId, `description`=:description where `id`=:projectId";
        project.setProjectId(id);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(project)) > 0;
    }

    @Override
    public boolean updateActiveStatus(int id, boolean isActive) throws Exception {
        String sql = "UPDATE `projectdetail` set `status`=:status where `id`=:projectId";
        Project project = new Project();
        project.setProjectId(id);
        project.setStatus(isActive);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(project)) > 0;
    }

    @Override
    public boolean deleteInventoryById(int id) throws Exception {
        String sql="DELETE FROM `projectinventory` WHERE id=:id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(sql,namedParameters) > 0;
    }

}
