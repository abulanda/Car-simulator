class GearBox {
    private int currentGear;

    public GearBox() {
        this.currentGear=0;
    }

    public void shiftUp() {
        if(currentGear<5) {
            currentGear++;
            System.out.println("Shifted up to gear: " + currentGear);
        } else {
            System.out.println("Already in top gear");
        }
    }

    public void shiftDown() {
        if(currentGear > 0) {
            currentGear--;
            System.out.println("Shifted down to gear: " + currentGear);
        } else {
            System.out.println("Already in neutral");
        }
    }

    public int getCurrentGear() {
        return currentGear;
    }
}
