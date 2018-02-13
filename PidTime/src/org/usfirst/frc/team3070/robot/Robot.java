/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot implements Pronstants{
	Modules modules;
	Drive drive;
	double setpoint, speed;
	int purpose = 0;
	double lp, li, ld, lf, rp, ri, rd, rf;
	//0 is stop, 1 is turn, 2 is drive
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		modules = new Modules();
		drive = new Drive(modules.TalRM, modules.TalRF, modules.TalLM, modules.TalLF);
		SmartDashboard.putNumber("Setpoint", 0);
		SmartDashboard.putNumber("SpeedL", 0);
		SmartDashboard.putNumber("SpeedR", 0);
		
		
		SmartDashboard.putNumber("LP", 0);
		SmartDashboard.putNumber("LI", 0);
		SmartDashboard.putNumber("LD", 0);
		SmartDashboard.putNumber("LF", 0);
		
		SmartDashboard.putNumber("RP", 0);
		SmartDashboard.putNumber("RI", 0);
		SmartDashboard.putNumber("RD", 0);
		SmartDashboard.putNumber("RF", 0);
		
		SmartDashboard.putNumber("Left output", 0);
		SmartDashboard.putNumber("Right output", 0);

		
		drive.configOutputs();
		modules.TalLM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		modules.TalRM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		//For test periodic:
		SmartDashboard.putNumber("percent", 0);
		
//		modules.TalRM.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
//		modules.TalLM.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
		
	}
	
	public void teleopInit() {
		
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {	
	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
	}
	
	
	
	public void testInit() {
		lp = SmartDashboard.getNumber("LP", 0);
		li = SmartDashboard.getNumber("LI", 0);
		ld = SmartDashboard.getNumber("LD", 0);
		lf = SmartDashboard.getNumber("LF", 0);
		drive.setLeftPID(lp, li, ld, lf);
		
		rp = SmartDashboard.getNumber("RP", 0);
		ri = SmartDashboard.getNumber("RI", 0);
		rd = SmartDashboard.getNumber("RD", 0);
		rf = SmartDashboard.getNumber("RF", 0);
		drive.setRightPID(rp, ri, rd, rf);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		setpoint = SmartDashboard.getNumber("Setpoint", 0);
		SmartDashboard.putNumber("gyro", modules.gyro.getHeading());
		SmartDashboard.putNumber("EncL", drive.getLeftEnc());
		SmartDashboard.putNumber("EncR", drive.getRightEnc());
//		SmartDashboard.putNumber("SpeedL", drive.TalLM.getSelectedSensorVelocity(0)/Vel_100ms);
//		SmartDashboard.putNumber("SpeedR", drive.TalRM.getSelectedSensorVelocity(0)/Vel_100ms);
		SmartDashboard.putNumber("SpeedL", drive.TalLM.getSelectedSensorVelocity(0) * 10.0 / 4096.0 * 60 );
		SmartDashboard.putNumber("SpeedL_last", drive.TalLM.getSelectedSensorVelocity(0) * 10.0 / 4096.0 * 60 );

		SmartDashboard.putNumber("SpeedR", drive.TalRM.getSelectedSensorVelocity(0) * 10.0 / 4096.0 * 60);
		SmartDashboard.putNumber("SpeedR_last", drive.TalRM.getSelectedSensorVelocity(0) * 10.0 / 4096.0 * 60);
		SmartDashboard.putNumber("Left output", drive.TalRM.getMotorOutputPercent());
		SmartDashboard.putNumber("Right output", drive.TalLM.getMotorOutputPercent());


		
		
		//SmartDashboard.putStringArray("hello", new String[] {"array", "hello again", "some other thing"});
		
		if(modules.xbox.getRawButton(1)) {
			purpose = 1;
		}
		if(modules.xbox.getRawButton(2)) {
			purpose = 2;
		}
		if(modules.xbox.getRawButton(3)) {
			purpose = 0;
		}
		if(modules.xbox.getRawButton(4)) {
			modules.TalLM.setSelectedSensorPosition(0, 0, 0); 
			modules.TalRM.setSelectedSensorPosition(0, 0, 0);
		}
		if(modules.xbox.getRawButton(6)) {
			purpose = 3;
		}
		
		SmartDashboard.putNumber("Mode: ", purpose);
		
		switch(purpose) {
		case 0: 
			drive.stop();
			break;
		case 1: 
			// 	//double Vel_100ms = 500.0 * 4096 / 600;
			modules.TalRM.set(ControlMode.Velocity, setpoint * 4096 / 600);
			modules.TalLM.set(ControlMode.Velocity, -setpoint * 4096 / 600);
			modules.TalLF.set(ControlMode.Follower, PORT_LM);
			modules.TalRF.set(ControlMode.Follower, PORT_RM);
			break;
		case 2:
			break;
		case 3: 
			drive.setLeft(0.5);
			drive.setRight(0.5);
		}
		
	}
}
