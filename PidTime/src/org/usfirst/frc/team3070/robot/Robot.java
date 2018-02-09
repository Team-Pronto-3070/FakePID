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
	double dist, angle;
	int purpose = 0;
	double p = 0;
	double i = 0;
	double d = 0;
	//0 is stop, 1 is turn, 2 is drive
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		modules = new Modules();
		drive = new Drive(modules.TalRM, modules.TalRF, modules.TalLM, modules.TalLF);
		SmartDashboard.putNumber("Dist", 0);
		SmartDashboard.putNumber("Angle", 0);
		
		drive.configOutputs();
		modules.TalLM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		modules.TalLF.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		modules.TalRF.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		modules.TalRM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		SmartDashboard.putNumber("P", 0);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0);
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
		dist = SmartDashboard.getNumber("Dist", 0);
		angle = SmartDashboard.getNumber("Angle", 0);
		SmartDashboard.putNumber("gyro", modules.gyro.getHeading());
		SmartDashboard.putNumber("EncL", drive.getLeftEnc());
		SmartDashboard.putNumber("EncR", drive.getRightEnc());
		p = SmartDashboard.getNumber("P", 0);
		i = SmartDashboard.getNumber("I", 0);
		d = SmartDashboard.getNumber("D", 0);
		
		SmartDashboard.putStringArray("hello", new String[] {"array", "hello again", "some other thing"});
		
		drive.setPID(p, i, d);
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
			drive.initEncL = modules.TalLM.getSelectedSensorPosition(0);
			drive.initEncR = modules.TalRM.getSelectedSensorPosition(0);
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
			modules.TalRM.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalLM.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalLF.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalRF.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			break;
		case 2:
			if(angle > 0&& modules.gyro.getHeading() <= angle) {
				modules.TalRM.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
				modules.TalRF.set(ControlMode.Follower, PORT_RM);
				modules.TalLM.set(ControlMode.Velocity, Pronstants.Vel_100ms);
				modules.TalLF.set(ControlMode.Follower, PORT_LM);
			}
			else if(angle < 0&& modules.gyro.getHeading() >= angle){
				modules.TalRM.set(ControlMode.Velocity, Pronstants.Vel_100ms);
				modules.TalRF.set(ControlMode.Follower, PORT_RM);
				modules.TalLM.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
				modules.TalLF.set(ControlMode.Follower, PORT_LM);
			}
			else {
				drive.stop();
			}
			break;
		case 3: 
			drive.setLeft(0.5);
			drive.setRight(0.5);
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		dist = SmartDashboard.getNumber("Dist", 0);
		angle = SmartDashboard.getNumber("Angle", 0);
		SmartDashboard.putNumber("gyro", modules.gyro.getHeading());
		SmartDashboard.putNumber("EncL", drive.getLeftEnc());
		SmartDashboard.putNumber("EncR", drive.getRightEnc());
		p = SmartDashboard.getNumber("P", 0);
		i = SmartDashboard.getNumber("I", 0);
		d = SmartDashboard.getNumber("D", 0);
		//String[] test = new String[] {"array", "hello again", "some other thing"};
		//SmartDashboard.putStringArray("hello", test);
	}
}
