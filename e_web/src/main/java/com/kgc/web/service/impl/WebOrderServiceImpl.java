package com.kgc.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kgc.web.model.WebOrderEntity;
import com.kgc.web.service.WebOrderService;

/**
 * 
 * @author djin
 *    WebOrder业务层实现类
 * @date 2020-03-09 10:05:59
 */
@Service
@Transactional
public class WebOrderServiceImpl extends BaseServiceImpl<WebOrderEntity> implements WebOrderService {
	
}
