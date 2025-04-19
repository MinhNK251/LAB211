package manager;

import entity.BoardingPass;
import entity.Reservation;
import entity_list.ReserveList;
import exception.InvalidInputException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import util.DataOperation;
import util.FileManager;
import validator.CheckAvailable;
import util.SeatsMatrixUtil;
import view.Menu;

public final class CheckInManager {
    private FlightManager flightM;
    private ReserveList reserveList;
    private Reservation currrentReservation = null;
    private BoardingPass boardingPass = null;

    public CheckInManager(FlightManager flightM) {
        this.flightM = flightM;
        this.reserveList = new ReserveList();
        checkIn();
        this.boardingPass = new BoardingPass(currrentReservation.getPassenger(), 
                flightM.getFlightsList().getByFlightNum(currrentReservation.getFlightNum()));
        boardingPass.setAllocatedSeat(randomizeSeat());
    }
    
    //<editor-fold desc="GETTERS" defaultstate="collapsed">
    public BoardingPass getBoardingPass () {
        return boardingPass;
    }
    //</editor-fold>
    
    public void checkInInterface () {
        do {
            boardingPass.printPass();
            Menu.printMenu(Menu.CHECKINOPTIONS);
            int choice = DataOperation.readInt("Enter your choice");
            switch (choice) {
                case 1 -> {
                    if (changeSeat()) Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else Menu.printStatement(Menu.FAILSTATE);
                } case 2 -> {
                    saveBoardingPass();
                    return;
                } case 3 -> {
                    return;
                } default -> {
                    Menu.printStatement(Menu.INVALIDCHOICE);
                    checkInInterface();
                }
            }
        } while (true);
    }
    
    public boolean checkIn () {
        String enteredID;
        try {
            enteredID = DataOperation.readString("Enter your reserved ID");
            this.currrentReservation = CheckAvailable.doesReservationExist(reserveList, enteredID);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            checkIn();
        }
        reserveList.remove(currrentReservation);
        FileManager.saveFile("reservation", reserveList);
        return true;
    }
    
    //<editor-fold desc="FUNCTION 1: CHANGE PASSENGER SEAT" defaultstate="collapsed">
    public boolean changeSeat () {
        boolean[][] seatsMatrix = boardingPass.getFlightInfo().getSeats();
        int max = boardingPass.getFlightInfo().getMaxSeats(), temp = max / 6;
        int rowIndex, colIndex;
        String seatString;
        
        SeatsMatrixUtil.printMatrix(seatsMatrix);
        while (true) {
            try {
                seatString = DataOperation.readString("Enter your new seat").toUpperCase().replaceAll("\\s+", "");
                rowIndex = Integer.parseInt(seatString.substring(0, seatString.length() - 1)) - 1;
                colIndex = (seatString.substring(seatString.length() - 1)).charAt(0) - 'A';
                if (seatsMatrix[rowIndex][colIndex]) throw new Exception("Seat is already taken, please try another one!");
                if (rowIndex + 1 > temp) throw new Exception("Row number exceed the limit, please try again!");
                if (colIndex + 1 > max / temp) throw new Exception("Column number exceed the limit, please try again!");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        removeCurrentSeat();
        saveNewSeat(rowIndex, colIndex, seatString);
        return true;
    }
    
    private void removeCurrentSeat () {
        int seatNum = SeatsMatrixUtil.seatStringToNum(boardingPass.getAllocatedSeat());
        int row = seatNum / 6, col = seatNum % 6;
        boardingPass.getFlightInfo().getSeats()[row][col] = false;
        boardingPass.getFlightInfo().getPassengerSeat()[row][col] = null;
    }
    
    private void saveNewSeat (int row, int col, String seatString) {
        boardingPass.getFlightInfo().getSeats()[row][col] = true;
        boardingPass.getFlightInfo().getPassengerSeat()[row][col] = boardingPass.getPassengerInfo();
        boardingPass.setAllocatedSeat(seatString);
    }
    //</editor-fold>
    
    //<editor-fold desc="FUNCTION 2: SAVE BOARING PASS" defaultstate="collapsed">
    public void saveBoardingPass () {
        FileManager.autoSaveFile("boardingpass", boardingPass.toString());
        FileManager.saveFile("flightseat", getFlightSeatsString());
    }
    
    private List<String> getFlightSeatsString () {
        List<String> result = new LinkedList<>();
        for (var flight: flightM.getFlightsList()) {
            result.add(flight.getAllocatedSeats());
        }
        return result;
    }
    //</editor-fold>
    
    private String randomizeSeat () {
        Random rand = new Random();
        int randomSeat;
        do {
            randomSeat = rand.nextInt(60);
        } while (boardingPass.getFlightInfo().getSeats()[randomSeat / 6][randomSeat % 6]);
        boardingPass.getFlightInfo().getSeats()[randomSeat / 6][randomSeat % 6] = true;
        return SeatsMatrixUtil.seatNumToString(randomSeat);
    }
}
