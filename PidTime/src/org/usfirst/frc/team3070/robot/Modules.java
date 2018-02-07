package org.usfirst.frc.team3070.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class Modules implements Pronstants {
	// Initializing modules
	Encoder encL, encR; // Encoders
	TalonSRX TalRM, TalRF, TalLM, TalLF; // Talons
	BNO055 gyro;
	Joystick xbox;
	
	
	public Modules() {

		// Setting motors
		TalRM = new TalonSRX(PORT_RM); // Right master Talon
		TalRF = new TalonSRX(PORT_RF); // Right follower Talon
		TalLM = new TalonSRX(PORT_LM); // Left master Talon
		TalLF = new TalonSRX(PORT_LF); // Left follower Talon
		//setting joystick
		xbox = new Joystick(JOY_PORT);
		// setting encoders
		TalRM.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		TalLM.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		// setting gyro
		gyro = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);
	
		TalRM.setInverted(false);
		TalRF.setInverted(false);
		TalLM.setInverted(true);	
		TalLF.setInverted(true);
		}
}
