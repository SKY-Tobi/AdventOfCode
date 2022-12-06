package dayFive;

public class Ship {
    String[][] cargo;
    int initializeHighestPoint;
    int cargoColumns;

    public Ship() {
    }

    public String[][] getCargo() {
        return cargo;
    }

    public void setCargo(String[][] cargo) {
        this.cargo = cargo;
    }

    public int getInitializeHighestPoint() {
        return initializeHighestPoint;
    }

    public void setInitializeHighestPoint(int initializeHighestPoint) {
        this.initializeHighestPoint = initializeHighestPoint;
    }

    public int getCargoColumns() {
        return cargoColumns;
    }

    public void setCargoColumns(int cargoColumns) {
        this.cargoColumns = cargoColumns;
    }
}
