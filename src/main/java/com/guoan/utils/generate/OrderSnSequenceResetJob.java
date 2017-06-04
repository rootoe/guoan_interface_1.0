package com.guoan.utils.generate;

public class OrderSnSequenceResetJob {

	public void execute() {
		System.out.println("---------------------我是定时器, 我已经归位了......---------------------------");
		GenerateOrderSnUtils.reset();
	}
}
