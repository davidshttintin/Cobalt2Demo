package framework.Cobalt.Cobalt2.api.hardware;

/**
 * Created by Codetector on 12/12/15.
 * Imported from original Cobalt.
 * Documentation by David on 1/27/16.
 */
public interface IMotor {
    /**
     * Reset the encoder for motor.
     */
    void resetEncoder();

    /**
     * Get the current position, which is recorded by encoder.
     *
     * @return current position of motor
     */
    double getCurrentPosition();

    /**
     * Set the target position for motor
     *
     * @param targetPosition : the target position the motor would reach
     */
    void setTargetPosition(int targetPosition);

    /**
     * Set the power(speed the motor is running at).
     *
     * @param power the power of motor
     */
    void setPower(double power);

    /**
     * Set the direction of motor
     *
     * @param direction the direction of motor
     */
    void setDirection(MotorDirection direction);

    /**
     * Get the current direction of motor
     *
     * @return the current motor direction
     */
    MotorDirection getCurrentDirection();

}
