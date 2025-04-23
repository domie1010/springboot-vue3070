
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 代驾司机订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daijiaOrder")
public class DaijiaOrderController {
    private static final Logger logger = LoggerFactory.getLogger(DaijiaOrderController.class);

    @Autowired
    private DaijiaOrderService daijiaOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private DaijiaService daijiaService;
@Autowired
private DaijiaCommentbackService daijiaCommentbackService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("代驾司机".equals(role))
            params.put("daijiaId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = daijiaOrderService.queryPage(params);

        //字典表数据转换
        List<DaijiaOrderView> list =(List<DaijiaOrderView>)page.getList();
        for(DaijiaOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaijiaOrderEntity daijiaOrder = daijiaOrderService.selectById(id);
        if(daijiaOrder !=null){
            //entity转view
            DaijiaOrderView view = new DaijiaOrderView();
            BeanUtils.copyProperties( daijiaOrder , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(daijiaOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                DaijiaEntity daijia = daijiaService.selectById(daijiaOrder.getDaijiaId());
                if(daijia != null){
                    BeanUtils.copyProperties( daijia , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDaijiaId(daijia.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DaijiaOrderEntity daijiaOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daijiaOrder:{}",this.getClass().getName(),daijiaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("代驾司机".equals(role))
            daijiaOrder.setDaijiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            daijiaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        daijiaOrder.setInsertTime(new Date());
        daijiaOrder.setCreateTime(new Date());
        daijiaOrderService.insert(daijiaOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaijiaOrderEntity daijiaOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,daijiaOrder:{}",this.getClass().getName(),daijiaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("代驾司机".equals(role))
//            daijiaOrder.setDaijiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("用户".equals(role))
//            daijiaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<DaijiaOrderEntity> queryWrapper = new EntityWrapper<DaijiaOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaijiaOrderEntity daijiaOrderEntity = daijiaOrderService.selectOne(queryWrapper);
        if(daijiaOrderEntity==null){
            daijiaOrderService.updateById(daijiaOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        daijiaOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<DaijiaOrderEntity> daijiaOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            DaijiaOrderEntity daijiaOrderEntity = new DaijiaOrderEntity();
//                            daijiaOrderEntity.setDaijiaOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            daijiaOrderEntity.setDaijiaId(Integer.valueOf(data.get(0)));   //代驾司机 要改的
//                            daijiaOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            daijiaOrderEntity.setBuyLicheng(data.get(0));                    //所需里程 要改的
//                            daijiaOrderEntity.setDaijiaOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            daijiaOrderEntity.setDaijiaOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            daijiaOrderEntity.setDaijiaOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            daijiaOrderEntity.setInsertTime(date);//时间
//                            daijiaOrderEntity.setCreateTime(date);//时间
                            daijiaOrderList.add(daijiaOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("daijiaOrderUuidNumber")){
                                    List<String> daijiaOrderUuidNumber = seachFields.get("daijiaOrderUuidNumber");
                                    daijiaOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daijiaOrderUuidNumber = new ArrayList<>();
                                    daijiaOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("daijiaOrderUuidNumber",daijiaOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<DaijiaOrderEntity> daijiaOrderEntities_daijiaOrderUuidNumber = daijiaOrderService.selectList(new EntityWrapper<DaijiaOrderEntity>().in("daijia_order_uuid_number", seachFields.get("daijiaOrderUuidNumber")));
                        if(daijiaOrderEntities_daijiaOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaijiaOrderEntity s:daijiaOrderEntities_daijiaOrderUuidNumber){
                                repeatFields.add(s.getDaijiaOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        daijiaOrderService.insertBatch(daijiaOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = daijiaOrderService.queryPage(params);

        //字典表数据转换
        List<DaijiaOrderView> list =(List<DaijiaOrderView>)page.getList();
        for(DaijiaOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaijiaOrderEntity daijiaOrder = daijiaOrderService.selectById(id);
            if(daijiaOrder !=null){


                //entity转view
                DaijiaOrderView view = new DaijiaOrderView();
                BeanUtils.copyProperties( daijiaOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(daijiaOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    DaijiaEntity daijia = daijiaService.selectById(daijiaOrder.getDaijiaId());
                if(daijia != null){
                    BeanUtils.copyProperties( daijia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDaijiaId(daijia.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody DaijiaOrderEntity daijiaOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daijiaOrder:{}",this.getClass().getName(),daijiaOrder.toString());
            DaijiaEntity daijiaEntity = daijiaService.selectById(daijiaOrder.getDaijiaId());
            if(daijiaEntity == null){
                return R.error(511,"查不到该代驾司机");
            }
            // Double daijiaNewMoney = daijiaEntity.getDaijiaNewMoney();

            if(false){
            }
            else if(daijiaEntity.getDaijiaNewMoney() == null){
                return R.error(511,"代驾司机价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - daijiaEntity.getDaijiaNewMoney()*daijiaOrder.getBuyLicheng();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            daijiaOrder.setDaijiaOrderTypes(1); //设置订单状态为已支付
            daijiaOrder.setDaijiaOrderTruePrice(daijiaEntity.getDaijiaNewMoney()*daijiaOrder.getBuyLicheng()); //设置实付价格
            daijiaOrder.setYonghuId(userId); //设置订单支付人id
            daijiaOrder.setDaijiaOrderUuidNumber(String.valueOf(new Date().getTime()));
            daijiaOrder.setDaijiaOrderPaymentTypes(1);
            daijiaOrder.setInsertTime(new Date());
            daijiaOrder.setCreateTime(new Date());
                daijiaOrderService.insert(daijiaOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            DaijiaOrderEntity daijiaOrder = daijiaOrderService.selectById(id);
        Double buyLicheng = daijiaOrder.getBuyLicheng();
        Integer daijiaOrderPaymentTypes = daijiaOrder.getDaijiaOrderPaymentTypes();
            Integer daijiaId = daijiaOrder.getDaijiaId();
            if(daijiaId == null)
                return R.error(511,"查不到该代驾司机");
            DaijiaEntity daijiaEntity = daijiaService.selectById(daijiaId);
            if(daijiaEntity == null)
                return R.error(511,"查不到该代驾司机");
            Double daijiaNewMoney = daijiaEntity.getDaijiaNewMoney();
            if(daijiaNewMoney == null)
                return R.error(511,"代驾司机价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(daijiaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = daijiaEntity.getDaijiaNewMoney() * buyLicheng  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }




            daijiaOrder.setDaijiaOrderTypes(2);//设置订单状态为退款
            daijiaOrderService.updateById(daijiaOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            daijiaService.updateById(daijiaEntity);//更新订单中代驾司机的信息
            return R.ok();
    }


    /**
     * 接单
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DaijiaOrderEntity  daijiaOrderEntity = new  DaijiaOrderEntity();;
        daijiaOrderEntity.setId(id);
        daijiaOrderEntity.setDaijiaOrderTypes(3);
        boolean b =  daijiaOrderService.updateById( daijiaOrderEntity);
        if(!b){
            return R.error("接单出错");
        }
        return R.ok();
    }














    /**
     * 使用
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DaijiaOrderEntity  daijiaOrderEntity = new  DaijiaOrderEntity();
        daijiaOrderEntity.setId(id);
        daijiaOrderEntity.setDaijiaOrderTypes(4);
        boolean b =  daijiaOrderService.updateById( daijiaOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer daijiaCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            DaijiaOrderEntity daijiaOrder = daijiaOrderService.selectById(id);
        if(daijiaOrder == null)
            return R.error(511,"查不到该订单");
        if(daijiaOrder.getDaijiaOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer daijiaId = daijiaOrder.getDaijiaId();
        if(daijiaId == null)
            return R.error(511,"查不到该代驾司机");

        DaijiaCommentbackEntity daijiaCommentbackEntity = new DaijiaCommentbackEntity();
            daijiaCommentbackEntity.setId(id);
            daijiaCommentbackEntity.setDaijiaId(daijiaId);
            daijiaCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            daijiaCommentbackEntity.setDaijiaCommentbackText(commentbackText);
            daijiaCommentbackEntity.setInsertTime(new Date());
            daijiaCommentbackEntity.setReplyText(null);
            daijiaCommentbackEntity.setUpdateTime(null);
            daijiaCommentbackEntity.setCreateTime(new Date());
            daijiaCommentbackService.insert(daijiaCommentbackEntity);

            daijiaOrder.setDaijiaOrderTypes(5);//设置订单状态为已评价
            daijiaOrderService.updateById(daijiaOrder);//根据id更新
            return R.ok();
    }












}
