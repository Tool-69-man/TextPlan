package com.xroad.dao;

import com.xroad.entity.Thing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ThingDao {
//    @Select("select * from thing")
    List<Thing> getAllThing();

    Thing getThingById(int id);

    Thing getThingByName(String name);

    int add(Map<String,Object> map);

    int updateThing(Thing thing);

    int deleteThing(int tid);

    int deleteThingByName(String name);

}
