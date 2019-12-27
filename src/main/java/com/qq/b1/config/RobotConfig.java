package com.qq.b1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qq.b1.robot.LeftArm;
import com.qq.b1.robot.RightArm;

@Configuration
public class RobotConfig {

	//@Bean("left")
	public LeftArm getLeftArm() throws Exception{
		LeftArm leftArm = new LeftArm();
		
		return leftArm;
	}
	
	//@Bean
	public RightArm getRightArm() throws Exception{
		return new RightArm();
	}
	
}
