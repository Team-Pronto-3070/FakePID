package org.usfirst.frc.team3070.robot;

public interface Pronstants {
	public final int PORT_RF = 2; // Right master CIM port
	public final int PORT_RM = 5; // Right follower CIM port

	public final int PORT_LF = 0; // Left master CIM port
	public final int PORT_LM = 1; // Left follower CIM port

	public final int PORT_ENC_R1 = 1; // Right encoder first port
	public final int PORT_ENC_R2 = 2; // Right encoder second port
	
	public final int JOY_PORT = 0;
	
	public final int ENCTICKS = 4096;
	public final double FWD_SPEED = .3;
	public final double BCKWRD_SPEED = -.3;
	
	public final int PORT_ENC_L1 = 7; // Left encoder first port
	public final int PORT_ENC_L2 = 8; // Left encoder second port
	
	public final double PI = 3.142; // Variable equal to pi
	public final double WHEEL_DIAMETER = 6; // Distance in inches of wheel diameter
	public final double WHEEL_CIRCUM = WHEEL_DIAMETER * PI; // Distance in inches of wheel circumference
	public final double FEET_TO_ENC = 12 * ENCTICKS / WHEEL_CIRCUM;
	public final double ENC_TO_FEET = 1/FEET_TO_ENC;
	public static double feetToEnc(double feet) {
			return feet*FEET_TO_ENC;
	}
	double Vel_100ms = .3 * 500.0 * 4096 / 600;
}
