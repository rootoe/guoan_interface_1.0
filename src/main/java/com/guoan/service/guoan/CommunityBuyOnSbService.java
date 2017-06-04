package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;

public interface CommunityBuyOnSbService {

	Result search(String jsonString);

	Result list(String jsonString);

	Result index(String jsonString);

	Result info(String jsonString);

	Result buy(String jsonString);

	
}
