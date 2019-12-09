package com.piesat.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */
@Data
@Accessors(chain = true)
@Builder
public class Emp{

    private static final long serialVersionUID = 1L;

    @TableId(value = "empno", type = IdType.AUTO)
    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private LocalDate hiredate;

    private BigDecimal sal;

    @TableField("COMM")
    private BigDecimal comm;

    private Integer deptno;


}
