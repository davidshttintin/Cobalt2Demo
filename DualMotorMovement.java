package framework.Cobalt.Cobalt2.framework.autonomous.task.motor.dualMotorTasks;

import framework.Cobalt.Cobalt2.api.hardware.IMotor;

/**
 * Created by David on 2016/2/18.
 */
public class DualMotorMovement extends DualMotorTask  {

    int targetPosition;

    public DualMotorMovement(IMotor leftMotor, IMotor rightMotor, double power, int targetPosition) {
        super(leftMotor, rightMotor, power);
        this.targetPosition = targetPosition;
    }

    @Override
    public boolean execute() {
        if (leftMotor.getCurrentPosition() >= targetPosition || rightMotor.getCurrentPosition() >= targetPosition){
            return true;
        }else {
            leftMotor.setPower(power);
            rightMotor.setPower(power);
            return false;
        }
    }
}
