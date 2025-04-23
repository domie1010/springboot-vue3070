package com.dao;

import com.entity.DaijiaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaijiaView;

/**
 * 代驾司机 Dao 接口
 *
 * @author 
 */
public interface DaijiaDao extends BaseMapper<DaijiaEntity> {

   List<DaijiaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
