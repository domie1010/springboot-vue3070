package com.dao;

import com.entity.DaijiaOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaijiaOrderView;

/**
 * 代驾司机订单 Dao 接口
 *
 * @author 
 */
public interface DaijiaOrderDao extends BaseMapper<DaijiaOrderEntity> {

   List<DaijiaOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
