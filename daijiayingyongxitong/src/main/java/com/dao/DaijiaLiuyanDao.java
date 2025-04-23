package com.dao;

import com.entity.DaijiaLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaijiaLiuyanView;

/**
 * 代驾司机留言 Dao 接口
 *
 * @author 
 */
public interface DaijiaLiuyanDao extends BaseMapper<DaijiaLiuyanEntity> {

   List<DaijiaLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
