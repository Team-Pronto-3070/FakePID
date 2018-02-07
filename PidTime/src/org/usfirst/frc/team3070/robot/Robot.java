/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3070.robot;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
	//0 is stop, 1 is turn, 2 is drive
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		modules = new Modules();
		drive = new Drive(modules.TalRM, modules.TalRF, modules.TalLM, modules.TalLF);
		SmartDashboard.putNumber("Distance", 0);
		SmartDashboard.putNumber("Angle", 0);
		
		modules.TalRM.configNominalOutputForward(0, 0);
		modules.TalRM.configNominalOutputReverse(0, 0);
		modules.TalRM.configPeakOutputForward(1, 0);
		modules.TalRM.configPeakOutputReverse(-1, 0);
		
		modules.TalRF.configNominalOutputForward(0, 0);
		modules.TalRF.configNominalOutputReverse(0, 0);
		modules.TalRF.configPeakOutputForward(1, 0);
		modules.TalRF.configPeakOutputReverse(-1, 0);

		modules.TalLF.configNominalOutputForward(0, 0);
		modules.TalLF.configNominalOutputReverse(0, 0);
		modules.TalLF.configPeakOutputForward(1, 0);
		modules.TalLF.configPeakOutputReverse(-1, 0);
		
		modules.TalLM.configNominalOutputForward(0, 0);
		modules.TalLM.configNominalOutputReverse(0, 0);
		modules.TalLM.configPeakOutputForward(1, 0);
		modules.TalLM.configPeakOutputReverse(-1, 0);
		
	
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
		dist = SmartDashboard.getNumber("Distance", 0);
		angle = SmartDashboard.getNumber("Angle", 0);
		if(modules.xbox.getRawButton(1)) {
			purpose = 1;
		}
		if(modules.xbox.getRawButton(2)) {
			purpose = 2;
		}
		if(modules.xbox.getRawButton(3)) {
			purpose = 0;
		}
		switch(purpose) {
		case 0: 
			drive.stop();
		case 1: 
			modules.TalRM.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalLM.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalLF.set(ControlMode.Position, Pronstants.feetToEnc(dist));
			modules.TalRF.set(ControlMode.Position, Pronstants.feetToEnc(dist));
		case 2:
			if(angle > 0&&modules.gyro.getHeading() <= angle) {
				modules.TalRM.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
				modules.TalRF.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
				modules.TalLF.set(ControlMode.Velocity, Pronstants.Vel_100ms);
				modules.TalLM.set(ControlMode.Velocity, Pronstants.Vel_100ms);
			}
			else if(angle < 0&&modules.gyro.getHeading() >= angle){
				modules.TalRM.set(ControlMode.Velocity, Pronstants.Vel_100ms);
				modules.TalRF.set(ControlMode.Velocity, Pronstants.Vel_100ms);
				modules.TalLF.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
				modules.TalLM.set(ControlMode.Velocity, -Pronstants.Vel_100ms);
			}
			else {
				drive.stop();
			}
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
