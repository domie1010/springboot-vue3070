package com.dao;

import com.entity.DaijiaTousuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaijiaTousuView;

/**
 * 代驾投诉 Dao 接口
 *
 * @author 
 */
public interface DaijiaTousuDao extends BaseMapper<DaijiaTousuEntity> {

   List<DaijiaTousuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
