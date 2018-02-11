package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive implements Pronstants {
	TalonSRX TalRM, TalRF, TalLM, TalLF;

	// Constructor
	public Drive(TalonSRX TalRM, TalonSRX TalRF, TalonSRX TalLM, TalonSRX TalLF) {
		this.TalRM = TalRM;
		this.TalRF = TalRF;
		this.TalLM = TalLM;
		this.TalLF = TalLF;
		
		setLeftPID(0.1,0,0, 0);
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
	
	public void setLeftPID(double p, double i, double d, double f) {
		TalLM.config_kF(0, f, 10);
		TalLM.config_kP(0, p, 10);
		TalLM.config_kI(0, i, 10);
		TalLM.config_kD(0, d, 10);
	}
	
	public void setRightPID(double p, double i, double d, double f) {
		TalRM.config_kF(0, f, 10);
		TalRM.config_kP(0, p, 10);
		TalRM.config_kI(0, i, 10);
		TalRM.config_kD(0, d, 10);
	}

	public void configOutputs() {
		TalRM.configNominalOutputForward(0, 0);
		TalRM.configNominalOutputReverse(0, 0);
		TalRM.configPeakOutputForward(1.0, 0);
		TalRM.configPeakOutputReverse(-1.0, 0);
		
		TalLM.configNominalOutputForward(0, 0);
		TalLM.configNominalOutputReverse(0, 0);
		TalLM.configPeakOutputForward(1.0, 0);
		TalLM.configPeakOutputReverse(-1.0, 0);
	}
}
