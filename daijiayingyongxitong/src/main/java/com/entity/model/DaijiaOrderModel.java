package com.entity.model;

import com.entity.DaijiaOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 代驾司机订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaijiaOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String daijiaOrderUuidNumber;


    /**
     * 代驾司机
     */
    private Integer daijiaId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 所需里程
     */
    private Double buyLicheng;


    /**
     * 实付价格
     */
    private Double daijiaOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer daijiaOrderTypes;


    /**
     * 支付类型
     */
    private Integer daijiaOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getDaijiaOrderUuidNumber() {
        return daijiaOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setDaijiaOrderUuidNumber(String daijiaOrderUuidNumber) {
        this.daijiaOrderUuidNumber = daijiaOrderUuidNumber;
    }
    /**
	 * 获取：代驾司机
	 */
    public Integer getDaijiaId() {
        return daijiaId;
    }


    /**
	 * 设置：代驾司机
	 */
    public void setDaijiaId(Integer daijiaId) {
        this.daijiaId = daijiaId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：所需里程
	 */
    public Double getBuyLicheng() {
        return buyLicheng;
    }


    /**
	 * 设置：所需里程
	 */
    public void setBuyLicheng(Double buyLicheng) {
        this.buyLicheng = buyLicheng;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getDaijiaOrderTruePrice() {
        return daijiaOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setDaijiaOrderTruePrice(Double daijiaOrderTruePrice) {
        this.daijiaOrderTruePrice = daijiaOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getDaijiaOrderTypes() {
        return daijiaOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setDaijiaOrderTypes(Integer daijiaOrderTypes) {
        this.daijiaOrderTypes = daijiaOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getDaijiaOrderPaymentTypes() {
        return daijiaOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setDaijiaOrderPaymentTypes(Integer daijiaOrderPaymentTypes) {
        this.daijiaOrderPaymentTypes = daijiaOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
