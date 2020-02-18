package com.gcamp.fcmserver.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gcamp.fcmserver.domain.PushVO;

@Repository("com.gcamp.fcmserver.mapper.PushMapper")
public interface PushMapper {
	
    public PushVO selectPushMember(PushVO pushVO) throws Exception;
    
    public List<PushVO> selectPushList() throws Exception;
}
