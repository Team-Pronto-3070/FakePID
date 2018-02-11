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
	
	public final double PI = 3.1415926535897932; // Variable equal to pi
	public final double WHEEL_DIAMETER = 6; // Distance in inches of wheel diameter
	public final double WHEEL_CIRCUM = WHEEL_DIAMETER * PI; // Distance in inches of wheel circumference
	public final double FEET_TO_ENC = 12 * ENCTICKS / WHEEL_CIRCUM;
	public final double ENC_TO_FEET = 1/FEET_TO_ENC;
	public static double feetToEnc(double feet) {
			return feet*FEET_TO_ENC;
	}
	//double Vel_100ms = 500.0 * 4096 / 600;
	
//	All calculations are assuming the robot starts as far out l/r as possible.
	
//	robot is 32 1/4 inches by 23 1/2 inches.
//	16.125 inches by 11.25 inches is center mass
//	Bumpers will add 3.25 in to each side, so true dimensions are 19.375 by 14.5
//	18.86 inches is circumfrence wheel
	
//	168 - 19.375= 148.625 to the switch 
//	148.625/18.86 = 7.88 rots to the switch
//	7.88*4096 = 32276 enc ticks to the switch  
	
//	323.65- 19.375 = 304.275 to the scale
//	304.275/18 =  16.9 rotations to the scale
//	16.9*4096 = 69222 enc ticks to the scale
	
//	71.57 is the dis from the wall to the scale.  <-What are these? Spencer
//	85.25 is the dis from the wall to the switch.
	
//	Horizontal dist. switch to wall = 85.25 in
//	Angle bit length = 29.69 in
	
//	2nd distance to switch = 41.06 in
	
//	Horizontal dist. scale to wall = 71.57 in
//	2nd distance to scale = 27.38 in
}
