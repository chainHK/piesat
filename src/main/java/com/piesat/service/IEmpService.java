package com.piesat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.entity.Emp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */
public interface IEmpService extends IService<Emp> {

	Emp getById(int empno);

}
