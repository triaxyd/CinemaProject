package com.triaxyd.users;

import com.triaxyd.cinema.Provoles;
import com.triaxyd.cinema.Reservations;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDate;



public class Customers extends Users {

    private String name;
    private int id;
    private List<Reservations> reservationsUser;

    public Customers() {
        reservationsUser = new ArrayList<>();
        this.setRole("Customer");
    }

    /*
    public Customers(String username, String password, String email , Date create_time, String role) {
        reservationsUser = new ArrayList<>();
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setCreationDate(create_time);
        this.setRole(role);
    }

     */

    public List<Reservations> getReservationsUser(){
        return reservationsUser;
    }

    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}


    public void login() {
        System.out.println("--- Welcome, " + getUsername().toUpperCase() + " ---");
        this.setLoggedIn(true);
    }

    public void logout() {
        System.out.println("Customer " + getUsername().toUpperCase() + " logged out.");
        this.setLoggedIn(false);
    }

    public void showUserMenu() {
        System.out.println("--\n1.Now Playing Films");
        System.out.println("2.Make a Reservation");
        System.out.println("3.View your Reservation");
        System.out.println("4.Logout\n--");
    }

    public void nowPlayingFilms(List<Provoles> provoles) {
        if (provoles.isEmpty()) {
            System.out.println("There are no Available Films");
        } else {
            for (Provoles provoli : provoles) {
                System.out.println("\t| Film ID: " + provoli.getProvoliId() + " | " + provoli.getProvoliFilm() + " - " + provoli.getProvoliCinema() + " - Reservations: " + provoli.getProvoliNumberOfReservations()  +" |");
            }
        }
    }

    public void makeReservation(List<Provoles> provoles, Users user) {
        if (provoles.isEmpty()) {
            System.out.println("No available Films for Reservation.");
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int filmIdInput, numOfSeatsInput;
            boolean provoliFound = false;
            Provoles provoliReservation = null;
            System.out.print("Enter the ID of the Provoli you want to Reserve: ");
            while (true) {
                try {
                    filmIdInput = Integer.parseInt(reader.readLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Error in film ID.");
                } catch (InputMismatchException e) {
                    System.out.print("Enter valid integer ID of the film you want to assign: ");
                } catch (NumberFormatException e) {
                    System.out.print("Enter integer ID of the film you want to assign: ");
                }
            }
            for (Provoles provoli : provoles) {
                if (filmIdInput == provoli.getProvoliId()) {
                    provoliReservation = provoli;
                    provoliFound = true;
                    break;
                }
            }
            if (provoliFound && provoliReservation.getProvoliIsAvailable()) {
                while (true) {
                    System.out.print("Enter the number of seats you want to Reserve: ");
                    try {
                        numOfSeatsInput = Integer.parseInt(reader.readLine());
                        break;
                    } catch (IOException e) {
                        System.out.println("Error in Seats Reservation.");
                    } catch (InputMismatchException e) {
                        System.out.print("Enter valid integer value for the seats: ");
                    } catch (NumberFormatException e) {
                        System.out.print("Enter integer value for the seats: ");
                    }
                }
                if (numOfSeatsInput > 0 && provoliReservation.getProvoliNumberOFSeatsLeft() >= numOfSeatsInput && provoliReservation.getProvoliIsAvailable()){
                    Reservations reservation = new Reservations(user, provoliReservation, numOfSeatsInput);
                    provoliReservation.addReservation(reservation);
                    reservationsUser.add(reservation);
                    System.out.println("Reservation for " + provoliReservation.getProvoliFilm() + " at " + provoliReservation.getProvoliCinema());
                }else if (!provoliReservation.getProvoliIsAvailable()) {
                    System.out.println("The film is full, can't make Reservation");
                }else {
                    System.out.println("Couldn't make the Reservation, try again.");
                }
            }else if(provoliFound && !provoliReservation.getProvoliIsAvailable()){
                System.out.println("Can't make Reservation, the film " + provoliReservation.getProvoliFilm() + " is full.");
            }
            else if(!provoliFound) {
                System.out.println("Couldn't find a film with ID: " + filmIdInput);
            }
        }
    }

    public void viewReservations(Users user) {
        if (reservationsUser.isEmpty()){
            System.out.println("You don't have any Reservations.");
        }else{
            for (Reservations reservation : reservationsUser) {
                if (reservation.getUser().getUsername().equals(user.getUsername()) ) /*&& reservation.getUser().getName().equals(user.getName())) */{
                    System.out.println("| Reservation at " + reservation.getProvoli().getProvoliStartDate() + " for " + reservation.getProvoli().getProvoliFilm() + " in "
                            + reservation.getProvoli().getProvoliCinema() + " for " + reservation.getSeats() + " people |");
                }
            }
        }
    }

}




