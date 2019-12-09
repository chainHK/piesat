package com.piesat.service.impl;

import com.piesat.entity.Emp;
import com.piesat.mapper.EmpDao;
import com.piesat.service.IEmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements IEmpService {

}
