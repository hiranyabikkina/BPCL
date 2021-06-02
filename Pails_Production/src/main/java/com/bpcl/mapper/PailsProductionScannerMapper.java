package com.bpcl.mapper;

import com.bpcl.dto.PailsProductionScanner;
import com.bpcl.util.IMapper;

public interface PailsProductionScannerMapper<T extends PailsProductionScanner> extends IMapper<T>  {

	int updateNull();

}
