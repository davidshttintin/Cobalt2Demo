package framework.Cobalt.Cobalt2.framework.hardware.motor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import framework.Cobalt.Cobalt2.api.hardware.IMotor;
import framework.Cobalt.Cobalt2.api.hardware.MotorDirection;

/**
 * The prototype of CobaltMotor is FoundationMotor.
 * It is a wrapper of DcMotor and aims to solve unpredictable problems caused by Qualcomm's code.
 * Instead of resetting encoder, CobaltMotor uses encoderInitialValue to calculate the actual position.
 * There's no buffer time for robot to reset encoder. No gap time between two tasks.
 * Created by David on 2016/1/28.
 * Updated by Codetector on 2016/2/13
 */
public class CobaltMotor implements IMotor {

    public DcMotor dcMotor;
    double encoderInitialValue; //Using initial value instead of reset encoder value to 0

    /**
     * Constructor: Initialize the CobaltMotor
     *
     * @param dcMotor DcMotor
     */
    public CobaltMotor(DcMotor dcMotor) {
        this.dcMotor = dcMotor;
        dcMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        resetEncoder();
    }

    /**
     * Reset the encoder
     * NOTICE: This is not an actual reset to the Encoder Value.. It will only reset a reference value. However, this does affect the return value of get currentPosition from CobaltMotor
     */
    @Override
    public void resetEncoder() {
        encoderInitialValue = dcMotor.getCurrentPosition();
    }

    /**
     * Get the current position
     *
     * @return current position
     */
    @Override
    public double getCurrentPosition() {
        return dcMotor.getCurrentPosition() - encoderInitialValue;
    }

    /**
     * Set the target position for motor
     *
     * @param targetPosition : the target position the motor would reach
     */
    @Override
    public void setTargetPosition(int targetPosition) {
        dcMotor.setTargetPosition(targetPosition);
    }

    /**
     * Set the power of the motor
     *
     * @param power the power of motor
     */
    @Override
    public void setPower(double power) {
        dcMotor.setPower(power);
    }

    /**
     * Set the direction of motor
     *
     * @param direction the direction of motor
     */
    @Override
    public void setDirection(MotorDirection direction) {
        dcMotor.setDirection(direction == MotorDirection.FORWARD ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);
    }

    /**
     * Get the current position
     *
     * @return current position
     */
    @Override
    public MotorDirection getCurrentDirection() {
        return DcMotor.Direction.FORWARD == dcMotor.getDirection() ? MotorDirection.FORWARD : MotorDirection.BACKWARD;
    }
}
