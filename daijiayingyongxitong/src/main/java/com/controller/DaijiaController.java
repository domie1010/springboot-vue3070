
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
 * 代驾司机
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daijia")
public class DaijiaController {
    private static final Logger logger = LoggerFactory.getLogger(DaijiaController.class);

    @Autowired
    private DaijiaService daijiaService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = daijiaService.queryPage(params);

        //字典表数据转换
        List<DaijiaView> list =(List<DaijiaView>)page.getList();
        for(DaijiaView c:list){
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
        DaijiaEntity daijia = daijiaService.selectById(id);
        if(daijia !=null){
            //entity转view
            DaijiaView view = new DaijiaView();
            BeanUtils.copyProperties( daijia , view );//把实体数据重构到view中

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
    public R save(@RequestBody DaijiaEntity daijia, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daijia:{}",this.getClass().getName(),daijia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<DaijiaEntity> queryWrapper = new EntityWrapper<DaijiaEntity>()
            .eq("username", daijia.getUsername())
            .or()
            .eq("daijia_phone", daijia.getDaijiaPhone())
            .or()
            .eq("daijia_id_number", daijia.getDaijiaIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaijiaEntity daijiaEntity = daijiaService.selectOne(queryWrapper);
        if(daijiaEntity==null){
            daijia.setDaijiaClicknum(1);
            daijia.setCreateTime(new Date());
            daijia.setPassword("123456");
            daijiaService.insert(daijia);
            return R.ok();
        }else {
            return R.error(511,"账户或者代驾司机手机号或者代驾司机身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaijiaEntity daijia, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,daijia:{}",this.getClass().getName(),daijia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<DaijiaEntity> queryWrapper = new EntityWrapper<DaijiaEntity>()
            .notIn("id",daijia.getId())
            .andNew()
            .eq("username", daijia.getUsername())
            .or()
            .eq("daijia_phone", daijia.getDaijiaPhone())
            .or()
            .eq("daijia_id_number", daijia.getDaijiaIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaijiaEntity daijiaEntity = daijiaService.selectOne(queryWrapper);
        if("".equals(daijia.getDaijiaPhoto()) || "null".equals(daijia.getDaijiaPhoto())){
                daijia.setDaijiaPhoto(null);
        }
        if(daijiaEntity==null){
            daijiaService.updateById(daijia);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者代驾司机手机号或者代驾司机身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        daijiaService.deleteBatchIds(Arrays.asList(ids));
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
            List<DaijiaEntity> daijiaList = new ArrayList<>();//上传的东西
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
                            DaijiaEntity daijiaEntity = new DaijiaEntity();
//                            daijiaEntity.setUsername(data.get(0));                    //账户 要改的
//                            //daijiaEntity.setPassword("123456");//密码
//                            daijiaEntity.setDaijiaUuidNumber(data.get(0));                    //工号 要改的
//                            daijiaEntity.setDaijiaName(data.get(0));                    //代驾司机姓名 要改的
//                            daijiaEntity.setDaijiaPhone(data.get(0));                    //代驾司机手机号 要改的
//                            daijiaEntity.setDaijiaIdNumber(data.get(0));                    //代驾司机身份证号 要改的
//                            daijiaEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            daijiaEntity.setDaijiaEmail(data.get(0));                    //电子邮箱 要改的
//                            daijiaEntity.setDaijiaPhoto("");//详情和图片
//                            daijiaEntity.setDaijiaJialing(data.get(0));                    //司机驾龄 要改的
//                            daijiaEntity.setDaijiaOldMoney(data.get(0));                    //司机原价/每公里 要改的
//                            daijiaEntity.setDaijiaNewMoney(data.get(0));                    //现价/每公里 要改的
//                            daijiaEntity.setDaijiaClicknum(Integer.valueOf(data.get(0)));   //司机热度 要改的
//                            daijiaEntity.setDaijiaContent("");//详情和图片
//                            daijiaEntity.setCreateTime(date);//时间
                            daijiaList.add(daijiaEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //工号
                                if(seachFields.containsKey("daijiaUuidNumber")){
                                    List<String> daijiaUuidNumber = seachFields.get("daijiaUuidNumber");
                                    daijiaUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daijiaUuidNumber = new ArrayList<>();
                                    daijiaUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("daijiaUuidNumber",daijiaUuidNumber);
                                }
                                //代驾司机手机号
                                if(seachFields.containsKey("daijiaPhone")){
                                    List<String> daijiaPhone = seachFields.get("daijiaPhone");
                                    daijiaPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> daijiaPhone = new ArrayList<>();
                                    daijiaPhone.add(data.get(0));//要改的
                                    seachFields.put("daijiaPhone",daijiaPhone);
                                }
                                //代驾司机身份证号
                                if(seachFields.containsKey("daijiaIdNumber")){
                                    List<String> daijiaIdNumber = seachFields.get("daijiaIdNumber");
                                    daijiaIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daijiaIdNumber = new ArrayList<>();
                                    daijiaIdNumber.add(data.get(0));//要改的
                                    seachFields.put("daijiaIdNumber",daijiaIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<DaijiaEntity> daijiaEntities_username = daijiaService.selectList(new EntityWrapper<DaijiaEntity>().in("username", seachFields.get("username")));
                        if(daijiaEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaijiaEntity s:daijiaEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //工号
                        List<DaijiaEntity> daijiaEntities_daijiaUuidNumber = daijiaService.selectList(new EntityWrapper<DaijiaEntity>().in("daijia_uuid_number", seachFields.get("daijiaUuidNumber")));
                        if(daijiaEntities_daijiaUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaijiaEntity s:daijiaEntities_daijiaUuidNumber){
                                repeatFields.add(s.getDaijiaUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //代驾司机手机号
                        List<DaijiaEntity> daijiaEntities_daijiaPhone = daijiaService.selectList(new EntityWrapper<DaijiaEntity>().in("daijia_phone", seachFields.get("daijiaPhone")));
                        if(daijiaEntities_daijiaPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaijiaEntity s:daijiaEntities_daijiaPhone){
                                repeatFields.add(s.getDaijiaPhone());
                            }
                            return R.error(511,"数据库的该表中的 [代驾司机手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //代驾司机身份证号
                        List<DaijiaEntity> daijiaEntities_daijiaIdNumber = daijiaService.selectList(new EntityWrapper<DaijiaEntity>().in("daijia_id_number", seachFields.get("daijiaIdNumber")));
                        if(daijiaEntities_daijiaIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaijiaEntity s:daijiaEntities_daijiaIdNumber){
                                repeatFields.add(s.getDaijiaIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [代驾司机身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        daijiaService.insertBatch(daijiaList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        DaijiaEntity daijia = daijiaService.selectOne(new EntityWrapper<DaijiaEntity>().eq("username", username));
        if(daijia==null || !daijia.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(daijia.getId(),username, "daijia", "代驾司机");
        R r = R.ok();
        r.put("token", token);
        r.put("role","代驾司机");
        r.put("username",daijia.getDaijiaName());
        r.put("tableName","daijia");
        r.put("userId",daijia.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody DaijiaEntity daijia){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<DaijiaEntity> queryWrapper = new EntityWrapper<DaijiaEntity>()
            .eq("username", daijia.getUsername())
            .or()
            .eq("daijia_phone", daijia.getDaijiaPhone())
            .or()
            .eq("daijia_id_number", daijia.getDaijiaIdNumber())
            ;
        DaijiaEntity daijiaEntity = daijiaService.selectOne(queryWrapper);
        if(daijiaEntity != null)
            return R.error("账户或者代驾司机手机号或者代驾司机身份证号已经被使用");
        daijia.setDaijiaOldMoney(0.0);
        daijia.setDaijiaNewMoney(0.0);
        daijia.setCreateTime(new Date());
        daijiaService.insert(daijia);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        DaijiaEntity daijia = new DaijiaEntity();
        daijia.setPassword("123456");
        daijia.setId(id);
        daijiaService.updateById(daijia);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        DaijiaEntity daijia = daijiaService.selectOne(new EntityWrapper<DaijiaEntity>().eq("username", username));
        if(daijia!=null){
            daijia.setPassword("123456");
            boolean b = daijiaService.updateById(daijia);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrDaijia(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        DaijiaEntity daijia = daijiaService.selectById(id);
        if(daijia !=null){
            //entity转view
            DaijiaView view = new DaijiaView();
            BeanUtils.copyProperties( daijia , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = daijiaService.queryPage(params);

        //字典表数据转换
        List<DaijiaView> list =(List<DaijiaView>)page.getList();
        for(DaijiaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaijiaEntity daijia = daijiaService.selectById(id);
            if(daijia !=null){

                //点击数量加1
                daijia.setDaijiaClicknum(daijia.getDaijiaClicknum()+1);
                daijiaService.updateById(daijia);

                //entity转view
                DaijiaView view = new DaijiaView();
                BeanUtils.copyProperties( daijia , view );//把实体数据重构到view中

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
    public R add(@RequestBody DaijiaEntity daijia, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daijia:{}",this.getClass().getName(),daijia.toString());
        Wrapper<DaijiaEntity> queryWrapper = new EntityWrapper<DaijiaEntity>()
            .eq("username", daijia.getUsername())
            .or()
            .eq("daijia_phone", daijia.getDaijiaPhone())
            .or()
            .eq("daijia_id_number", daijia.getDaijiaIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaijiaEntity daijiaEntity = daijiaService.selectOne(queryWrapper);
        if(daijiaEntity==null){
            daijia.setCreateTime(new Date());
        daijia.setPassword("123456");
        daijiaService.insert(daijia);
            return R.ok();
        }else {
            return R.error(511,"账户或者代驾司机手机号或者代驾司机身份证号已经被使用");
        }
    }


}
