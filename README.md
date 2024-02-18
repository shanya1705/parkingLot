
# Project Title

An automated Parking System.



## Run Locally

Clone the project
```bash
git clone https://github.com/shanya1705/parkingLot.git
```

Go to the project directory

```bash
cd parkingLot
```

Install dependencies

```bash
install jdk 8 on the machine
```

Start the server

```bash
run the main file named "ParkingLotApplication"
```


## Features

The application provides both Interactice CommandLine and API access to the below functionalities.


## These are the cammands to access the functionalities with through command prompt. 

- create a parking slot with a given capacity
  ```bash
  create_parking_lot capacity
  ```
- park a car in the nearest available slot
  ```bash
  park registrationNumber color
  ```
- leave-free up the parking with a given slotNumber
  ```bash
  leave slotNumber
  ```
- status- get the data in the format: Slot Registration No Color
  ```bash
  status
  ```
- to get the slot number of a car with the given car Registration Number.
  ```bash
  slot_number_of_car_with_registration registrationNumber
  ```

- to print the slot number of all cars with the given Color
  ```bash
  slot_numbers_of_all_cars_of_color color
   ```

- get the registration numbers of all the cars with the given color 
  ```bash
  registration_numbers_for_cars_with_colour color
   ```


## Below are the api signatures in to access the features through APIS:

- create a parking slot with a given capacity
  ```bash
  /parking/createSlots/{capacity}
  ```
- update the capacity of the parking 
  ```bash
  /parking/increaseParkingSlot/{value}
  ```
- park a car in the nearest available slot
  ```bash
  /parking/parkCar/{registrationNumber}/{colour}/{slotNumber}
  ```
- leave-free up the parking with a given slotNumber
  ```bash
  /parking/freeSlots/{slotNumber}
  ```
- to get the slot number of a car with the given car Registration Number.
  ```bash
  /parking/slotsNumber/{registrationNo}
  ```
- to print the slot number of all cars with the given Color
  ```bash
  /parking/slotNumbers/{color}
  ```
- get the registration numbers of all the cars with the given color 
  ```bash
  /parking/registrationNumber/{colour}
  ```





