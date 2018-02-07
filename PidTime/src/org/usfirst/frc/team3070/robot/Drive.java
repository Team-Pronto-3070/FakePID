package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive implements Pronstants {
	TalonSRX TalRM, TalRF, TalLM, TalLF;

	// Constructor
	public Drive(TalonSRX TalRM, TalonSRX TalRF, TalonSRX TalLM, TalonSRX TalLF) {
		this.TalRM = TalRM;
		this.TalRF = TalRF;
		this.TalLM = TalLM;
		this.TalLF = TalLF;
	}
	
//	public void
	public int initEncL = 0;
	public int initEncR = 0; 
	// sets right
	public void setRight(double amountR) {
		TalRM.set(ControlMode.PercentOutput, -amountR);
		TalRF.set(ControlMode.Follower, Pronstants.PORT_RM);
	}

	// sets left
	void setLeft(double amountL) {
		TalLM.set(ControlMode.PercentOutput, amountL);
		TalLF.set(ControlMode.Follower, Pronstants.PORT_LM);
	}

	// stops motors
	void stop() { // Sets both sides to 0
		setRight(0);
		setLeft(0);
	}

	// gets the left encoder value
	public int getLeftEnc() {
		return TalLM.getSelectedSensorPosition(0)-initEncL;
	}

	// gets the right encoder value
	public int getRightEnc() {
		return TalRM.getSelectedSensorPosition(0)-initEncR;
	}
}
