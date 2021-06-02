package com.bpcl.mapper;


  
  import com.bpcl.dto.LineInformation;
import com.bpcl.util.IMapper;
  
  public interface LineInformationMapper<T extends LineInformation> extends  IMapper<T> {

	LineInformation orderByCreateTime();
	  
  }
 