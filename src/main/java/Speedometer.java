class Speedometer {
    private double speed;

    public Speedometer() {
        this.speed = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public void increaseSpeed(double increment) {
        speed += increment;
    }

    public void decreaseSpeed(double decrement) {
        speed = Math.max(0, speed - decrement);
    }
}
